package net.softsociety.spring3.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring3.dao.PersonDAO;
import net.softsociety.spring3.vo.Person;

//PersonService 인터페이스의 구현체
@Service
@Slf4j
public class PersonSeviceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

	//저장
	@Override
	public int insertPerson(Person person) {

		 int result = personDAO.insertPerson(person);
		 
		return result;
	}



	@Override
	public int deletePerson(String name) {
		
		int result = personDAO.deletePerson(name);		
		
		return result;
	}

	@Override
	public ArrayList<Person> selectPerson() {
		ArrayList<Person> list = personDAO.selectPerson();
		return list;
	}



	@Override
	public Person selectOne(String name) {
		Person person = personDAO.selectOne(name);
		return person;
	}



	@Override
	public int updatePerson(Person person) {
		int result = personDAO.updatePerson(person);
		return result;
	}




	
	

}
