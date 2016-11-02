package com.voyagerproject.json.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to handle auth calls from the app 
 * @author eandre
 *
 */
public class JsonAuth {

	private String userName;
	private String password;
	@JsonCreator
	public JsonAuth(@JsonProperty("userName")String userName, @JsonProperty("password")String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	/**
	 * getUsername
	 * @return string
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * setUsername
	 * @param username string
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * getPassword
	 * @return string
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * setPassword
	 * @param password string
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
