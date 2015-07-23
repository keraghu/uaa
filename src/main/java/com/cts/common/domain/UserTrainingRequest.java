package com.cts.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_training_req_tb")
public class UserTrainingRequest implements java.io.Serializable{
	@Id @GeneratedValue
	@Column(name = "req_id")
	 private int reqId;
	
	@Column(name = "req_status")
	 private String reqStatus;
	
	@Column(name = "approved_dt")
	 private Date approvedDate;
	
	@Column(name = "training_status")
	 private String trainingStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "training_ID", nullable = false)
	private Training training;
	
	
	public User getUser() {
		return this.user;
	}
	
	
	public Training getTraining(){
		return this.training;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public int getReqId() {
		return reqId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}
