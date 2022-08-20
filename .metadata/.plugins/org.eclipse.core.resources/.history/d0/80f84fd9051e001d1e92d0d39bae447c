package net.scit.bluemarble.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.scit.bluemarble.dao.bluemarbleDAO;
import net.scit.bluemarble.vo.Player;

@Service
public class bluemarbleServiceImpl implements bluemarbleService {
	@Autowired
	private bluemarbleDAO dao;  
	
	@Override
	public Player selectOnePlayer(int playerid) {
		Player player = dao.selectOnePlayer(playerid);
		return player;
	}

	@Override
	public ArrayList<Player> selectAllPlayer() {
		ArrayList<Player> list = dao.selectAllPlayer();
		return list;
	}

}
