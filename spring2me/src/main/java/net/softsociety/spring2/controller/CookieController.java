package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
	//쿠키 저장
	@GetMapping("ck/cookie1")
	public String cookie1(HttpServletResponse response) {
		/* 쿠키 생성 (이름 = 값) */
		Cookie c1 = new Cookie("str", "happy");
		Cookie c2 = new Cookie("num", "0829");
		
		/* 수명 정하기*/
		c1.setMaxAge(60*60*24*3); //60초 * 60분 * 24시간 * 3일 = 3일의 초시간
		c2.setMaxAge(60*60*24*3);
		
		/* 클라이언트로 쿠키 보내서 저장 */
		response.addCookie(c1);
		response.addCookie(c2);
		
		System.out.println(" ** 쿠키 저장이 완료되었습니다. ** "); //코드 확인용
		
		return "redirect:/"; 
	}

	//쿠키 삭제
	@GetMapping("ck/cookie2")
	public String cookie2(HttpServletResponse response) {
		/* 이미 저장된 쿠키와 같은 이름을 가진 쿠키를 생성 */
		Cookie c1 = new Cookie("str", "null");
		Cookie c2 = new Cookie("num", "null");
		
		/* 쿠키의 수명을 0초로 지정 */
		c1.setMaxAge(0); 
		c2.setMaxAge(0);
		
		/* 덮어쓰면서 값을 지우기 */
		response.addCookie(c1);
		response.addCookie(c2);
		return "redirect:/"; 
	}

		//쿠키 읽기
	@GetMapping("ck/cookie3")
	public String cookie3(
			@CookieValue(name = "str", defaultValue = "없음") String str,
			@CookieValue(name = "num", defaultValue="0") int num
			) {
			
		System.out.println("쿠키값 1 : " + str);
		System.out.println("쿠키값 2 : " + num);
				
		return "redirect:/"; 
	}
	
		//방문횟수 카운트
	@GetMapping("/ex/cookie")
	public String cookie(
			/* 방문횟수가 저장된 쿠키를 읽어온다 */
			/* 없으면 방문횟수는 현재 0으로 처리 */
			/* 있으면 쿠키에 저장된 숫자가 기존 방문횟수 */
			@CookieValue(name = "count", defaultValue = "0") int count,
			HttpServletResponse response,
			Model model
			) {
		
		/* 방문횟수에 1을 더한다 */
		count++;
		/* 쿠키에 증가된 방문횟수를 저장하여 클라이언트로 보낸다 */
		model.addAttribute("count", count);
		
		/* 방문횟수를 Model에 저장하여 html페이지에서 환영 문구 출력 */
		Cookie cookie = new Cookie ("count", Integer.toString(count)); //저장하고 싶은 값을  String으로 만들어야함
		cookie.setMaxAge(60*60*24*3);
		response.addCookie(cookie);
		
		
		return "cookie";
	}

}
