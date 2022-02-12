package org.proj.game.cup;

public class CupRoad {

	public int[][] cupArr = new int[10][3];
	public int[][] cupRoadArr = new int[3][10];

	public CupRoad() {

		cupArr[0][0] = 1;
		cupArr[0][1] = 2;
		cupArr[0][2] = 3;

		// 각 컵 위치 중복제거
		for (int i = 1; i < cupArr.length; i++) {
			for(int j = 0; j<cupArr[i].length; j++) {
				int loc = (int)(Math.random()*1000) % 3 + 1;
				cupArr[i][j] = loc;
				for(int k = 0; k < j; k++) {
					if(cupArr[i][k] == cupArr[i][j]) {
						j--;
						break;
					}
				}
			}
		} // end of for문
		
		// 컵의 위치 담기
		for(int i = 0; i < cupRoadArr.length; i++) {
			for(int j = 0; j < cupRoadArr[i].length; j++) {
				cupRoadArr[i][j] = cupArr[j][i];
			}
		} // end of for문
		
		// 위치 보여주기 
		for(int i = 0; i < cupRoadArr.length; i++) {
			System.out.print("cup["+ i + "] : ");
			for(int j = 0; j < cupRoadArr[i].length; j++) {
				if( j == 9) {
					break;
				}
				System.out.print(cupRoadArr[i][j] + " ");
			}
			System.out.println();
		} // end of for문
	}
	
}
