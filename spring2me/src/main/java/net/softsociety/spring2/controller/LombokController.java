package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring2.vo.Member;

@Slf4j
@Controller
public class LombokController {
	@GetMapping("/log/lombok")
	public String lombok() {
		Member m = new Member();
		
		return "redirect:/";
	}
	
	@GetMapping("/log/logger")
	public String logger() {
		String s = "변수내용";
		
		log.error("error()로 출력" + s);
		log.warn("warn()로 출력 {}{}", s, 100);
		log.info("info()로 출력");
		log.debug("debug()로 출력");
		log.trace("trace()로 출력");
		
		return "redirect:/";
	}

}
