package com.bit2015.mysite3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2015.mysite3.annotation.Auth;
import com.bit2015.mysite3.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
		if (auth == null) {
			return true;
		}

		HttpSession session = request.getSession();
		if (session == null) {// 로그인 안함
			response.sendRedirect("/mysite3/user/loginform");
			return false;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {// 로그인 안함
			response.sendRedirect("/mysite3/user/loginform");
			return false;
		}
		return true;
	}

}
