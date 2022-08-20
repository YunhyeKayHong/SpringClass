package net.softsociety.spring4.service;

import java.util.ArrayList;

import net.softsociety.spring4.vo.Guestbook;

public interface GuestbookService {
	//저장
	public int write(Guestbook guestbook);
	//전체출력
	public ArrayList<Guestbook> selectGuestbook();
	//삭제
	public int deleteGuestbook(Guestbook guestbook);
	

}
