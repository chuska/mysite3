package com.bit2015.mysite3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2015.mysite3.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	public GuestbookVo get(Long no) {
		return sqlSession.selectOne("guestbook.get", no);
	}

	public List<GuestbookVo> getList(Long page) {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.listbypage",
				page);
		return list;
	}

	public void insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}

	public int delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo);
	}
}
