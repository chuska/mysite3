package com.bit2015.mysite3.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bit2015.mysite3.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		// TODO Auto-generated method stub
		if (supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		HttpServletRequest request = webRequest
				.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		return authUser;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		boolean result = parameter.getParameterAnnotation(AuthUser.class) != null
				&& parameter.getParameterType().equals(UserVo.class);
		return result;
	}

}
