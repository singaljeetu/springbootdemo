package springbootdemo.springbootdemo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException une, WebRequest req){
		ErrorMessage em = new ErrorMessage(une.getMessage(), une.getMessage()); 
		ResponseEntity<ErrorMessage> response = new ResponseEntity<ErrorMessage>(em, HttpStatus.NOT_FOUND);
		
		return response;
	} 
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception une, WebRequest req){
		ErrorMessage em = new ErrorMessage(une.getMessage(), une.getMessage()); 
		ResponseEntity<ErrorMessage> response = new ResponseEntity<ErrorMessage>(em, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}
}
