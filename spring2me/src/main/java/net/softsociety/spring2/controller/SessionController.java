package net.softsociety.spring2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 세션을 처리하기 위한 콘트롤러 클래스
 */
@Controller
public class SessionController {

	/**
	 * 세션 값 저장
	 * @param session 세션객체(이게 뭘 받는 변수인지 메모)
	 * @return 메인화면으로 리다이렉트(이 메소드의 리턴값은 의미가 무엇이고 어떤게 리턴되는가)
	 */
	@GetMapping("ss/session1")
	public String session1(HttpSession session) {
		session.setAttribute("id", "abc"); //("이름", "무엇이든지 저장가능(객체도!)")
		return "redirect:/"; //Model은 이 타이밍에서 값이 없어지고, Session은 남아있음
	}
	/**
	 * 세션 값 읽기
	 * @return session.html이라는 뷰 화면으로 이동
	 */

	@GetMapping("ss/session2")
	public String session2() {
		return "session"; 
	}

	@GetMapping("ss/session3")
	public String session3(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/"; 
	}

	@GetMapping("ss/session4")
	public String session4(HttpSession session) {
		String id = (String)session.getAttribute("id");		
		if(id==null) {
			return"redirect:/";
		}else {
			return "session2";
		}
	}

}
