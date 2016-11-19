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

import com.voyagerproject.domain.controller.BugSystemTypeController;
import com.voyagerproject.domain.entities.DomainType;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the BugSystemType class
 * @author eandre
 *
 */
@Path("/BugSystemType")
public class BugSystemTypeService {
	
	private static final Log log = LogFactory.getLog(BugSystemTypeService.class);
	
	// Controllers
	BugSystemTypeController bugSystemTypeController = new BugSystemTypeController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the bugSystem types
	 * 
	 * @return bugSystemTypes list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystemType")
	public VoyagerServiceResponse getBugSystemTypes() throws IOException {
		Collection<DomainType> bugSystemTypes;
		
		// Log into de DB
		try {
			bugSystemTypes = bugSystemTypeController.getBugSystemTypes();
			return new VoyagerServiceResponse(bugSystemTypes);
		} catch (Exception ex) {			
			log.error("Failed to get bugSystem types", ex);
			String error = "Failed to get bugSystem types. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that creates a bugSystem type from a bugSystem type name
	 * 
	 * @param name: new bugSystem type name
	 * 
	 * @throws Exception 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystemType")
	public VoyagerServiceResponse createBugSystemType(String name) throws IOException {
		
		DomainType bugSystemType;
		
		try {
			bugSystemType = bugSystemTypeController.createBugSystemType(name);
			return new VoyagerServiceResponse(bugSystemType);
		} catch (Exception ex) {			
			log.error("Failed to create bugSystem type", ex);
			String error = "Failed to create bugSystem type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a bugSystem type
	 * 
	 * @param bugSystemType: updated bugSystem type
	 * @return the updated bugSystemType
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystemType")
	public VoyagerServiceResponse updateBugSystemType(DomainType bugSystemType) throws IOException {
		
		try {
			bugSystemTypeController.updateBugSystemType(bugSystemType);
			return new VoyagerServiceResponse(bugSystemType);
		} catch (Exception ex) {
			log.error("Failed to update bugSystem type", ex);
			String error = "Failed to update bugSystem type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a bugSystem type
	 * @param bugSystemTypeId: id of the bugSystem type to delete
	 * @return VoyagerServiceResponse 
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteBugSystemType")
	public VoyagerServiceResponse deleteBugSystemType(Integer bugSystemTypeId) throws IOException {
		
		try {
			bugSystemTypeController.deleteBugSystemType(bugSystemTypeId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete bugSystem type", ex);
			String error = "Failed to delete bugSystem type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
