package com.dw.discord.model;

import java.time.LocalDate;

import com.dw.discord.enumStatus.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity //Entity로 설정하면 Jpa가 테이블로 만들어줌!!
@Table(name = "member",
		uniqueConstraints = {@UniqueConstraint(name="uk_member_login_id", columnNames = {"loginId"})})//데이터베이스에서 중복이 가능하지 않도록 관리할 수 있음! 
//		//유니크하도록 만드는데 이름을 uk_member_login_id로 만든다!(데이터베이스에서 사용하기 때문에 언더스코어를 사용함!)
//데이터 베이스를 컨트롤 하기 위해 쓰임!(데이터베이스 핸들링용) 상세 설정용!!
public class Member {

	@Id //ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@GeneratedValue(strategy = GenerationType.IDENTITY) //databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	
	@Column(nullable = false, length = 30, updatable = false) // 꼭 필수로 입력해야하는 정보이기 때문에 null 값을 허용하지 않고 30자까지 인정, 업데이트도 가능하지 않도록 함!
	private String loginId;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE) //localdate 의 타입을 설정!
	private LocalDate birthDate; //생일!
	
	@Column(nullable = false, length = 5)
	@Enumerated(EnumType.STRING) //MAN, WOMAN 둘중에 하나를 선택할 것이기 때문에 //ordinal은 순서대로 나간다!(ex)month
	private Gender gender; // Enume을 만들기 전까지는 Gender에서 오류가 남!
	
	@Column(nullable = false, length = 100)
	private String email;

	
	// Entity에서는 반드시 기본생성자 꼭 만들어야 됨!!!
	public Member() {
		super();
	}


	public Member(Long id, String loginId, String password, String name, LocalDate birthDate, Gender gender,
			String email) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	
}
