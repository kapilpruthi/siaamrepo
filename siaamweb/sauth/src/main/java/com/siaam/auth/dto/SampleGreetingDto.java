/**
 * 
 */
package com.siaam.auth.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.siaam.auth.utils.SiaamWebConstants;

/**
 * A dummy resource for demo purposes.
 * 
 * @author pruthik
 */
public class SampleGreetingDto {
	/**
	 * id.
	 */
	private final long id;
	/**
	 * content.
	 */
	@NotEmpty(message = "Please enter your Name.")
	@Pattern(regexp = SiaamWebConstants.REGEX_FIRST_NAME, message = "Name format is invalid.")
	private final String content;

	/**
	 * Constructor.
	 * 
	 * @param paramId
	 *            Id
	 * @param paramContent
	 *            Content
	 */
	public SampleGreetingDto(final long paramId, final String paramContent) {
		this.id = paramId;
		this.content = paramContent;
	}

	/**
	 * @return id long
	 */
	public final long getId() {
		return id;
	}

	/**
	 * @return content string
	 */
	public final String getContent() {
		return content;
	}
}
