package com.voyagerproject.service;

import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.voyagerproject.domain.controller.UserController;
import com.voyagerproject.domain.entities.DomainUser;
import com.voyagerproject.json.entities.JsonAuth;
import com.voyagerproject.service.response.VoyagerServiceResponse;

 
/**
 * Class that holds the services for login, logout and register
 * @author EAndre
 *
 */
@Path("/Login")
public class LoginService {
	
	private static final Log log = LogFactory.getLog(LoginService.class);
	
	// Controllers
	UserController userController = new UserController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;
	
	/**
	 * Login service
	 * 
	 * @param authBean json object with userName and password properties
	 * @return User 
	 * @throws IOException 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll
	@Path("/login")
	public VoyagerServiceResponse login(JsonAuth authBean) throws IOException {
		DomainUser user = new DomainUser();
		
		// Log into the DB
		try {
			user = userController.logIn(authBean.getUserName(), authBean.getPassword());
			return new VoyagerServiceResponse(user.getToken());
		} catch (Exception ex) {
			log.error("Failed to authenticate user", ex);
			return new VoyagerServiceResponse(401, ex.getMessage());
		}
	}
	
	/**
	 * Logout service
	 * 
	 * @param the user token
	 * @return 
	 * @return String 
	 * @throws IOException 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll
	@Path("/logout")
	public VoyagerServiceResponse logout(String token) throws IOException {
		
		// Log into de DB
		try {
			userController.logOut(token);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to log out user", ex);
            return new VoyagerServiceResponse(401, ex.getMessage());
		}
	}
}
