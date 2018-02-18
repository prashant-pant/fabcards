/**
 * 
 */
package com.fabhotels.assignment.fabcards.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabhotels.assignment.fabcards.dao.FabCardDao;
import com.fabhotels.assignment.fabcards.dto.FabcardServiceExceptionDto;
import com.fabhotels.assignment.fabcards.model.FabCard;
import com.fabhotels.assignment.fabcards.model.User;

/**
 * @author prashant
 *
 */
@Service
@Transactional
public class FabCardService extends GenericeService {

	@Autowired
	private FabCardDao fabCardDao;
	
	public FabCard getFabCard(User user){
		return fabCardDao.getByUserId(user.getId());
	}
	
	public FabCard recharge(long id,BigDecimal amount){
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new FabcardServiceExceptionDto("NEGATIVE_RECHARGE","Amount can not be less that equal to zero");
		}
		FabCard fabCard = fabCardDao.get(id);
		fabCard.setBalance(fabCard.getBalance().add(amount));
		return fabCard;		
	}
	
	public FabCard create(User user, BigDecimal amount){
		if(amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new FabcardServiceExceptionDto("NEGATIVE_RECHARGE","Amount can not be less that equal to zero");
		}
		FabCard fabCard = new FabCard();
		fabCard.setBalance(amount);
		fabCard.setUser(user);
		fabCard.setUuid(UUID.randomUUID().toString());
		fabCardDao.save(fabCard);
		return fabCard;
	}

	public boolean compareBalance(User user, BigDecimal amount){
		BigDecimal balance = getFabCard(user).getBalance();
		return balance.compareTo(amount) >= 0;
	}

	public void deduct(User user, BigDecimal amount){
		FabCard fabCard = getFabCard(user);
		fabCard.setBalance(fabCard.getBalance().subtract(amount));
	}
	
}
