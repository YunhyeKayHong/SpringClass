package net.scit.vo;

public class DogVO {
	int shelternum;
	
	int dognum;
	int dogage;
	
	String dogname;
	String dogbreed;
	String doggender;
	String dogdisease;
	String dogenterdate;
	String dogsponsor;

	public DogVO() {
		super();
	}

	public DogVO(int dognum, int dogage, String dogname, String dogbreed, String doggender, String dogdisease,
			String dogenterdate, String dogsponsor, int shelternum) {
		super();
		this.dognum = dognum;
		this.dogage = dogage;
		this.dogname = dogname;
		this.dogbreed = dogbreed;
		this.doggender = doggender;
		this.dogdisease = dogdisease;
		this.dogenterdate = dogenterdate;
		this.dogsponsor = dogsponsor;
		this.shelternum = shelternum;
	}

	public int getDognum() {
		return dognum;
	}

	public void setDognum(int dognum) {
		this.dognum = dognum;
	}

	public int getDogage() {
		return dogage;
	}

	public void setDogage(int dogage) {
		this.dogage = dogage;
	}

	public String getDogname() {
		return dogname;
	}

	public void setDogname(String dogname) {
		this.dogname = dogname;
	}

	public String getDogbreed() {
		return dogbreed;
	}

	public void setDogbreed(String dogbreed) {
		this.dogbreed = dogbreed;
	}

	public String getDoggender() {
		return doggender;
	}

	public void setDoggender(String doggender) {
		this.doggender = doggender;
	}

	public String getDogdisease() {
		return dogdisease;
	}

	public void setDogdisease(String dogdisease) {
		this.dogdisease = dogdisease;
	}

	public String getDogenterdate() {
		return dogenterdate;
	}

	public void setDogenterdate(String dogenterdate) {
		this.dogenterdate = dogenterdate;
	}

	public String getDogsponsor() {
		return dogsponsor;
	}

	public void setDogsponsor(String dogsponsor) {
		this.dogsponsor = dogsponsor;
	}
	
	public int getShelternum() {
		return shelternum;
	}

	public void setShelternum(int shelternum) {
		this.shelternum = shelternum;
	}

	@Override
	public String toString() {
		String temp = String.format("%3d %13s %13d %21s %13s %22s %24s %9s",
									dognum, dogname, dogage, dogbreed, doggender, dogdisease, dogenterdate, dogsponsor);
		return temp;
	}

}
