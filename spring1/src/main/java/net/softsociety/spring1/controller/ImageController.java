package net.softsociety.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {
	@GetMapping("/image")
	public String image() {
		return "image";
	}
	
	@GetMapping("test/image2")
	public String image2() {
		return "image2";
	}
	
	@GetMapping("/css")
	public String css() {
		return "css";
	}
	
	@GetMapping("/js")
	public String js() {
		return "js";
	}

}
