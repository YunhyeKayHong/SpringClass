package net.scit.vo;

public class VolunteerVO {
   int volunteernum;
   // 보호소 및 특정 유기견에게 후원한 금액 정보
   int shelterdonation;
   int dogdonation;

   // 후원 물품 정보
   int pad;
   int food;
   int tissue;

   // 후원 및 봉사 일시를 받아 올 SYSDATE
   String volunteerdate;
   
   // 1:1 결연 맺은 강아지
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