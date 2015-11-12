package com.bit2015.mysite3.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2015.mysite3.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	SqlSession sqlSession;

	public void update(UserVo vo) {
		sqlSession.update("user.update", vo);
	}

	public UserVo get(UserVo vo) {
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", vo);
		return userVo;
	}

	public UserVo get(Long no) {
		UserVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
	}

	public UserVo get(String email) {
		UserVo vo = sqlSession.selectOne("user.getByEmail", email);
		return vo;
	}

	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}

}
