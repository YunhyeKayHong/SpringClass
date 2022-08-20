package net.softsociety.shoppinglist.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.shoppinglist.vo.Itemlist;
import net.softsociety.shoppinglist.vo.Orderlist;

@Mapper
public interface ShoppinglistDAO {
	//아이템리스트 출력
	ArrayList<Itemlist> selectItemlist();
	//상세보기
	Itemlist selectOne(int p_num);
	//장바구니에 담기 버튼
	int addToCart(Orderlist orderlist);
	//장바구니 정보 출력
	ArrayList<Orderlist> selectAll();
	//장바구니에서 한 개 항목 삭제
	int deleteOrderlist(int num);

}
