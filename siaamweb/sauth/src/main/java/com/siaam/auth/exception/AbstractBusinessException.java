package com.siaam.auth.exception;

/**
 * Abstract Business Exception thrown by Service Component
 * and caught in defined ControllerAdvice ExceptionController.
 * @author Kapil Pruthi
 */
public class AbstractBusinessException extends Exception {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception Cause- Enum.
	 */
	public enum CauseCode {
		/**
		 * DATA_LAYER_ERROR.
		 */
		DATA_LAYER_ERROR,
		/**
		 * VALIDATION_ERROR.
		 */
		VALIDATION_ERROR,
		/**
		 * BUSINESS_RULE_ERROR.
		 */
		BUSINESS_RULE_ERROR
	};

	/**
	 * Cause.
	 */
	private CauseCode reason;

	/**
	 * Constructor.
	 * @param inReason CauseCode
	 */
	protected AbstractBusinessException(CauseCode inReason) {
		super();
		this.reason = inReason;
	}

	/**
	 * Constructor.
	 * @param inReason CauseCode
	 * @param inMessage String
	 */
	protected AbstractBusinessException(CauseCode inReason, String inMessage) {
		super(inMessage);
		this.reason = inReason;
	}

	/**
	 * Constructor.
	 * @param inReason CauseCode
	 * @param inCause Throwable
	 */
	protected AbstractBusinessException(CauseCode inReason, Throwable inCause) {
		super(inCause);
		this.reason = inReason;
	}

	/**
	 * Constructor.
	 * get exception cause.
	 * @return CauseCode
	 */
	public CauseCode getReason() {
		return reason;
	}

}