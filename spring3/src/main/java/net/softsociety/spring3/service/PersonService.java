package net.softsociety.spring3.service;

import java.util.ArrayList;

import net.softsociety.spring3.vo.Person;

//서비스 인터페이스
public interface PersonService {
	//저장
	public int insertPerson(Person person);
	//삭제
	public int deletePerson(String name);
	//전체출력
	public ArrayList<Person>selectPerson();
	//하나출력
	public Person selectOne(String name);
	//수정
	public int updatePerson(Person person);

}
