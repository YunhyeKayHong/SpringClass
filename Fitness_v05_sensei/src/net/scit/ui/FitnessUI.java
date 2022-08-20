package net.scit.ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import net.scit.service.FitnessService;
import net.scit.service.FitnessServiceImpl;
import net.scit.vo.FitnessVO;

public class FitnessUI {
	FitnessService service = new FitnessServiceImpl();
	Scanner scanner = new Scanner(System.in);

	public FitnessUI() {
		String choice;

		while(true) {
			menu();
			choice = scanner.next();

			switch(choice) {
			case "1" : input();     break;
			case "2" : output();    break;
			case "3" : selectOne(); break;
			case "4" : delete();    break;
			case "5" : update();    break;
			case "0" : 
				System.out.println("** 프로그램을 종료합니다.");
				service.setFile();
				return;
			default : System.out.println("err) 메뉴를 다시 선택하세요");
			}
			scanner.nextLine(); 
		}
	}

	public void menu() {
		System.out.println("===== [관리 프로그램] =====");
		System.out.println("        1. 입  력");
		System.out.println("        2. 전체 출력");
		System.out.println("        3. 조  회");
		System.out.println("        4. 삭  제");
		System.out.println("        5. 수  정");
		System.out.println("        0. 종  료");
		System.out.println("------------------------");
		System.out.print  ("       선택> ");
	}

	/**
	 * 아이디 중복확인 체크
	 * true : 중복된 아이디가 있음, false : 사용가능한 id 
	 */
	private boolean idCheck(String usrid) {
		FitnessVO vo = service.findById(usrid);

		if(vo != null) 
			return true;
		return false;
	}

	/**
	 * 회원 가입
	 */
	public void input() {
		String usrid, usrname;
		double height, weight;

		System.out.print("> 아이디 : ");
		usrid = scanner.next();

		if(idCheck(usrid)) {   
			System.out.println("** 중복된 아이디가 존재합니다.");	
			return;
		}

		System.out.print("> 이름 : ");
		usrname = scanner.next();
		System.out.print("> 키(cm) : ");
		height = scanner.nextDouble();		
		System.out.print("> 몸무게 : ");
		weight = scanner.nextDouble();

		FitnessVO vo = new FitnessVO(usrid, usrname, height, weight);

		service.regist(vo);
		System.out.println("** 회원가입이 완료되었습니다.");
	}

	/**
	 * 회원의 전체 정보 출력
	 */
	public void output() {
		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}

		Collections.sort(list);
		list.forEach(x->System.out.println(x));
//		Iterator<FitnessVO> iter = list.iterator();
//
//		while(iter.hasNext())
//			System.out.println(iter.next());
	}

	/** 
	 * 회원 정보 검색
	 */
	public void selectOne() {
		String usrid;

		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}

		System.out.print("> 조회할 회원의 아이디 : ");
		usrid = scanner.next();

		for(int i=0; i < list.size(); ++i) {
			if(list.get(i).getUsrid().equals(usrid)) {
				list.get(i).output();
				return;
			}
		}

		System.out.println("** 입력한 아이디가 존재하지 않습니다.");
	}

	/**
	 * 회원 탈퇴
	 */
	public void delete() {
		String answer, usrid;
		int no = -1;

		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}		

		System.out.print("> 삭제할 회원의 아이디 : ");
		usrid = scanner.next();

		FitnessVO vo = null;
		for(int i=0; i<list.size(); ++i) {
			vo = list.get(i);
			if(vo.getUsrid().equals(usrid)) {
				no = i;
				break;
			} 
		}
		if(no == -1) {
			System.out.println("** 아이디가 존재하지 않습니다");
			return;
		}
		
		vo.output();
		
		System.out.print("** 정말로 탈퇴하시겠습니까?(y/n)");
		answer = scanner.next();

		if(!answer.equals("y")) {
			System.out.println("** 탈퇴작업이 취소되었습니다.");
			return;
		}

		list.remove(no);

		System.out.println("** 탈퇴되었습니다.");
	}

	/**
	 * 회원정보 수정 : 키, 몸무게
	 */
	public void update() {
		String usrid;
		double height, weight;
		int no = -1;
		List<FitnessVO> list = service.findAll();

		if(list.isEmpty()) {
			System.out.println("** 가입한 회원이 없습니다.");
			return;
		}

		System.out.print("> 수정할 회원의 아이디 : ");
		usrid = scanner.next();

		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getUsrid().equals(usrid)) {
				no = i;
				break;
			} 
		}

		if(no == -1) {
			System.out.println("** 입력한 id의 회원은 존재하지 않습니다.");
			return;
		}

		System.out.print("> 수정할 키(cm) : ");
		height = scanner.nextDouble();

		System.out.print("> 수정할 몸무게(kg) : ");
		weight = scanner.nextDouble();

		list.get(no).setHeight(height);
		list.get(no).setWeight(weight);

		System.out.println("** 수정이 완료되었습니다.");
	}
}
