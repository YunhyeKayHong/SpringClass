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
				System.out.println("** ���α׷��� �����մϴ�.");
				service.setFile();
				return;
			default : System.out.println("err) �޴��� �ٽ� �����ϼ���");
			}
			scanner.nextLine(); 
		}
	}

	public void menu() {
		System.out.println("===== [���� ���α׷�] =====");
		System.out.println("        1. ��  ��");
		System.out.println("        2. ��ü ���");
		System.out.println("        3. ��  ȸ");
		System.out.println("        4. ��  ��");
		System.out.println("        5. ��  ��");
		System.out.println("        0. ��  ��");
		System.out.println("------------------------");
		System.out.print  ("       ����> ");
	}

	/**
	 * ���̵� �ߺ�Ȯ�� üũ
	 * true : �ߺ��� ���̵� ����, false : ��밡���� id 
	 */
	private boolean idCheck(String usrid) {
		FitnessVO vo = service.findById(usrid);

		if(vo != null) 
			return true;
		return false;
	}

	/**
	 * ȸ�� ����
	 */
	public void input() {
		String usrid, usrname;
		double height, weight;

		System.out.print("> ���̵� : ");
		usrid = scanner.next();

		if(idCheck(usrid)) {   
			System.out.println("** �ߺ��� ���̵� �����մϴ�.");	
			return;
		}

		System.out.print("> �̸� : ");
		usrname = scanner.next();
		System.out.print("> Ű(cm) : ");
		height = scanner.nextDouble();		
		System.out.print("> ������ : ");
		weight = scanner.nextDouble();

		FitnessVO vo = new FitnessVO(usrid, usrname, height, weight);

		service.regist(vo);
		System.out.println("** ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}

	/**
	 * ȸ���� ��ü ���� ���
	 */
	public void output() {
		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
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
	 * ȸ�� ���� �˻�
	 */
	public void selectOne() {
		String usrid;

		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}

		System.out.print("> ��ȸ�� ȸ���� ���̵� : ");
		usrid = scanner.next();

		for(int i=0; i < list.size(); ++i) {
			if(list.get(i).getUsrid().equals(usrid)) {
				list.get(i).output();
				return;
			}
		}

		System.out.println("** �Է��� ���̵� �������� �ʽ��ϴ�.");
	}

	/**
	 * ȸ�� Ż��
	 */
	public void delete() {
		String answer, usrid;
		int no = -1;

		List<FitnessVO> list = service.findAll();
		if(list.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}		

		System.out.print("> ������ ȸ���� ���̵� : ");
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
			System.out.println("** ���̵� �������� �ʽ��ϴ�");
			return;
		}
		
		vo.output();
		
		System.out.print("** ������ Ż���Ͻðڽ��ϱ�?(y/n)");
		answer = scanner.next();

		if(!answer.equals("y")) {
			System.out.println("** Ż���۾��� ��ҵǾ����ϴ�.");
			return;
		}

		list.remove(no);

		System.out.println("** Ż��Ǿ����ϴ�.");
	}

	/**
	 * ȸ������ ���� : Ű, ������
	 */
	public void update() {
		String usrid;
		double height, weight;
		int no = -1;
		List<FitnessVO> list = service.findAll();

		if(list.isEmpty()) {
			System.out.println("** ������ ȸ���� �����ϴ�.");
			return;
		}

		System.out.print("> ������ ȸ���� ���̵� : ");
		usrid = scanner.next();

		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getUsrid().equals(usrid)) {
				no = i;
				break;
			} 
		}

		if(no == -1) {
			System.out.println("** �Է��� id�� ȸ���� �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print("> ������ Ű(cm) : ");
		height = scanner.nextDouble();

		System.out.print("> ������ ������(kg) : ");
		weight = scanner.nextDouble();

		list.get(no).setHeight(height);
		list.get(no).setWeight(weight);

		System.out.println("** ������ �Ϸ�Ǿ����ϴ�.");
	}
}
