package com.sbs.untactTeacher.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.untactTeacher.dto.Rq;
import com.sbs.untactTeacher.util.Util;

@Component
public class NeedToLoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(rq.isNotLogined()) {
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().append(Util.msgAndBack("로그인 후 이용해주세요."));
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
