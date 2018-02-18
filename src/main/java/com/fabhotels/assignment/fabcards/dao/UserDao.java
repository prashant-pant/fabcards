/**
 * 
 */
package com.fabhotels.assignment.fabcards.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.fabhotels.assignment.fabcards.model.User;

/**
 * @author prashant
 *
 */
@Repository
public class UserDao extends BaseDao {
	
	public User get(Long id) {
		return fetch(User.class, id);
	}

	public User getUserByEmail(String email) {
		Query query = getCurrentSession().createQuery("FROM User user WHERE user.email = :email");
		query.setString("email", email);
		return (User) query.uniqueResult();
	}

	public void addUsers(List<User> users) {
		if(users != null){
			for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
				User user = iterator.next();
				getCurrentSession().persist(user);
			}
		}
	}
	
	
}
