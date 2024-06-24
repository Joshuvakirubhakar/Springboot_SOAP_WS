package com.javatechie.spring.soap.api.ExceptionHandling;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SuppressWarnings("serial")
//@SoapFault(faultCode = FaultCode.SERVER,faultStringOrReason = "This is and Internal or External Error")
public class CustomException extends Exception {

	private Fault fault;

	public CustomException(String message, Fault fault) {
		super(message);
		this.fault = fault;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

}
