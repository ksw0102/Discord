package com.dw.discord.jwtauthority.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authority") // 인가와 관련된 테이블
public class Authority {
	 @Id
	 @Column(name = "authority_name", length = 50)
	 private String authorityName; // ROLE_ADMIN , ROLE_USER 만 들어감

	public Authority() {
		super();
	}

	public Authority(String authorityName) {
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
