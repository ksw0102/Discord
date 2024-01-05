package com.dw.discord.jwtauthority.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler { // AccessDeniedHandler는 예외처리를 핸들링 하기 위한 인터페이스
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		// 필요한 권한이 없이 접근하려 할때 403 // ex) User가 Admin 권한에 접근하려고 할 때
		response.sendError(HttpServletResponse.SC_FORBIDDEN); // 여기에 있는 내용을 CustomExeptionHandler에 넣으면 파일이 있을 필요가 없음
	}
}