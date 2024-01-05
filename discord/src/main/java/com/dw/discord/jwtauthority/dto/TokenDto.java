package com.dw.discord.jwtauthority.dto;

// 토큰을 주고 받을 때 사용
public class TokenDto {

	private String token;

	public TokenDto() {
		super();
	}

	public TokenDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
