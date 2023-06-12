package utopia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utopia.dto.LoginDto;
import utopia.dto.MemberDto;
import utopia.dto.PerfumeDto;
import utopia.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	// 로그인 화면에 대한 요청
	@GetMapping("/login.do")
	public String login(HttpSession session) throws Exception {
		if (session.getAttribute("memberEmail") == null) {
			return "login.html";
		} else {
			return "main.html";
		}
	}

	// 로그인 처리에 대한 요청
	@PostMapping("/login.do")
	public String login(LoginDto loginDto, HttpSession session) throws Exception {

		MemberDto memberDto = loginService.login(loginDto);
		if (memberDto == null) {
			session.setAttribute("message", "일치하는 정보가 존재하지 않습니다.");
			return "redirect:/login.do";
		} else {
			session.setAttribute("member", memberDto);
			return "redirect:/main.do";
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("member");
		session.invalidate();
		return "redirect:/login.do";
	}

	// 회원가입 화면에 대한 요청
	@RequestMapping("/openSignup.do")
	public String openSignup() throws Exception {
		return "signup.html";
	}

	// 회원가입 처리에 대한 요청
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberDto memberDto) throws Exception {
		loginService.memberInsert(memberDto);
		return "redirect:/login.do";
	}
	
	// 메인 화면에 대한 요청
	// 메인 화면에 베스트셀러 출력
	@GetMapping("/main.do")
	public ModelAndView openMain() throws Exception {
		ModelAndView mv = new ModelAndView("/main");
		
		List<PerfumeDto> bestList = loginService.selectPerfumeBest();
		mv.addObject("bestList", bestList);
		return mv;
	}


}