package springbootdemo.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(ErrorMessage errMsg) {
		super(errMsg.getErrorMsg());
	}

}
