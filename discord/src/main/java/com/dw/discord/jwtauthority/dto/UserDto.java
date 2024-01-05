package com.dw.discord.jwtauthority.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.dw.discord.jwtauthority.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// 가입을 하기 위한 dto
public class UserDto {

	@NotNull
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // dto 형태에서 읽을 수 없다. //USERDTO에서 JSOM형태로 응답을 했되 password를 넣지
															// 않음.
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,20}$", message = "영문 숫자 특수문자를 포함한 8~20자리로 입력해주세요") // 받을
																																		// 때의
																																		// 규칙
	private String password;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 50) // 최소 3자에서 최대 50자까지
	private String nickname;

	private Set<AuthorityDto> authorityDtoSet; // 가입할 때 자동으로 user권한 부여

	public UserDto() {
		super();
	}

	public UserDto(String username, String password, String nickname, Set<AuthorityDto> authorityDtoSet) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.authorityDtoSet = authorityDtoSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<AuthorityDto> getAuthorityDtoSet() {
		return authorityDtoSet;
	}

	public void setAuthorityDtoSet(Set<AuthorityDto> authorityDtoSet) {
		this.authorityDtoSet = authorityDtoSet;
	}

	public static UserDto from(User user) {
		if (user == null)
			return null;

		Set<AuthorityDto> authorityDtoSet = user.getAuthorities().stream()
				.map(authority -> new AuthorityDto(authority.getAuthorityName())).collect(Collectors.toSet());

		return new UserDto(user.getUsername(), null, user.getNickname(), authorityDtoSet);
	}



}
