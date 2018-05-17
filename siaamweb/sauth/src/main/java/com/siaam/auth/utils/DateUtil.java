package com.siaam.auth.utils;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Date Utility operations.
 * @author kapil pruthi
 */
public final class DateUtil {

	/**
	 * private constructor.
	 */
	private DateUtil() {
	}
	/**
	 * Convert java.util.date Obj to XMLGregorianCalendar Obj.
	 * @param date Date
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertDateToXMLGC(final Date date) {

		try {
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(date);
			XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			return xgcal;
		} catch (DatatypeConfigurationException ex) {
			return null;
		}
	}

}
