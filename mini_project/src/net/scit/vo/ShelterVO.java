package net.scit.vo;

public class ShelterVO {
	// 보호소 객체
	int shelternum;
	String sheltername;
	String shelteraddress;
	String shelterdetailaddress;
	String shelteradmin;
	String shelteraccount;		// 숫자 오버플로우 : number(int) => varchar2(String)
	
	public ShelterVO() {
		super();
	}

	public ShelterVO(String sheltername, String shelteraddress, String shelterdetailaddress, String shelteradmin,
			String shelteraccount, int shelternum) {
		super();
		this.sheltername = sheltername;
		this.shelteraddress = shelteraddress;
		this.shelterdetailaddress = shelterdetailaddress;
		this.shelteradmin = shelteradmin;
		this.shelteraccount = shelteraccount;
		this.shelternum = shelternum;
	}

	public String getSheltername() {
		return sheltername;
	}

	public void setSheltername(String sheltername) {
		this.sheltername = sheltername;
	}

	public String getShelteraddress() {
		return shelteraddress;
	}

	public void setShelteraddress(String shelteraddress) {
		this.shelteraddress = shelteraddress;
	}

	public String getShelterdetailaddress() {
		return shelterdetailaddress;
	}

	public void setShelterdetailaddress(String shelterdetailaddress) {
		this.shelterdetailaddress = shelterdetailaddress;
	}

	public String getShelteradmin() {
		return shelteradmin;
	}

	public void setShelteradmin(String shelteradmin) {
		this.shelteradmin = shelteradmin;
	}

	public String getShelteraccount() {
		return shelteraccount;
	}

	public void setShelteraccount(String shelteraccount) {
		this.shelteraccount = shelteraccount;
	}

	public int getShelternum() {
		return shelternum;
	}

	public void setShelternum(int shelternum) {
		this.shelternum = shelternum;
	}

	@Override
	public String toString() {
		String temp = String.format("%15s %18s %21s",sheltername,shelteraddress,shelteradmin);
		
		return temp;
	}
}
