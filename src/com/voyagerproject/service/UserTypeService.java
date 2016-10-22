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

import com.voyagerproject.domain.controller.UserTypeController;
import com.voyagerproject.domain.entities.DomainUserType;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the types (UserType, BugSystemType, etc)
 * @author eandre
 *
 */
@Path("/UserType")
public class UserTypeService {
	
	private static final Log log = LogFactory.getLog(UserTypeService.class);
	
	// Controllers
	UserTypeController userTypeController = new UserTypeController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the user types
	 * 
	 * @return userTypes list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/userType")
	public VoyagerServiceResponse getUserTypes() throws IOException {
		Collection<DomainUserType> userTypes;
		
		// Log into de DB
		try {
			userTypes = userTypeController.getUserTypes();
			return new VoyagerServiceResponse(userTypes);
		} catch (Exception ex) {			
			log.error("Failed to get user types", ex);
			String error = "Failed to get user types. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that creates a user type from a user type name
	 * 
	 * @param name: new user type name
	 * 
	 * @throws Exception 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/userType")
	public VoyagerServiceResponse createUserType(String name) throws IOException {
		
		DomainUserType userType;
		
		try {
			userType = userTypeController.createUserType(name);
			return new VoyagerServiceResponse(userType);
		} catch (Exception ex) {			
			log.error("Failed to create user types", ex);
			String error = "Failed to create user type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a user type
	 * 
	 * @param userType: updated user type
	 * @return the updated userType
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/userType")
	public VoyagerServiceResponse updateUserType(DomainUserType userType) throws IOException {
		
		try {
			userTypeController.updateUserType(userType);
			return new VoyagerServiceResponse(userType);
		} catch (Exception ex) {
			log.error("Failed to update user type", ex);
			String error = "Failed to update user type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a user type
	 * @param userTypeId: id of the user type to delete
	 * @return userTypes list in json format 
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteUserType")
	public VoyagerServiceResponse deleteUserType(Integer userTypeId) throws IOException {
		
		try {
			userTypeController.deleteUserType(userTypeId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete user type", ex);
			String error = "Failed to delete user type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
