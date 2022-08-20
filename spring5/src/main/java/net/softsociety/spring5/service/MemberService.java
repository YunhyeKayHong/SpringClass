package net.softsociety.spring5.service;

import net.softsociety.spring5.domain.Member;

public interface MemberService {
	/**
	 * 회원가입
	 * @param member 저장할 회원정보
	 * @return 저장된 행 개수
	 **/
	public int signup(Member member);

	public boolean idcheck(String searchId);
	
	/**
	 * 전달받은 ID로 회원정보를 조회
	 * @param id를 조회할 ID
	 * @return 해당 id로 조회 된 회원정보
	 **/
	public Member getMemberInfo(String id);

	/**
	 * 수정된 회원정보를 전달
	 * @param 수정된 정보를 담은 member객체
	 * @return 성공1, 실패0
	 **/
	public int update(Member member);

}
