package net.softsociety.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.web.dao.commentDAO;
import net.softsociety.web.domain.Comment;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("aj")
public class CommentRestController {

	@Autowired
	commentDAO dao;
	
	//댓글 저장
	@PostMapping("insert")
	public void insert(Comment comment){
		log.debug("comment:{}", comment);
		dao.insertComment(comment);
	}
	
	//댓글 읽어오기
	@GetMapping("getComment")
	public ArrayList<Comment> getComment(){
		
		ArrayList<Comment> list = dao.selectAll();
		
		return list;
	}
	
	//댓글 삭제하기
	@PostMapping("commentDel")
	public void commentDel(int num) {
		log.debug("몇번 댓글을 지울거니 : {}", num);
		dao.deleteComment(num);
	
	}
	
}
