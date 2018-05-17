/**
 * 
 */
package com.siaam.auth.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siaam.auth.dto.SampleGreetingDto;

/**
 * This class demo an access to OAuth Protected resource. 
 * 
 * @author Kapil Pruthi
 */
@RestController
public class SampleOAuthProtectedResourceController {
	/**
	 * sample template.
	 */
	private static final String TEMPLATE = "Hello, %s!";
	/**
	 * counter.
	 */
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleOAuthProtectedResourceController.class);

	/**
	 * @param name
	 *            Name
	 * @return SampleResource
	 */
	@RequestMapping(value = "/protected/greeting", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SampleGreetingDto greeting(@RequestParam(value = "name", defaultValue = "World") final String name) {
		LOGGER.info("Welcome to oAuth protected resource for guest {}.", name);
		return new SampleGreetingDto(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}

}
