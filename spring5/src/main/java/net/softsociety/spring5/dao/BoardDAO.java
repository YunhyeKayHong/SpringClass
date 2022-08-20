package net.softsociety.spring5.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;

@Mapper
public interface BoardDAO {
	//게시글 등록
	public int writeBoard(Board board);
	
	//글 개수
	public int count(HashMap<String, String> map);
	
	//전체 게시글 목록 불러오기
	public ArrayList<Board> selectAll(HashMap<String, String> map, RowBounds rb);
	
	//게시글 1개 불러오기 
	public Board selectOne(int boardnum);
	
	//조회수 1 증가시키기
	public int updateHits(int hits);

	//글 삭제
	public int delete(Board board);

	//글 수정
	public int updateBaord(Board board);
	
	//댓글 작성
	int replyWrite(Reply reply);

	//댓글 불러오기
	public ArrayList<Reply> replyAll(int boardnum);
	
	//댓글 삭제하기
	public int replyDelete(int replynum);
	
	//댓글 1개 조회하기
	public Reply selectReply(int replynum);
	

}
