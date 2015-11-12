package com.bit2015.mysite3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2015.mysite3.dao.GuestbookDao;
import com.bit2015.mysite3.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	GuestbookDao guestbookDao;

	public GuestbookVo insert(GuestbookVo vo) {
		guestbookDao.insert(vo);
		return guestbookDao.get(vo.getNo());
	}

	public boolean delete(GuestbookVo vo) {
		int count = guestbookDao.delete(vo);
		return count == 1;
	}

	public List<GuestbookVo> listMessage(Long page) {
		List<GuestbookVo> list = guestbookDao.getList(page);
		return list;
	}
}
