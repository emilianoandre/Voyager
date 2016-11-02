package com.voyagerproject.json.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to handle calls with user information
 * @author eandre
 *
 */
public class JsonUser {

	private String userName;
	private String name;
	private String email;
	private String password;
	private int userTypeId;
	

	@JsonCreator
	public JsonUser(@JsonProperty("userName")String userName, @JsonProperty("name")String name,
			@JsonProperty("password")String password, @JsonProperty("email")String email,
			@JsonProperty("userTypeId")int userTypeId) {
		this.setUserName(userName);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(email);
		this.setUserTypeId(userTypeId);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userTypeId
	 */
	public int getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
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
