package net.scit.service;

import java.util.List;

import net.scit.vo.FitnessVO;

public interface FitnessService {
	// ���
	public int regist(FitnessVO vo);
	
	// ���̵�� ��ȸ
	public FitnessVO findById(String usrid);
	
	// ��ü ������ ��ȸ --> UI�ʿ��� ����� �� �̸������� ��µǵ��� �� ��
	// forEach()�� ����� ��
	public List<FitnessVO> findAll();
	
	// ���� --> removeIf�� �̿��� ���ٽ����� 
	public int delete(String usrid);
	
	// ����
	public int update(FitnessVO vo);
	
	public void setFile();
	// ���Ͽ� ���� (setFile:����� ��������!, getFile: ������ ó����!)
}
