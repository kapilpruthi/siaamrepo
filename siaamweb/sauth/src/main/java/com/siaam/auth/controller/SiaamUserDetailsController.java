package com.siaam.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siaam.auth.dto.UserDetailsDto;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;

/**
 * Rest Service to fetch user details.
 * @author Kapil Pruthi
 */
@RestController
@RequestMapping("/ws/read")
public class SiaamUserDetailsController extends AuthenticatedBaseController {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamUserDetailsController.class);
	
	/**
	 * Service interface for {@link SiaamUserSO}.
	 */
	@Autowired
	private SiaamUserSO siaamUserSo;

	/**
	 * Register a new User to the system.
	 * @return {@link ResponseEntity<UserDetailsDto>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/userdetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetailsDto> getUserDetails()
			throws AbstractBusinessException {
		LOGGER.info("Request to read user " + getOnlineIdLoggedIn());
		//TODO get it from session if its available.
		User user = siaamUserSo.readUser(getOnlineIdLoggedIn(), SiaamWebConstants.ONLINEID_IND);
		
		UserDetailsDto ud = new UserDetailsDto();
		ud.setUserName(user.getOnlineId());
		ud.setFirstName(user.getFirstName());
		ud.setLastName(user.getLastName());
		ud.setUserId(user.getGuid());
		ud.setUserIdType(SiaamWebConstants.GUID_IND);
		ud.setEmail(user.getEmail());
		ud.setLastLoginDate(user.getLastLoginTs().toGregorianCalendar().getTime().toString());
		ud.setPwdExpiryDate(user.getPasswordExpDate().toGregorianCalendar().getTime().toString());
		return new ResponseEntity<UserDetailsDto>(ud, HttpStatus.OK);
	}
}
