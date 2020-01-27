package com.app.pojos;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_leave")
public class Leave 
{
    private int id;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    private LeaveType leaveType;
    private String reason;
    private int duration;
    private boolean acceptRejectFlag;
    private boolean active;
    private String status;

    //@JsonBackReference
    private User user;
    
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(Date  fromDate, Date  toDate, LeaveType leaveType, String reason,
			int duration, boolean acceptRejectFlag, boolean active,String status) 
	{
		super();
		
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.leaveType = leaveType;
		this.reason = reason;
		this.duration = duration;
		this.acceptRejectFlag = acceptRejectFlag;
		this.active = active;
		this.status= status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Please provide start date!")
	@Column(name = "fromDate")
	public Date  getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date  fromDate) {
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Please provide end date!")
	@Column(name = "toDate")
	public Date  getToDate() {
		return toDate;
	}

	public void setToDate(Date  toDate) {
		this.toDate = toDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "leaveType")
	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	 @NotEmpty(message = "Please provide a reason for the leave!")
	    @Column(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	 @Column(name = "duration")
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	 @Column(name = "acceptRejectFlag")
	public boolean isAcceptRejectFlag() {
		return acceptRejectFlag;
	}

	public void setAcceptRejectFlag(boolean acceptRejectFlag) {
		this.acceptRejectFlag = acceptRejectFlag;
	}

	@Column(name = "active")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "u_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id +  ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", leaveType=" + leaveType + ", reason=" + reason + ", duration="
				+ duration + ", acceptRejectFlag=" + acceptRejectFlag + ", active=" + active + "]";
	}
    
}