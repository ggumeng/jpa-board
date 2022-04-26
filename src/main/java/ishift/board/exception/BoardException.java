package ishift.board.exception;

import org.springframework.http.HttpStatus;

// RuntimeException을 상속한 CustomException
public class BoardException extends RuntimeException {
    
    private HttpStatus status;
    private String message;

    public BoardException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus(){
        return status;
    }
    
    public void setStatus(HttpStatus status){
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
