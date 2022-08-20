package net.softsociety.calc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController {
	//calc.html로 이동
	@GetMapping("/ex/calc")
	public String calc() {
		return "exview/calc";
		
	}
	//calc.html에서 전송받은 parameter
	@PostMapping("/ex/calc")
	public String result(String num1, String num2, String calc, Model model) {
		int result=0;
		
	try {	
		int n1 = Integer.parseInt(num1);
		int n2 = Integer.parseInt(num2);
		
		switch(calc) {
		case "+" : 	result = n1+n2;	break;
		case "-" :	result = n1-n2;	break;
		case "*" :	result = n1*n2;	break;
		case "/" :	result = n1/n2;	break;
			default : throw new Exception("연산자 오류"); 
			//없는 예외를 만들어냄, 강제로 만들어서 던짐. 사실 select로 받은거라 입력오류는 없겠지만 자바의 문법상 미리 준비된 예외들을 처리하기 위함
		}
		
		//저장시킬것들 모델.어트리뷰스
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("calc", calc);
		model.addAttribute("result", result);
	}
	catch(Exception e) {
		e.printStackTrace();
		return "redirect:/ex/calc"; //예외 발생시 계산 폼으로 다시 이동
	}
		return "exview/view";
		
	}
	
	

}
