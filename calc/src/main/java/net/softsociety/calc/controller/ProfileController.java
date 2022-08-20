package net.softsociety.calc.controller;


import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
	//Profile.html로 이동
	@GetMapping("/ex/profile")
	public String profile() {
		return "exview/profile";

	}

//Profile.html에서 전송받은 parameter
	@GetMapping("/ex/profile")
	public String input(String name, String number, Model model) {
		//String number = 930829-1234123;
		int age=0;
		
		//현재 연도
		Calendar c = Calendar.getInstance(); //불러오는 날짜 기준으로 불러오기
		int year = c.get(Calendar.YEAR);
		
		//글자수
		if (number.length() != 14) {
			return "redirect:/ex/profile";
		}
		//'-' 문자 확인
		if (number.indexOf('-') != 6) {
			return "redirect:/ex/profile";
		}
		//성별
		String ch = number.substring(7);
		if ( !(ch == "1" && ch == "2" && ch =="3" && ch == "4")) {
			return "redirect:/ex/profile";
		}

		try {
		String y = number.substring(0,2);
		String m = number.substring(2,2);
		String d = number.substring(4, 2);
		
		//성별
		String str = ch =="1" || ch =="3" ? "남자":"여자";
		//나이
		if(ch =="1" || ch =="2") {
			age = year - Integer.parseInt(y) - 1900;
		}else {
			age = year - Integer.parseInt(y) - 2000;
		}
		
		model.addAttribute("name", name);
		model.addAttribute("year", year);
		model.addAttribute("month", m);
		model.addAttribute("day", d);
		model.addAttribute("age", age);
		model.addAttribute("gender", str);
		}catch (Exception e) {
			return "redirect:/ex/profile";
		}
		
		return "exview/view2";
		
	}


}
