package com.wolfsoft.kafkaconnector.producer;

public class EmailConfig {
	private String emailTo;
	private String message;
	
	public EmailConfig() {
	}

	
	public EmailConfig(String emailTo, String message) {
		super();
		this.emailTo = emailTo;
		this.message = message;
	}

	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}


	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "\nEmail To: "+this.emailTo+"\nMessage: "+this.message;
	}
}
