package org.proj.model;

import java.io.Serializable;

public class GameDataDto implements Serializable{
	String id;
	int ansGame1;
	int totalGame1;
	int ansGame2;
	int totalGame2;
	int ansGame3;
	int totalGame3;
	int ansGame4;
	int totalGame4;
	int ansGame5;
	int totalGame5;
	
	String day;
	
	public GameDataDto() {
		this("", 0,0,0,0,0,0,0,0,0,0,"");
	}
	
	public GameDataDto(String id, int ansGame1, int totalGame1,int ansGame2, int totalGame2, int ansGame3, int totalGame3, int ansGame4, int totalGame4, int ansGame5, int totalGame5, String day) {
		this.id = id;
		this.ansGame1 = ansGame1;
		this.totalGame1 = totalGame1;
		this.ansGame2 = ansGame2;
		this.totalGame2 = totalGame2;
		this.ansGame3 = ansGame3;
		this.totalGame3 = totalGame3;
		this.ansGame4 = ansGame4;
		this.totalGame4 = totalGame4;
		this.ansGame5 = ansGame5;
		this.totalGame5 = totalGame5;
		
		this.day = day;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAnsGame1() {
		return ansGame1;
	}

	public void setAnsGame1(int ansGame1) {
		this.ansGame1 = ansGame1;
	}

	public int getTotalGame1() {
		return totalGame1;
	}

	public void setTotalGame1(int totalGame1) {
		this.totalGame1 = totalGame1;
	}

	public int getAnsGame2() {
		return ansGame2;
	}

	public void setAnsGame2(int ansGame2) {
		this.ansGame2 = ansGame2;
	}

	public int getTotalGame2() {
		return totalGame2;
	}

	public void setTotalGame2(int totalGame2) {
		this.totalGame2 = totalGame2;
	}

	public int getAnsGame3() {
		return ansGame3;
	}

	public void setAnsGame3(int ansGame3) {
		this.ansGame3 = ansGame3;
	}

	public int getTotalGame3() {
		return totalGame3;
	}

	public void setTotalGame3(int totalGame3) {
		this.totalGame3 = totalGame3;
	}

	public int getAnsGame4() {
		return ansGame4;
	}

	public void setAnsGame4(int ansGame4) {
		this.ansGame4 = ansGame4;
	}

	public int getTotalGame4() {
		return totalGame4;
	}

	public void setTotalGame4(int totalGame4) {
		this.totalGame4 = totalGame4;
	}

	public int getAnsGame5() {
		return ansGame5;
	}

	public void setAnsGame5(int ansGame5) {
		this.ansGame5 = ansGame5;
	}

	public int getTotalGame5() {
		return totalGame5;
	}

	public void setTotalGame5(int totalGame5) {
		this.totalGame5 = totalGame5;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return String.format("%s : %3.2f%%  %3.2f%%  %3.2f%%  %3.2f%%  %3.2f%%",day,(totalGame1 == 0)?0: ((double) ansGame1/totalGame1)*100,(totalGame2 == 0)?0: ((double)ansGame2/totalGame2)*100,(totalGame3 == 0)?0: ((double)ansGame3/totalGame3)*100,(totalGame4 == 0)?0: ((double)ansGame4/totalGame4)*100,(totalGame5 == 0)?0: ((double)ansGame5/totalGame5)*100);
	}
	
	
}



