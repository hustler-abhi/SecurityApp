package com.masai.exception;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MyErrordetails {
	
	public MyErrordetails(LocalDate time, String msg, String details) {
		super();
		this.time = time;
		this.msg = msg;
		this.details = details;
	}
	private LocalDate time;
	private String msg;
	private String details;
	

}
