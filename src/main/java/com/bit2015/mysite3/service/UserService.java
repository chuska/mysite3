package com.bit2015.mysite3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.UserDao;
import com.bit2015.mysite3.vo.UserVo;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
	}

	public UserVo login(UserVo vo) {
		UserVo userVo = userDao.get(vo);
		return userVo;
	}

	public UserVo getUser(Long no) {
		UserVo userVo = userDao.get(no);
		return userVo;
	}

	public UserVo getUser(String email) {
		UserVo userVo = userDao.get(email);
		return userVo;
	}

	public void updateInfo(UserVo vo) {
		userDao.update(vo);
	}
}
