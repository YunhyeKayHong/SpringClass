package net.softsociety.shoppinglist.service;

import java.util.ArrayList;

import net.softsociety.shoppinglist.vo.Itemlist;
import net.softsociety.shoppinglist.vo.Orderlist;

public interface ShoppinglistService {
	//아이템 출력
	public ArrayList<Itemlist> selectItemlist();
	//상세페이지
	public Itemlist selectOne(int p_num);
	//장바구니에 넣기
	public int addToCart(Orderlist orderlist);
	//장바구니 정보 불러오기
	public ArrayList<Orderlist> selectAll();
	//장바구니 1개 항목 삭제
	public int deleteOrderlist(int num);
	

}
