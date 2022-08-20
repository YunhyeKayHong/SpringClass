package net.softsociety.shoppinglist.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.shoppinglist.service.ShoppinglistService;
import net.softsociety.shoppinglist.vo.Itemlist;
import net.softsociety.shoppinglist.vo.Orderlist;

@Controller
@Slf4j
public class ShoppinglistController {
	
	@Autowired
	ShoppinglistService service;
	
	@GetMapping("/wishlist")
	public String goWishlist(Orderlist orderlist, Model model) {
		ArrayList<Orderlist> olist = service.selectAll();
		model.addAttribute("orderlist", olist);
		
		return "wishlist";
	}
	
	@GetMapping("/coupang")
	public String selectItemlist(Model model) {
		ArrayList<Itemlist> ilist = service.selectItemlist();
		log.debug("결과:{}", ilist);
		
		model.addAttribute("ilist", ilist);
		
		return "coupang";
		
	}
	@GetMapping("/selectOne")
	public String selectOne(int p_num, Model model) {
		Itemlist detail = service.selectOne(p_num); 	
		
		
		model.addAttribute("detail", detail);
		
		return "detail";	
		
	}
	
	@PostMapping("/addToCart")
	public String update(Orderlist orderlist, Model model) {
		log.debug("전달된 넘버 : {}", orderlist);
//		//받아온 p_num으로 제품정보를 찾는다.
//		Itemlist temp = service.selectOne(p_num); 
//		log.debug("temp",temp);
//		//찾은 제품 정보 + 유저 정보를 사용하여 Orderlist vo를 생성한다.
//		Orderlist list = new Orderlist(0, "shopping123", "홍길동", "서울시 용산구", temp.getP_name(), temp.getP_amount(), temp.getP_price(), temp.getP_num()); 
		//service와 dao를 이용해 Orderlist vo를 Oderlist 테이블에 저장한다.
//		log.debug("값",list);
		
//		log.debug("전달된 객체 : {}", olist);
		
		orderlist.setId("shopping123");
		orderlist.setName("홍길동");
		orderlist.setAddress("인천시 부평구");
		
		int result = service.addToCart(orderlist);	

		model.addAttribute("orderlist", orderlist);
		
		return "home";
		
	}
	@PostMapping("/delete")
	public String delete(int num) {
		log.debug("전달 된 숫자 : {}",num);
		int result = service.deleteOrderlist(num);
		
		if(result == 0) {
			log.debug("삭제 실패!");
			return "wishlist";
		}
		return "redirect:/wishlist";
	
	
}
	

}
