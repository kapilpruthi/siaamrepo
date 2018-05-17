package com.siaam.auth.exception;

/**
 * BusinessRuleException.
 * @author Kapil Pruthi
 */
public class BusinessRuleException extends AbstractBusinessException {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public BusinessRuleException() {
		super(CauseCode.BUSINESS_RULE_ERROR);
	}

	/**
	 * Constructor.
	 * @param message String
	 */
	public BusinessRuleException(String message) {
		super(CauseCode.BUSINESS_RULE_ERROR, message);
	}

	/**
	 * Constructor.
	 * @param cause Throwable
	 */
	public BusinessRuleException(Throwable cause) {
		super(CauseCode.BUSINESS_RULE_ERROR, cause);
	}
}