package net.softsociety.spring5.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.dao.BoardDAO;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public int writeBoard(Board board) {
	int result = boardDAO.writeBoard(board);
		return result;
	}

	@Override
	public ArrayList<Board> selectAll(PageNavigator navi,String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type); //xml 에서 꺼낼 때 사용할 이름(VO의 변수명 같은 역할)
		map.put("searchWord", searchWord);
		
		RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage()); //숫자를 담아서 몇개만 보냄(가장 위에서부터 10개 => 0~10개)
		ArrayList<Board> blist= boardDAO.selectAll(map, rb);		
		return blist;
	}
	
	@Override
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
			String searchWord) {
		//전체 글 개수
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type); 
		map.put("searchWord", searchWord);
		int total = boardDAO.count(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
		return navi;
	}

	@Override
	public Board selectOne(int boardnum) {
		Board board = boardDAO.selectOne(boardnum);
		return board;
	}

	@Override
	public int updateHits(int hits) {
		int newHits = boardDAO.updateHits(hits);
		return newHits;
	}

	@Override
	public int count(HashMap<String, String> map) {
		int result = boardDAO.count(map);
		return result;
	}

	@Override
	public int delete(Board board) {
		int result = boardDAO.delete(board);
		
		return result;
	}

	@Override
	public int updateBoard(Board board) {
		int result = boardDAO.updateBaord(board);
		return result;
	}

	@Override
	public int replyWrite(Reply reply) {

		int result = boardDAO.replyWrite(reply);
		return result;
	}

	@Override
	public ArrayList<Reply> replyAll(int boardnum) {
		ArrayList<Reply> rlist= boardDAO.replyAll(boardnum);		
		
		return rlist;
	}

	@Override
	public int replyDelete(int replynum) {
		int result = boardDAO.replyDelete(replynum);
		return result;
	}

	@Override
	public Reply selectReply(int replynum) {
		Reply reply = boardDAO.selectReply(replynum);
		return reply;
	}


	

}
