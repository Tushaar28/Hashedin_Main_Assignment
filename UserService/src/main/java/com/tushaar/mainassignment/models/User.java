package com.tushaar.mainassignment.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private String address;
	
	@Column(unique = true)
	private String mobile;

	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt = new Date();
	
	@Column(nullable = false, unique = true)
	private String password;
	
	public User(String name, String address, String mobile, Date date, String password) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		createdAt = date;
		this.password = password;
	}
}
