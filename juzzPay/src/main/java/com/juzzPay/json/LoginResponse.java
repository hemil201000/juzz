package com.juzzPay.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
	
	@JsonProperty("access_token")
	private String access_token;
	
	@JsonProperty("is_verified")
	private Boolean isVerified ;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	
}
