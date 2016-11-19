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

import com.voyagerproject.domain.controller.RuleManagerTypeController;
import com.voyagerproject.domain.entities.DomainType;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the RuleManagerType class
 * @author eandre
 *
 */
@Path("/RuleManagerType")
public class RuleManagerTypeService {
	
	private static final Log log = LogFactory.getLog(RuleManagerTypeService.class);
	
	// Controllers
	RuleManagerTypeController ruleManagerTypeController = new RuleManagerTypeController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the ruleManager types
	 * 
	 * @return ruleManagerTypes list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManagerType")
	public VoyagerServiceResponse getRuleManagerTypes() throws IOException {
		Collection<DomainType> ruleManagerTypes;
		
		// Log into de DB
		try {
			ruleManagerTypes = ruleManagerTypeController.getRuleManagerTypes();
			return new VoyagerServiceResponse(ruleManagerTypes);
		} catch (Exception ex) {			
			log.error("Failed to get ruleManager types", ex);
			String error = "Failed to get ruleManager types. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that creates a ruleManager type from a ruleManager type name
	 * 
	 * @param name: new ruleManager type name
	 * 
	 * @throws Exception 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManagerType")
	public VoyagerServiceResponse createRuleManagerType(String name) throws IOException {
		
		DomainType ruleManagerType;
		
		try {
			ruleManagerType = ruleManagerTypeController.createRuleManagerType(name);
			return new VoyagerServiceResponse(ruleManagerType);
		} catch (Exception ex) {			
			log.error("Failed to create ruleManager type", ex);
			String error = "Failed to create ruleManager type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a ruleManager type
	 * 
	 * @param ruleManagerType: updated ruleManager type
	 * @return the updated ruleManagerType
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/ruleManagerType")
	public VoyagerServiceResponse updateRuleManagerType(DomainType ruleManagerType) throws IOException {
		
		try {
			ruleManagerTypeController.updateRuleManagerType(ruleManagerType);
			return new VoyagerServiceResponse(ruleManagerType);
		} catch (Exception ex) {
			log.error("Failed to update ruleManager type", ex);
			String error = "Failed to update ruleManager type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a ruleManager type
	 * @param ruleManagerTypeId: id of the ruleManager type to delete
	 * @return VoyagerServiceResponse 
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteRuleManagerType")
	public VoyagerServiceResponse deleteRuleManagerType(Integer ruleManagerTypeId) throws IOException {
		
		try {
			ruleManagerTypeController.deleteRuleManagerType(ruleManagerTypeId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete ruleManager type", ex);
			String error = "Failed to delete ruleManager type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
