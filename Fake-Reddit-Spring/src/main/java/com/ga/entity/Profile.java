package com.ga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {
	@Id
	@Column(name="profile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;
	
	@Column
	private String mobile;
	
	@Column
	private String address;

	public Profile() {
	}

	public Profile(int profileId, String mobile, String address) {
		this.profileId = profileId;
		this.mobile = mobile;
		this.address = address;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
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
