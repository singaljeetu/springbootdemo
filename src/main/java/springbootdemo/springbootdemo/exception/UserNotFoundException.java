package springbootdemo.springbootdemo.exception;

public class UserNotFoundException extends RuntimeException{
	String errorCode;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	String errorMsg;
	public UserNotFoundException(String errCode, String errMsg) {
		super(errMsg);
	}

}
