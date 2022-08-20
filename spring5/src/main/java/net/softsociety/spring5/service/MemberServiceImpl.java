package net.softsociety.spring5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.dao.MemberDAO;
import net.softsociety.spring5.domain.Member;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int signup(Member member) {
		String encodePassword = passwordEncoder.encode(member.getMemberpw()); //encode 암호화하는 메소드
		member.setMemberpw(encodePassword);
		
		int result = memberDAO.signup(member);		
		return result;
	}

	@Override
	public boolean idcheck(String searchId) {
		boolean result = false;
		//전달받은 ID로 회원정보 검색. 사용중인 회원이 있으면 사용불가.
		Member member = memberDAO.selectOne(searchId);
		result = member == null? true : false;
		
		return result;
	}

	@Override
	public Member getMemberInfo(String id) {
		return memberDAO.selectOne(id);
	}

	@Override
	public int update(Member member) {
		String encodePassword = passwordEncoder.encode(member.getMemberpw()); //encode 암호화하는 메소드
		member.setMemberpw(encodePassword);
		
		int result = memberDAO.update(member);
		return result;
	}

}
