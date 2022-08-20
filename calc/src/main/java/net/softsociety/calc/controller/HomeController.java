//메인화면으로 이동하는 컨트롤러
package net.softsociety.calc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping({"","/"})
	public String index() {
		return "home";
	}

}
