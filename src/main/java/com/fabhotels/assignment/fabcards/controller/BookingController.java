/**
 * 
 */
package com.fabhotels.assignment.fabcards.controller;



import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabhotels.assignment.fabcards.model.User;
import com.fabhotels.assignment.fabcards.service.BookingService;

/**
 * @author prashant
 *
 */
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@RequestMapping("/getPrice")
	public BigDecimal getPrice(@RequestParam("roomType") String roomType) {
		return bookingService.getPrice(roomType);
	}

	@RequestMapping("/book")
	public String book(@AuthenticationPrincipal User user,@RequestParam("roomType") String roomType,
			@RequestParam(value="startDate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(value="endDate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
			){	
		bookingService.book(user, roomType,startDate,endDate);
		return "Room booked successfully";
	}
	
}
