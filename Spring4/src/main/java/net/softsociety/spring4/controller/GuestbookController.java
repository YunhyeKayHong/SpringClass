package net.softsociety.spring4.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.service.GuestbookService;
import net.softsociety.spring4.vo.Guestbook;

@Slf4j
@Controller
public class GuestbookController {

	@Autowired
	GuestbookService service;
	
	@GetMapping("/write")
	public String write() {
		return "/write";
	}
	
	@PostMapping("/write")
	public String insert(Guestbook guestbook) {
		log.debug("전달된 객체 : {}", guestbook);//실제로 서버에 값을 남기는 것이 아닌 궁금해서 찍는건 debug
				
		int result = service.write(guestbook); //int형이 리턴
		
		return "redirect:/";				
	}
	
	@GetMapping("/list")
	public String select(Model model) {
		ArrayList<Guestbook> list = service.selectGuestbook();
		log.debug("결과:{}", list);
		
		model.addAttribute("list", list);
			
		return "list";				
	}
	@PostMapping("/delete")
		public String delete(Guestbook guestbook) {
			int result = service.deleteGuestbook(guestbook);
			
			if(result == 0) {
				log.debug("삭제 실패!");
				return "/list";
			}
			return "redirect:/list";
		
		
	}
	
	/* 전체 목록보기 2*/
	@GetMapping("/list2")
	public String list2(Model model) {
		ArrayList<Guestbook> list = service.selectGuestbook();
		log.debug("결과:{}", list);
		
		model.addAttribute("list", list);
			
		return "list2";				
	}
	
}
