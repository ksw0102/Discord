package com.dw.discord.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.dto.MemberDto;
import com.dw.discord.dto.MemberLoginDto;
import com.dw.discord.enumStatus.Gender;
import com.dw.discord.enumStatus.ResultCode;
import com.dw.discord.exception.InvalidRequestException;
import com.dw.discord.model.Member;
import com.dw.discord.repository.MemberRepository;
import com.dw.discord.service.MemberService;

import jakarta.transaction.Transactional;

//싱글턴으로 관리하는 녀석들 = bean (퍼포먼스가 좋다) (일이 많은 녀석들)
//(서비스나 레파지토리 등 특별한 어노테이션을 붙일 수 없는데 빈으로 만들고 싶은 녀석들은 @Component를 붙인다)
@Service //bean으로 만들어줌 이 안에 @Component가 있음
@Transactional //DataBase와 관련이 있음 //서비스에 호출을 부탁한다
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	
	// 다른 클래스를 사용하려면 (인스턴스화) 원래 아래와 같이 사용해야 함
	// memberRepository = new MemberRepository();
	// memberRepository.findAll();
	// 그러나 이렇게 될 경우 퍼포먼스적인 부분에서 떨어지기 때문에(메모리에 새로 생성과 삭제를 계속 반복하기 때문) 의존성주입(@AutoWired)을 사용한다!
	
	@Autowired // 의존성 주입! 모든 관리를 대신 할 수 있음 
	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public BaseResponse<Void> signUp(MemberDto memberDto) {
		Member member = memberRepository.findByLoginId(memberDto.getLoginId());
		if(member != null) {
		// 예외를 던진다, 예외를 날린다.
		throw new InvalidRequestException("Duplicate ID", "이미 등록된 ID입니다."); //memberDto.getLoginId(),"이미 등록된 ID입니다." 라고 작성해도 됨.
//			return new BaseResponse<Void>(
//					"Fail", 
//					null ,
//					"이미등록된 ID입니다.");
			
		};
//		return "이미 등록된 ID입니다";
		member = new Member();
		member.setId(null);
		member.setLoginId(memberDto.getLoginId());
		member.setBirthDate(LocalDate.parse(memberDto.getBirthDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"))); 
		//기존의 model과 dto 타입이 맞지 않기 때문에 오류가 나는데 getter를 수정하거나 여기(받는쪽에)서 수정함. 
		member.setEmail(memberDto.getEmail());
		member.setGender(Gender.valueOf(memberDto.getGender()));  // valueOf는 자동으로 Enumn 뒤에 붙는데 얘는 스트링과 똑같은 이넘 타입을 넣는 역할을 한다.
		member.setName(memberDto.getName());
		member.setPassword(memberDto.getPassword());
		
		memberRepository.save(member);
		return new BaseResponse<Void>( //BaseResponse<> 이렇게 작성해도 됨
				ResultCode.SUCCESS.name(), // 인간의 실수를 최소화하기 위해! //Success 라는 단어가 잘못입력되면 fail이 되어버리기 때문
				//"SUCCESS" 
				null,
				"회원가입이 완료되었습니다.");
	};

	@Override
	public BaseResponse<Void> login(MemberLoginDto memberLoginDto) {
		Member member = memberRepository.findByLoginId(memberLoginDto.getLoginId());
		if(member != null && member.getPassword().matches(memberLoginDto.getPassword()) ) { //memberLoginDto에 있는 loginId, password와 입력한 값이 일치하는가 
		return new BaseResponse<Void>(
				ResultCode.SUCCESS.name(),
				//"SUCCESS",
				null,
				"로그인이 성공했습니다.");
		}else {
//		return new BaseResponse<Void>("Fail", null, "ID 또는 PASSWORD가 올바르지 않습니다.");
		throw new InvalidRequestException("Invalid ID / Password", "ID 또는 Password가 올바르지 않습니다.");
		}
	}
}
