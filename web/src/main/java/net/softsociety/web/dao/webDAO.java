package net.softsociety.web.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.web.domain.Member;

@Mapper
public interface webDAO {

	//조회수 올리기
	public int updateHits(int boardnum);
	
	//현재 조회수
	public int now(int boardnum) ;

	//id 중복체크
	public int idCheck(String memberid);
	
	//가입하기
	public String join(Member member);
}


//DAO는 class가 아닌 인터페이스로 만들것 8ㅅ8