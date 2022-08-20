package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.DogVO;
import net.scit.vo.ShelterVO;
import net.scit.vo.UsrVO;
import net.scit.vo.VolunteerVO;

public interface volunteerForDogMapper {
	// 회원 가입
	public int input(UsrVO uvo);
	
	// 보호자 정보 등록
	public int input2(UsrVO uvo);
	
	// 날짜만 등록
	public int input3(UsrVO uvo);
	
	// 보호소 조회
	public ShelterVO searchShelter(int snum);
	
	// 보호소 검색
	public List<ShelterVO> searchShelter2(Map<String, Object> map);
	
	//강아지 목록 조회
	public List<DogVO> searchDog(int snum);
	
	// 후원 신청
	public int donationRequest(ShelterVO svo);
	
	// 정보 수정
	public int update(UsrVO uvo);
	 
	// 정보 삭제
	public int delete(String usrid);
	
	// 유저 정보 조회
	public UsrVO findById(String usrid);
	
	// 회원 전체 조회
	public List<UsrVO> findAll();
	
	// 봉사 이력 조회
	public List<VolunteerVO> listRequest(String usrid);

	// 봉사 이력 입력
	public int writeRequest(VolunteerVO vvo);
	
	// 보호소에게 후원
	public int ShelterDonation(VolunteerVO vvo);
	
	// 강아지 1마리 정보 조회
	public DogVO findByDogId(int dognum);
	
	//유기견에게 후원
	public int writeDogDonation(VolunteerVO vvo);
	
	// 용품으로 후원
	public int TissueDonation(VolunteerVO vvo);
	public int PadDonation(VolunteerVO vvo);
	public int FoodDonation(VolunteerVO vvo);
	
	// 1:1 결연 후원자 정보->dog sponsor
	public int makeluckydog(DogVO dogVO);
	
}
