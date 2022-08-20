package net.softsociety.spring4.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.dao.GuestbookDAO;
import net.softsociety.spring4.vo.Guestbook;

@Service
@Slf4j
@Transactional
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	GuestbookDAO guestbookDAO;
	
	@Override
	public int write(Guestbook guestbook) {
		
		int result = guestbookDAO.write(guestbook);
		
		return result;
	}

	@Override
	public ArrayList<Guestbook> selectGuestbook() {
		
		ArrayList<Guestbook> list = guestbookDAO.selectGuestbook();
		
		return list;
	}

	@Override
	public int deleteGuestbook(Guestbook guestbook) {
		
		int result = guestbookDAO.deleteGuestbook(guestbook);
		
		return result;
	}





}
