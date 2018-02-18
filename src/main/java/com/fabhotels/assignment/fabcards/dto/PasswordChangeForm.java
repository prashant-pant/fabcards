/**
 * 
 */
package com.fabhotels.assignment.fabcards.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author prashant
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PasswordChangeForm {

	private String email;
	private String oldPassword;
	private String newPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
