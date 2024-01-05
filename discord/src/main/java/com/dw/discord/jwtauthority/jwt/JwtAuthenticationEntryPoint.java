package com.dw.discord.jwtauthority.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
   @Override // AuthenticationEntryPoint라는 인터페이스에 값을 넣기 때문에 Override 사용
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      // 유효한 자격증명을 제공하지 않고 접근하려 할때 401 
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 여기에 있는 내용을 CustomExeptionHandler에 넣으면 파일이 있을 필요가 없음
   }
}