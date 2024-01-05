package com.dw.discord.jwtauthority.dto;

public class AuthorityDto {

	private String authorityName; // 권한 이름

	public AuthorityDto() {
		super();
	}

	public AuthorityDto(String authorityName) {
		super();
		this.authorityName = authorityName;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}	
	
}
