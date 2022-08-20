package net.softsociety.spring2.vo;

public class Person {
	String name;
	String phone;
	String phonenum;
	
	//기본생성자
	public Person() {
		super();
	}

	public Person(String name, String phone, String phonenum) {
		super();
		this.name = name;
		this.phone = phone;
		this.phonenum = phonenum;
	}
	
	
	//getter, setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	//개발자 본인이 로그로 확인하기 위한 코드, 사용자에게는 HTML에서 예쁘게 출력

	@Override
	public String toString() {
		return "Person [name=" + name + ", phone=" + phone + ", phonenum=" + phonenum + "]";
	}
	
	
	
	
	

}
