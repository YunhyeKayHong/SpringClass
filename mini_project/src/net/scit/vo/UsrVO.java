package net.scit.vo;

public class UsrVO {
	// ��ȣ�ҿ� ����Ǵ� KEY
	int shelternum;
	
	// ���� ���� ����
	String usrid;
	String usrname;
	String usrgender;
	int usrage;
	String usraddress;
	String usrphone;
	
	// ��ȣ�� ���� ����
	String parentsname;
	int parentsage;
	String parentsaddress;
	String parentsphone;
	
	public UsrVO() {
		super();
	}

	public UsrVO(int shelternum, String usrid, String usrname, String usrgender, int usrage, String usraddress,
			String usrphone, String parentsname, int parentsage, String parentsaddress, String parentsphone) {
		super();
		this.shelternum = shelternum;
		this.usrid = usrid;
		this.usrname = usrname;
		this.usrgender = usrgender;
		this.usrage = usrage;
		this.usraddress = usraddress;
		this.usrphone = usrphone;
		this.parentsname = parentsname;
		this.parentsage = parentsage;
		this.parentsaddress = parentsaddress;
		this.parentsphone = parentsphone;
	}

	public int getShelternum() {
		return shelternum;
	}

	public void setShelternum(int shelternum) {
		this.shelternum = shelternum;
	}

	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getUsrgender() {
		return usrgender;
	}

	public void setUsrgender(String usrgender) {
		this.usrgender = usrgender;
	}

	public int getUsrage() {
		return usrage;
	}

	public void setUsrage(int usrage) {
		this.usrage = usrage;
	}

	public String getUsraddress() {
		return usraddress;
	}

	public void setUsraddress(String usraddress) {
		this.usraddress = usraddress;
	}

	public String getUsrphone() {
		return usrphone;
	}

	public void setUsrphone(String usrphone) {
		this.usrphone = usrphone;
	}

	public String getParentsname() {
		return parentsname;
	}

	public void setParentsname(String parentsname) {
		this.parentsname = parentsname;
	}

	public int getParentsage() {
		return parentsage;
	}

	public void setParentsage(int parentsage) {
		this.parentsage = parentsage;
	}

	public String getParentsaddress() {
		return parentsaddress;
	}

	public void setParentsaddress(String parentsaddress) {
		this.parentsaddress = parentsaddress;
	}

	public String getParentsphone() {
		return parentsphone;
	}

	public void setParentsphone(String parentsphone) {
		this.parentsphone = parentsphone;
	}

	@Override
	   public String toString() {
	      return "���̵� : " + usrid + "\n�̸� : " + usrname + "\n���� : " +usrgender + "\n���� : " + usrage
	             + "\n����ó : " + usrphone + "\n�ּ� : " + usraddress;
	   }
	
}