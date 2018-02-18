/**
 * 
 */
package com.fabhotels.assignment.fabcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fabhotels.assignment.fabcards.dao.UserDao;
import com.fabhotels.assignment.fabcards.dto.PasswordChangeForm;
import com.fabhotels.assignment.fabcards.dto.UserProfileDto;
import com.fabhotels.assignment.fabcards.dto.UserRegistrationForm;
import com.fabhotels.assignment.fabcards.model.Address;
import com.fabhotels.assignment.fabcards.model.User;

/**
 * @author prashant
 *
 */
@Service
@Transactional
public class UserService extends GenericeService implements UserDetailsService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	public void register(UserRegistrationForm userRegistrationForm){
		User user = new User();
		user.setEmail(userRegistrationForm.getEmail());
		user.setName(userRegistrationForm.getName());
		user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		Address address = new Address();
		address.setAddressLine(userRegistrationForm.getAddressLine());
		address.setArea(userRegistrationForm.getArea());
		address.setCity(userRegistrationForm.getCity());
		address.setPincode(userRegistrationForm.getPincode());
		address.setState(userRegistrationForm.getState());
		user.setAddress(address);
		userDao.save(user);
	}
	
	public void changePassword(PasswordChangeForm passwordChangeForm){
		User user = userDao.getUserByEmail(passwordChangeForm.getEmail());
		if(passwordEncoder.matches(passwordChangeForm.getOldPassword(), user.getPassword())){
			user.setPassword(passwordEncoder.encode(passwordChangeForm.getNewPassword()));
		}else{
			throw new ServiceException("Old Password is incorrect");
		}
	}

	public boolean accountExists(String emailId) {
		User user = userDao.getUserByEmail(emailId);
		if(user == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try{
			user = getUserByEmail(username);
		}catch(Throwable e){
			e.printStackTrace();
		}
		if(user == null) throw new UsernameNotFoundException("No such User");	
		return user;
	}

	public User updateProfile(Long id,UserProfileDto userProfileDto) {
		User user = userDao.get(id);
		Address address = user.getAddress();
		if(! StringUtils.isEmpty(userProfileDto.getName())){
			user.setName(userProfileDto.getName());
		}
		if(! StringUtils.isEmpty(userProfileDto.getEmail())){
			user.setEmail(userProfileDto.getEmail());
		}
		if(address == null){
			address = new Address();
			user.setAddress(address);
			getBaseDao().save(address);
		}
		address.setAddressLine(userProfileDto.getAddressLine());
		address.setCity(userProfileDto.getCity());	
		if(! StringUtils.isEmpty(userProfileDto.getArea())){
			address.setArea(userProfileDto.getArea());
		}
		if(! StringUtils.isEmpty(userProfileDto.getState())){
			address.setState(userProfileDto.getState());
		}
		if(! StringUtils.isEmpty(userProfileDto.getPincode())){
			address.setPincode(userProfileDto.getPincode());
		}
		return user;
	}

	public UserProfileDto getUserProfile(long id) {
		User user = userDao.get(id);
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setEmail(user.getEmail());
		userProfileDto.setName(user.getName());
		if(user.getAddress() == null){
			return userProfileDto;
		}
		userProfileDto.setAddressLine(user.getAddress().getAddressLine());
		userProfileDto.setArea(user.getAddress().getArea());
		userProfileDto.setCity(user.getAddress().getCity());
		userProfileDto.setPincode(user.getAddress().getPincode());
		userProfileDto.setState(user.getAddress().getState());
		return userProfileDto;
	}
}
