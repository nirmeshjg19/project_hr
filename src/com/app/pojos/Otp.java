package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_otp")
public class Otp 
{

	private Integer id;
	private int otp;
	
	public Otp() {
	System.out.println("in otp pojo");
	}

	public Otp(int otp) {
		super();
		this.otp = otp;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "Otp [id=" + id + ", otp=" + otp + "]";
	}
	
}