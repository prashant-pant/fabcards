/**
 * 
 */
package com.fabhotels.assignment.fabcards.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabhotels.assignment.fabcards.dto.FabcardServiceExceptionDto;
import com.fabhotels.assignment.fabcards.model.Booking;
import com.fabhotels.assignment.fabcards.model.User;

/**
 * @author prashant
 *
 */
@Service
@Transactional
public class BookingService extends GenericeService {
	
	@Autowired
	private FabCardService fabCardService;

	public BigDecimal getPrice(String roomType) {
		switch (roomType) {
		case "delux":
			return BigDecimal.valueOf(100);
		case "delight":
			return BigDecimal.valueOf(200);
		case "executive":
			return BigDecimal.valueOf(300);
		}
		return null;
	}

	public void book(User user, String roomType,Date startDate, Date endDate) {
		BigDecimal price = getPrice(roomType);
		if(price == null){
			throw new FabcardServiceExceptionDto("UNKNOWN_ROOM_TYPE","Unknown room type"+roomType);
		}
		long diff =  endDate.getTime() - startDate.getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
		BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(days));
		boolean result = fabCardService.compareBalance(user, totalPrice);
		if(!result){
			throw new FabcardServiceExceptionDto("INSUFFICIENT_BALANCE","Insufficient Balance"+roomType);
		}
		fabCardService.deduct(user, totalPrice);
		Booking booking = new Booking();
		booking.setAmountCharged(totalPrice);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		booking.setRoomType(roomType);
		booking.setUser(user);
		getBaseDao().save(booking);
	}
	

}
