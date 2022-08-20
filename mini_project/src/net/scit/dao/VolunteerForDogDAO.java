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
	// 회원 가입
	public int input(UsrVO uvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.input(uvo);
		session.commit();
		
		return result;
	}
	
	// 보호자 정보 등록
	public int input2(UsrVO uvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.input2(uvo);
		session.commit();
		
		return result;
	}
	
	
	// 보호소 조회
	public ShelterVO searchShelter(int shelternum) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		ShelterVO shelter = mapper.searchShelter(shelternum);
		
		return shelter;
	}
	
	// 보호소 검색
	public List<ShelterVO> searchShelter2(Map<String, Object> map){
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<ShelterVO> searchResult = mapper.searchShelter2(map);
		
		return searchResult;
	}
	
	//강아지 목록 조회
	public List<DogVO> searchDog(int shelternum) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<DogVO> dogList = mapper.searchDog(shelternum);
		
		return dogList;
	}
	
	// 정보 조회
	public UsrVO findById(String usrid) {
		SqlSession session = null;
		
		session = factory.openSession();
		
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		UsrVO usrVO = mapper.findById(usrid);
		
		return usrVO;
	}
	
	// 정보 수정
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
	
	// 정보 삭제
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
	
	// 회원 리스트 출력

	public List<UsrVO> findAll(){
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		List<UsrVO> list = mapper.findAll();

		return list;
	}
	
	// 봉사 이력 조회
	public List<VolunteerVO> listRequest(String usrid){
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		List<VolunteerVO> list = mapper.listRequest(usrid);
		
		return list;
	}
	
	// 봉사 이력 입력
	public int writeRequest(VolunteerVO vvo) {
		SqlSession session = null;
		
		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
				
		int result = mapper.writeRequest(vvo);
		session.commit();
		
		return result;
		
	}
	
	// 보호소에게 후원
	public int ShelterDonation(VolunteerVO vvo) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);
		
		int result = mapper.ShelterDonation(vvo);
		session.commit();
		
		return result;
	}
	
	// 강아지 1마리 정보 조회
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
	
	// 용품으로 후원
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
	
	// 1:1 결연 후원자 정보->dog sponsor
	public void makeluckydog(DogVO dogVO) {
		SqlSession session = null;

		session = factory.openSession();
		volunteerForDogMapper mapper = session.getMapper(volunteerForDogMapper.class);

		int result = mapper.makeluckydog(dogVO);
		session.commit();

		return;
	}

}
