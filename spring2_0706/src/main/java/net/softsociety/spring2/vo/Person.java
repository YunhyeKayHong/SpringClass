package net.softsociety.spring2.vo;

public class Person {
	String name;
	String phone;
	String phonenum;
	
	public Person() {
	}

	public Person(String name, String phone, String phonenum) {
		super();
		this.name = name;
		this.phone = phone;
		this.phonenum = phonenum;
	}

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

	@Override
	public String toString() {
		return "Person [name=" + name + ", phone=" + phone + ", phonenum=" + phonenum + "]";
	}
}
