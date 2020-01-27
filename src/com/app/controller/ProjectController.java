package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IProject;
import com.app.pojos.Project;
import com.app.pojos.User;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/project")
public class ProjectController 
{
	@Autowired
	private IProject iprodao;
	
	@GetMapping
	public List<Project> getAllProject()
	{
		List<Project> pr=iprodao.getAllProjects();
		System.out.println(pr);
		return pr;
		
	}
	
	@PostMapping("/register")
	public Integer register(@RequestBody Project proj,HttpServletRequest request, Model map, HttpSession hs)
	{
		return iprodao.registerProject(proj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProjectDetails(@RequestBody Project p)
	{
		System.out.println("in update project details " + " " + p);
		
		try 
		{
			return new ResponseEntity<Project>(iprodao.updateProject(p),HttpStatus.OK);
		}
		catch (RuntimeException e1)
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> getProjectDetails(@PathVariable int id)
	{
		System.out.println("in get project dtls " + id);
	
		Project projectdetails = iprodao.getProjectDetails(id);
		if (projectdetails == null)
		{
			return new ResponseEntity<String>("PROJECT found...", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Project>(projectdetails, HttpStatus.OK);
	}
	
	
	
}
