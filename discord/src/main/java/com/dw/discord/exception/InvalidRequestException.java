package com.dw.discord.exception;

//익셉션이라는 이름을 쓰기 위한 껍데기임! // 이런 내용의 익셉션을 만들고 싶어!
//일반 클래스임, (필드,생성자,게터세터 가 있기 때문에) 다른 점은 런타임익셉션을 상속받음
public class InvalidRequestException extends RuntimeException {

	//커스텀 익셉션
    private String fieldName;
    private String message;

    public InvalidRequestException(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}