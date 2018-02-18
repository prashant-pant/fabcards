/**
 * 
 */
package com.fabhotels.assignment.fabcards.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author prashant
 *
 */
@Repository
public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Object object){
		getCurrentSession().persist(object);
	}
	
	public <T> T fetch(Class<T> clz,Serializable key){
		return getCurrentSession().get(clz, key);
	}
	
	public List<?> getListUsingHQL(String hql,Object parameters){
		return (List<?>) getCurrentSession().createQuery(hql).setProperties(parameters).list();
	}
}
