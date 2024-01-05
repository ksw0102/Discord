package com.dw.discord.enumStatus;

public enum Gender {

	
	MAN("남성"),
	WOMAN("여성");
	
	//디스크립션! // final은 수정이 불가한 아이임!
	private final String desc;

	//Entity가 아니기 때문에 기본생성자는 필수가 아님!!
	private Gender(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	
	
}
