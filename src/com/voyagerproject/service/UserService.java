package com.voyagerproject.service;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.voyagerproject.domain.controller.UserController;
import com.voyagerproject.domain.entities.DomainUser;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the User class
 * @author eandre
 *
 */
@Path("/User")
public class UserService {
	
	private static final Log log = LogFactory.getLog(UserService.class);
	
	// Controllers
	UserController userController = new UserController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the users
	 * 
	 * @return VoyagerServiceResponse users list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/user")
	public VoyagerServiceResponse getUsers() throws IOException {
		Collection<DomainUser> users;
		
		// Log into de DB
		try {
			users = userController.getUsers();
			return new VoyagerServiceResponse(users);
		} catch (Exception ex) {			
			log.error("Failed to get users", ex);
			String error = "Failed to get users. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * 
	 * Service that creates a user from a user name
	 * 
	 * @param userName: UserName for the new user
	 * @param name: Name of the new user
	 * @param email: email of the new user
	 * @param password: password of the new user
	 * @param userTypeId: User Type of the new user
	 * @return VoyagerServiceResponse with the created user
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/user")
	public VoyagerServiceResponse createUser(DomainUser user) throws IOException {
		
		try {
			user = userController.createUser(user.getUserName(), user.getName(), user.getEmail(), 
					user.getPassword(), user.getUserType());
			return new VoyagerServiceResponse(user);
		} catch (Exception ex) {			
			log.error("Failed to create user", ex);
			String error = "Failed to create user. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a user
	 * 
	 * @param user: updated user
	 * @return VoyagerServiceResponse with the updated user
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/user")
	public VoyagerServiceResponse updateUser(DomainUser user) throws IOException {
		
		try {
			userController.updateUser(user);
			return new VoyagerServiceResponse(user);
		} catch (Exception ex) {
			log.error("Failed to update user", ex);
			String error = "Failed to update user. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a user
	 * @param userId: id of the user to delete
	 * @return VoyagerServiceResponse
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteUser")
	public VoyagerServiceResponse deleteUser(Integer userId) throws IOException {
		
		try {
			userController.deleteUser(userId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete user", ex);
			String error = "Failed to delete user. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
