package com.springboot.payload.response;

import com.springboot.models.EPosition;

import java.sql.Date;
import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private EPosition position;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, EPosition position, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.position = position;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public EPosition getPosition() {
		return position;
	}

	public void setPosition(EPosition position) {
		this.position = position;
	}
}
