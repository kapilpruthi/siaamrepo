package com.siaam.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siaam.auth.dto.ForgotIdDto;
import com.siaam.auth.dto.ResetPwdDto;
import com.siaam.auth.dto.ResetPwdRequestDto;
import com.siaam.auth.event.ResponseEvent;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.exception.ValidationException;
import com.siaam.auth.service.SiaamUserOperationsSO;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.CommonUtils;
import com.siaam.auth.utils.SiaamWebConstants;
import com.siaam.auth.utils.WebUtils;
import com.siaam.sdm.auth.User;

/**
 * Rest Service for sending email for credentials reset or lookup.
 * @author Kapil Pruthi
 */
@RestController
@RequestMapping("/ws/reset")
public class ResetController {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResetController.class);
	
	/**
	 * Inject bean {@link SiaamUserOperationsSO}
	 */
	@Autowired
	private SiaamUserSO siaamUserSO;
	
	/**
	 * Inject bean {@link SiaamUserOperationsSO}
	 */
	@Autowired
	private SiaamUserOperationsSO siaamUserOperationsSO;
	
	/**
	 * MailSender API
	 */
	@Autowired
    private MailSender mailSender;
	/**
	 * Validate if online id and email combo supplied is correct, 
	 * then Sends the pwd reset URL to supplied email which user need to click to reset pwd.
	 * @param request {@link HttpServletRequest}
	 * @param resetPwdRequestDto {@link ResetPwdRequestDto}
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/pwd/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> resetPwdRequest(final HttpServletRequest request,
			@Validated @RequestBody ResetPwdRequestDto resetPwdRequestDto) throws AbstractBusinessException {
		LOGGER.info("Request to send reset pwd link in email for " + resetPwdRequestDto.getOnlineId());
		try {
			String resetToken = siaamUserOperationsSO.createOTTForResetPwd(resetPwdRequestDto.getOnlineId(),
					resetPwdRequestDto.getEmail());
			String hyperlink = createHyperLinkForReset(request, resetToken);
			CommonUtils.sendMail(mailSender, resetPwdRequestDto.getEmail(), "Your request to reset password",
					"Click this link to reset your pwd " + hyperlink);
		} catch (AbstractBusinessException aEx) {
			LOGGER.error("user not found, but dont display on screen ");
		}
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Email is sent if user found");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * Reset pwd for the user if token is valid and onlineId supplied matches 
	 * with user token was issued for.
	 * @param request {@link HttpServletRequest}
	 * @param resetPwdDto {@link ResetPwdDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/pwd/submit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> resetPwd(final HttpServletRequest request, @Validated @RequestBody ResetPwdDto resetPwdDto)
			throws AbstractBusinessException {
		LOGGER.info("Request to reset pwd for " + resetPwdDto.getOnlineId());
		String token = (String) request.getParameter("token");
		if (token == null) {
			throw new ValidationException("Invalid Request - Missing token");
		}
		// validate OTT
		String guid = siaamUserOperationsSO.validateOTTForResetPwd(resetPwdDto.getOnlineId(), token);
		
		// reset new pwd
		siaamUserOperationsSO.resetPwd(guid, resetPwdDto.getNewPwd());
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Pwd reset complete");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}
	
	/**
	 * Id lookup based on registered email.
	 * @param request {@link HttpServletRequest}
	 * @param forgotIdDto {@link ForgotIdDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/id/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> resetIdRequest(final HttpServletRequest request,
			@Validated @RequestBody ForgotIdDto forgotIdDto) throws AbstractBusinessException {
		String email = forgotIdDto.getEmail();
		LOGGER.info("Request to lookup online id for " + email);
		try {
			User user = siaamUserSO.readUser(email, SiaamWebConstants.EMAIL_IND);
			String onlineId = user.getOnlineId();
			CommonUtils.sendMail(mailSender, email, "Id Lookup",
					"As per your request, your username is " + onlineId);
		} catch (AbstractBusinessException aEx) {
			LOGGER.error("user not found, but dont display on screen ");
		}
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Email is sent if user found");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * Create a hyperlink for reset pwd request.
	 * @param request {@link HttpServletRequest}
	 * @param token Token String
	 * @return hyperlink
	 */
	private String createHyperLinkForReset(final HttpServletRequest request, final String token) {
		return WebUtils.getCurrentURL(request) + "/resources/index.html#/resetPwdConfirm?token=" + token;
	}
}
