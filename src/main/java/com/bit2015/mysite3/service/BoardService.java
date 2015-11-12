package com.bit2015.mysite3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.BoardDao;
import com.bit2015.mysite3.vo.BoardVo;

@Service
public class BoardService {
	private final int PAGE_SIZE = 2;
	private final int BLOCK_SIZE = 2;
	@Autowired
	BoardDao boardDao;

	public Map<String, Object> getList(Long page, String keyWord) {
		// 1. calculate pager's basic data
		long totalCountNum = boardDao.totalCount(keyWord);
		long totalPage = (int) Math.ceil((double) totalCountNum / PAGE_SIZE);
		long totalBlock = (long) Math.ceil((double) totalPage / BLOCK_SIZE);
		long currentBlock = (int) Math.ceil((double) page / BLOCK_SIZE);

		// 2. page validation
		if (page > totalPage) {
			page = totalPage;
			currentBlock = (int) Math.ceil((double) page / BLOCK_SIZE);
		}

		// 3. calculate pager's data
		long startPage = (currentBlock == 0) ? 1 : (currentBlock - 1)
				* BLOCK_SIZE + 1;
		long endPage = startPage + BLOCK_SIZE - 1;

		// 4. fetch list
		List<BoardVo> list = boardDao.getList(page, PAGE_SIZE, keyWord);

		// 5. pack all information of list
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("currentPage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		map.put("keyWord", keyWord);
		return map;
	}

	public void insert(BoardVo vo) {
		boardDao.insert(vo);
	}

	public void delete(Long no, Long memberNo) {
		boardDao.delete(no, memberNo);
	}

	public BoardVo view(Long no) {
		BoardVo vo = boardDao.get(no);
		boardDao.viewCount(vo);
		return vo;
	}

	public void update(BoardVo vo) {
		boardDao.update(vo);
	}

	public BoardVo get(Long no) {
		BoardVo vo = boardDao.get(no);
		return vo;
	}
}
