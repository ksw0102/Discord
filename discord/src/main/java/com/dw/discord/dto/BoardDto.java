package com.dw.discord.dto;

import jakarta.validation.constraints.NotBlank;

//board Entity를 그대로 사용하기엔 불편하기 때문에 BoardDto를 새로 만듦(여기서는 타임스탬프가 없음)
public class BoardDto {

	// hivernate : 데이터베이스용 낫 블랭크
	// validation으로 import해야 됨 위는 x

	@NotBlank
	private String author;

	@NotBlank
	private String title;

	@NotBlank
	private String text;

	@NotBlank
	private String category;

	public BoardDto() {
		super();
	}

	public BoardDto(@NotBlank String author, @NotBlank String title, @NotBlank String text, @NotBlank String category) {
		super();
		this.author = author;
		this.title = title;
		this.text = text;
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
