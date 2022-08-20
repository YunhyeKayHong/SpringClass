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
		String test;
		String choice;

		while(true) {
			menu();
			choice = keyboard.next();

			switch(choice) {
			case "1" : input(); break; 						//ȸ�� ���� ���
			case "2" : searchShelter(); break;				//��ȣ�� ��ȸ
			case "3" : volunteerRequest(); break;			//���� ��û
			case "4" : donationRequest(); break;			//�Ŀ� ��û
			case "5" : selectOne(); break;					//ȸ�� ���� ��ȸ
			case "6" : update(); break;						//ȸ�� ���� ����
			case "7" : delete(); break;						//ȸ�� ���� ����
			case "0" : System.out.println("** ���α׷��� �����մϴ�. ** ");
			return;
			default : System.out.println("Error> �޴��� �ٽ� �������ּ���!");
			}
		}
	}

	// 1. ȸ�� ���� ���
	private void input() {	// ���� ���Է½� ���� �޴��� �ƴ�, ���� �Է¶������� �����ϱ�
		String usrid, usrname, usrgender, usraddress, usrphone;
		int usrage;
		int shelternum;

		System.out.println("\n      [ ȸ�� ����  ]");
		
		System.out.println("\n      [ ��ȣ�� ��� ]");  
		selectSheltermenu();
		System.out.print(" ������ ���Ͻô� ��ȣ�� ��ȣ�� �Է����ּ��� : ");
		shelternum = keyboard.nextInt();

		if(!(shelternum ==1 || shelternum ==2 || shelternum ==3 || shelternum ==4 || shelternum ==5)) {
			System.out.println("���) ��ȣ�� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���!");
			return;
		}

		keyboard.nextLine();

		System.out.println("���) ���̵�� ���� ������ �Ұ����մϴ�. ������ �������ֽñ� �ٶ��ϴ�.");
		System.out.print("> ���̵� : ");
		usrid = keyboard.nextLine();

		if(usrid.length() == 0) {
			System.out.println("** ���̵� �Է����ּ���");
			return;
		}

		System.out.print("> �̸� : ");
		usrname = keyboard.nextLine();

		if(usrname.length() == 0) {
			System.out.println("** �̸��� �Է����ּ���");
			return;
		}
		System.out.print("> ���� : ");
		usrage = keyboard.nextInt();

		if(usrage == 0) { 
			System.out.println("** ���̸� �Է����ּ���");
			return;
		}

		keyboard.nextLine();

		System.out.print("> ���� (F/M): "); 
		usrgender = keyboard.nextLine();
		switch (usrgender) {
		case "F":
			usrgender = "F";
			break;
		case "M":
			usrgender = "M";
			break;
		default :
			System.out.println("** �����̸� F, �����̸� M�� �Է����ּ���.");
			return;
		} 

		System.out.print("> �ּ� : ");
		usraddress = keyboard.nextLine();

		if(usraddress.length() == 0) {
			System.out.println("** �ּҸ� �Է����ּ���");
			return;
		}

		System.out.print("> ����ó : ");
		usrphone = keyboard.nextLine();

		if(usrphone.length() == 0) {
			System.out.println("** ����ó�� �Է����ּ���");
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
			System.out.println("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
			System.out.println("------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
			System.out.println("------------------------------------------------------------");
			return;
		}
	}

	// 2. ��ȣ�� ��ȸ
	private void searchShelter() {
		int shelternum;
		String shelternum2;
		
		System.out.println("\n      [ ��ȣ�� ��� ]");  
		selectSheltermenu();
		System.out.print("> ������ ��ȸ�Ͻ� ��ȣ�� ��ȣ�� �Է����ּ��� : ");

		try {
			shelternum = keyboard.nextInt();
			keyboard.nextLine();
		} catch (Exception e) {
			System.out.println("���) ���� �̿��� ���� �Է��� �Ұ����մϴ�!");
			keyboard.nextLine();
			return;
		}

		ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

		System.out.println("------------------------------------------------------------");
		System.out.println("��ȣ�Ҹ� : " + shelter.getSheltername());
		System.out.println("��ȣ�� ��Ϲ�ȣ : " + shelter.getShelternum() + "��");
		System.out.println("�ּ� : " + shelter.getShelteraddress());
		System.out.println("å���� : " + shelter.getShelteradmin());
		System.out.println("------------------------------------------------------------");

		while(true) {
			shelterSubmenu();
			shelternum2 = keyboard.nextLine();

			switch(shelternum2) {
			case "1" : 
				System.out.println("��ȣ	 	�̸�		 ����	 		����	 	����		 	����		 	�Լ���		 	�Ŀ���");
				List<DogVO> dogList = volunteerForDogDAO.searchDog(shelternum);
				dogList.forEach(d -> System.out.println(d));
				break;
			case "2" : System.out.println("�� �ּ� : " + shelter.getShelterdetailaddress()); break;
			case "3" : System.out.println("�Ŀ� ���� : " + shelter.getShelteraccount()); break;
			case "0" : System.out.println("** �޴� ȭ������ ���ư��ϴ�.");
			return;
			default : System.out.println("���) �׸��� ��ȣ�� �ùٸ��� �Է����ּ���!");
			}
		}
	}

	// 3. ���� ��û
	private void volunteerRequest() {
		List<UsrVO> list = volunteerForDogDAO.findAll();
		int age = 0;

		int choice, parentsage, shelternum;
		String parentsname, parentsaddress, parentsphone,
		volunteerdate, usrid, searchWord = null, searchArea = null;

		System.out.println("\n      [ ���� ��û ]");
		System.out.println("���� - ���� �����ϰ� ��� ������ ���� ����� ��ȣ�Ҹ� �˷��帮�� ����� �߰��Ǿ����ϴ�!");

		volunteermenu();
		choice = keyboard.nextInt();

		keyboard.nextLine();

		if(choice == 1) {
			System.out.println("\n      [ ��ȣ�� ��� ]");  
			selectSheltermenu();
			System.out.print("> ���� ��û �Ͻ� ��ȣ�Ҹ� �������ּ��� : ");
			shelternum = keyboard.nextInt();
			keyboard.nextLine();

			System.out.print("> ���縦 ����Ͻô� ��¥�� 'YYYY/MM/DD' �������� �Է����ּ��� : ");
			volunteerdate = keyboard.nextLine();

			System.out.print("> ȸ������ ��ϵ� ���̵� �Է����ּ��� : ");
			usrid = keyboard.nextLine();

			if(usrid.equals("")) {
				System.out.println("���) ���̵�� �ʼ� �Է� �����Դϴ�.");
				return;
			}

			if(!(usrid.equals(usrid))) {
				System.out.println("���) �Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
				return;
			}

			/*���̸� �Ǵ��ϴ� �κ�*/
			for(int i=0; i < list.size(); ++i) {
				if(list.get(i).getUsrid().equals(usrid)) {
					age = list.get(i).getUsrage();
				}
			}

			if(age < 20) {
				System.out.println("���) �̼������� ��� ��ȣ���� ���� �� ���� ������ �ʿ��մϴ�.");
				System.out.print("> ��ȣ�� ���� : ");
				parentsname = keyboard.nextLine();

				if(parentsname.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				System.out.print("> ��ȣ�� ���� : ");
				parentsage = keyboard.nextInt();

				if(parentsage == 0) { 
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				keyboard.nextLine();

				System.out.print("> �ּ� : ");
				parentsaddress = keyboard.nextLine();

				if(parentsaddress.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				System.out.print("> ����ó : ");
				parentsphone = keyboard.nextLine();

				if(parentsphone.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				uvo.setParentsname(parentsname);
				uvo.setParentsage(parentsage);
				uvo.setParentsaddress(parentsaddress);
				uvo.setParentsphone(parentsphone);

				int result = volunteerForDogDAO.input2(uvo);


				/* ����Ȱ�� ��û���� �ֱ�*/

				//List<VolunteerVO> ListRequest = volunteerForDogDAO.listRequest(usrid);   	
				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result1 = volunteerForDogDAO.writeRequest(vvo);	                         

				if(result == 1 && result1 == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("���� ��û�� ���������� ��ϵǾ����ϴ�.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			} else {
				/* ������ ��� */
				UsrVO uvo = volunteerForDogDAO.findById(usrid);

				VolunteerVO vvo = new VolunteerVO();
				vvo.setUsrid(usrid);
				vvo.setShelternum(shelternum);
				vvo.setVolunteerdate(volunteerdate);
				int result = volunteerForDogDAO.writeRequest(vvo);	

				if(result == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("���� ��û�� ���������� ��ϵǾ����ϴ�.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			}
		} else if(choice == 2) {

			searchArea = "saddress";

			System.out.print("> ���� �Է�(����/���/��õ) : ");
			searchWord = keyboard.nextLine();

			Map<String, Object> map = new HashMap<>();

			map.put("searchArea", searchArea);
			map.put("searchWord", searchWord);

			List<ShelterVO> searchResult = volunteerForDogDAO.searchShelter2(map);

			if(searchResult.size() == 0 || searchResult == null) {
				System.out.println("------------------------------------------------------------");
				System.out.println("** �˻��Ͻ� �������� ���� ��ϵ� ��ȣ�Ұ� �����ϴ�.");
				return;
			}

			System.out.println("------------------------------------------------------------");
			System.out.println("�˻��Ͻ� Ű����� ���� ��ϵ� ��ȣ�Ҵ� ������ �����ϴ�.");
			System.out.println("------------------------------------------------------------");
			System.out.println("         ��ȣ�Ҹ�		           �ּ�				������");
			searchResult.forEach(r -> System.out.println(r));
			System.out.println("\n	�غ��� ��û�ÿ��� ��ȣ�Ҹ��� �ƴ� ��ȣ�� ��ȣ�� �Է����ּ���!");
			selectSheltermenu();
			System.out.print("> ���� ��û �Ͻ� ��ȣ�Ҹ� �������ּ��� : ");
			shelternum = keyboard.nextInt();
			keyboard.nextLine();
			
			System.out.print("> ���縦 ����Ͻô� ��¥�� 'YYYY/MM/DD' �������� �Է����ּ��� : ");
			volunteerdate = keyboard.nextLine();

			System.out.print("> ȸ������ ��ϵ� ���̵� �Է����ּ��� : ");
			usrid = keyboard.nextLine();

			if(usrid.equals("")) {
				System.out.println("���) ���̵�� �ʼ� �Է� �����Դϴ�.");
				return;
			}

			if(!(usrid.equals(usrid))) {
				System.out.println("���) �Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
				return;
			}

			/*���̸� �Ǵ��ϴ� �κ�*/
			for(int i=0; i < list.size(); ++i) {
				if(list.get(i).getUsrid().equals(usrid)) {
					age = list.get(i).getUsrage();
				}
			}

			if(age < 20) {
				System.out.println("���) �̼������� ��� ��ȣ���� ���� �� ���� ������ �ʿ��մϴ�.");
				System.out.print("> ��ȣ�� ���� : ");
				parentsname = keyboard.nextLine();

				if(parentsname.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				System.out.print("> ��ȣ�� ���� : ");
				parentsage = keyboard.nextInt();

				if(parentsage == 0) { 
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				keyboard.nextLine();

				System.out.print("> �ּ� : ");
				parentsaddress = keyboard.nextLine();

				if(parentsaddress.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
					return;
				}

				System.out.print("> ����ó : ");
				parentsphone = keyboard.nextLine();

				if(parentsphone.length() == 0) {
					System.out.println("���) �ʼ� �Է� �����Դϴ�.");
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
					System.out.println("���� ��û�� ���������� ��ϵǾ����ϴ�.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
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
					System.out.println("���� ��û�� ���������� ��ϵǾ����ϴ�.");
					System.out.println("------------------------------------------------------------");
				} else {
					System.out.println("------------------------------------------------------------");
					System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
					System.out.println("------------------------------------------------------------");
					return;
				}
			}

		} else if(choice == 0) {
			System.out.println("** �޴� ȭ������ ���ư��ϴ�.");
			return;
		} else {
			System.out.println("���) �ùٸ� ��ȣ�� �Է����ּ���!");
		}
	}

	// 4. �Ŀ� ��û
	private void donationRequest() {
		List<UsrVO> list = volunteerForDogDAO.findAll();
		
		int choice, shelternum, shelterdonation, tissue, pad, food, dognum, dogdonation;
		String volunteerdate, answer, usrid;

		System.out.println("\n      [ �Ŀ� ��û ]");
		
		keyboard.nextLine();
		
		System.out.print("> ȸ������ ��ϵ� ���̵� �Է����ּ��� : ");
		usrid = keyboard.nextLine();
		
		if(usrid.equals("")) {
			System.out.println("���) ���̵�� �ʼ� �Է� �����Դϴ�.");
			return;
		}
		
		if(!(usrid.equals(usrid))) {
			System.out.println("���) �Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
			return;
		}
		
		donationmenu();
		System.out.print("> �Ŀ� ����� �������ּ��� : ");
		choice = keyboard.nextInt();

		if(choice == 1) {
			System.out.println("\n      [ ��ȣ�� ��� ]");
			System.out.println("�Ŀ������� ��ȣ�� �����ϱ⸦ �����ϼ̽��ϴ�.");
			System.out.println("�� ���� ��ϵǾ��ִ� ��ȣ�Ҵ� ������ �����ϴ�.");
			selectSheltermenu();
			System.out.print("> �Ŀ��� ����Ͻô� ��ȣ�Ҹ� �������ּ��� : ");
			shelternum = keyboard.nextInt();

			keyboard.nextLine();

			ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

			System.out.println("** �����Ͻ� ��ȣ���� �� ������ ������ �����ϴ�.");
			System.out.println("------------------------------------------------------------");
			System.out.println("��ȣ�Ҹ� : " + shelter.getSheltername());
			System.out.println("��ȣ�� ��Ϲ�ȣ : " + shelter.getShelternum() + "��");
			System.out.println("�ּ� : " + shelter.getShelteraddress());
			System.out.println("å���� : " + shelter.getShelteradmin());
			System.out.println("���¹�ȣ : " + shelter.getShelteraccount());
			System.out.println("------------------------------------------------------------");
			System.out.println("��ȣ�ҿ� �Ŀ��Ͻ� �ݾ��� ������ ���� ���·� �Է����ּ���");
			System.out.println("ex) 3000 / 30000 / 300000������");
			
			System.out.print("> �Ŀ��� �Է� : ");
			shelterdonation = keyboard.nextInt();

			System.out.print("> �Ŀ� �������� YYYY/MM/DD �������� �Է����ּ��� : ");
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
				System.out.println("** �Ŀ��� �Ϸ�Ǿ����ϴ�. �Ŀ��ڴ��� ���ɿ� ����帮��, ������ ���� �ϸ� �����Ͻñ� "+shelter.getSheltername()+"��(��) �����մϴ٢�");
				System.out.println("------------------------------------------------------------");
			} else {
				System.out.println("------------------------------------------------------------");
				System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
				System.out.println("------------------------------------------------------------");
				return;
			}
			} else if(choice == 2) {
				System.out.println("------------------------------------------------------------");
				System.out.println("\n      [ ��ȣ�� ��� ]"); 
				System.out.println("�Ŀ������� ����� �Ŀ��ϱ⸦ �����ϼ̽��ϴ�.");

			    selectSheltermenu();
			    System.out.print("> ����� ������ ��ȸ�Ͻ� ��ȣ�� ��ȣ�� �Է����ּ��� : ");

			      try {
			         shelternum = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("���) ���� �̿��� ���� �Է��� �Ұ����մϴ�!");
			         keyboard.nextLine();
			         return;
			      }

			      ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);
			      System.out.println("> �ش� ��ȣ�ҿ��� �������� ����� ����� ������ �����ϴ�. ");
			      System.out.println("------------------------------------------------------------");
			      System.out.println("��ȣ       �̸�       ����          ����       ����          ����          �Լ���          �Ŀ���");
			      List<DogVO> dogList = volunteerForDogDAO.searchDog(shelternum);
			      dogList.forEach(d -> System.out.println(d));
			      System.out.println("------------------------------------------------------------");
			      
			      System.out.println("�� �ǵ��� ������ �Ŀ��ڰ� ���� ������� �������ֽø� �����ϰڽ��ϴ�( _ _)!");
			      System.out.print("> �Ŀ��Ͻ� ������� ��� ��ȣ�� �Է����ּ��� : ");
			      try {
			         dognum = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("���) ���� �̿��� ���� �Է��� �Ұ����մϴ�!");
			         keyboard.nextLine();
			         return;
			      }
			      DogVO dogVO = volunteerForDogDAO.findByDogId(dognum);
			      
			      System.out.print("> �Ŀ��� ����Ͻô� ��¥�� 'YYYY/MM/DD' �������� �Է����ּ��� : ");
			      volunteerdate = keyboard.nextLine();
			      
			      //����� ����
			      System.out.print("> �Ŀ��Ͻ� �ݾ��� �Է����ּ��� : ");
			      try {
			         dogdonation = keyboard.nextInt();
			         keyboard.nextLine();
			      } catch (Exception e) {
			         System.out.println("���) ���� �̿��� ���� �Է��� �Ұ����մϴ�!");
			         keyboard.nextLine();
			         return;
			      }
			      
			      System.out.print(dogVO.getDogname()+ "���� "+ dogdonation + "���� �Ŀ��Ͻðڽ��ϱ�? (Y/N)");
			      answer = keyboard.nextLine();

			      if(!answer.equals("Y")) {
			         System.out.println("------------------------------------------------------------");
			         System.out.println("�Ŀ��� ��ҵǾ����ϴ�.");
			         return;
			      }
			       
			      //���� ���̵� �´� vvo �� �ֱ�
			      UsrVO uvo = volunteerForDogDAO.findById(usrid);
			      
			      //�Ŀ��� �ִ� �κ�
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
			         System.out.println("> " + dogVO.getDogname() + "�� �Ŀ��ڰ� �Ǿ��ּż� �����մϴ�. ");
			         System.out.println("> ������ �� ��Ź�����. �Ŀ��ڴ�! U'�� �� ��'U \t -" + dogVO.getDogname() + "-");
			         System.out.println("------------------------------------------------------------");
			      } else {
			         System.out.println("------------------------------------------------------------");
			         System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
			         System.out.println("------------------------------------------------------------");
			         return;
			      }
				
			} else if(choice == 3) {
				System.out.println("------------------------------------------------------------");
				System.out.println("��ǰ���� �Ŀ��ϱ⸦ �����ϼ̽��ϴ�.");
				System.out.println("�� ���� ��ϵǾ��ִ� ��ȣ�Ҵ� ������ �����ϴ�.");
				selectSheltermenu();
				System.out.print("> �Ŀ��� ����Ͻô� ��ȣ�Ҹ� �������ּ��� : ");
				shelternum = keyboard.nextInt();

				keyboard.nextLine();

				ShelterVO shelter = volunteerForDogDAO.searchShelter(shelternum);

				System.out.println("** �����Ͻ� ��ȣ���� �� ������ ������ �����ϴ�.");
				System.out.println("------------------------------------------------------------");
				System.out.println("��ȣ�Ҹ� : " + shelter.getSheltername());
				System.out.println("��ȣ�� ��Ϲ�ȣ : " + shelter.getShelternum() + "��");
				System.out.println("�ּ� : " + shelter.getShelteraddress());
				System.out.println("�� �ּ� : " + shelter.getShelterdetailaddress());
				System.out.println("å���� : " + shelter.getShelteradmin());
				
				goodsmenu();
				System.out.print("> �Ŀ��Ͻ� ��ǰ�� ��ȣ�� �Է����ּ��� : ");
				choice = keyboard.nextInt();
				
				if(choice == 1) {
					System.out.println("------------------------------------------------------------");
					System.out.println("�Ŀ� ��ǰ : ��Ƽ���� �����ϼ̽��ϴ�.");
					System.out.print("> �Ŀ��Ͻ� ��ǰ�� ������ �Է����ּ��� : ");
					tissue = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> �Ŀ� �������� YYYY/MM/DD �������� �Է����ּ��� : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setTissue(tissue);
					
					int result = volunteerForDogDAO.TissueDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** �Ŀ��� �Ϸ�Ǿ����ϴ�. �Ŀ��ڴ��� ���ɿ� ����帮��, ������ ���� �ϸ� �����Ͻñ� "+shelter.getSheltername()+"��(��) �����մϴ٢�");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 2) {
					System.out.println("------------------------------------------------------------");
					System.out.println("�Ŀ� ��ǰ : �躯 �е带 �����ϼ̽��ϴ�.");
					System.out.print("> �Ŀ��Ͻ� ��ǰ�� ������ �Է����ּ��� : ");
					pad = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> �Ŀ� �������� YYYY/MM/DD �������� �Է����ּ��� : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setPad(pad);
					
					int result = volunteerForDogDAO.PadDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** �Ŀ��� �Ϸ�Ǿ����ϴ�. �Ŀ��ڴ��� ���ɿ� ����帮��, ������ ���� �ϸ� �����Ͻñ� "+shelter.getSheltername()+"��(��) �����մϴ٢�");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 3) {
					System.out.println("------------------------------------------------------------");
					System.out.println("�Ŀ� ��ǰ : ��Ḧ �����ϼ̽��ϴ�.");
					System.out.print("> �Ŀ��Ͻ� ��ǰ�� ������ �Է����ּ��� : ");
					food = keyboard.nextInt();
					
					keyboard.nextLine();
					
					System.out.print("> �Ŀ� �������� YYYY/MM/DD �������� �Է����ּ��� : ");
					volunteerdate = keyboard.nextLine();
					
					VolunteerVO vvo = new VolunteerVO();
					vvo.setUsrid(usrid);
					vvo.setShelternum(shelternum);
					vvo.setVolunteerdate(volunteerdate);
					vvo.setFood(food);
					
					int result = volunteerForDogDAO.FoodDonation(vvo);	                         

					if(result == 1) {
						System.out.println("------------------------------------------------------------");
						System.out.println("** �Ŀ��� �Ϸ�Ǿ����ϴ�. �Ŀ��ڴ��� ���ɿ� ����帮��, ������ ���� �ϸ� �����Ͻñ� "+shelter.getSheltername()+"��(��) �����մϴ٢�");
						System.out.println("------------------------------------------------------------");
					} else {
						System.out.println("------------------------------------------------------------");
						System.out.println("���) ���� �ý��� �������Դϴ�. �����ڿ��� �������ּ���.");
						System.out.println("------------------------------------------------------------");
						return;
					}
				} else if(choice == 0) {
					System.out.println("** �޴� ȭ������ ���ư��ϴ�.");
					return;
				} else {
					System.out.println("���) �ùٸ� ��ȣ�� �Է����ּ���!");
				}
				
			} else if(choice == 0) {
				System.out.println("** �޴� ȭ������ ���ư��ϴ�.");
				return;
			} else {
				System.out.println("���) �ùٸ� ��ȣ�� �Է����ּ���!");
			}
			}
	
	// 5. ȸ�� ���� ��ȸ
	private void selectOne() {
		int choice, usrage, shelterdonation, dogdonation, pad, food, tissue, shelternum;
		String usrid, usrname, usraddress, usrphone, volunteerdate, luckydog;
		
		System.out.println("\n      [ ȸ�� ���� ��ȸ]");
		
		keyboard.nextLine();

		System.out.print("> ������ ��ȸ�Ͻ� ���̵� �Է����ּ��� : ");
		usrid = keyboard.nextLine();

		UsrVO uvo = volunteerForDogDAO.findById(usrid);

		if(uvo == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) �ش� ���̵�� ��ϵ� ȸ�� ������ �����ϴ�. ���̵� Ȯ�����ּ���.");
			return;
		}

		usrmenu();
		System.out.println("------------------------------------------------------------");
		System.out.print("��ȭ�Ͻ� �׸��� ��ȣ�� �Է����ּ��� : ");
		choice = keyboard.nextInt();
		
		keyboard.nextLine();
		
		if(choice == 1) {
			System.out.println("------------------------------------------------------------");
			System.out.println("�Է��Ͻ� ���̵�� ��ȸ�� ȸ�� ������ ������ �����ϴ�.");
			System.out.println(uvo);
			System.out.println("------------------------------------------------------------");
		} else if(choice == 2) {
			System.out.println("------------------------------------------------------------");
			System.out.println("�Է��Ͻ� ���̵�� ��ȸ�� ���� �� �Ŀ� ������ ������ �����ϴ�.");
			System.out.println("------------------------------------------------------------");
			System.out.println("                  �Ͻ�                  ��ȣ�� ��ȣ   ��ȣ�� �Ŀ� �ݾ�(��)  �Ŀ����� �����   ����� �Ŀ� �ݾ�(��)    ��Ƽ��(��)     �躯 �е�(��Ʈ)    ���(����) ");
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
		      System.out.println(" * �� ��ȣ�� �Ŀ� �ݾ� : " + TotalShelterDonation + "��");
		      System.out.println(" * �� ����� �Ŀ� �ݾ� : " + TotalDogDonation +"��");
		      System.out.println(" * �Ŀ��� ��Ƽ�� ���� : " + TotalTissue + "��");
		      System.out.println(" * �Ŀ��� �躯 �е� ���� : " + TotalPad +"��Ʈ");
		      System.out.println(" * �Ŀ��� ��� ���� : " + TotalFood + "����");


		      System.out.println();
			
		} else if(choice == 0) {
			return;
		} else {System.out.println("��ȸ�Ͻ� �׸��� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���!");
	}}

	// 6. ȸ�� ���� ����
	private void update() {
		String answer, usrid, usraddress, usrphone;
		int result;

		System.out.println("\n      [ ȸ�� ���� ���� ]");
		keyboard.nextLine();

		System.out.print("> �����Ͻ� ���̵� �Է����ּ��� : ");
		usrid = keyboard.nextLine();

		UsrVO findById = volunteerForDogDAO.findById(usrid);

		if(findById == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) �ش� ���̵�� ��ϵ� ȸ�� ������ �����ϴ�. ���̵� Ȯ�����ּ���.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("�Է��Ͻ� ���̵�� ��ȸ�� ȸ�� ������ ������ �����ϴ�.");
		System.out.println(findById);
		System.out.println("------------------------------------------------------------");

		System.out.print("�����Ͻðڽ��ϱ�? (y/n) ");
		answer = keyboard.nextLine();

		if(!answer.equals("y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) ���� �۾��� ��ҵǾ����ϴ�.");
			return;
		}

		System.out.print("���� Ȯ���� ���� ���̵� ���Է� : ");
		usrid = keyboard.nextLine();

		if(usrid.length() == 0) {
			System.out.println("���) �ʼ� �Է� �׸��Դϴ�.");
			return;
		}

		System.out.print("���ο� �ּ� �Է� : ");
		usraddress = keyboard.nextLine();

		if(usraddress.length() == 0) {
			System.out.println("���) �ּҴ� �ʼ� �Է� �׸��Դϴ�.");
			return;
		}

		System.out.print("���ο� ����ó �Է� : ");
		usrphone = keyboard.nextLine();

		if(usrphone.length() == 0) {
			System.out.println("���) ����ó �ʼ� �Է� �׸��Դϴ�.");
			return;
		}

		UsrVO uvo = new UsrVO();
		uvo.setUsrid(usrid);
		uvo.setUsraddress(usraddress);
		uvo.setUsrphone(usrphone);

		result = volunteerForDogDAO.update(uvo);

		if(result == 1) {
			System.out.println("------------------------------------------------------------");
			System.out.println("ȸ�� ������ ���������� �����Ǿ����ϴ�.");
			System.out.println("------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) ���� �۾��� ��ҵǾ����ϴ�. �����ڿ��� �������ּ���.");
			System.out.println("------------------------------------------------------------");
			return;
		}

	}

	// 7. ȸ�� ���� ����
	private void delete() {
		String usrid, answer, answer2;
		int result;

		System.out.println("\n      [ ȸ�� ���� ����]");
		keyboard.nextLine();

		System.out.print("> Ż���Ͻ� ���̵� �Է����ּ��� : ");
		usrid = keyboard.nextLine();

		UsrVO findById = volunteerForDogDAO.findById(usrid);

		if(findById == null) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) �ش� ���̵�� ��ϵ� ȸ�� ������ �����ϴ�. ���̵� Ȯ�����ּ���.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("�Է��Ͻ� ���̵�� ��ȸ�� ȸ�� ������ ������ �����ϴ�.");
		System.out.println(findById);
		System.out.println("------------------------------------------------------------");

		System.out.print("Ż���Ͻðڽ��ϱ�? (y/n) ");
		answer = keyboard.nextLine();

		if(!answer.equals("y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) Ż�� �۾��� ��ҵǾ����ϴ�.");
			return;
		}

		System.out.println("------------------------------------------------------------");
		System.out.print("Ż�� �� 1�Ⱓ �簡���� �Ұ��մϴ�. ���� Ż���Ͻðڽ��ϱ�? (Y/N) ");
		answer2 = keyboard.nextLine();

		if(!answer2.equals("Y")) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) Ż�� �۾��� ��ҵǾ����ϴ�.");
			return;
		}

		result = volunteerForDogDAO.delete(usrid);

		if(result == 0) {
			System.out.println("------------------------------------------------------------");
			System.out.println("���) Ż�� �۾��� ��ҵǾ����ϴ�. �����ڿ��� �������ּ���.");
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("ȸ������ Ż�� �Ϸ�Ǿ����ϴ�. �̿����ּż� �����մϴ�.");

	}

	public void menu() {
		System.out.println("===== [����� ���� Ȱ�� ��û ���α׷�] =====");
		System.out.println("	 1. ȸ�� ���� ���");
		System.out.println("	 2. ��ȣ�� ��ȸ");
		System.out.println("	 3. ���� ��û");
		System.out.println("	 4. �Ŀ� ��û");
		System.out.println("	 5. ȸ�� ���� ��ȸ");
		System.out.println("	 6. ȸ�� ���� ����");
		System.out.println("	 7. ȸ�� ���� ����");
		System.out.println("	 0. �� ��");
		System.out.println("==========================");
		System.out.print  ("     ����> ");
	}

	private void selectSheltermenu() {    
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) �����: ���⵿�� ��� ������");
		System.out.println("        2) ������ ����");
		System.out.println("        3) ��� ���� ��Ʈ��ũ");
		System.out.println("        4) ����� �ٵ���");
		System.out.println("        5) �ൿ�ϴ� �������");
		System.out.println("------------------------------------------------------------");
	}

	private void shelterSubmenu() {
		System.out.println("        1) �������� ����� ����Ʈ Ȯ��");
		System.out.println("        2) ������ �� �ּ� Ȯ��");
		System.out.println("        3) �Ŀ� ���� Ȯ��");
		System.out.println("        0) ���� �޴��� ���ư���");
		System.out.println("------------------------------------------------------------");
		System.out.print  ("          ����> ");
	}

	private void volunteermenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) ���� ��ϵ� ��� ��ȣ�� ����Ʈ Ȯ���ϱ�");
		System.out.println("        2) �� ��ó ��ȣ�� ã��");
		System.out.println("        0) ���� �޴��� ���ư���");
		System.out.println("------------------------------------------------------------");
		System.out.print("     ����> ");
	}
	
	private void donationmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) �Ŀ������� ��ȣ�� �����ϱ�");
		System.out.println("        2) �Ŀ������� ����� �Ŀ��ϱ�");
		System.out.println("        3) ��ǰ���� �Ŀ��ϱ�");
		System.out.println("        0) ���� �޴��� ���ư���");
		
	}
	
	private void goodsmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) ��Ƽ��");
		System.out.println("        2) �躯 �е�");
		System.out.println("        3) ���");
		System.out.println("        0) ���� �޴��� ���ư���");
	}
	
	private void usrmenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("        1) ȸ�� ���� Ȯ���ϱ�");
		System.out.println("        2) ���� �� �Ŀ� ���� Ȯ���ϱ�");
		System.out.println("        0) ���� �޴��� ���ư���");
	}

}
