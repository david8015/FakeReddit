package com.ga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile") //tells hibernate to create a table named profile
public class Profile {
	//@column - create a column in the table profile

	@Id //mark this column as the primaryKey
	@Column(name="profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	
	@Column
	private String mobile;
	
	@Column
	private String address;

	@Column(name="additional_email")
	private String additionalEmail;

	public Profile() {
	}

	public Profile(Long profileId, String mobile, String address) {
		this.profileId = profileId;
		this.mobile = mobile;
		this.address = address;
	}

	//getters and setters


	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getAdditionalEmail() {
		return additionalEmail;
	}

	public void setAdditionalEmail(String additionalEmail) {
		this.additionalEmail = additionalEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
