package com.bit2015.mysite3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.bit2015.mysite3.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList(Long page, int pageSize, String keyWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("keyWord", keyWord);
		List<BoardVo> list = sqlSession.selectList("board.list", map);
		return list;
	}

	public void insert(BoardVo vo) {
		if (null != vo.getGroupNo()) {
			sqlSession.update("board.updateOrderNo", vo.getOrderNo());
		}
		sqlSession.insert("board.insert", vo);
	}

	public void delete(Long no, Long memberNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("no", no);
		map.put("memberNo", memberNo);
		sqlSession.delete("board.delete", map);
	}

	public BoardVo get(Long no) {
		BoardVo vo = sqlSession.selectOne("board.selectByNo", no);
		return vo;
	}

	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);
	}

	public void viewCount(BoardVo vo) {
		sqlSession.update("board.viewCount", vo);
	}

	public Long totalCount(String keyWord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyWord", keyWord);
		Long totalCountNum = sqlSession.selectOne("board.totalCount", map);
		return totalCountNum;
	}
}
