package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "t_users")
public class User {
	private Integer id;
	private UserRole role;
	private String first_name;
	private String last_name;
	private String email_id;
	private String password;
	private String permanant_address;
	private String city;
	private float salary;
	private String contact;
	private Gender gender;
	private int hr_id;
	private byte[] empimage;
	
	private int otp;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_birth;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_joining;
	private float experience;
	private MStatus maritial_status;


	private Project project;


	private Department department;
	
	
	@JsonBackReference
	private List<Leave> leaves = new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public User(UserRole role, String first_name, String last_name, String email_id, String password,
			String permanant_address, String city, float salary, String contact, Gender gender, int hr_id,
			byte[] empimage, int otp, Date date_of_birth, Date date_of_joining, float experience,
			MStatus maritial_status) {
		super();
		this.role = role;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.password = password;
		this.permanant_address = permanant_address;
		this.city = city;
		this.salary = salary;
		this.contact = contact;
		this.gender = gender;
		this.hr_id = hr_id;
		this.empimage = empimage;
		this.otp = otp;
		this.date_of_birth = date_of_birth;
		this.date_of_joining = date_of_joining;
		this.experience = experience;
		this.maritial_status = maritial_status;
	}




	public User(UserRole role, String first_name, String last_name, String email_id, String password,
			String permanant_address, String city, float salary, String contact, Gender gender, int hr_id,
			Date date_of_birth, Date date_of_joining, float experience, MStatus maritial_status) {
		super();
		this.role = role;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.password = password;
		this.permanant_address = permanant_address;
		this.city = city;
		this.salary = salary;
		this.contact = contact;
		this.gender = gender;
		this.hr_id = hr_id;
		this.date_of_birth = date_of_birth;
		this.date_of_joining = date_of_joining;
		this.experience = experience;
		this.maritial_status = maritial_status;		
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", length = 8, unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Column(name = "first_name")
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Column(name = "last_name")
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Email(message = "Please enter a valid email")
	@Column(name = "email_id", nullable = false, unique = true)
	@NotEmpty(message = "Email cannot be empty")
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	@NotEmpty(message = "Password cannot be empty")
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "permanant_address")
	public String getPermanant_address() {
		return permanant_address;
	}

	public void setPermanant_address(String permanant_address) {
		this.permanant_address = permanant_address;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "salary", nullable = false)
	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Column(name = "contact", nullable = false, unique = true)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = false)
	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_joining", nullable = false)
	public Date getDate_of_joining() {

		return date_of_joining;
	}

	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}

	@Column(name = "experience")
	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "maritial_status")
	public MStatus getMaritial_status() {
		return maritial_status;
	}

	public void setMaritial_status(MStatus maritial_status) {
		this.maritial_status = maritial_status;
	}

	public int getHr_id() {
		return hr_id;
	}

	public void setHr_id(int hr_id) {
		this.hr_id = hr_id;
	}
	
	@Transient
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@ManyToOne
	@JoinColumn(name = "project_id")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	@Lob
	@Column(length = 16777215)
	public byte[] getEmpimage() {
		return empimage;
	}

	public void setEmpimage(byte[] empimage) {
		this.empimage = empimage;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email_id=" + email_id + ", password=" + password + ", permanant_address=" + permanant_address
				+ ", city=" + city + ", salary=" + salary + ", contact=" + contact + ", gender=" + gender + ", hr_id="
				+ hr_id + ", date_of_birth=" + date_of_birth + ", date_of_joining=" + date_of_joining + ", experience="
				+ experience + ", maritial_status=" + maritial_status + "]";
	}

	

	

}
