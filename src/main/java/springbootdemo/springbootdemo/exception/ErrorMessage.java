package springbootdemo.springbootdemo.exception;

import org.springframework.stereotype.Component;

public class ErrorMessage {
	String errorCode;
	String errorMsg;
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
	public ErrorMessage (String errCode, String errMsg) {
		errorCode = errCode;
		errorMsg = errMsg;
	}
}
