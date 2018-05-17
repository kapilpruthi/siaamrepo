/**
 * 
 */
package com.siaam.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;

/**
 * Base class for Authenticated Controllers.
 * @author kapil pruthi
 */
public class AuthenticatedBaseController {

	/**
	 * Service interface for {@link SiaamUserSO}.
	 */
	@Autowired
	private SiaamUserSO siaamUserSo;
	
	/**
	 * API to return onlineId of user currently logged in
	 * @return onlineId of the user.
	 * @throws AbstractBusinessException 
	 */
	protected String getOnlineIdLoggedIn() throws AbstractBusinessException {
		String guid = getGuidLoggedIn();
		User user = siaamUserSo.readUser(guid, SiaamWebConstants.GUID_IND);
		return user.getOnlineId(); // get logged in username
	}
	
	/**
	 * API to return onlineId of user currently logged in
	 * @return onlineId of the user.
	 */
	protected String getGuidLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); // get logged in username
	}
}
