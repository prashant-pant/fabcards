/**
 * 
 */
package com.fabhotels.assignment.fabcards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabhotels.assignment.fabcards.dao.BaseDao;

/**
 * @author prashant
 *
 */
@Service
@Transactional
public class GenericeService {

	@Autowired
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}
}
