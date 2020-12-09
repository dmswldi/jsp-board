package member.service;

import java.util.Map;

public class JoinRequest {
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	public void validate(Map<String, Boolean> errors) {
		// id
		checkEmpty(errors, id, "id");
		// name
		checkEmpty(errors, name, "name");
		// password
		checkEmpty(errors, password, "password");
		// confirmPassword
		checkEmpty(errors, confirmPassword, "confirmPassword");
		
		if(! errors.containsKey("confirmPassword")) {// not null && not empty
			if(! isPasswordEqualToConfirm()) {
				errors.put("notMatch", true);
			}
		}
		
	}
	
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, true);// 문제 있다고 true
		}
	}
	
}
