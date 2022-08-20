//메인화면으로 이동하는 콘트롤러
package net.softsociety.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping({"","/"})
	public String index() {
		return "home";
		
	}

}
