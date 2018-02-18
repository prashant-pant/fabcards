/**
 * 
 */
package com.fabhotels.assignment.fabcards.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabhotels.assignment.fabcards.dto.PasswordChangeForm;
import com.fabhotels.assignment.fabcards.dto.UserProfileDto;
import com.fabhotels.assignment.fabcards.dto.UserRegistrationForm;
import com.fabhotels.assignment.fabcards.model.User;
import com.fabhotels.assignment.fabcards.service.UserService;

/**
 * @author prashant
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public User login(@AuthenticationPrincipal User user){
		// User Login is being checked by Spring Security using Authorization header
		return user;
	}
	
	@RequestMapping("/register")
	public String register(@RequestBody UserRegistrationForm userRegistrationForm){
		userService.register(userRegistrationForm);
		return "User Registered Successfully";
	}

	@RequestMapping("/updateProfile")
	public String updateProfile(@AuthenticationPrincipal User user,@RequestBody UserProfileDto profileUpdateForm){	
		userService.updateProfile(user.getId(),profileUpdateForm);
		return "Profile updated Successfully";
	}

	@RequestMapping("/changePassword")
	public String changePassword(@RequestBody PasswordChangeForm passwordChangeForm){
		userService.changePassword(passwordChangeForm);
		return "Password Changed Successfully";
	}

	@RequestMapping("/getMyProfile")
	public UserProfileDto getMyProfile(@AuthenticationPrincipal User user){
		return userService.getUserProfile(user.getId());
	}
	
	@RequestMapping("/accountExists")
	public boolean accountExists(@RequestParam String emailId){
		return userService.accountExists(emailId);
	}
}
