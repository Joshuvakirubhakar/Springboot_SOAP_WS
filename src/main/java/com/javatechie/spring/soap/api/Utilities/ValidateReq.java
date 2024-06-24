package com.javatechie.spring.soap.api.Utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.javatechie.spring.soap.api.ExceptionHandling.CustomException;
import com.javatechie.spring.soap.api.ExceptionHandling.Fault;
import com.javatechie.spring.soap.api.ExceptionHandling.Opstatus;
import com.javatechie.spring.soap.api.loaneligibility.CustomerRequest;

public class ValidateReq {

	public static void validaterequest(CustomerRequest request) throws CustomException {
		if(request.getCustomerName() ==null || request.getCustomerName().isEmpty()) {
			Opstatus opstatus = new Opstatus("FAILED", getCurrentESTTime());
			Fault fault = new Fault("Exception!!!", "NULL CANNOT BE A NAME", opstatus);
			CustomException customException = new CustomException("INTERNAL SERVER ERROR", fault);
			throw customException;
		}
		// TODO Auto-generated method stub
		
	}
	private static XMLGregorianCalendar getCurrentESTTime() {
        try {
            // Get current time in EST time zone
            ZoneId estZoneId = ZoneId.of("America/New_York");
            ZonedDateTime estZonedDateTime = ZonedDateTime.now(estZoneId);
            
            // Convert to GregorianCalendar
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(estZonedDateTime);
            
            // Create XMLGregorianCalendar
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            
            return xmlGregorianCalendar;
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
