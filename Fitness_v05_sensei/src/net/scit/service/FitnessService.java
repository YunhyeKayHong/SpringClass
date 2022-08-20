package net.scit.service;

import java.util.List;

import net.scit.vo.FitnessVO;

public interface FitnessService {
	// 등록
	public int regist(FitnessVO vo);
	
	// 아이디로 조회
	public FitnessVO findById(String usrid);
	
	// 전체 데이터 조회 --> UI쪽에서 출력할 때 이름순으로 출력되도록 할 것
	// forEach()로 출력할 것
	public List<FitnessVO> findAll();
	
	// 삭제 --> removeIf를 이용한 람다식으로 
	public int delete(String usrid);
	
	// 수정
	public int update(FitnessVO vo);
	
	public void setFile();
	// 파일에 접근 (setFile:종료시 마지막에!, getFile: 구동시 처음에!)
}
