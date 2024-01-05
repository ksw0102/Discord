package com.dw.discord.dto;


// 얘네는 빈으로 만들어도 의미가 없음(어떠한 시간이 걸릴만한 일을 하지 않기 때문에)
// backEnd가 frontEnd 개발자에게 편리함을 주기 위함(에러 케이스를 최대한 case by case대로 넘겨줌)
// 약속된 응답형태로 에러 메세지를 보냄
// 만드는 것은 아주 좋은 습관임
// 프론트와 백엔드가 동일한 응답처리를 하기 위해 사용
public class BaseResponse<T> {
// <T>는 제네릭 문법임
	
	private String resultCode;
	private T data; //type의 약자임! // 여기에 오는 데이터타입은 뭐든지 올 수 있어!! // 제네릭 타입을 사용하고 있는 클래스는 | |<T> 라고 사용함!
	private String message;
	
	
	public BaseResponse(String resultCode, T data, String message) {
		super();
		this.resultCode = resultCode;
		this.data = data;
		this.message = message;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
