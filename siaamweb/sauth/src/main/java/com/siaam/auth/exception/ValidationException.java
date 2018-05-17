package com.siaam.auth.exception;

/**
 * ValidationException.
 * @author Kapil Pruthi
 */
public class ValidationException extends AbstractBusinessException {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 */
	public ValidationException() {
		super(CauseCode.VALIDATION_ERROR);
	}

	/**
	 * Constructor.
	 * @param message String
	 */
	public ValidationException(String message) {
		super(CauseCode.VALIDATION_ERROR, message);
	}

	/**
	 * Constructor.
	 * @param cause Throwable
	 */
	public ValidationException(Throwable cause) {
		super(CauseCode.VALIDATION_ERROR, cause);
	}
}