package net.scit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.DogVO;
import net.scit.vo.ShelterVO;
import net.scit.vo.UsrVO;
import net.scit.vo.VolunteerVO;

public class VolunteerForDogDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	// ȸ�� ����
	public int input(UsrVO uvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.input(uvo);
		session.commit();
		
		return result;
	}
	
	// ��ȣ�� ���� ���
	public int input2(UsrVO uvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.input2(uvo);
		session.commit();
		
		return result;
	}
	
	
	// ��ȣ�� ��ȸ
	public ShelterVO searchShelter(int shelternum) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		ShelterVO shelter = mapper.searchShelter(shelternum);
		
		return shelter;
	}
	
	// ��ȣ�� �˻�
	public List<ShelterVO> searchShelter2(Map<String, Object> map){
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<ShelterVO> searchResult = mapper.searchShelter2(map);
		
		return searchResult;
	}
	
	//������ ��� ��ȸ
	public List<DogVO> searchDog(int shelternum) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<DogVO> dogList = mapper.searchDog(shelternum);
		
		return dogList;
	}
	
	// ���� ��ȸ
	public UsrVO findById(String usrid) {
		SqlSession session = null;
		
		session = factory.openSession();
		
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		UsrVO usrVO = mapper.findById(usrid);
		
		return usrVO;
	}
	
	// ���� ����
	public int update(UsrVO uvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.update(uvo);
		
		if(result == 1) {
			session.commit();
			return 1;
		}
		session.rollback();
		return 0;
	}
	
	// ���� ����
	public int delete(String usrid) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.delete(usrid);
		if(result == 1) {
			session.commit();
			return 1;
		}
		session.rollback();
		return 0;
	}
	
	// ȸ�� ����Ʈ ���

	public List<UsrVO> findAll(){
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		List<UsrVO> list = mapper.findAll();

		return list;
	}
	
	// ���� �̷� ��ȸ
	public List<VolunteerVO> listRequest(String usrid){
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<VolunteerVO> list = mapper.listRequest(usrid);
		
		return list;
	}
	
	// ���� �̷� �Է�
	public int writeRequest(VolunteerVO vvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
				
		int result = mapper.writeRequest(vvo);
		session.commit();
		
		return result;
		
	}
	
	// ��ȣ�ҿ��� �Ŀ�
	public int ShelterDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.ShelterDonation(vvo);
		session.commit();
		
		return result;
	}
	
	// ������ 1���� ���� ��ȸ
	public DogVO findByDogId(int dognum) {
		SqlSession session = null;

		session = factory.openSession();

		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		DogVO dogVO = mapper.findByDogId(dognum);

		return dogVO;
	}
	
	public int writeDogDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		int result = mapper.writeDogDonation(vvo);
		session.commit();

		return result;

	}
	
	// ��ǰ���� �Ŀ�
	public int TissueDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.TissueDonation(vvo);
		session.commit();
		
		return result;
	}
	
	public int PadDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.PadDonation(vvo);
		session.commit();
		
		return result;
	}
	
	public int FoodDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.FoodDonation(vvo);
		session.commit();
		
		return result;
	}
	
	// 1:1 �Ῥ �Ŀ��� ����->dog sponsor
	public void makeluckydog(DogVO dogVO) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		int result = mapper.makeluckydog(dogVO);
		session.commit();

		return;
	}

}
