package com.siaam.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siaam.auth.dto.DeleteUserDto;
import com.siaam.auth.dto.EditEmailDto;
import com.siaam.auth.dto.EditIdDto;
import com.siaam.auth.dto.EditLegalNameDto;
import com.siaam.auth.dto.EditPwdDto;
import com.siaam.auth.dto.RegisterUserDto;
import com.siaam.auth.event.ResponseEvent;
import com.siaam.auth.exception.AbstractBusinessException;
import com.siaam.auth.exception.ValidationException;
import com.siaam.auth.service.SiaamUserOperationsSO;
import com.siaam.auth.service.SiaamUserSO;
import com.siaam.auth.utils.SiaamWebConstants;

/**
 * Rest Service for User Maintenance Operations.
 * /ws/user/edit URL patterns will be protected and allowed only for ROLE_USER
 * /ws/user/admin URL patterns will also be protected and allowed only for ROLE_ADMIN
 * /ws/ws/user/add is open for all to register.
 * @author Kapil Pruthi
 */
@RestController
@RequestMapping("/ws/user")
public class SiaamMaintController extends AuthenticatedBaseController {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SiaamMaintController.class);
	
	/**
	 * Service interface for {@link SiaamUserSO}.
	 */
	@Autowired
	private SiaamUserSO siaamUserSo;

	/**
	 * Service interface for {@link SiaamUserOperationsSO}.
	 */
	@Autowired
	private SiaamUserOperationsSO siaamUserOperationsSO;

	/**
	 * Register a new User to the system.
	 * @param registerUserDto
	 *            {@link RegisterUserDto}
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> addUser(@Validated @RequestBody RegisterUserDto registerUserDto)
			throws AbstractBusinessException {
		LOGGER.info("Request to add user " + registerUserDto.getUsername());
		siaamUserSo.createUser(registerUserDto, SiaamWebConstants.APP_SIAAM, SiaamWebConstants.ROLE_USER);
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.CREATED, "User Created");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.CREATED);
	}

	/**
	 * Edit onlineId of the user.
	 * @param editIdDto {@link EditIdDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/edit/id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> editId(@Validated @RequestBody EditIdDto editIdDto)
			throws AbstractBusinessException {
		LOGGER.info("Request to change onlineId for" + editIdDto.getCurrOnlineId());
		if (!isMaintForSameUser(editIdDto.getCurrOnlineId())) {
			throw new ValidationException("Invalid current Username supplied.");
		}
		siaamUserOperationsSO.changeOnlineId(getGuidLoggedIn(), editIdDto.getNewOnlineId());
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Online Id updated");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * Edit pwd of the user.
	 * @param editPwdDto {@link EditPwdDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/edit/pwd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> editPwd(@Validated @RequestBody EditPwdDto editPwdDto)
			throws AbstractBusinessException {
		String sessionGuid = getGuidLoggedIn();
		LOGGER.info("Request to change pwd for " + sessionGuid);
		siaamUserOperationsSO.changePwd(sessionGuid, editPwdDto.getCurrPwd(), editPwdDto.getNewPwd());
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Pwd is updated");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * Edit Name of the user.
	 * @param editNameDto {@link EditLegalNameDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/edit/name", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> editLegalName(@Validated @RequestBody EditLegalNameDto editNameDto)
			throws AbstractBusinessException {
		LOGGER.info("Request to change name for " + editNameDto.getOnlineId());
		if (!isMaintForSameUser(editNameDto.getOnlineId())) {
			throw new ValidationException("User trying to edit incorrect Id");
		}
		siaamUserOperationsSO.updateLegalName(getGuidLoggedIn(), editNameDto.getFirstName(),
				editNameDto.getLastName());
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Name is updated");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * Edit Email of the user.
	 * @param editEmailDto {@link EditEmailDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/edit/email", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> editEmail(@Validated @RequestBody EditEmailDto editEmailDto)
			throws AbstractBusinessException {
		String sessionGuid = getGuidLoggedIn();
		LOGGER.info("Request to change email for " + sessionGuid);
		if (siaamUserOperationsSO.validatePwd(null, sessionGuid, editEmailDto.getCurrPwd())) {
			siaamUserOperationsSO.updateEmail(sessionGuid, editEmailDto.getEmail());
		} else {
			throw new ValidationException("Current pwd suppiled doesnt match with current pwd in Db");
		}
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "Email is updated");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}
	/**
	 * Deleting the user, can be done by Admin only.
	 * @param deleteUserDto {@link DeleteUserDto} DTO obj.
	 * @return {@link ResponseEntity<ResponseEvent>}
	 * @throws AbstractBusinessException Exception
	 */
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseEvent> deleteUser(@Validated @RequestBody DeleteUserDto deleteUserDto)
			throws AbstractBusinessException {
		LOGGER.info("Request to delete user " + deleteUserDto.getUniqueId());
		siaamUserSo.deleteUser(deleteUserDto.getUniqueId(), deleteUserDto.getIdType());
		ResponseEvent successEvent = ResponseEvent.build(ResponseEvent.Type.OK, "User Deleted");
		return new ResponseEntity<ResponseEvent>(successEvent, HttpStatus.OK);
	}

	/**
	 * API to determine if logged in user is making update to its own credentials. 
	 * @param onlineId OnlineId of the user for which maint request is received.
	 * @return true if onlineId matches with id of user currently logged in.
	 * @throws AbstractBusinessException 
	 */
	private boolean isMaintForSameUser(String onlineId) throws AbstractBusinessException {
		return (onlineId.equalsIgnoreCase(getOnlineIdLoggedIn())); //get logged in username
	}
}
