package com.siaam.auth.event;

import java.io.Serializable;

/**
 * Generic Response Event. Can be used for Success or Failure response.
 * More specific success or failure response events can extend this Event class.
 * @author Kapil Pruthi
 */
public class ResponseEvent implements Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * response code.
	 */
	private int code;
	/**
	 * text description.
	 */
	private String description;
	/**
	 * message text.
	 */
	private String message;

	/**
	 * return response code.
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * set response code.
	 * @param inCode int
	 */
	public void setCode(int inCode) {
		this.code = inCode;
	}

	/**
	 * get description.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set description.
	 * @param inDescription String
	 */
	public void setDescription(String inDescription) {
		this.description = inDescription;
	}

	/**
	 * get message text.
	 * @return message String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * set message text.
	 * @param inMessage String
	 */
	public void setMessage(String inMessage) {
		this.message = inMessage;
	}

	/**
	 * Constructor.
	 * @param inErrorType Type
	 * @param inDescription String
	 * @return ResponseEvent
	 */
	public static ResponseEvent build(Type inErrorType, String inDescription) {
		ResponseEvent response = new ResponseEvent();
		response.setCode(inErrorType.getCode());
		response.setDescription(inDescription);
		response.setMessage(inErrorType.getMessage());
		return response;
	}

	/**
	 * Enum Type.
	 * @author Kapil Pruthi
	 */
	public enum Type {
		/**
		 * BAD_REQUEST_ERROR.
		 */
		BAD_REQUEST_ERROR(4002, "Bad request error"),
		/**
		 * INTERNAL_SERVER_ERROR.
		 */
		INTERNAL_SERVER_ERROR(5001, "Unexpected server error"),
		/**
		 * VALIDATION_ERROR.
		 */
		VALIDATION_ERROR(4001, "Found validation issues"),
		/**
		 * OK.
		 */
		OK(2001, "Success"),
		/**
		 * CREATED.
		 */
		CREATED(2011, "Created"),
		/**
		 * ACCEPTED.
		 */
		ACCEPTED(2021, "Accepted");

		/**
		 * code.
		 */
		private int code;
		/**
		 * message text.
		 */
		private String message;

		/**
		 * Constructor.
		 * @param inCode int
		 * @param inMessage String
		 */
		Type(int inCode, String inMessage) {
			this.code = inCode;
			this.message = inMessage;
		}

		/**
		 * get code.
		 * @return code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * get message.
		 * @return message String
		 */
		public String getMessage() {
			return message;
		}
	}
}
