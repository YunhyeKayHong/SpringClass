package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.DogVO;
import net.scit.vo.ShelterVO;
import net.scit.vo.UsrVO;
import net.scit.vo.VolunteerVO;

public interface volunteerForDogMapper {
	// ȸ�� ����
	public int input(UsrVO uvo);
	
	// ��ȣ�� ���� ���
	public int input2(UsrVO uvo);
	
	// ��¥�� ���
	public int input3(UsrVO uvo);
	
	// ��ȣ�� ��ȸ
	public ShelterVO searchShelter(int snum);
	
	// ��ȣ�� �˻�
	public List<ShelterVO> searchShelter2(Map<String, Object> map);
	
	//������ ��� ��ȸ
	public List<DogVO> searchDog(int snum);
	
	// �Ŀ� ��û
	public int donationRequest(ShelterVO svo);
	
	// ���� ����
	public int update(UsrVO uvo);
	 
	// ���� ����
	public int delete(String usrid);
	
	// ���� ���� ��ȸ
	public UsrVO findById(String usrid);
	
	// ȸ�� ��ü ��ȸ
	public List<UsrVO> findAll();
	
	// ���� �̷� ��ȸ
	public List<VolunteerVO> listRequest(String usrid);

	// ���� �̷� �Է�
	public int writeRequest(VolunteerVO vvo);
	
	// ��ȣ�ҿ��� �Ŀ�
	public int ShelterDonation(VolunteerVO vvo);
	
	// ������ 1���� ���� ��ȸ
	public DogVO findByDogId(int dognum);
	
	//����߿��� �Ŀ�
	public int writeDogDonation(VolunteerVO vvo);
	
	// ��ǰ���� �Ŀ�
	public int TissueDonation(VolunteerVO vvo);
	public int PadDonation(VolunteerVO vvo);
	public int FoodDonation(VolunteerVO vvo);
	
	// 1:1 �Ῥ �Ŀ��� ����->dog sponsor
	public int makeluckydog(DogVO dogVO);
	
}
