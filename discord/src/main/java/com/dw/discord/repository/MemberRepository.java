package com.dw.discord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.discord.model.Member;

//interface는 추상메소드 이기 때문에 구현체가 필요함
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//대소문자는 상관 없음 findBy~ //중복을 허용했으면 list로 받아야 하나 우리의 loginId는 유니크하기 때문에 member로 받음
	Member findByLoginId(String loginId); // 함수의 선언이 없음에도 불구하고 작동이 되는 것은 jpa의 기능이기 때문!(jpa의 구현체는 하이버네이트임!) 
	//jpa에서 sql문법을 사용할 수 있음!
}
