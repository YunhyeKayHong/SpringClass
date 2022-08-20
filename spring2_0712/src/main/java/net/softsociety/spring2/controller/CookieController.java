//쿠키사용
package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
	//쿠키 저장
	@GetMapping("/ck/cookie1")
	public String cookie1(HttpServletResponse response) {
		//쿠키 생성 (이름=값)
		Cookie c1 = new Cookie("str", "abcde");
		Cookie c2 = new Cookie("num", "123");
		//쿠키 종료 시점 지정
		c1.setMaxAge(60*60*24*3);
		c2.setMaxAge(60*60*24*3);
		//클라이언트로 쿠키 보내서 저장
		response.addCookie(c1);
		response.addCookie(c2);
		
		System.out.println("쿠키 저장");
		return "redirect:/";
	}
	//쿠키 삭제
	@GetMapping("/ck/cookie2")
	public String cookie2(HttpServletResponse response) {
		//이미 저장된 쿠키와 같은 이름을 가진 쿠키를 생성
		Cookie c1 = new Cookie("str", null);
		Cookie c2 = new Cookie("num", null);
		//쿠키의 수명을 0초로 지정
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		//쿠키를 클라이언트로 보냄
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "redirect:/";
	}
	//쿠키 읽기
	@GetMapping("/ck/cookie3")
	public String cookie3(
			@CookieValue(name="str", defaultValue="없음") String str,
			@CookieValue(name="num", defaultValue="0") int num) {
		
		System.out.println("쿠키값1:" + str);
		System.out.println("쿠키값2:" + num);
		return "redirect:/";
	}
}
