package com.dw.discord.jwtauthority.controller;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.enumStatus.ResultCode;
import com.dw.discord.jwtauthority.dto.LoginDto;
import com.dw.discord.jwtauthority.dto.TokenDto;
import com.dw.discord.jwtauthority.jwt.JwtFilter;
import com.dw.discord.jwtauthority.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, 
    		AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate") // 인증만료, 로그인 하는 애
    public ResponseEntity<BaseResponse<TokenDto>> authorize(@RequestBody @Valid LoginDto loginDto) { //userName과 password를 받아옴(loginDto) 그렇게 로그인을 하고 나면 Token을 줌

    	// UsernamePasswordAuthenticationToken은 유저가 보내온 아이디와 패스워드를 이용해서 만들어놓는 기본클래스임
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), // 인증이 실패하면 
                		loginDto.getPassword()); // 인증정보를 담아두는 박스

        Authentication authentication = authenticationManagerBuilder.getObject() //authenticationManagerBuilder는 지금 현재 로그인한 사람의 정보를 담고 있음
        		.authenticate(authenticationToken); // 여기서 부터 성공한 경우만 실행되는 코드
        SecurityContextHolder.getContext().setAuthentication(authentication); //  SecurityContextHolder는 싱글턴임! 현재 authentication(유저네임과 권한이 들어있음)을 저장
        //( 현재 로그인한(접속한) 사람의 정보를 알 수 있음)
        
        String jwt = tokenProvider.createToken(authentication); // 로그인에 성공하면 Token을 줌!

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt); // 응답 메세지의 헤더에 토큰을 담아서 줌 // 토큰 앞자리에 7자(여기서는 Bearer)가 꼭 담아 있어야 함!

        return new ResponseEntity<>(
                new BaseResponse<>(ResultCode.SUCCESS.name(), new TokenDto(jwt), ResultCode.SUCCESS.getMsg()),
                httpHeaders, //2번째 응답메세지 header를 보냄!!
                HttpStatus.OK); // 3번째 응답메세지 Status 메세지    
        }
}