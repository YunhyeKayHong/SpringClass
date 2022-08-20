package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.softsociety.spring2.vo.Person;

/**
 * Model 사용 테스트를 위한 콘트롤러 클래스
 * @author 22.07.05 박수연
 *
 */
@Controller
public class ModelController {

	/**
	 * Model을 사용하기 위한 테스트 기능
	 * @param model Model객체
	 * @return 포워딩할 HTML파일명
	 */
	@GetMapping("/model/model1")
	public String model1(Model model) {
		String str = "서버의 문자열";
		int num = 100;
		Person p = new Person("김","KT","1111");
		
		model.addAttribute("string", str);
		model.addAttribute("number", num);
		model.addAttribute("person", p);
		
		return "model1";
	}

}
