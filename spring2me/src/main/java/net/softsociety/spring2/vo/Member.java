package net.softsociety.spring2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	String id;
	String password;
	String name;
	String address;
	
	public void Test() {
		System.out.println("추가 정의된 메소드");
	}
	
}
