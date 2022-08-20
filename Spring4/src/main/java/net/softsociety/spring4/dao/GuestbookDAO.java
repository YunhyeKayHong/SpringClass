package net.softsociety.spring4.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring4.vo.Guestbook;

@Mapper
public interface GuestbookDAO {
	//저장
	int write(Guestbook guestbook);
	//전체출력
	ArrayList<Guestbook>selectGuestbook();
	//삭제
	int deleteGuestbook(Guestbook guestbook);
	
}
