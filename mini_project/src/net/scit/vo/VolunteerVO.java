package net.scit.vo;

public class VolunteerVO {
   int volunteernum;
   // ��ȣ�� �� Ư�� ����߿��� �Ŀ��� �ݾ� ����
   int shelterdonation;
   int dogdonation;

   // �Ŀ� ��ǰ ����
   int pad;
   int food;
   int tissue;

   // �Ŀ� �� ���� �Ͻø� �޾� �� SYSDATE
   String volunteerdate;
   
   // 1:1 �Ῥ ���� ������
   String luckydog;
   
   int shelternum;
   String usrid;
   
   public VolunteerVO() {
      super();
   }
   
   public VolunteerVO(int volunteernum, int shelterdonation, int dogdonation, int pad, int food, int tissue,
         String volunteerdate, int shelternum, String usrid, String luckydog) {
      super();
      this.volunteernum = volunteernum;
      this.shelterdonation = shelterdonation;
      this.dogdonation = dogdonation;
      this.pad = pad;
      this.food = food;
      this.tissue = tissue;
      this.volunteerdate = volunteerdate;
      this.shelternum = shelternum;
      this.usrid = usrid;
      this.luckydog = luckydog;
   }

   public int getVolunteernum() {
      return volunteernum;
   }

   public void setVolunteernum(int volunteernum) {
      this.volunteernum = volunteernum;
   }

   public int getShelterdonation() {
      return shelterdonation;
   }

   public void setShelterdonation(int shelterdonation) {
      this.shelterdonation = shelterdonation;
   }

   public int getDogdonation() {
      return dogdonation;
   }

   public void setDogdonation(int dogdonation) {
      this.dogdonation = dogdonation;
   }

   public int getPad() {
      return pad;
   }

   public void setPad(int pad) {
      this.pad = pad;
   }

   public int getFood() {
      return food;
   }

   public void setFood(int food) {
      this.food = food;
   }

   public int getTissue() {
      return tissue;
   }

   public void setTissue(int tissue) {
      this.tissue = tissue;
   }

   public String getVolunteerdate() {
      return volunteerdate;
   }

   public void setVolunteerdate(String volunteerdate) {
      this.volunteerdate = volunteerdate;
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
   
   public String getLuckydog() {
      return luckydog;
   }

   public void setLuckydog(String luckydog) {
      this.luckydog = luckydog;
   }

   @Override
   public String toString() {
	   String temp = String.format("%3s %13d %17d %21s %18d %15d %17d %18d",
				volunteerdate, shelternum, shelterdonation, luckydog, dogdonation, tissue, pad, food);
	   return temp;
   }
}