package springbootdemo.springbootdemo.dto;

public class UserDTO {
	String sId;
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	String sFirstName;
	String sLastName;
	String sEmail;
	String sPassword;
	
	public String getsFirstName() {
		return sFirstName;
	}
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getsLastName() {
		return sLastName;
	}
	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

}
