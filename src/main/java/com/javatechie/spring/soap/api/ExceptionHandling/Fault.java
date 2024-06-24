package com.javatechie.spring.soap.api.ExceptionHandling;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "This is and internal error")
public class Fault {

	@XmlElement
	private String faultmsg1;
	@XmlElement
	private String faultmsg2;
	@XmlElement
	private Opstatus opstatus;

	public Fault() {
		super();
	}

	public Fault(String faultmsg1, String faultmsg2, Opstatus opstatus) {
		super();
		this.faultmsg1 = faultmsg1;
		this.faultmsg2 = faultmsg2;
		this.opstatus = opstatus;
	}

	public String getFaultmsg2() {
		return faultmsg2;
	}

	public void setFaultmsg2(String faultmsg2) {
		this.faultmsg2 = faultmsg2;
	}

	public String getFaultmsg1() {
		return faultmsg1;
	}

	public void setFaultmsg1(String faultmsg1) {
		this.faultmsg1 = faultmsg1;
	}

	public Opstatus getOpstatus() {
		return opstatus;
	}

	public void setOpstatus(Opstatus opstatus) {
		this.opstatus = opstatus;
	}

}
