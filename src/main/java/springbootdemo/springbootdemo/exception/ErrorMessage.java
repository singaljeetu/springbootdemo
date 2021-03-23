package springbootdemo.springbootdemo.exception;

public class ErrorMessage {
	String errorCode;
	String errorMsg;
	public ErrorMessage (String errCode, String errMsg) {
		errorCode = errCode;
		errorMsg = errMsg;
	}
}
