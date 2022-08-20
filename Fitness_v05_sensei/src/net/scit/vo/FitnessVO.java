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

	// 기본생성자
	public FitnessVO() {}

	// 오버로딩 생성자
	public FitnessVO(String usrid, String usrname, 
			         double height, double weight) {
		this.usrid = usrid;
		this.usrname = usrname;
		this.height = height;
		this.weight = weight;
		
		calcBmi();
	}
	private void calcBmi() {
		double tmp;   // m로 환산한 임시변수
		tmp = this.height * 0.01;
		
		this.bmi = this.weight / (tmp * tmp);
		
		setResult();
	}
	
	public double getBmi() {
		return this.bmi;
	}
	// bmi에 대한 결과값으로 외부에서 이 데이터 하나만 접근할 필요가 없다!
	private void setResult() {
		if(bmi >= 35)      	result = BmiResult.고도비만;
		else if(bmi >= 30) 	result = BmiResult.중도비만;
		else if(bmi >= 25) 	result = BmiResult.경도비만;
		else if(bmi >= 23) 	result = BmiResult.과체중;
		else if(bmi > 18.5) result = BmiResult.정상;
		else 				result = BmiResult.저체중;
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
	
	// 멤버의 정보를 화면에 출력하는 메소드
	public void output() {
		System.out.printf("%5s(%s님) : %.2fcm, %.2fKg, BMI=%.2f, 결과=%s%n"
				,usrid, usrname, height, weight, bmi, result);
	}
	
	@Override
	// 소숫점 이하 2자리씩!
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





