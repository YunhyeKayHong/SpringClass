package net.softsociety.spring5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Member;
import net.softsociety.spring5.service.MemberService;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
@RequestMapping("Member")
public class Membercontroller {

	@Autowired
	MemberService service;
	
	
	@GetMapping("/signup")
	public String signup() {		
		return "signup";
	}
	
	@PostMapping("signup")
	public String signup(Member member) {
		log.debug("가입정보 : {}", member);
		int result = service.signup(member);
		
		return "redirect:/";	
	}
	
	@GetMapping("/idcheck")
	public String idcheck() {
		return "/memberView/idForm";
	}
	
	@PostMapping("/idcheck")
	public String idcheck(String searchId, Model model) {
		log.debug("검색할 ID : {}", searchId);
		
		boolean result = service.idcheck(searchId);//전달받은 ID의 사용여부
		
		model.addAttribute("result", result);
		model.addAttribute("searchId", searchId);
		
		log.debug("사용가능여부 : {}", result);
		
		return "/memberView/idForm";
	}
	

	@GetMapping("loginForm")
	public String loginForm() {
		return "/memberView/loginForm";
	}
	
	/**
	 * 개인정보 수정폼으로 이동
	 *	
	 */ 
	@GetMapping("/mypage")//수정하러 가는 단계
	public String mypage(Model model, @AuthenticationPrincipal UserDetails user) { //인증된 아이디의 정보를 주세요 라는 의미
		String id = user.getUsername();
		Member member = service.getMemberInfo(id);
		model.addAttribute("member", member);
		return "/memberView/mypageForm";
	}
	
	/**
	 * 사용자가 입력한 개인정보를 DB에 update
	 * @param member
	 * @return 성공1, 실패0
	 */
	@PostMapping("/mypage")
	public String mypage(Member member, @AuthenticationPrincipal UserDetails user) {
		//전달된 member객체의 값을 출력
		log.debug("수정된 값이 넘어오나요 : {}", member);
		//로그인한 id를 읽어서 member객체에 추가
		String id = user.getUsername();
		log.debug("해당 아이디가 맞나요 : {}", id);
		member.setMemberid(id);
		//member객체를 서비스로 전달하여 DB에 update
		int result = service.update(member);

		//1이 오면 수정완료
		if(result == 1) {
			System.out.println("수정완료!!");
		}else {
			System.out.println("수정실패!");
		}
		
		return "redirect:/";
		
	}
	
	
				
}
