package com.bit2015.mysite3.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2015.mysite3.annotation.Auth;
import com.bit2015.mysite3.annotation.AuthUser;
import com.bit2015.mysite3.service.BoardService;
import com.bit2015.mysite3.vo.BoardVo;
import com.bit2015.mysite3.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping("")
	public String list(
			Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "kw", required = true, defaultValue = "") String keyWord) {
		Map<String, Object> map = boardService.getList(page, keyWord);
		model.addAttribute("listData", map);
		return "/board/list";
	}

	// 글쓰기 폼 요청
	@Auth
	@RequestMapping("/writeform")
	public String writeForm() {
		return "/board/writeform";
	}

	// 글(새글/답글) 등록 요청
	@Auth
	@RequestMapping({ "/write", "/reply" })
	public String write(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo) {
		vo.setMemberNo(authUser.getNo());
		boardService.insert(vo);
		return "redirect:/board";
	}

	// 글(새글/답글) 삭제 요청
	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, @AuthUser UserVo authUser) {
		boardService.delete(no, authUser.getNo());
		return "redirect:/board";
	}

	// 글(새글/답글) 보기 요청
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo", vo);
		return "/board/view";
	}

	// 글(새글/답글) 수정폼 요청
	@Auth
	@RequestMapping("/modifyform/{no}")
	public String modifyForm(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/board/modifyform";
	}

	// 글(새글/답글) 수정 요청
	@Auth
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo, @AuthUser UserVo authUser) {
		vo.setMemberNo(authUser.getNo());
		boardService.update(vo);
		return "redirect:/board";
	}

	// 답글달기 폼 요청
	@RequestMapping("/replyform/{no}")
	public String replyForm(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.get(no);
		model.addAttribute("vo", vo);
		return "/board/replyform";
	}
}
