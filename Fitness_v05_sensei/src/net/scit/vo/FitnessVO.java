package net.scit.vo;

import java.io.Serializable;

public class FitnessVO implements Serializable, Comparable<FitnessVO> {
	private static final long serialVersionUID = 1L;
	
	private String usrid;
	private String usrname;
	private double height;
	private double weight;
	private double bmi;
	private BmiResult result;

	// �⺻������
	public FitnessVO() {}

	// �����ε� ������
	public FitnessVO(String usrid, String usrname, 
			         double height, double weight) {
		this.usrid = usrid;
		this.usrname = usrname;
		this.height = height;
		this.weight = weight;
		
		calcBmi();
	}
	private void calcBmi() {
		double tmp;   // m�� ȯ���� �ӽú���
		tmp = this.height * 0.01;
		
		this.bmi = this.weight / (tmp * tmp);
		
		setResult();
	}
	
	public double getBmi() {
		return this.bmi;
	}
	// bmi�� ���� ��������� �ܺο��� �� ������ �ϳ��� ������ �ʿ䰡 ����!
	private void setResult() {
		if(bmi >= 35)      	result = BmiResult.����;
		else if(bmi >= 30) 	result = BmiResult.�ߵ���;
		else if(bmi >= 25) 	result = BmiResult.�浵��;
		else if(bmi >= 23) 	result = BmiResult.��ü��;
		else if(bmi > 18.5) result = BmiResult.����;
		else 				result = BmiResult.��ü��;
	}
	
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
		calcBmi();
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
		calcBmi();
	}
	
	// ����� ������ ȭ�鿡 ����ϴ� �޼ҵ�
	public void output() {
		System.out.printf("%5s(%s��) : %.2fcm, %.2fKg, BMI=%.2f, ���=%s%n"
				,usrid, usrname, height, weight, bmi, result);
	}
	
	@Override
	// �Ҽ��� ���� 2�ڸ���!
	public String toString() {
		String temp 
		    = String.format("%5s %8s %6.2f %6.2f %6.2f %s", 
		    		usrid, usrname, height, weight, bmi, result);
		
		return temp;
	}

	@Override
	public int compareTo(FitnessVO o) {
		return this.usrname.compareTo(o.getUsrname());
	}
}





