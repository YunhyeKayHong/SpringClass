package net.softsociety.web.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.web.domain.Comment;

@Mapper
public interface commentDAO {
	
	//댓글 저장
	void insertComment(Comment comment);
	
	//댓글 전체 읽기
	ArrayList<Comment> selectAll();

	//댓글 삭제
	void deleteComment(int num);

}
