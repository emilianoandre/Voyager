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

import com.voyagerproject.domain.controller.PermissionController;
import com.voyagerproject.domain.entities.DomainPermission;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the Permission class
 * @author eandre
 *
 */
@Path("/Permission")
public class PermissionService {
	
	private static final Log log = LogFactory.getLog(PermissionService.class);
	
	// Controllers
	PermissionController permissionController = new PermissionController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the permission types
	 * 
	 * @return VoyagerServiceResponse permissions list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/permission")
	public VoyagerServiceResponse getPermissions() throws IOException {
		Collection<DomainPermission> permissions;
		
		// Log into de DB
		try {
			permissions = permissionController.getPermissions();
			return new VoyagerServiceResponse(permissions);
		} catch (Exception ex) {			
			log.error("Failed to get permission types", ex);
			String error = "Failed to get permission types. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that creates a permission type from a permission type name
	 * 
	 * @param name: new permission type name
	 * @return VoyagerServiceResponse with the created permission
	 * @throws Exception 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/permission")
	public VoyagerServiceResponse createPermission(String name) throws IOException {
		
		DomainPermission permission;
		
		try {
			permission = permissionController.createPermission(name);
			return new VoyagerServiceResponse(permission);
		} catch (Exception ex) {			
			log.error("Failed to create permission type", ex);
			String error = "Failed to create permission type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a permission type
	 * 
	 * @param permission: updated permission type
	 * @return VoyagerServiceResponse with the updated permission
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/permission")
	public VoyagerServiceResponse updatePermission(DomainPermission permission) throws IOException {
		
		try {
			permissionController.updatePermission(permission);
			return new VoyagerServiceResponse(permission);
		} catch (Exception ex) {
			log.error("Failed to update permission type", ex);
			String error = "Failed to update permission type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a permission type
	 * @param permissionId: id of the permission type to delete
	 * @return VoyagerServiceResponse 
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deletePermission")
	public VoyagerServiceResponse deletePermission(Integer permissionId) throws IOException {
		
		try {
			permissionController.deletePermission(permissionId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete permission type", ex);
			String error = "Failed to delete permission type. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
