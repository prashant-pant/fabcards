/**
 * 
 */
package com.fabhotels.assignment.fabcards.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.fabhotels.assignment.fabcards.model.FabCard;

/**
 * @author prashant
 *
 */
@Repository
public class FabCardDao extends BaseDao {
	
	public FabCard getByUserId(long userId){
		Session currentSession = getCurrentSession();
		Query query = currentSession.createQuery("from FabCard fabCard where fabCard.user.id = :userId").setLong("userId", userId);
		return (FabCard) query.uniqueResult();
	}
	
	public FabCard get(long id){
		return fetch(FabCard.class, id);
	}
}
