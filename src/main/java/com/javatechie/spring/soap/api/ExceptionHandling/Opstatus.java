package com.javatechie.spring.soap.api.ExceptionHandling;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
public class Opstatus {
	private String status;
	private XMLGregorianCalendar current_date;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public XMLGregorianCalendar getDate() {
		return current_date;
	}

	public void setDate(XMLGregorianCalendar date) {
		this.current_date = date;
	}

	public Opstatus(String status, XMLGregorianCalendar date) {
		super();
		this.status = status;
		this.current_date = date;
	}

	public Opstatus() {
		// TODO Auto-generated constructor stub
	}

}
