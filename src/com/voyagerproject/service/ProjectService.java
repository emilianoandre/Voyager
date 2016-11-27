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

import com.voyagerproject.domain.controller.ProjectController;
import com.voyagerproject.domain.entities.DomainProject;
import com.voyagerproject.service.response.VoyagerServiceResponse;

/**
 * Class that holds the CRUD methods for the Project class
 * @author eandre
 *
 */
@Path("/Project")
public class ProjectService {
	
	private static final Log log = LogFactory.getLog(ProjectService.class);
	
	// Controllers
	ProjectController projectController = new ProjectController();
	
	@Context
    private HttpServletResponse servletResponse;
	@Context
	private HttpServletRequest servletRequest;

	
	/**
	 * Service that gets all the projects
	 * 
	 * @return VoyagerServiceResponse projects list in json format 
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/project")
	public VoyagerServiceResponse getProjects() throws IOException {
		Collection<DomainProject> projects;
		
		// Log into de DB
		try {
			projects = projectController.getProjects();
			return new VoyagerServiceResponse(projects);
		} catch (Exception ex) {			
			log.error("Failed to get projects", ex);
			String error = "Failed to get projects. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * 
	 * Service that creates a project from a project name
	 * 
	 * @param project an object with the following attributes: name, url, bugSystem
	 * @return VoyagerServiceResponse with the created project
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/project")
	public VoyagerServiceResponse createProject(DomainProject project) throws IOException {
		
		try {
			project = projectController.createProject(project.getName(), project.getRuleManager(), project.getBugSystem());
			return new VoyagerServiceResponse(project);
		} catch (Exception ex) {			
			log.error("Failed to create project", ex);
			String error = "Failed to create project. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that updates a project
	 * 
	 * @param project: updated project
	 * @return VoyagerServiceResponse the updated project
	 * @throws Exception 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/project")
	public VoyagerServiceResponse updateProject(DomainProject project) throws IOException {
		
		try {
			projectController.updateProject(project);
			return new VoyagerServiceResponse(project);
		} catch (Exception ex) {
			log.error("Failed to update project", ex);
			String error = "Failed to update project. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
	/**
	 * Service that deletes a project
	 * @param projectId: id of the project to delete
	 * @return VoyagerServiceResponse
	 * @throws IOException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@PermitAll	
	@Path("/deleteProject")
	public VoyagerServiceResponse deleteProject(Integer projectId) throws IOException {
		
		try {
			projectController.deleteProject(projectId);
			return new VoyagerServiceResponse();
		} catch (Exception ex) {
			log.error("Failed to delete project", ex);
			String error = "Failed to delete project. " + ex.getMessage();
			return new VoyagerServiceResponse(500, error);
		}
		
	}
	
}
