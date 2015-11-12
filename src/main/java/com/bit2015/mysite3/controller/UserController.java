package com.bit2015.mysite3.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2015.mysite3.annotation.Auth;
import com.bit2015.mysite3.service.UserService;
import com.bit2015.mysite3.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/joinform")
	public String joinform() {
		return "/user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}

	@RequestMapping("/loginform")
	public String loginform() {
		return "/user/loginform";
	}

	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("vo", vo);
		return "/user/modifyform";
	}

	@Auth
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		vo.setNo(authUser.getNo());
		userService.updateInfo(vo);
		return "redirect:/user/modifyform";
	}
}
