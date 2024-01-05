package com.dw.discord.jwtauthority.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.discord.jwtauthority.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	 @EntityGraph(attributePaths = "authorities") // @EntityGraph는 미리 캐싱 해놓는 개념임(메모리를 효율적으로 사용하기 위해!) 
	 Optional<User> findOneWithAuthoritiesByUsername(String username); //findOneWith ~~ entity명 jointable 용임! // 유저네임과 유저권한을 찾아라! // 작명법으로 권한을 줌

	 // 위 메소드와 동일한 메소드 (JPQL 버전) // @Query를 적용하면 SQL을 직접 사용할 수 있음
	@Query("SELECT u FROM User u JOIN FETCH u.authorities WHERE u.username = :username")// authorities를 join해서 찾는데 username을 이용해서 찾아라 // 객체 명을 사용 // u는 user 의 구현체
	Optional<User> findAuthoritiesByUsername(@Param("username") String username); // 변수 이름은 userName //  u.username = @Param("username") // :username = String username

	User findByUsername(String loginId);
}
