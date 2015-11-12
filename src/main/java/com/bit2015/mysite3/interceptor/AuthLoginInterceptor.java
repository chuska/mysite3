package com.bit2015.mysite3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2015.mysite3.service.UserService;
import com.bit2015.mysite3.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		UserService userService = applicationContext.getBean(UserService.class);
		UserVo authUser = userService.login(vo);
		if (authUser == null) {
			response.sendRedirect("/mysite3/user/loginform?result=error");
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect("/mysite3/");
		return false;
	}

}
