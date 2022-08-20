package net.softsociety.spring5.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.service.BoardService;
import net.softsociety.spring5.util.FileService;
import net.softsociety.spring5.util.PageNavigator;

@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	//게시판 목록의 페이지당 글 수
	@Value("${user.board.page}")
	int countPerPage;
	
	//게시판 목록의 페이지 이동 링크 수 
	@Value("${user.board.group}")
	int pagePerGroup;
	
	//첨부파일 저장할 경로
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	//글 목록으로 이동
	@GetMapping("list")
	public String list(Model model
			, @RequestParam(name = "page", defaultValue="1")int page
			, String type
			, String searchWord) {
		
		log.debug("페이지당 글 수 : {}, 페이지 이동 링크 수 : {}, 현재 페이지 : {}, 검색 대상 : {}, 검색어 : {}"
				, countPerPage, pagePerGroup, page, type, searchWord);
		
		//페이지 정보 생성
		PageNavigator navi = service.getPageNavigator(
				 pagePerGroup, countPerPage, page, type, searchWord);
		ArrayList<Board> blist = service.selectAll(navi, type, searchWord);
		

		model.addAttribute("navi", navi);
		model.addAttribute("blist", blist);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		
		return "/boardView/list";
	}
	
	//글쓰기 폼으로 이동
	@GetMapping("write")
	public String write() {
		return "boardView/writeForm";
	}
	
	//글 저장 : 입력받은 글내용 DB에 전송하기
	@PostMapping("write")
	public String write(
			Board board
			, @AuthenticationPrincipal UserDetails user
			, MultipartFile upload
			) {		
		
		//로그인되어있는 아이디정보 가져오기, 확인
		String id = user.getUsername();
		log.debug("해당 아이디가 맞나요 : {}", id);
		
		//게시글에 로그인한 아이디 넣어주기
		board.setMemberid(id);
		
		//전달된 게시글 제목, 내용 출력
		log.debug("입력된 게시글 내용1 :{}", upload.getOriginalFilename());
		
		if(upload != null && !upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			board.setOriginalfile(upload.getOriginalFilename());
			board.setSavedfile(savedfile); //리턴받은 객체
		}
		
		//전달된 게시글 제목, 내용 출력
		log.debug("입력된 게시글 내용2 :{}", board);
		
		
		//DB로 보내기
		int result = service.writeBoard(board);
		
		//1이 오면 입력완료
		if(result == 1) {
			System.out.println("입력완료!!");
		}else {
			System.out.println("입력실패!");
		}
		
		return "redirect:/board/list";				
	}
	
	//글 읽기
	@GetMapping("read")
	public String read(
		@RequestParam(name = "boardnum", defaultValue="0") int boardnum, Model model) {
		log.debug("VIEW에서 넘어온 글번호 : {}", boardnum);
		
		//DB에서 글을 읽어서
		Board board = service.selectOne(boardnum);
		log.debug("DB에서 넘어온 게시글 정보 : {} ", board);
	
		int newHits = service.updateHits(boardnum);
		log.debug("갱신된 조회수 : {}", newHits);
		
		if(boardnum==0) {
			return "redirect:/board/list"; //글이 없으면 목록으로
		}
		//해당글의 리플 목록을 읽어서 모델에 저장
		ArrayList<Reply> replylist = service.replyAll(boardnum);
				
		board.setHits(newHits);
		model.addAttribute("board", board);
		model.addAttribute("replylist", replylist);
		
		
		//결과를 모델에 담아서 HTML에서 출력
		return "/boardView/read";
	}	
	
	//첨부파일 다운로드
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String fileDownload(int boardnum, Model model, HttpServletResponse response) {
		Board board = service.selectOne(boardnum);
		
		//원래의 파일명
		String originalfile = new String(board.getOriginalfile());
		try { 
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로
		String fullPath = uploadPath + "/" + board.getSavedfile();
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸 이용하여 출력
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}	
	
	//글 삭제	
	@GetMapping("delete")
	public String delete(int boardnum
			, @AuthenticationPrincipal UserDetails user
			, MultipartFile upload) {
		
		//해당 번호의 글 정보 조회
		Board board = service.selectOne(boardnum);
		
		//첨부파일 이름 확인
		String savedfile = board.getSavedfile();
		
		//지금 로그인한 아이디 확인 -> boardnum 1, 작성자 1 인데 여기서 로그인 한 사람이 2라면 getset했을때 mapper에서 1!=2라고 뜨기때문에 삭제 불가. 컨트롤러에서 굳이 막지 않아도 됨.
		String id = user.getUsername(); 
		
		if(board.getMemberid() != id) {
			System.out.println("로그인 한 아이디와 글을 작성한 아이디가 다릅니다.");
		}
		board.setMemberid(id);
		
		//글 삭제(글 번호와 아이디 전달)
		int result = service.delete(board);
		
		//글 삭제 성공 여부 and 첨부파일이 있는 경우 파일도 삭제
		if(result == 1 && savedfile  != null) {
			FileService.deleteFile(
					uploadPath + "/" + savedfile);
		
		
	}
	
		return "redirect:/board/list";

	}
	
	//수정폼으로 이동
	@GetMapping("update")
	public String update(int boardnum, Model model) {
		//a태그로 링크걸어서 여기로 글 번호 보냄, 개인정보수정눌렀을때랑 비슷
		Board board = service.selectOne(boardnum);
		//전달받은 번호의 글 정보를 가지고 HTML로
		model.addAttribute("board", board);
		
		return "/boardView/updateForm";
	}
	
	//수정 처리 : Post로 해야함 get방식이면 파일을 못받음, 그 내용도 길게는 못받음
	@PostMapping("update")
	public String update(
			Board board
			, MultipartFile upload
			, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("수정된 게시글 내용 :{}", board);
		
		//첨부파일 처리 원래파일이 있던 없던 새로운 파일이 안올라오면 신경안써도됨
		if(upload != null && !upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			board.setOriginalfile(upload.getOriginalFilename());
			board.setSavedfile(savedfile); //리턴받은 객체
		}
		//본인글인지 확인
		String id = user.getUsername(); 
		if(board.getMemberid() != id) {
			System.out.println("로그인 한 아이디와 글을 작성한 아이디가 다릅니다.");
		}
		
		//DB로 보내기
		int result = service.updateBoard(board);
		
		//1이 오면 입력완료
		if(result == 1) {
			System.out.println("수정완료!!");
		}else {
			System.out.println("수정실패!");
		}
		
		//읽던 본인 글로 이동, 글을 읽을때보내는 경로와 같음 보드넘은 몇번
		return "redirect:/board/read?boardnum=" + board.getBoardnum();
	}
	
	//댓글 작성
	@PostMapping("replyWrite")
	public String replyWrite(
			int boardnum
			, String replytext
			, Board board
			, Reply reply
			, @AuthenticationPrincipal UserDetails user
			) {
		
		//본인글인지 확인
		reply.setBoardnum(boardnum);
		reply.setReplytext(replytext);
		reply.setMemberid(user.getUsername());
		
		//DB로 보내기
		int result = service.replyWrite(reply);
		
		//1이 오면 입력완료
		if(result == 1) {
			System.out.println("입력완료!!");
		}else {
			System.out.println("입력실패!");
		}
		
		return "redirect:/board/read?boardnum=" + board.getBoardnum();		
	}
	
	//댓글 삭제
	@GetMapping("deleteReply")
	public String deleteReply(
			int replynum
			, @AuthenticationPrincipal UserDetails user
			) {
		
		log.debug("넘어온 댓글 글번호", replynum);
		
		Reply reply = service.selectReply(replynum);
		//본인글인지 확인
		String id = user.getUsername(); 
		
		if(reply.getMemberid() != id) {
			System.out.println("로그인 한 아이디와 글을 작성한 아이디가 다릅니다.");
		}
		reply.setMemberid(id);
		
		//DB로 보내기
		int result = service.replyDelete(replynum);
		
		//1이 오면 입력완료
		if(result == 1) {
			System.out.println("삭제완료!!");
		}else {
			System.out.println("삭제실패!");
		}
		
		return "redirect:/board/read?boardnum=" + reply.getBoardnum();
		
	}
	
	
	
	
	
	
	
	
	
}
