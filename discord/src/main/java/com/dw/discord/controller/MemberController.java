package com.dw.discord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.MemberDto;
import com.dw.discord.dto.MemberLoginDto;
import com.dw.discord.service.impl.MemberServiceImpl;
import jakarta.validation.Valid;

//프론트 사이드와의 약속!!
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
// 출처 동일 정책 // 보내는 곳이 같은 경우에만 받아줌 
//원래는 localhost 8080만 받아줘야 하는데 localhost 3000도 받아주자(get과 post,delete 메소드)
public class MemberController {
	
	private final MemberServiceImpl memberServiceImpl;//service는 의존성 주입이 안됨! serviceImpl로 하기
	
	
	@Autowired
	public MemberController(MemberServiceImpl memberServiceeIpmpl) {
		super();
		this.memberServiceImpl = memberServiceeIpmpl;
	}

	@PostMapping("/basic/signup") // api 로 시작하는 것이 좋은 습관임 (백엔드에서는 api를 넣어서 사용하고 프론트에서는 api만 빼서 사용하면 좋음)
	// 리액트 라우터에서 사용하는 주소는 페이지 주소를 url로 표현한 것(클라이언트사이드에서 보여지는 것)이고 여기서 표현한 것은 리액트에서 유즈쿼리를 사용하고 패치를 사용해서 맞춰지기 때문에 그곳에서 사용하는 api만 같으면 상관없음
	public ResponseEntity<BaseResponse<Void>> signUp(@RequestBody @Valid MemberDto memberDto){ 
	//request요청을 미리 만들어놓음(바디에 있는 것을 넣어달라) controller에 들어오는 것은 모두 서블렛이 넣어주는데 서블렛들에게 내가 원하는 것이 어디에 있는지 위치를 알려줘야하기 때문에
	//원래는 <T>가 들어가야하는데 아무것도 안들어간다는 것을 의미하기 위해 <Void>를 입력
	// 회원가입이나 로그인들은 보안과 관련이 있기때문에 RequestHead 말고 RequestBody에 넣어야 함
		//dto에는 유효성 검사를 해야하기 때문에 valid를 넣어줌! 아니면 의미가 없음
	return new ResponseEntity<BaseResponse<Void>>( 
//			new BaseResponse<Void>( //void인 이유는 null 이 있기 때문임( 숫자가 들어가면 int고 클래스가 들어가면 class 가 들어가면 됨)
//					"SUCCESS",
//					null,
					memberServiceImpl.signUp(memberDto), //여기에 값이 들어갈 경우에는 여기 4줄(new~"완료되었습니다.") 말고 서비스를 호출하는 함수를 넣어야 함
					HttpStatus.CREATED);
	}
	
	@PostMapping("/basic/login") //유효성검사를 통과(validation을 통과)하고 나서 틀려야 문구가 나옴
	public ResponseEntity<BaseResponse<Void>> login(@RequestBody @Valid MemberLoginDto memberLoginDto){
	return new ResponseEntity<BaseResponse<Void>>(
//			new BaseResponse<Void>(
//					"SUCCESS",
//					null, // Service쪽에서 처리할 수 있도록 하게끔 넘김
					memberServiceImpl.login(memberLoginDto),
					HttpStatus.OK);
	}
	
}
