package com.ga.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {
	
	private int profileId;
	private String mobile;
	private String address;
}
