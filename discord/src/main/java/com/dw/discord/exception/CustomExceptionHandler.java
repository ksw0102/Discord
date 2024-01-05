package com.dw.discord.exception;

import java.util.HashMap;
import java.util.Map;

import com.dw.discord.dto.BaseResponse;
import com.dw.discord.enumStatus.ResultCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// try,catch와 익셉션 처리는 자바에서 꼭 필요함!!
// 에러처리 이름을 정하고
// 어떤 상황에서 에러를 던질지 정하고
// 어떠한 메세지를 던질지 정해야 됨

// 익셉션의 진짜 내용물!
// 각 예외 사항을 어떻게 처리하겠느냐(커스텀이 붙었기 때문에 개발자가 직접 마음대로 정한 것임)
// 미처 처리하지 못한 익셉션은 여기에 추가
@Order(Ordered.HIGHEST_PRECEDENCE) // 컨트롤러 어드바이스가 여러 개 있을 수도 있는데 그 중에 우선순위를 정해놓기 위해 쓰임!
@ControllerAdvice // 익셉션핸들러는 내가 만든 것이니까 이걸로 예외 처리를 대신하겠다고 스프링에게 얘기함!
public class CustomExceptionHandler { // MethodArgumentNotValidException는 예를 들어 내가 가입할 때 양식에 맞지 않게 입력했을 경우 발생
	@ExceptionHandler(MethodArgumentNotValidException.class) // 자주 발생하는 에러이기 때문에 내가 처리할게
	protected ResponseEntity<BaseResponse<Map<String, String>>> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>(); // 데이터 타입이 맵임, String(Key) : String(Value)
		ex.getBindingResult().getAllErrors().forEach(error -> { // forEach로 돌렸기 때문에 여러 애러가 발생해도 깔끔하게 나누어서 나옴
			// MethodArgumentNotValidException에서 원하는 것을 골라서 fieldName, errorMessage만 골라
			// 옴(errors)
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage != null ? errorMessage : "No Exception Message");
		});
		return new ResponseEntity<>(new BaseResponse<>(ResultCode.ERROR.name(), errors, ResultCode.ERROR.getMsg()),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidRequestException.class) // 자주 발생하는 익셉션들은 여기에 정의
	protected ResponseEntity<BaseResponse<Map<String, String>>> handleInvalidIdException(InvalidRequestException ex) {
		// 익셉션핸들러 라는 어노테이션을 붙이고 헨들링하고싶은 익셉션 이름을 붙임! // 여기 같은 경우는
		Map<String, String> errors = Map.of(ex.getFieldName(),
				(ex.getMessage() != null ? ex.getMessage() : "No Exception Message"));
		return new ResponseEntity<>(new BaseResponse<>(ResultCode.ERROR.name(), errors, ResultCode.ERROR.getMsg()),
				HttpStatus.BAD_REQUEST);
	}
//	    @ExceptionHandler(Exception.class)
//	    protected ResponseEntity<BaseResponse<Map<String, String>>> defaultException(Exception ex) {
//	        Map<String, String> errors = Map.of("미처리에러", (ex.getMessage() != null ? ex.getMessage() : "No Exception Message"));
//	        return new ResponseEntity<>(new BaseResponse<>(
//	                ResultCode.ERROR.name(),
//	                errors,
//	                ResultCode.ERROR.getMsg()
//	        ), HttpStatus.BAD_REQUEST);
//	    }
}
