package net.softsociety.spring2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
	@GetMapping("/ss/session1")
	public String session1(HttpSession session) {
		session.setAttribute("id", "abc");
		return "redirect:/";
	}
	
	@GetMapping("/ss/session2")
	public String session2(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}
	
	@GetMapping("/ss/session3")
	public String session3() {
		return "session";
	}
	
	
	@GetMapping("/ss/session4")
	public String session4(HttpSession session) {
		String id = (String) session.getAttribute("id");
		
		if (id != null)
			return "view";
		else
			return "redirect:/";
		
	}

	
	
}
