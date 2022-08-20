package net.softsociety.spring3.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring3.dao.PersonDAO;
import net.softsociety.spring3.service.PersonService;
import net.softsociety.spring3.vo.Person;
import oracle.jdbc.proxy.annotation.GetProxy;

@Slf4j
@Controller
public class PersonController {
	
	@Autowired
	PersonService service;
	
	//어디로 이동하나요 insert
	@GetMapping("/insert")
	public String insert() {
		return "/insertform";
	}
	

	@PostMapping("/insert")
	public String insert(Person person) {
		log.debug("전달된 객체 : {}", person);//실제로 서버에 값을 남기는 것이 아닌 궁금해서 찍는건 debug
				
		int result = service.insertPerson(person); //int형이 리턴
		
		if(result == 0) {
			return "redirect:/insert";
		}
		return "redirect:/select";				
	}
	
		
	@GetMapping("/delete")
	public String delete(String name) {
		log.debug("삭제할 이름 : {}", name);//실제로 서버에 값을 남기는 것이 아닌 궁금해서 찍는건 debug
		//서비스의 삭제 메소드 호출
		int result = service.deletePerson(name);
		
		if(result == 0) {
			log.debug("삭제가 안되었어요..");
		}else {
			log.debug("삭제 성공!");
		}
		
		return "redirect:/select";				
	}
	
	@GetMapping("/select")
	public String select(Model model) {
		ArrayList<Person> list = service.selectPerson();
		log.debug("결과:{}", list);
		
		model.addAttribute("list", list);
		
		model.addAttribute("data1", 123);
		model.addAttribute("data2", "abc");
		
		return "list";				
	}
	
	@GetMapping("/update")
	public String update(String name, Model model) {
		log.debug("수정할 이름 : {}", name);

		//전달받은 이름을 정보 검색하여 Person 객체로 받음
		Person person = service.selectOne(name);
		
		//db에서 셀렉해서 가져와야함
		
		//객체를 모델에 저장
		model.addAttribute("person", person);
		
		//html로 이동(새로운 정보입력하는 창 만들기)				
		return "updateForm";
		
	}
	
	@PostMapping("/update")
	public String update2(String name, Model model, Person person) {
		log.debug("수정할 이름 : {}", name);

		
		
		return "redirect:/select";	
	}
}
