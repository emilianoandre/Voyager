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

import com.voyagerproject.domain.controller.BugSystemController;
import com.voyagerproject.domain.entities.DomainBugSystem;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the BugSystem class
 * @author eandre
 *
 */
@Path("/BugSystem")
public class BugSystemService {
	
	private static final Log log = LogFactory.getLog(BugSystemService.class);
	
	// Controllers
	BugSystemController bugSystemController = new BugSystemController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the bugSystems
	 * 
	 * @return bugSystems list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystem")
	public VoyagerServiceResponse getBugSystems() throws IOException {
		Collection<DomainBugSystem> bugSystems;
		
		// Log into de DB
		try {
			bugSystems = bugSystemController.getBugSystems();
			return new VoyagerServiceResponse(bugSystems);
		} catch (Exception ex) {			
			log.error("Failed to get bugSystems", ex);
			String error = "Failed to get bugSystems. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * 
	 * Service that creates a bugSystem from a bugSystem name
	 * 
	 * @param bugSystemName: BugSystemName for the new bugSystem
	 * @param name: Name of the new bugSystem
	 * @param email: email of the new bugSystem
	 * @param password: password of the new bugSystem
	 * @param bugSystemTypeId: BugSystem Type of the new bugSystem
	 * @return VoyagerServiceResponse with the created bugSystem
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystem")
	public VoyagerServiceResponse createBugSystem(DomainBugSystem bugSystem) throws IOException {
		
		try {
			bugSystem = bugSystemController.createBugSystem(bugSystem.getName(), bugSystem.getUrl(), bugSystem.getBugSystemType().getIdType());
			return new VoyagerServiceResponse(bugSystem);
		} catch (Exception ex) {			
			log.error("Failed to create bugSystem", ex);
			String error = "Failed to create bugSystem. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a bugSystem
	 * 
	 * @param bugSystem: updated bugSystem
	 * @return the updated bugSystem
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/bugSystem")
	public VoyagerServiceResponse updateBugSystem(DomainBugSystem bugSystem) throws IOException {
		
		try {
			bugSystemController.updateBugSystem(bugSystem);
			return new VoyagerServiceResponse(bugSystem);
		} catch (Exception ex) {
			log.error("Failed to update bugSystem", ex);
			String error = "Failed to update bugSystem. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a bugSystem
	 * @param bugSystemId: id of the bugSystem to delete
	 * @return VoyagerServiceResponse
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteBugSystem")
	public VoyagerServiceResponse deleteBugSystem(Integer bugSystemId) throws IOException {
		
		try {
			bugSystemController.deleteBugSystem(bugSystemId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete bugSystem", ex);
			String error = "Failed to delete bugSystem. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
