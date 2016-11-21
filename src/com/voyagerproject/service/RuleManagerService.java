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

import com.voyagerproject.domain.controller.RuleManagerController;
import com.voyagerproject.domain.entities.DomainRuleManager;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the RuleManager class
 * @author eandre
 *
 */
@Path("/RuleManager")
public class RuleManagerService {
	
	private static final Log log = LogFactory.getLog(RuleManagerService.class);
	
	// Controllers
	RuleManagerController ruleManagerController = new RuleManagerController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the ruleManagers
	 * 
	 * @return ruleManagers list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManager")
	public VoyagerServiceResponse getRuleManagers() throws IOException {
		Collection<DomainRuleManager> ruleManagers;
		
		// Log into de DB
		try {
			ruleManagers = ruleManagerController.getRuleManagers();
			return new VoyagerServiceResponse(ruleManagers);
		} catch (Exception ex) {			
			log.error("Failed to get ruleManagers", ex);
			String error = "Failed to get ruleManagers. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * 
	 * Service that creates a ruleManager from a ruleManager name
	 * 
	 * @param ruleManagerName: RuleManagerName for the new ruleManager
	 * @param name: Name of the new ruleManager
	 * @param email: email of the new ruleManager
	 * @param password: password of the new ruleManager
	 * @param ruleManagerTypeId: RuleManager Type of the new ruleManager
	 * @return VoyagerServiceResponse with the created ruleManager
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManager")
	public VoyagerServiceResponse createRuleManager(DomainRuleManager ruleManager) throws IOException {
		
		try {
			ruleManager = ruleManagerController.createRuleManager(ruleManager.getName(), ruleManager.getUrl(), ruleManager.getRuleManagerType().getIdType());
			return new VoyagerServiceResponse(ruleManager);
		} catch (Exception ex) {			
			log.error("Failed to create ruleManager", ex);
			String error = "Failed to create ruleManager. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a ruleManager
	 * 
	 * @param ruleManager: updated ruleManager
	 * @return the updated ruleManager
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManager")
	public VoyagerServiceResponse updateRuleManager(DomainRuleManager ruleManager) throws IOException {
		
		try {
			ruleManagerController.updateRuleManager(ruleManager);
			return new VoyagerServiceResponse(ruleManager);
		} catch (Exception ex) {
			log.error("Failed to update ruleManager", ex);
			String error = "Failed to update ruleManager. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a ruleManager
	 * @param ruleManagerId: id of the ruleManager to delete
	 * @return VoyagerServiceResponse
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteRuleManager")
	public VoyagerServiceResponse deleteRuleManager(Integer ruleManagerId) throws IOException {
		
		try {
			ruleManagerController.deleteRuleManager(ruleManagerId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete ruleManager", ex);
			String error = "Failed to delete ruleManager. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
