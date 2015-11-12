package com.bit2015.mysite3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("/")
	public String index() {
		return "/main/index";
	}

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "헬로우 마이사이트";
	}

	@ResponseBody
	@RequestMapping("/hello2")
	public Map<String, Object> hello2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "ok");
		map.put("data", 3.14);
		map.put("data2", 100000);
		return map;
	}
}
