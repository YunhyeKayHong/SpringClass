package net.scit.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.scit.dao.VolunteerForDogDAO;
import net.scit.vo.DogVO;
import net.scit.vo.ShelterVO;
import net.scit.vo.UsrVO;
import net.scit.vo.VolunteerVO;

public class volunteerForDogUI {
	Scanner keyboard = new Scanner(System.in);
	VolunteerForDogDAO volunteerForDogDAO = new VolunteerForDogDAO();

	public volunteerForDogUI() {

		String choice;

		while(true) {
			menu();
			choice = keyboard.next();

			switch(choice) {
			case "1" : input(); break; 						//회원 정보 등록
			case "2" : searchShelter(); break;				//보호소 조회
			case "3" : volunteerRequest(); break;			//봉사 신청
			case "4" : donationRequest(); break;			//후원 신청
			case "5" : selectOne(); break;					//회원 정보 조회
			case "6" : update(); break;						//회원 정보 수정
			case "7" : delete(); break;						//회원 정보 삭제
			case "0" : System.out.println("** 프로그램을 종료합니다. ** ");
			return;
			default : System.out.println("Error> 메뉴를 다시 선택해주세요!");
			}
		}
	}

	// 1. 회원 정보 등록
	private void input() {	// 성별 오입력시 메인 메뉴가 아닌, 성별 입력라인으로 리턴하기
		String usrid, usrname, usrgender, usraddress, usrphone;
		int usrage;
		int shelternum;

		System.out.println("\n      [ 회원 가입  ]");
		
		System.out.println("\n      [ 보호소 목록 ]");  
		selectSheltermenu();
		System.out.print(" 가입을 원하시는 보호소 번호를 입력해주세요 : ");
		shelternum = keyboard.nextInt();

		if(!(shelternum ==1 || shelternum ==2 || shelternum ==3 || shelternum ==4 || shelternum ==5)) {
			System.out.println("경고) 보호소 번호를 정확하게 입력해주세요!");
			return;
		}

		keyboard.nextLine();

		System.out.println("경고) 아이디는 추후 변경이 불가능합니다. 신중히 기입해주시기 바랍니다.");
		System.out.print("> 아이디 : ");
		usrid = keyboard.nextLine();

		if(usrid.length() == 0) {
			System.out.println("** 아이디를 입력해주세요");
			return;
		}

		System.out.print("> 이름 : ");
		usrname = keyboard.nextLine();

		if(usrname.length() == 0) {
			System.out.println("** 이름을 입력해주세요");
			return;
		}
		System.out.print("> 나이 : ");
		usrage = keyboard.nextInt();

		if(usrage == 0) { 
			System.out.println("** 나이를 입력해주세요");
			return;
		}

		keyboard.nextLine();

		System.out.print("> 성별 (F/M): "); 
		usrgender = keyboard.nextLine();
		switch (usrgender) {
		case "F":
			usrgender = "F";
			break;
		case "M":
			usrgender = "M";
			break;
		default :
			System.out.println("** 여성이면 F, 남성이면 M을 입력해주세요.");
			return;
		} 

		System.out.print("> 주소 : ");
		usraddress = keyboard.nextLine();

		if(usraddress.length() == 0) {
			System.out.println("** 주소를 입력해주세요");
			return;
		}

		System.out.print("> 연락처 : ");
		usrphone = keyboard.nextLine();

		if(usrphone.length() == 0) {
			System.out.println("** 연락처를 입력해주세요");
			return;
		}

		UsrVO uvo = new UsrVO();
		uvo.setShelternum(shelternum);
		uvo.setUsrid(usrid);
		uvo.setUsrname(usrname);
		uvo.setUsrage(usrage);
		uvo.setUsrgender(usrgender);
		uvo.setUsraddress(usraddress);
		uvo.setUsrphone(usrphone);

		int result = volunteerForDogDAO.input(uvo);

		if(result == 1) {
			System.out.println("------------------------------------------------------------");
			System.out.println("회원 가입이 완료되었습니다.");
			System.out.println("------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
			System.out.println("------------------------------------------------------------");
			return;
		}
	}

	// 2. 보호소 조회
	private void searchShelter() {
		int shelternum;
		String shelternum2;
		
		System.out.println("\n      [ 보호소 목록 ]");  
		selectSheltermenu();
		System.out.print("> 정보를 조회하실 보호소 번호를 입력해주세요 : ");

		try {
			shelternum = keyboard.nextInt();
			keyboard.nextLine();
		} catch (Exception e) {
			System.out.println("경고) 숫자 이외의 문자 입력은 불가능합니다!");
			keyboard.nextLine();
			return;
		}

		ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

		System.out.println("------------------------------------------------------------");
		System.out.println("보호소명 : " + shelter.getSheltername());
		System.out.println("보호소 등록번호 : " + shelter.getShelternum() + "번");
		System.out.println("주소 : " + shelter.getShelteraddress());
		System.out.println("책임자 : " + shelter.getShelteradmin());
		System.out.println("------------------------------------------------------------");

		while(true) {
			shelterSubmenu();
			shelternum2 = keyboard.nextLine();

			switch(shelternum2) {
			case "1" : 
				System.out.println("번호	 	이름		 나이	 		견종	 	성별		 	질병		 	입소일		 	후원자");
				List<DogVO> dogList = volunteerForDogDAO.searchDog(shelternum);
				dogList.forEach(d -> System.out.println(d));
				break;
			case "2" : System.out.println("상세 주소 : " + shelter.getShelterdetailaddress()); break;
			case "3" : System.out.println("후원 계좌 : " + shelter.getShelteraccount()); break;
			case "0" : System.out.println("** 메뉴 화면으로 돌아갑니다.");
			return;
			default : System.out.println("경고) 항목의 번호를 올바르게 입력해주세요!");
			}
		}
	}

	// 3. 봉사 신청
	private void volunteerRequest() {
		List<UsrVO> list = volunteerForDogDAO.findAll();
		int age = 0;

		int choice, parentsage, shelternum;
		String parentsname, parentsaddress, parentsphone,
		volunteerdate, usrid, searchWord = null, searchArea = null;

		System.out.println("\n      [ 봉사 신청 ]");
		System.out.println("공지 - 현재 거주하고 계신 곳에서 가장 가까운 보호소를 알려드리는 기능이 추가되었습니다!");

		volunteermenu();
		choice = keyboard.nextInt();

		keyboard.nextLine();

		if(choice == 1) {
			System.out.println("\n      [ 보호소 목록 ]");  
			selectSheltermenu();
			System.out.print("> 봉사 신청 하실 보호소를 선택해주세요 : ");
			shelternum = keyboard.nextInt();
			keyboard.nextLine();

			System.out.print("> 봉사를 희망하시는 날짜를 'YYYY/MM/DD' 형식으로 입력해주세요 : ");
			volunteerdate = keyboard.nextLine();

			System.out.print("> 회원으로 등록된 아이디를 입력해주세요 : ");
			usrid = keyboard.nextLine();

			if(usrid.equals("")) {
				System.out.println("경고) 아이디는 필수 입력 사항입니다.");
				return;
			}

			if(!(usrid.equals(usrid))) {
				System.out.println("경고) 입력하신 아이디가 존재하지 않습니다.");
				return;
			}

			/*나이를 판단하는 부분*/
			for(int i=0; i < list.size(); ++i) {
				if(list.get(i).getUsrid().equals(usrid)) {
					age = list.get(i).getUsrage();
				}
			}

			if(age < 20) {
				System.out.println("경고) 미성년자의 경우 보호자의 동의 및 정보 제공이 필요합니다.");
				System.out.print("> 보호자 성명 : ");
				parentsname = keyboard.nextLine();

				if(parentsname.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				System.out.print("> 보호자 연령 : ");
				parentsage = keyboard.nextInt();

				if(parentsage == 0) { 
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				keyboard.nextLine();

				System.out.print("> 주소 : ");
				parentsaddress = keyboard.nextLine();

				if(parentsaddress.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				System.out.print("> 연락처 : ");
				parentsphone = keyboard.nextLine();

				if(parentsphone.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				uvo.setParentsname(parentsname);
				uvo.setParentsage(parentsage);
				uvo.setParentsaddress(parentsaddress);
				uvo.setParentsphone(parentsphone);

				int result = volunteerForDogDAO.input2(uvo);


				/* 봉사활동 신청내역 넣기*/

				//List<VolunteerVO> ListRequest = volunteerForDogDAO.listRequest(usrid);   	
				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result1 = volunteerForDogDAO.writeRequest(vvo);	                         

				if(result == 1 && result1 == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("봉사 신청이 정상적으로 등록되었습니다.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			} else {
				/* 성인의 경우 */
				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result = volunteerForDogDAO.writeRequest(vvo);	

				if(result == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("봉사 신청이 정상적으로 등록되었습니다.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			}
		} else if(choice == 2) {

			searchArea = "saddress";

			System.out.print("> 지역 입력(서울/경기/인천) : ");
			searchWord = keyboard.nextLine();

			Map<String, Object> map = new HashMap<>();

			map.put("searchArea", searchArea);
			map.put("searchWord", searchWord);

			List<ShelterVO> searchResult = volunteerForDogDAO.searchShelter2(map);

			if(searchResult.size() == 0 || searchResult == null) {
				System.out.println("------------------------------------------------------------");
				System.out.println("** 검색하신 지역으로 현재 등록된 보호소가 없습니다.");
				return;
			}

			System.out.println("------------------------------------------------------------");
			System.out.println("검색하신 키워드로 현재 등록된 보호소는 다음과 같습니다.");
			System.out.println("------------------------------------------------------------");
			System.out.println("         보호소명		           주소				관리자");
			searchResult.forEach(r -> System.out.println(r));
			System.out.println("\n	※봉사 신청시에는 보호소명이 아닌 보호소 번호로 입력해주세요!");
			selectSheltermenu();
			System.out.print("> 봉사 신청 하실 보호소를 선택해주세요 : ");
			shelternum = keyboard.nextInt();
			keyboard.nextLine();
			
			System.out.print("> 봉사를 희망하시는 날짜를 'YYYY/MM/DD' 형식으로 입력해주세요 : ");
			volunteerdate = keyboard.nextLine();

			System.out.print("> 회원으로 등록된 아이디를 입력해주세요 : ");
			usrid = keyboard.nextLine();

			if(usrid.equals("")) {
				System.out.println("경고) 아이디는 필수 입력 사항입니다.");
				return;
			}

			if(!(usrid.equals(usrid))) {
				System.out.println("경고) 입력하신 아이디가 존재하지 않습니다.");
				return;
			}

			/*나이를 판단하는 부분*/
			for(int i=0; i < list.size(); ++i) {
				if(list.get(i).getUsrid().equals(usrid)) {
					age = list.get(i).getUsrage();
				}
			}

			if(age < 20) {
				System.out.println("경고) 미성년자의 경우 보호자의 동의 및 정보 제공이 필요합니다.");
				System.out.print("> 보호자 성명 : ");
				parentsname = keyboard.nextLine();

				if(parentsname.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				System.out.print("> 보호자 연령 : ");
				parentsage = keyboard.nextInt();

				if(parentsage == 0) { 
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				keyboard.nextLine();

				System.out.print("> 주소 : ");
				parentsaddress = keyboard.nextLine();

				if(parentsaddress.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				System.out.print("> 연락처 : ");
				parentsphone = keyboard.nextLine();

				if(parentsphone.length() == 0) {
					System.out.println("경고) 필수 입력 사항입니다.");
					return;
				}

				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				uvo.setParentsname(parentsname);
				uvo.setParentsage(parentsage);
				uvo.setParentsaddress(parentsaddress);
				uvo.setParentsphone(parentsphone);

				int result = volunteerForDogDAO.input2(uvo);

				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result1 = volunteerForDogDAO.writeRequest(vvo);

				if(result == 1 && result1 == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("봉사 신청이 정상적으로 등록되었습니다.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			} else {
				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result = volunteerForDogDAO.writeRequest(vvo);

				if(result == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("봉사 신청이 정상적으로 등록되었습니다.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			}

		} else if(choice == 0) {
			System.out.println("** 메뉴 화면으로 돌아갑니다.");
			return;
		} else {
			System.out.println("경고) 올바른 번호를 입력해주세요!");
		}
	}

	// 4. 후원 신청
	private void donationRequest() {
		List<UsrVO> list = volunteerForDogDAO.findAll();
		
		int choice, shelternum, shelterdonation, tissue, pad, food, dognum, dogdonation;
		String volunteerdate, answer, usrid;

		System.out.println("\n      [ 후원 신청 ]");
		
		keyboard.nextLine();
		
		System.out.print("> 회원으로 등록된 아이디를 입력해주세요 : ");
		usrid = keyboard.nextLine();
		
		if(usrid.equals("")) {
			System.out.println("경고) 아이디는 필수 입력 사항입니다.");
			return;
		}
		
		if(!(usrid.equals(usrid))) {
			System.out.println("경고) 입력하신 아이디가 존재하지 않습니다.");
			return;
		}
		
		donationmenu();
		System.out.print("> 후원 방식을 선택해주세요 : ");
		choice = keyboard.nextInt();

		if(choice == 1) {
			System.out.println("\n      [ 보호소 목록 ]");
			System.out.println("후원금으로 보호소 지원하기를 선택하셨습니다.");
			System.out.println("※ 현재 등록되어있는 보호소는 다음과 같습니다.");
			selectSheltermenu();
			System.out.print("> 후원을 희망하시는 보호소를 선택해주세요 : ");
			shelternum = keyboard.nextInt();

			keyboard.nextLine();

			ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

			System.out.println("** 선택하신 보호소의 상세 정보는 다음과 같습니다.");
			System.out.println("------------------------------------------------------------");
			System.out.println("보호소명 : " + shelter.getSheltername());
			System.out.println("보호소 등록번호 : " + shelter.getShelternum() + "번");
			System.out.println("주소 : " + shelter.getShelteraddress());
			System.out.println("책임자 : " + shelter.getShelteradmin());
			System.out.println("계좌번호 : " + shelter.getShelteraccount());
			System.out.println("------------------------------------------------------------");
			System.out.println("보호소에 후원하실 금액을 다음과 같은 형태로 입력해주세요");
			System.out.println("ex) 3000 / 30000 / 300000···");
			
			System.out.print("> 후원금 입력 : ");
			shelterdonation = keyboard.nextInt();

			System.out.print("> 후원 예정일을 YYYY/MM/DD 형식으로 입력해주세요 : ");
			volunteerdate = keyboard.next();
			
			keyboard.nextLine();
			
			VolunteerVO vvo = new VolunteerVO();
			vvo.setUsrid(usrid);
			vvo.setShelternum(shelternum);
			vvo.setVolunteerdate(volunteerdate);
			vvo.setShelterdonation(shelterdonation);
			
			int result = volunteerForDogDAO.ShelterDonation(vvo);	                         

			if(result == 1) {
				System.out.println("------------------------------------------------------------");
				System.out.println("** 후원이 완료되었습니다. 후원자님의 관심에 감사드리며, 앞으로 좋은 일만 가득하시길 "+shelter.getSheltername()+"이(가) 응원합니다♥");
				System.out.println("------------------------------------------------------------");
			} else {
				System.out.println("------------------------------------------------------------");
				System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
				System.out.println("------------------------------------------------------------");
				return;
			}
			} else if(choice == 2) {
				System.out.println("------------------------------------------------------------");
				System.out.println("\n      [ 보호소 목록 ]"); 
				System.out.println("후원금으로 유기견 후원하기를 선택하셨습니다.");

			    selectSheltermenu();
			    System.out.print("> 유기견 정보를 조회하실 보호소 번호를 입력해주세요 : ");

			      try {
			         shelternum = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("경고) 숫자 이외의 문자 입력은 불가능합니다!");
			         keyboard.nextLine();
			         return;
			      }

			      ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);
			      System.out.println("> 해당 보호소에서 수용중인 유기견 목록은 다음과 같습니다. ");
			      System.out.println("------------------------------------------------------------");
			      System.out.println("번호       이름       나이          견종       성별          질병          입소일          후원자");
			      List<DogVO> dogList = volunteerForDogDAO.searchDog(shelternum);
			      dogList.forEach(d -> System.out.println(d));
			      System.out.println("------------------------------------------------------------");
			      
			      System.out.println("※ 되도록 기존에 후원자가 없는 유기견을 선택해주시면 감사하겠습니다( _ _)!");
			      System.out.print("> 후원하실 유기견의 등록 번호를 입력해주세요 : ");
			      try {
			         dognum = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("경고) 숫자 이외의 문자 입력은 불가능합니다!");
			         keyboard.nextLine();
			         return;
			      }
			      DogVO dogVO = volunteerForDogDAO.findByDogId(dognum);
			      
			      System.out.print("> 후원을 희망하시는 날짜를 'YYYY/MM/DD' 형식으로 입력해주세요 : ");
			      volunteerdate = keyboard.nextLine();
			      
			      //유기견 선택
			      System.out.print("> 후원하실 금액을 입력해주세요 : ");
			      try {
			         dogdonation = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("경고) 숫자 이외의 문자 입력은 불가능합니다!");
			         keyboard.nextLine();
			         return;
			      }
			      
			      System.out.print(dogVO.getDogname()+ "에게 "+ dogdonation + "원을 후원하시겠습니까? (Y/N)");
			      answer = keyboard.nextLine();

			      if(!answer.equals("Y")) {
			         System.out.println("------------------------------------------------------------");
			         System.out.println("후원이 취소되었습니다.");
			         return;
			      }
			       
			      //본인 아이디에 맞는 vvo 에 넣기
			      UsrVO uvo = volunteerForDogDAO.findById(usrid);
			      
			      //후원자 넣는 부분
			      dogVO.setDogsponsor(uvo.getUsrname());
			      
			      volunteerForDogDAO.makeluckydog(dogVO);
			      
			      VolunteerVO vvo = new VolunteerVO();
			      vvo.setUsrid(usrid);
			      vvo.setShelternum(shelternum);
			      vvo.setVolunteerdate(volunteerdate);
			      vvo.setDogdonation(dogdonation);
			      vvo.setLuckydog(dogVO.getDogname());
			      int result = volunteerForDogDAO.writeDogDonation(vvo);

			      if(result == 1) {
			         System.out.println("------------------------------------------------------------");
			         System.out.println("> " + dogVO.getDogname() + "의 후원자가 되어주셔서 감사합니다. ");
			         System.out.println("> 앞으로 잘 부탁드려요. 후원자님! U'● ㅅ ●'U \t -" + dogVO.getDogname() + "-");
			         System.out.println("------------------------------------------------------------");
			      } else {
			         System.out.println("------------------------------------------------------------");
			         System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
			         System.out.println("------------------------------------------------------------");
			         return;
			      }
				
			} else if(choice == 3) {
				System.out.println("------------------------------------------------------------");
				System.out.println("용품으로 후원하기를 선택하셨습니다.");
				System.out.println("※ 현재 등록되어있는 보호소는 다음과 같습니다.");
				selectSheltermenu();
				System.out.print("> 후원을 희망하시는 보호소를 선택해주세요 : ");
				shelternum = keyboard.nextInt();

				keyboard.nextLine();

				ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

				System.out.println("** 선택하신 보호소의 상세 정보는 다음과 같습니다.");
				System.out.println("------------------------------------------------------------");
				System.out.println("보호소명 : " + shelter.getSheltername());
				System.out.println("보호소 등록번호 : " + shelter.getShelternum() + "번");
				System.out.println("주소 : " + shelter.getShelteraddress());
				System.out.println("상세 주소 : " + shelter.getShelterdetailaddress());
				System.out.println("책임자 : " + shelter.getShelteradmin());
				
				goodsmenu();
				System.out.print("> 후원하실 용품의 번호를 입력해주세요 : ");
				choice = keyboard.nextInt();
				
				if(choice == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("후원 물품 : 물티슈를 선택하셨습니다.");
					System.out.print("> 후원하실 물품의 수량을 입력해주세요 : ");
					tissue = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> 후원 예정일을 YYYY/MM/DD 형식으로 입력해주세요 : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setTissue(tissue);
					
					int result = volunteerForDogDAO.TissueDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** 후원이 완료되었습니다. 후원자님의 관심에 감사드리며, 앞으로 좋은 일만 가득하시길 "+shelter.getSheltername()+"이(가) 응원합니다♥");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 2) {
					System.out.println("------------------------------------------------------------");
					System.out.println("후원 물품 : 배변 패드를 선택하셨습니다.");
					System.out.print("> 후원하실 물품의 수량을 입력해주세요 : ");
					pad = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> 후원 예정일을 YYYY/MM/DD 형식으로 입력해주세요 : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setPad(pad);
					
					int result = volunteerForDogDAO.PadDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** 후원이 완료되었습니다. 후원자님의 관심에 감사드리며, 앞으로 좋은 일만 가득하시길 "+shelter.getSheltername()+"이(가) 응원합니다♥");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 3) {
					System.out.println("------------------------------------------------------------");
					System.out.println("후원 물품 : 사료를 선택하셨습니다.");
					System.out.print("> 후원하실 물품의 수량을 입력해주세요 : ");
					food = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> 후원 예정일을 YYYY/MM/DD 형식으로 입력해주세요 : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setFood(food);
					
					int result = volunteerForDogDAO.FoodDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** 후원이 완료되었습니다. 후원자님의 관심에 감사드리며, 앞으로 좋은 일만 가득하시길 "+shelter.getSheltername()+"이(가) 응원합니다♥");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("경고) 현재 시스템 점검중입니다. 관리자에게 문의해주세요.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 0) {
					System.out.println("** 메뉴 화면으로 돌아갑니다.");
					return;
				} else {
					System.out.println("경고) 올바른 번호를 입력해주세요!");
				}
				
			} else if(choice == 0) {
				System.out.println("** 메뉴 화면으로 돌아갑니다.");
				return;
			} else {
				System.out.println("경고) 올바른 번호를 입력해주세요!");
			}
			}
	
	// 5. 회원 정보 조회
	private void selectOne() {
		int choice, usrage, shelterdonation, dogdonation, pad, food, tissue, shelternum;
		String usrid, usrname, usraddress, usrphone, volunteerdate, luckydog;
		
		System.out.println("\n      [ 회원 정보 조회]");
		
		keyboard.nextLine();

		System.out.print("> 정보를 조회하실 아이디를 입력해주세요 : ");
		usrid = keyboard.nextLine();

		UsrVO uvo = volunteerForDogDAO.findById(usrid);

		if(uvo == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 해당 아이디로 등록된 회원 정보는 없습니다. 아이디를 확인해주세요.");
			return;
		}

		usrmenu();
		System.out.println("------------------------------------------------------------");
		System.out.print("조화하실 항목의 번호를 입력해주세요 : ");
		choice = keyboard.nextInt();
		
		keyboard.nextLine();
		
		if(choice == 1) {
			System.out.println("------------------------------------------------------------");
			System.out.println("입력하신 아이디로 조회된 회원 정보는 다음과 같습니다.");
			System.out.println(uvo);
			System.out.println("------------------------------------------------------------");
		} else if(choice == 2) {
			System.out.println("------------------------------------------------------------");
			System.out.println("입력하신 아이디로 조회된 봉사 및 후원 정보는 다음과 같습니다.");
			System.out.println("------------------------------------------------------------");
			System.out.println("                  일시                  보호소 번호   보호소 후원 금액(원)  후원중인 유기견   유기견 후원 금액(원)    물티슈(개)     배변 패드(세트)    사료(포대) ");
			List<VolunteerVO> VList = volunteerForDogDAO.listRequest(usrid);
			VList.forEach(v -> System.out.println(v));
			int TotalShelterDonation = 0;
			int TotalDogDonation = 0;
			int TotalTissue = 0;
			int TotalPad = 0;
			int TotalFood = 0;
			
		      for(VolunteerVO vvo : VList) {
		    	  TotalShelterDonation += vvo.getShelterdonation();
		    	  TotalDogDonation += vvo.getDogdonation();
		          TotalTissue += vvo.getTissue();
		          TotalPad += vvo.getPad();
		          TotalFood += vvo.getFood();
		       }
		      
		      System.out.println();
		      System.out.println("--------------------------------------------------------------------");
		      System.out.println(" * 총 보호소 후원 금액 : " + TotalShelterDonation + "원");
		      System.out.println(" * 총 유기견 후원 금액 : " + TotalDogDonation +"원");
		      System.out.println(" * 후원한 물티슈 갯수 : " + TotalTissue + "개");
		      System.out.println(" * 후원한 배변 패드 갯수 : " + TotalPad +"세트");
		      System.out.println(" * 후원한 사료 갯수 : " + TotalFood + "포대");


		      System.out.println();
			
		} else if(choice == 0) {
			return;
		} else {System.out.println("조회하실 항목의 번호를 정확하게 입력해주세요!");
	}}

	// 6. 회원 정보 수정
	private void update() {
		String answer, usrid, usraddress, usrphone;
		int result;

		System.out.println("\n      [ 회원 정보 수정 ]");
		keyboard.nextLine();

		System.out.print("> 수정하실 아이디를 입력해주세요 : ");
		usrid = keyboard.nextLine();

		UsrVO findById = volunteerForDogDAO.findById(usrid);

		if(findById == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 해당 아이디로 등록된 회원 정보는 없습니다. 아이디를 확인해주세요.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("입력하신 아이디로 조회된 회원 정보는 다음과 같습니다.");
		System.out.println(findById);
		System.out.println("------------------------------------------------------------");

		System.out.print("수정하시겠습니까? (y/n) ");
		answer = keyboard.nextLine();

		if(!answer.equals("y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 수정 작업이 취소되었습니다.");
			return;
		}

		System.out.print("본인 확인을 위한 아이디 재입력 : ");
		usrid = keyboard.nextLine();

		if(usrid.length() == 0) {
			System.out.println("경고) 필수 입력 항목입니다.");
			return;
		}

		System.out.print("새로운 주소 입력 : ");
		usraddress = keyboard.nextLine();

		if(usraddress.length() == 0) {
			System.out.println("경고) 주소는 필수 입력 항목입니다.");
			return;
		}

		System.out.print("새로운 연락처 입력 : ");
		usrphone = keyboard.nextLine();

		if(usrphone.length() == 0) {
			System.out.println("경고) 연락처 필수 입력 항목입니다.");
			return;
		}

		UsrVO uvo = new UsrVO();
		uvo.setUsrid(usrid);
		uvo.setUsraddress(usraddress);
		uvo.setUsrphone(usrphone);

		result = volunteerForDogDAO.update(uvo);

		if(result == 1) {
			System.out.println("------------------------------------------------------------");
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");
			System.out.println("------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 수정 작업이 취소되었습니다. 관리자에게 문의해주세요.");
			System.out.println("------------------------------------------------------------");
			return;
		}

	}

	// 7. 회원 정보 삭제
	private void delete() {
		String usrid, answer, answer2;
		int result;

		System.out.println("\n      [ 회원 정보 삭제]");
		keyboard.nextLine();

		System.out.print("> 탈퇴하실 아이디를 입력해주세요 : ");
		usrid = keyboard.nextLine();

		UsrVO findById = volunteerForDogDAO.findById(usrid);

		if(findById == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 해당 아이디로 등록된 회원 정보는 없습니다. 아이디를 확인해주세요.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("입력하신 아이디로 조회된 회원 정보는 다음과 같습니다.");
		System.out.println(findById);
		System.out.println("------------------------------------------------------------");

		System.out.print("탈퇴하시겠습니까? (y/n) ");
		answer = keyboard.nextLine();

		if(!answer.equals("y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 탈퇴 작업이 취소되었습니다.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.print("탈퇴 후 1년간 재가입이 불가합니다. 정말 탈퇴하시겠습니까? (Y/N) ");
		answer2 = keyboard.nextLine();

		if(!answer2.equals("Y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 탈퇴 작업이 취소되었습니다.");
			return;
		}

		result = volunteerForDogDAO.delete(usrid);

		if(result == 0) {
			System.out.println("------------------------------------------------------------");
			System.out.println("경고) 탈퇴 작업이 취소되었습니다. 관리자에게 문의해주세요.");
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("회원님의 탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.");

	}

	public void menu() {
		System.out.println("===== [유기견 봉사 활동 신청 프로그램] =====");
		System.out.println("	 1. 회원 정보 등록");
		System.out.println("	 2. 보호소 조회");
		System.out.println("	 3. 봉사 신청");
		System.out.println("	 4. 후원 신청");
		System.out.println("	 5. 회원 정보 조회");
		System.out.println("	 6. 회원 정보 수정");
		System.out.println("	 7. 회원 정보 삭제");
		System.out.println("	 0. 종 료");
		System.out.println("==========================");
		System.out.print  ("     선택> ");
	}

	private void selectSheltermenu() {    
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) 유사랑: 유기동물 사랑 나누기");
		System.out.println("        2) 아지네 마을");
		System.out.println("        3) 비글 구조 네트워크");
		System.out.println("        4) 내사랑 바둑이");
		System.out.println("        5) 행동하는 동물사랑");
		System.out.println("------------------------------------------------------------");
	}

	private void shelterSubmenu() {
		System.out.println("        1) 수용중인 유기견 리스트 확인");
		System.out.println("        2) 봉사지 상세 주소 확인");
		System.out.println("        3) 후원 계좌 확인");
		System.out.println("        0) 메인 메뉴로 돌아가기");
		System.out.println("------------------------------------------------------------");
		System.out.print  ("          선택> ");
	}

	private void volunteermenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) 현재 등록된 모든 보호소 리스트 확인하기");
		System.out.println("        2) 내 근처 보호소 찾기");
		System.out.println("        0) 메인 메뉴로 돌아가기");
		System.out.println("------------------------------------------------------------");
		System.out.print("     선택> ");
	}
	
	private void donationmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) 후원금으로 보호소 지원하기");
		System.out.println("        2) 후원금으로 유기견 후원하기");
		System.out.println("        3) 용품으로 후원하기");
		System.out.println("        0) 메인 메뉴로 돌아가기");
		
	}
	
	private void goodsmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) 물티슈");
		System.out.println("        2) 배변 패드");
		System.out.println("        3) 사료");
		System.out.println("        0) 메인 메뉴로 돌아가기");
	}
	
	private void usrmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) 회원 정보 확인하기");
		System.out.println("        2) 봉사 및 후원 내역 확인하기");
		System.out.println("        0) 메인 메뉴로 돌아가기");
	}

}
