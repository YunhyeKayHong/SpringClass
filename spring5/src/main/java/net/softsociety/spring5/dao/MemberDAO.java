package net.softsociety.spring5.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring5.domain.Member;

@Mapper
public interface MemberDAO {
	//회원가입
	public int signup(Member member); 
	//아이디체크
	public Member selectOne(String searchId);
	//회원정보 수정
	public int update(Member member); 


}
