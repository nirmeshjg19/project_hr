package com.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.IUserDao;
import com.app.pojos.Gender;
import com.app.pojos.MStatus;
import com.app.pojos.Otp;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.service.IUserOtp;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController 
{
		int otp;
		
		User user;
	
		@Autowired
		IUserDao iuserdao;
		
		@Autowired
		IUserOtp IUserOtp;
		
		@Autowired
		private JavaMailSender mailsender;
		
	
		@GetMapping("/getuser/{id}") 
		public ResponseEntity<?> getUserDetails(@PathVariable int id)
		{
			System.out.println("in get emp dtls " + id);
		
			User empDetails = iuserdao.getUserDetails(id);
			if (empDetails == null)
			{
				return new ResponseEntity<String>("Emp not found...", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(empDetails, HttpStatus.OK);
		}
	
		@GetMapping
		public List<User> getAllUser()
		{
			List<User> ul = iuserdao.getAllUsers();
			System.out.println(ul);
			return ul;
		}
	
	
	
		@PostMapping("/register")
		public ResponseEntity<?> register(@RequestParam String role,
				@RequestParam String first_name,
				@RequestParam String last_name,
				@RequestParam String email_id,
				@RequestParam String password,
				@RequestParam String permanant_address,
				@RequestParam String city,
				@RequestParam float salary,
				@RequestParam String contact,
				@RequestParam String gender,
				@RequestParam int hr_id,
				@RequestParam float experience,
				@RequestParam String date_of_birth,
				@RequestParam String date_of_joining,
				@RequestParam String maritial_status,
				@RequestParam(value = "empimage", required = false) MultipartFile empimage,int department_id,@RequestParam int project_id)
		{
			System.out.println(user);
			
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Date date2 = null;
			try {
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_joining);
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			User user=new User(UserRole.valueOf(role), first_name, last_name, email_id, password, permanant_address, city, salary, contact,Gender.valueOf(gender), hr_id,
					date1,date2, experience,MStatus.valueOf(maritial_status));
			if (empimage != null) {
				try {
					System.out.println(empimage.getOriginalFilename());
					user.setEmpimage(empimage.getBytes());
					System.out.println(user);
					return new ResponseEntity<Integer>(iuserdao.registerUser(user,project_id,department_id), HttpStatus.CREATED);
				} catch (Exception e) {

					return new ResponseEntity<User>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return null;
//			if(iuserdao.registerUser(user,project_id,department_id)!=0)
//			{
//				String msg = "your email id is "+user.getEmail_id()+"and password is"+user.getPassword();
//				
//				SimpleMailMessage mailMessage = new SimpleMailMessage();
//				mailMessage.setTo(user.getEmail_id());
//				mailMessage.setSubject("email id");
//				mailMessage.setText(msg);
//				
//				try
//				{
//					mailsender.send(mailMessage);
//				}
//				catch(MailException error)
//				{
//					System.out.println("inside mail exception");
//					error.printStackTrace();
//				}
//			}
//			else
//			{
//				System.out.println("error sending mail");
//				return 0;
//			}
//			return iuserdao.registerUser(user,project_id,department_id);
		}
	
	
	
		
		@PostMapping("/login")
		public User login(@RequestBody User user)
		{
			System.out.println(user);
			return iuserdao.login(user);
		}
		
	
		
		@PutMapping("/{id}")
		public ResponseEntity<?> updateEmpDetails(@RequestBody User u)
		{
			System.out.println("in update " + " " + u);
			
			try 
			{
				return new ResponseEntity<User>(iuserdao.updateDetails(u),HttpStatus.OK);
			}
			catch (RuntimeException e1)
			{
				e1.printStackTrace();
				return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@GetMapping("/forgot")
		public Integer processForgotPassword(@RequestParam String email)
		{
			
			System.out.println(email);
			try
			{
				user= iuserdao.findByEmail(email);
				System.out.println(user);
				if(user !=null)
				{
					System.out.println("asdfghj");
					otp = IUserOtp.generatorOTP();
					System.out.println(otp);
					String msg="Your one time password for forgot password is = "+otp;
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(user.getEmail_id());
					mailMessage.setSubject("One Time Password");
					mailMessage.setText(msg);
					System.out.println();
					try
					{
						
						mailsender.send(mailMessage);
					}
					catch (MailException e) 
					{
						System.out.println("inside mail exception");
						e.printStackTrace();
					}
					return 1;
				}
			} catch (NoResultException e) 
			{
				
				e.printStackTrace();
			}
			return 0;
		}
		
		
		
		@PostMapping("/confirmOtp")
		public ResponseEntity<?> confirmOtp(@RequestBody User user1) 
		{
			if (otp == user1.getOtp())
			{
				System.out.println(user.getEmail_id());
				System.out.println(user1.getPassword());
				IUserOtp.setPassword(user1.getPassword(), user.getEmail_id());
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} 
			else 
			{
				return new ResponseEntity<Integer>(0,HttpStatus.NOT_FOUND);
			}
		}
		
}
