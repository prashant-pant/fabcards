/**
 * 
 */
package com.fabhotels.assignment.fabcards.controller;



import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabhotels.assignment.fabcards.model.FabCard;
import com.fabhotels.assignment.fabcards.model.User;
import com.fabhotels.assignment.fabcards.service.FabCardService;

/**
 * @author prashant
 *
 */
@RestController
@RequestMapping("/fabcard")
public class FabCardController {

	@Autowired
	private FabCardService fabCardService;

	@RequestMapping("/getFabCard")
	public FabCard getFabCard(@AuthenticationPrincipal User user){	
		return fabCardService.getFabCard(user);
	}

	@RequestMapping("/create")
	public FabCard create(@AuthenticationPrincipal User user,@RequestParam("amount") BigDecimal amount){	
		return fabCardService.create(user, amount);
	}

	@RequestMapping("/recharge")
	public FabCard recharge(@RequestParam("id") Long id,@RequestParam("amount") BigDecimal amount){	
		return fabCardService.recharge(id, amount);
	}	
	
}
