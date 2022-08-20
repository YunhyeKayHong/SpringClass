package net.softsociety.shoppinglist.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.shoppinglist.dao.ShoppinglistDAO;
import net.softsociety.shoppinglist.vo.Itemlist;
import net.softsociety.shoppinglist.vo.Orderlist;

@Service
@Slf4j
@Transactional
public class ShoppinglistServiceImpl implements ShoppinglistService {
	
	@Autowired
	ShoppinglistDAO shoppinglistDAO;
	
	@Override
	public ArrayList<Itemlist> selectItemlist() {
		ArrayList<Itemlist> ilist = shoppinglistDAO.selectItemlist();
		
		return ilist;
	}

	@Override
	public Itemlist selectOne(int p_num) {
		
		Itemlist detail = shoppinglistDAO.selectOne(p_num);
		
		return detail;
	}


	@Override
	public ArrayList<Orderlist> selectAll() {
		ArrayList<Orderlist> olist = shoppinglistDAO.selectAll();
		
		return olist;
	}

	@Override
	public int deleteOrderlist(int num) {
		int result = shoppinglistDAO.deleteOrderlist(num);
		
		return result;
	}

	@Override
	public int addToCart(Orderlist orderlist) {
		int result = shoppinglistDAO.addToCart(orderlist);
		
		return result;
	}



}
