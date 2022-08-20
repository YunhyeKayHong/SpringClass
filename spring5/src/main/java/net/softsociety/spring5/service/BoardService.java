package net.softsociety.spring5.service;

import java.util.ArrayList;
import java.util.HashMap;

import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

public interface BoardService {
	/**
	 * 게시글 등록
	 * @param board 저장할 게시글 정보
	 * @return 저장된 게시글 개수
	 */
	public int writeBoard(Board board);

	/**
	 * 게시글 목록 불러오기
	 * @return 게시글 전체 목록(검색어를 포함한)
	 */
	public ArrayList<Board> selectAll(PageNavigator navi, String type, String searchWord);
	
	/**
	 * 게시판 글 1개 불러오기
	 * @param boardnum 글번호
	 * @return 게시글
	 */
	public Board selectOne(int boardnum);
	
	/**
	 * 조회수 1 증가시키기
	 * @param hits
	 * @return hits
	 */
	public int updateHits(int hits);
	
	/**
	 *글 개수 
	 */
	public int count(HashMap<String, String> map);
	
	/**
	 * 페이지 정보 생성
	 * @param pagePerGroup
	 * @param countPerPage
	 * @param page
	 * @param type
	 * @param searchWord
	 * @return
	 */

	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);
	

	/**
	 * 글 삭제
	 * @param board
	 * @return
	 */
	public int delete(Board board);

	/**
	 * 글 수정
	 * @param board
	 * @return
	 */
	public int updateBoard(Board board);


	/**
	 * 댓글 작성
	 * @param 작성한 댓글 reply
	 * @return 성공 여부
	 */
	public int replyWrite(Reply reply);

	/**
	 * 댓글 불러오기
	 * @param 해당 게시글 번호 boardnum
	 * @return 댓글 내용
	 */
	public ArrayList<Reply> replyAll(int boardnum);

	/**
	 * 댓글 삭제하기
	 * @param 해당 댓글 번호replynum
	 * @return 성공 여부
	 */
	public int replyDelete(int replynum);
	
	/**
	 * 댓글 1개 조회하기
	 * @param 해당 댓글 번호 replynum
	 * @return 댓글 객체(내용, 아이디, 시간)
	 */
	public Reply selectReply(int replynum);
	



	
	
}
