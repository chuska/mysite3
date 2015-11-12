package com.bit2015.mysite3.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2015.mysite3.service.GuestbookService;
import com.bit2015.mysite3.vo.GuestbookVo;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping("")
	public Map<String, Object> list(@RequestParam("page") Long page) {
		List<GuestbookVo> list = guestbookService.listMessage(page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Map<String, Object> insert(@ModelAttribute GuestbookVo vo) {
		GuestbookVo guestbookVo = guestbookService.insert(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", guestbookVo);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Map<String, Object> delete(@ModelAttribute GuestbookVo vo) {
		boolean result = guestbookService.delete(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", result);
		return map;
	}
}
