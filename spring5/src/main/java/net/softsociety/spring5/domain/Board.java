package net.softsociety.spring5.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	int boardnum;  		//글번호
	String memberid;    //작성자id
	String title;		//제목
	String contents; 	//내용
	String inputdate;	//작성일
	int hits;		//조회수
	String originalfile;	//첨부파일의 원래 이름
	String savedfile;		//첨부파일이 서버에 저장된 이름

}
