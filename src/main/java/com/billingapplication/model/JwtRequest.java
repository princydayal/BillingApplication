package com.billingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//@entity creates table so remove this annotation for POJO
@Data
@AllArgsConstructor
public class JwtRequest {
  private String username;
  private String password;
  
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
  
}
