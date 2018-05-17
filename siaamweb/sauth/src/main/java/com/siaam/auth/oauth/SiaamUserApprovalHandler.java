package com.siaam.auth.oauth;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;

/**
 * @author Dave Syer
 */
public class SiaamUserApprovalHandler extends ApprovalStoreUserApprovalHandler {

	/**
	 * useApprovalStore.
	 */
	private boolean useApprovalStore = true;

	/**
	 * ClientDetailsService - pulls the client configs.
	 */
	private ClientDetailsService clientDetailsService;

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamUserApprovalHandler.class);
	
	/**
	 * Service to load client details (optional) for auto approval checks.
	 * @param inClientDetailsService a client details service
	 */
	public void setClientDetailsService(ClientDetailsService inClientDetailsService) {
		this.clientDetailsService = inClientDetailsService;
		super.setClientDetailsService(inClientDetailsService);
	}

	/**
	 * @param inUseApprovalStore the useTokenServices to set
	 */
	public void setUseApprovalStore(boolean inUseApprovalStore) {
		this.useApprovalStore = inUseApprovalStore;
	}

	/**
	 * Allows automatic approval for a white list of clients in the implicit grant case.
	 * @param authorizationRequest The authorization request.
	 * @param userAuthentication the current user authentication
	 * @return An updated request if it has already been approved by the current user.
	 */
	@Override
	public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest,
			Authentication userAuthentication) {

		boolean approved = false;
		// If we are allowed to check existing approvals this will short circuit
		// the decision
		if (useApprovalStore) {
			authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
			approved = authorizationRequest.isApproved();
		} else {
			if (clientDetailsService != null) {
				Collection<String> requestedScopes = authorizationRequest.getScope();
				try {
					ClientDetails client = clientDetailsService
							.loadClientByClientId(authorizationRequest.getClientId());
					for (String scope : requestedScopes) {
						if (client.isAutoApprove(scope) || client.isAutoApprove("all")) {
							approved = true;
							break;
						}
					}
				} catch (ClientRegistrationException ex) {
					LOGGER.error("Error in client registeration " + ex);
				}
			}
		}
		authorizationRequest.setApproved(approved);

		return authorizationRequest;

	}

}