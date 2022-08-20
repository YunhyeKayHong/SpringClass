package net.softsociety.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.dao.webDAO;
import net.softsociety.web.domain.Member;
import net.softsociety.web.domain.Person;

@Slf4j
@RequestMapping("aj")
@Controller
public class AjexsController {
	
	@Autowired
	webDAO dao;
	
	@GetMapping("aj1")
	public String aj1() {
		return "/ajView/aj1";
	}
	
	@ResponseBody
	@GetMapping("test1")
	public void test1() {
		log.debug("Controller의 test1() 실행");
	}
	
	@ResponseBody
	@PostMapping("test2")
	public void test2(String str) {
		log.debug("Controller의 test2() 실행");
	}
	
	@ResponseBody
	@GetMapping("test3")
	public String test3() { //리턴값은 상황에 맞게 맞추기
		String s = "서버에서 보낸 문자열";
		return s;
	}
	
	@ResponseBody
	@PostMapping("test4")
	public String test4(String str) {
		str = str.toUpperCase();
		return str;
	}
	
	@ResponseBody
	@PostMapping("test5")
	public String test5(String str) {
		str = str.toUpperCase();
		return str;
	}
	
	@GetMapping("aj2")
	public String aj2() {
		return "/ajView/aj2";
	}
	
	@ResponseBody
	@PostMapping("insert1")
	public void insert1(String name, int age, String phone) {
		log.debug("name:{}, age:{}, phone:{}", name, age, phone);
	}
	
	@ResponseBody
	@PostMapping("insert2")
	public void insert2(Person person) {
		log.debug("Person객체 {}", person);
		
	}
	
	@ResponseBody
	@PostMapping("insert3")
	public void insert3(Person person) {
		log.debug("Person객체 {}", person);
		
	}
	
	@ResponseBody
	@GetMapping("getObject1")
	public Person getObject1() { 
		Person p = new Person("홍길동", 30, "010-1111-1111");
		return p;
	}
	
	@ResponseBody
	@GetMapping("getObject2")
	public Person getObject2() { 
		Person p = new Person("홍길동", 30, "010-1111-1111");
		return p;
	}
	
	@ResponseBody
	@GetMapping("getList")
	public ArrayList<Person> getList() {
		
		ArrayList<Person> list = new ArrayList<>();
		
		list.add(new Person("콩이", 10, "010-1234-1234"));
		list.add(new Person("뭉이", 6, "010-1234-1234"));
		list.add(new Person("뉴뉴", 2, "010-1234-1234"));
		list.add(new Person("아롱", 13, "010-1234-1234"));
		
		return list;
	}
	
	//추천 기능 페이지로 이동
	@GetMapping("recommend")
	public String recommend() {
		return "/ajView/recommend";
	}
	
	//추천 Ajax 요청 처리
	@ResponseBody
	@GetMapping("vote")
	public int vote(int boardnum) {
		//Autowired빼먹지않기
		int cnt = 0;
		//해당 글번호의 추천수를 1 증가
		dao.updateHits(boardnum);
		log.debug("boardnum", boardnum);
		//현재 추천수 읽어서 리턴
		cnt = dao.now(boardnum);
		log.debug("cnt", cnt);
		return cnt;
	}
	
	//경로설정 : 아이디 체크 화면
	@GetMapping("idCheck")
	public String IdCheck() {
		return "/ajView/idCheck";
	}
	
	//가입
	@ResponseBody
	@PostMapping("join")
	public String join(Member member) {
		log.debug("가입정보 : {}", member);
		dao.join(member);
		
		return "redirect:/";	
	}
	
	
	//ID 중복 체크
	@ResponseBody
	@PostMapping("IdCheck")
	public int IdCheck(String memberid) {
		int res = 0;
		res = dao.idCheck(memberid);
		
		return res;
		
	}
		
		
}
