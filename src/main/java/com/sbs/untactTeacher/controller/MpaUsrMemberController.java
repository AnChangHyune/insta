package com.sbs.untactTeacher.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.untactTeacher.dto.Member;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.service.MemberService;
import com.sbs.untactTeacher.util.Util;



@Controller
public class MpaUsrMemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/mpaUsr/member/login")
	public String showLogin(HttpServletRequest req) {
		return "mpaUsr/member/login";
	}
	
	@RequestMapping("/mpaUsr/member/doLogout")
	public String doLogout(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("loginedMemberId");
		
		String msg = "로그아웃 되었습니다.";
		return Util.msgAndReplace(req, msg, "/");
	}
	
	
	@RequestMapping("/mpaUsr/member/doLogin")
	public String doLogin(HttpServletRequest req, HttpSession session,String loginId, String loginPw, String redirectUrl) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.msgAndBack(req, loginId+"(은)는 사용중 이거나 등록된 회원이 아닙니다.");
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}
		
		session.setAttribute("loginedMemberId", member.getId());
		
		String msg = member.getNickname() + "님 환영합니다.";
		
		return Util.msgAndReplace(req, msg, redirectUrl);
	}
	
	@RequestMapping("/mpaUsr/member/join")
	public String showJoin(HttpServletRequest req) {
		
		return "mpaUsr/member/join";
	}
	
	 @RequestMapping("/mpaUsr/member/doJoin")
	    public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
	        Member oldMember = memberService.getMemberByLoginId(loginId);

	        if (oldMember != null) {
	            return Util.msgAndBack(req, loginId + "(은)는 이미 사용중인 로그인아이디 입니다.");
	        }

	        ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

	        if (joinRd.isFail()) {
	            return Util.msgAndBack(req, joinRd.getMsg());
	        }

	        return Util.msgAndReplace(req, joinRd.getMsg(), "login");
	    }

}
