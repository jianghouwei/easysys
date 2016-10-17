/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.androidpn.server.service.impl;

import java.util.Date;
import java.util.List;

import org.androidpn.server.dao.ApnUserMapper;
import org.androidpn.server.model.User;
import org.androidpn.server.service.UserExistsException;
import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * This class is the implementation of UserService.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class UserServiceImpl implements UserService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ApnUserMapper apnUserMapper;

	public User getUser(String userId) {
		return apnUserMapper.selectByPrimaryKey(new Long(userId));
		// return userDao.getUser(new Long(userId));
	}

	public List<User> getUsers() {
		return apnUserMapper.queryList(null);
	}

	public List<User> getUsersFromCreatedDate(Date createDate) {
		User user = new User();
		user.setCreatedDate(createDate);
		return apnUserMapper.queryList(user);
		// return userDao.getUsersFromCreatedDate(createDate);
	}

	public User saveUser(User user) throws UserExistsException {
		try {
			// return userDao.saveUser(user);
			apnUserMapper.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
		} catch (Exception e) { // needed for JPA
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
		}
	}

	public User getUserByUsername(String username) throws UserNotFoundException {
		User user = new User();
		user.setUsername(username);
		user.setUpdatedDate(null);
		user.setCreatedDate(null);
		List<User> list = apnUserMapper.queryList(user);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public void removeUser(Long userId) {
		log.debug("removing user: " + userId);
		apnUserMapper.delByPrimaryKey(userId);
		// userDao.removeUser(userId);
	}

}
