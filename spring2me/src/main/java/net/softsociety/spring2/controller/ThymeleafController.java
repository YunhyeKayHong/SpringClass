package net.softsociety.spring2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.softsociety.spring2.vo.Member;

@RequestMapping("/th") //인터넷상의 URL
@Controller
public class ThymeleafController {
	@GetMapping("thymeleaf1")
	public String thymeleaf1(Model model) {
		String str="문자열";
		int num = 100;
		Member member = new Member("aaa", "111", "콩이", "인천시");
		String tag = "<marquee>html태그</marquee>";
		String url = "https://google.com";
		
		model.addAttribute("str", str);
		model.addAttribute("num", num);
		model.addAttribute("member", member);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		
		int n1 = 1000000;
		double n2 = 123.45678;
		Date d = new Date(); //Import할때 java.util쪽에서 가져오기
		
		model.addAttribute("inum", n1); //정수
		model.addAttribute("dnum", n2); //실수
		model.addAttribute("date", d);  //날짜타입
		
		
		return "th/thymeleaf1"; //템플릿 밑에 th라는 폴더를 만들고 그 안에 thymeleaf1이라는 파일을 만들어둠
	}
	
	@GetMapping("thymeleaf2")
	public String thymeleaf2(Model model) {
		String str = "문자열";
		String values = "Java, HTML, CSS";
		ArrayList<String> list = new ArrayList<>();
		list.add("콩이");
		list.add("뭉이");
		list.add("뉴뉴");
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "키보드");
		map.put("price", 10000);
		
		model.addAttribute("str", str);
		model.addAttribute("values", values);
		model.addAttribute("list", list);
		model.addAttribute("map", map);

		
		return "th/thymeleaf2";
	}
	

}
