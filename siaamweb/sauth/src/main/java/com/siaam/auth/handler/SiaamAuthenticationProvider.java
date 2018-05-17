package com.siaam.auth.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.service.SiaamUserOperationsSO;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.CommonValidationUtils;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.sdm.auth.User;

/**
 * A custom authentication provider that allows access if the user details exist
 * in the database and if the username and password are not the same. Otherwise,
 * throw a {@link BadCredentialsException}
 * @author kapil pruthi
 *
 */
public class SiaamAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamAuthenticationProvider.class);
	/**
	 * Constant status value for Locked Pwd.
	 */
	private static final String PWD_LOCKED = "L";
	
	/**
	 * Service interface for user maintenance.
	 */
	@Autowired
	private SiaamUserSO siaamUserSo;
	
	/**
	 * Service interface for user credential maint.
	 */
	@Autowired
	private SiaamUserOperationsSO siaamUserOperationsSO;
	

	@Override
	public Authentication authenticate(final Authentication auth) throws AuthenticationException {
		// Validate Input
		if (!CommonValidationUtils.isValidUserName(auth.getName())
				|| !CommonValidationUtils.isValidPassword((String) auth.getCredentials())) {
			LOGGER.debug("Invalid input based on regex validation! Username provided " + auth.getName()
					+ " And Pwd provided " + (String) auth.getCredentials());
			throw new BadCredentialsException("Invalid input based on regex validation!");
		}
		
		// Validate Id and fetch user details from Db.
		LOGGER.debug("Performing custom authentication");
		// Init a database user object
		User user = null;
		try {
			// Retrieve user details from database
			user = siaamUserSo.readUser(auth.getName(), SiaamWebConstants.ONLINEID_IND);
		} catch (Exception ex) {
			LOGGER.error("Exception occured:- " + ex.getMessage());
			throw new BadCredentialsException("User does not exists!");
		}
		
		// if password status is locked, return error
		if (PWD_LOCKED.equalsIgnoreCase(user.getPasswordStatus())) {
			throw new BadCredentialsException("Password Locked!");
		}
		
		//validate pwd
		try {
			if (!siaamUserOperationsSO.validatePwd(user, null, (String) auth.getCredentials())) {
				throw new BadCredentialsException("Wrong password!");
			}
		} catch (AbstractBusinessException aEx) {
			LOGGER.error("unable to increment pwd lock ctr for user {} ", user.getOnlineId());
			throw new BadCredentialsException("Wrong password!");
		}
		
		// Post Validation
		// Username and password must NOT be the same to authenticate
		if (auth.getName().equals(auth.getCredentials())) {
			LOGGER.debug("Entered username and password are the same!");
			throw new BadCredentialsException("Entered username and password are the same!");
		} else {
			// 5. Handle success
			//update last login ts
			siaamUserOperationsSO.updateLastLoginTs(user.getGuid());
			// Set User in request scope so token can be added post success
			// based on user fields.
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			request.setAttribute("user", user);
	
			LOGGER.debug("User details are good and ready to go");
			return new UsernamePasswordAuthenticationToken(user.getGuid(), auth.getCredentials(),
					getAuthorities(user.getRole()));
		}
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin.
	 * @param role value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(final String role) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		LOGGER.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority(SiaamWebConstants.ROLE_USER));
		// Check if this user has admin access.
		// We interpret Integer(1) as an admin user
		if (SiaamWebConstants.ROLE_ADMIN.equalsIgnoreCase(role)) {
			// User has admin access
			LOGGER.info("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority(SiaamWebConstants.ROLE_ADMIN));
		}

		// Return list of granted authorities
		return authList;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
