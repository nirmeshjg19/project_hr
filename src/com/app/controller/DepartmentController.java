package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IDepartment;

import com.app.pojos.Department;
import com.app.pojos.User;



@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/department")
public class DepartmentController 
{
	@Autowired
	IDepartment idepartment;
	
	@GetMapping("/{id}") 
	public ResponseEntity<?> getUserDetails(@PathVariable int id)
	{
		System.out.println("in get user dtls " + id);
	
		Department departmentDetails = idepartment.getDepartmentDetails(id);
		if (departmentDetails == null)
		{
			return new ResponseEntity<String>("Emp not found...", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>(departmentDetails, HttpStatus.OK);
	}
	
	@GetMapping
	public List<Department> getAllDepartment()
	{
		List<Department> dl = idepartment.getAllDepartment();
		System.out.println(dl);
		return dl;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDepartmentDetails(@RequestBody Department d)
	{
		System.out.println("in update " + " " + d);
		
		try 
		{
			return new ResponseEntity<Department>(idepartment.updateDepartmentDetails(d),HttpStatus.OK);
		}
		catch (RuntimeException e1)
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/register")
	public Integer register(@RequestBody Department department)
	{
		System.out.println(department);
		return idepartment.registerDepartment(department);
	}
}
