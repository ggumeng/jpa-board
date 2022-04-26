package ishift.board.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Controller 에서 뜨는 exception 관리
@RestControllerAdvice
public class ControllerAdvice {
    
    // BoardException 의 응답 담당
    @ExceptionHandler(value = BoardException.class)
    public ResponseEntity<Map<String, Object>> handler(BoardException e){
        Map<String, Object> res = new HashMap<>();
        res.put("message", e.getMessage());
        return new ResponseEntity<>(res, e.getStatus());
    }

    // entity의 noblank 관련 exception 담당
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> blankHandler(ConstraintViolationException e){
        Map<String, Object> res = new HashMap<>();
        res.put("message", "공백이 들어갈 수 없습니다.");
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
