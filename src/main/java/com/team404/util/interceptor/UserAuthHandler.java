package com.team404.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UserVO;

public class UserAuthHandler extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//세션에서 UserVO를 얻는다.
		HttpSession session = request.getSession();//현재의 세션을 얻는다.
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		//userVO가 없다는것은 로그인x
		//리턴에 true가 들어가면 컨트롤러를 그대로 실행, false가 들어가면 컨트롤러를 실행하지 않는다.
		if(userVO==null) {
			response.sendRedirect(request.getContextPath()+"/user/userLogin");
			return false;
		}else {
			return true;
		}		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	//1. HandlerInterceptorAdapter를 상속 받고 prehandler, posthandler메서드를 오버라이딩
	
}
