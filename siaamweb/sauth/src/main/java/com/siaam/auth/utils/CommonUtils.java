package com.siaam.auth.utils;

import java.util.UUID;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Common App Utils placed here.
 * @author Kapil Pruthi
 *
 */
public final class CommonUtils {

	/**
	 * private constructor.
	 */
	private CommonUtils() {
	}
	/**
	 * Generate a unique GUID.
	 * @return guid
	 */
	public static String generateGUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Generate BCrypt encoded of clear text.
	 * @param clearTextPwd clearTextPwd
	 * @return hash based on Bcrypt
	 */
	public static String generateHash(final String clearTextPwd) {
		return new BCryptPasswordEncoder().encode(clearTextPwd);
	}
	
	/**
     * This method will send compose and send the message.
     * @param mailSender inject {@link MailSender}
     * @param to Recipient
     * @param subject Subject line
     * @param body Message Body
     */
    public static void sendMail(MailSender mailSender, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
