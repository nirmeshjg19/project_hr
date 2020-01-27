package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ILeaveDao;
import com.app.dao.IUserDao;
import com.app.pojos.Leave;
import com.app.pojos.User;


@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/leave")
public class LeaveController 
{
	@Autowired
	private ILeaveDao ileavedao;
	
	@Autowired
	private IUserDao dao;
	@Autowired
	private JavaMailSender mailsender;
	
	@GetMapping
	public List<Leave> getAllLeave()
	{
		List<Leave> l = ileavedao.getAllLeaves();
		
		System.out.println(l);
		
		return l;
	}
	
	@PostMapping("/apply")
	public Integer register(@RequestBody Leave leave,@RequestParam int u_id)
	{
		System.out.println(leave);
		
		if(ileavedao.applyLeave(leave, u_id)!=0)
		{
			User u = dao.getUserDetails(u_id);
			String email_id = u.getEmail_id();
			
			System.out.println("email_id");
			
			String msg = "HELLO "+u.getFirst_name()+"!!!!!   Your leave is applyed successfully!!!!!!   WAIT FOR THE APPROVALE!";
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(u.getEmail_id());
			mailMessage.setSubject("email id");
			mailMessage.setText(msg);
			
			try
			{
				mailsender.send(mailMessage);
			}
			catch(MailException error)
			{
				System.out.println("inside mail exception");
				error.printStackTrace();
			}
		}
		else
		{
			System.out.println("error sending mail");
			return 0;
		}
		return ileavedao.applyLeave(leave, u_id);
	}
	
	
	
	@GetMapping("/{u_id}") 
	public List<Leave> getAllLeavesOfUser(@PathVariable int u_id)
	{
		System.out.println("in get leave dtls " + u_id);
	
		List<Leave> leaves = ileavedao.getAllLeavesOfUser(u_id);
		
		return leaves;
	}
	
	@GetMapping("/active")
	public List<Leave> getAllActiveLeaves()
	{
		System.out.println("in get active leaves ");
		
		List<Leave> aleaves = ileavedao.getAllActiveLeaves();
		return aleaves;
	}
	
	
	@PutMapping("/approve")
	public ResponseEntity<?> approveLeave(@RequestBody Leave l)
	{
		System.out.println("in approve " + " " + l);
		
		try 
		{ 
			if(ileavedao.approveLeave(l)!=null)
			{
				String email_id = l.getUser().getEmail_id();
				
				System.out.println("email_id");
				
				String msg = "HELLO "+l.getUser().getFirst_name()+"!!!!!   Your leave of "+l.getDuration()+" is APPROVED!!!!!!ENJOY";
				
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(l.getUser().getEmail_id());
				mailMessage.setSubject("email id");
				mailMessage.setText(msg);
				
				try
				{
					mailsender.send(mailMessage);
				}
				catch(MailException error)
				{
					System.out.println("inside mail exception");
					error.printStackTrace();
				}
			}
			else
			{
				System.out.println("error sending mail");
				return null;
				
			}
			return new ResponseEntity<Leave>(ileavedao.approveLeave(l),HttpStatus.OK);
		}
		catch (RuntimeException e1)
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/disapprove")
	public ResponseEntity<?> disapproveLeave(@RequestBody Leave l)
	{
		System.out.println("in disapprove " + " " + l);
		
		try 
		{ 
			if(ileavedao.disapproveLeave(l)!=null)
			{
				String email_id = l.getUser().getEmail_id();
				
				System.out.println("email_id");
				
				String msg = "HELLO "+l.getUser().getFirst_name()+"!!!!!   Your leave of "+l.getDuration()+" is DISAPPROVED!!!!!!GET BACK TO WORK";
				
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(l.getUser().getEmail_id());
				mailMessage.setSubject("email id");
				mailMessage.setText(msg);
				
				try
				{
					mailsender.send(mailMessage);
				}
				catch(MailException error)
				{
					System.out.println("inside mail exception");
					error.printStackTrace();
				}
			}
			else
			{
				System.out.println("error sending mail");
				return null;
			}
			return new ResponseEntity<Leave>(ileavedao.disapproveLeave(l),HttpStatus.OK);
		}
		catch (RuntimeException e1)
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
