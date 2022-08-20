package net.softsociety.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {//멤버변수 갖은 객체로 정의, 롬곡 이용해서 게터세터
	String name;
	int age;
	String phone;
}
