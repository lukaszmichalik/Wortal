package com.springboot.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EPosition position;

	@Column
	private Date dob;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "team_id")
//	@JsonManagedReference
//	private Team team;
//
//	@OneToOne(mappedBy = "manager")
//	private Team managerTeam;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, EPosition position, Date dob, String email, String password) {
		this.username = username;
		this.position = position;
		this.dob = dob;
		this.email = email;
		this.password = password;

	}
	public User(String username,  Date dob, String email, String password) {
		this.username = username;
		this.dob = dob;
		this.email = email;
		this.password = password;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public EPosition getPosition() { return position; }

	public void setPosition(EPosition position) { this.position = position; }

	public Date getDob() { return dob; }

	public void setDob(Date dob) { this.dob = dob; }
}
