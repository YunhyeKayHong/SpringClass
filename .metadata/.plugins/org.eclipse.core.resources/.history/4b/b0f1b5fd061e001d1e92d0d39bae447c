package net.scit.bluemarble.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.scit.bluemarble.service.bluemarbleService;
import net.scit.bluemarble.vo.Player;

@Controller
@Slf4j
public class boardController {
	@Autowired
	private bluemarbleService service;
	
	
	@GetMapping("/board")
	public String map(Model model) {
		//처음 시작시, 전체 유저 정보를 가져온다
		ArrayList<Player> player = service.selectAllPlayer();
		log.debug("가져온 값 : {}", player);
		
		model.addAttribute("player", player);
		return "board";
	}
}
