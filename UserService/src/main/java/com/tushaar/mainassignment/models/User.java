package com.tushaar.mainassignment.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String mobile;

	@Column
	private String name;

	@Column
	private String address;

	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt = new Date();
}