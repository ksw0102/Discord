package com.dw.discord.jwtauthority.controller;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.enumStatus.ResultCode;
import com.dw.discord.jwtauthority.dto.UserDto;
import com.dw.discord.jwtauthority.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<UserDto>> signup(@RequestBody @Valid UserDto userDto) {
    	
        return ResponseEntity.ok(new BaseResponse<>(
                ResultCode.SUCCESS.name(),
                userService.signup(userDto), // 성공한 다음에 가입한 정보를 그대로 return
                ResultCode.SUCCESS.getMsg()
        ));
    }

    @GetMapping("/user") //자기자신의 정보
    // hasAnyRole (일종의 권한 필터링 / 토큰 안에 포함)
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 메소드 수준의 보안방법
    public ResponseEntity<BaseResponse<UserDto>> getCurrentUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(new BaseResponse<>(
                        ResultCode.SUCCESS.name(),
                        userService.getCurrentUserWithAuthorities(), //Security ContextHolder에 저장되어 있는 user(현재 사용하고 있는 토큰 주인)의 정보를 보내주겠다.
                        ResultCode.SUCCESS.getMsg()
        ));
    }

    @GetMapping("/user/{username}") // 누구던지 유저이름을 알면 조회할 수 있음
    @PreAuthorize("hasAnyRole('ADMIN')") // 메소드 수준의 보안방법
    public ResponseEntity<BaseResponse<UserDto>> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(new BaseResponse<>(
                        ResultCode.SUCCESS.name(),
                        userService.getUserWithAuthorities(username),
                        ResultCode.SUCCESS.getMsg()
        ));
    }
}