package com.siaam.auth.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 * @author kapil pruthi
 *
 */
@SuppressWarnings("deprecation")
public class SiaamSSOAuthManager implements AuthenticationManager {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamSSOAuthManager.class);

	@Override
	public Authentication authenticate(final Authentication auth) throws AuthenticationException {
		LOGGER.debug("Performing custom authentication");

		if (auth.getName() == null || auth.getCredentials() == null) {
			throw new BadCredentialsException("User does not exists!");
		}

		LOGGER.debug("User details are good and ready to go");
		return new PreAuthenticatedAuthenticationToken(auth.getName(), auth.getCredentials(),
					getAuthorities("ROLE_USER"));
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * @param role value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(final String role) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
			// All users are granted with ROLE_USER access
			// Therefore this user gets a ROLE_USER by default
			LOGGER.debug("Grant ROLE_USER to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_USER"));
			// Check if this user has admin access.
			// We interpret Integer(1) as an admin user
			if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
				// User has admin access
				LOGGER.debug("Grant ROLE_ADMIN to this user");
				authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			}

			// Return list of granted authorities
			return authList;
	  }

}
