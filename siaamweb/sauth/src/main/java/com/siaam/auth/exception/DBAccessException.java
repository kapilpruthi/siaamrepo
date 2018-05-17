package com.siaam.auth.exception;

/**
 * DBAccessException.
 * @author Kapil Pruthi
 */
public class DBAccessException extends AbstractBusinessException {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public DBAccessException() {
		super(CauseCode.DATA_LAYER_ERROR);
	}

	/**
	 * Constructor.
	 * @param message String
	 */
	public DBAccessException(String message) {
		super(CauseCode.DATA_LAYER_ERROR, message);
	}

	/**
	 * Constructor.
	 * @param cause Throwable
	 */
	public DBAccessException(Throwable cause) {
		super(CauseCode.DATA_LAYER_ERROR, cause);
	}
}