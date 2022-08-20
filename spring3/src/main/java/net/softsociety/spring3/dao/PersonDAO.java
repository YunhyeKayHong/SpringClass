package net.softsociety.spring3.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring3.vo.Person;

@Mapper
public interface PersonDAO {
	//저장
	int insertPerson(Person person);
	//삭제
	int deletePerson(String name);
	//전체출력
	ArrayList<Person>selectPerson();
	//데이터 하나만 출력 
	Person selectOne(String name);
	//수정
	int updatePerson(Person person);
}
