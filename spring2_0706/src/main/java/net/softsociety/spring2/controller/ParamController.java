//메인화면으로 이동
package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.softsociety.spring2.vo.Person;

@Controller
public class ParamController {

	@GetMapping("/param/param1")
	public String param1() {
		return "param1";
	}
	
	@GetMapping("/param/input1")
	public String input1(String name, String phone, String phonenum) {
		System.out.println("이름:" + name);
		System.out.println("통신사:" + phone);
		System.out.println("전화번호:" + phonenum);
		//return "redirect:/";
		return "home";
	}
	
	@GetMapping("/param/param2")
	public String param2() {
		return "param2";
	}
	
	@PostMapping("/param/input2")
	public String input2(String name, String phone, String phonenum) {
		System.out.println("이름:" + name);
		System.out.println("통신사:" + phone);
		System.out.println("전화번호:" + phonenum);
		return "redirect:/";
	}
	
	
	@GetMapping("/param/param3")
	public String param3() {
		return "param3";
	}
	
	@GetMapping("/param/input3")
	public String input3(Person p) {
		System.out.println(p);
		return "redirect:/";
	}
	
	@GetMapping("/param/param4")
	public String input4(
			@RequestParam(name="name", defaultValue="기본값") String name, 
			@RequestParam(name="age", defaultValue="0") int age) {
		System.out.println(name + ", " + age);
		return "redirect:/";
	}
}
