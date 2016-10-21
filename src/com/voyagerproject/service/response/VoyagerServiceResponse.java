package com.voyagerproject.service.response;

import java.util.ArrayList;

/**
 * Default class to be used as response on API calls
 * The default status response is 200;
 * @author eandre
 *
 */
public class VoyagerServiceResponse {
	
	private int status = 200;
	private ArrayList<String> errors;
	private Object body;
	
	public VoyagerServiceResponse() {};
	
	public VoyagerServiceResponse(int status, ArrayList<String> errorList, Object body) {
		this.status = status;
		this.errors = errorList;
		this.body = body;
	}
	
	/**
	 * Constructor to create a new response with a single error
	 * @param status response status
	 * @param error response error
	 * @param body response body
	 */
	public VoyagerServiceResponse(int status, String error) {
		this.status = status;
		
		// Create the error list with the single error
		ArrayList<String> errorList = new ArrayList<String>();
		errorList.add(error);
		this.errors = errorList;
	}
	
	/**
	 * Constructor to create a new success response 
	 * @param body response body
	 */
	public VoyagerServiceResponse(Object body) {
		this.body = body;
	}
	
	/**
	 * Gets the status of the response
	 * @return status of the response
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the response
	 * @param status of the response
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Gets the error list of the response
	 * @return error list of the response
	 */
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	/**
	 * Sets the error list of the response
	 * @param error list of the response
	 */
	public void setErrors(ArrayList<String> error) {
		this.errors = error;
	}
	
	/**
	 * Gets the body of the response
	 * @return body of the response
	 */
	public Object getBody() {
		return body;
	}
	
	/**
	 * Sets the body of the response
	 * @param body of the response
	 */
	public void setBody(Object body) {
		this.body = body;
	}

}
