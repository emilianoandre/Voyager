package com.voyagerproject.service;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.voyagerproject.domain.controller.UserTypeController;
import com.voyagerproject.domain.entities.DomainUserType;

/**
 * Class that holds the CRUD methods for the types (UserType, BugSystemType, etc)
 * @author eandre
 *
 */
@Path("/Types")
public class TypesService {
	
	private static final Log log = LogFactory.getLog(TypesService.class);
	
	// Controllers
	UserTypeController userTypeController = new UserTypeController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Logout service
	 * 
	 * @param String user
	 * @param String password
	 * @return String 
	 * @throws IOException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/userTypes")
	public Collection<DomainUserType> logout() throws IOException {
		Collection<DomainUserType> userTypes;
		
		// Log into de DB
		try {
			userTypes = userTypeController.getUserTypes();
			return userTypes;
		} catch (Exception ex) {
			log.error("Failed to log out user", ex);
            servletResponse.sendError(401, ex.getMessage());
            return null;
		}
		
	}
	
}
