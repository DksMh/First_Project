package org.proj.game.plusminus;

public class GamePlayMain {
	int answer; // 정답
	String question; // 문제
	int[] answerArr = new int[4]; // 4개의 정답 선지를 담는 배열

	public GamePlayMain() {
		run();
	}

	public void run() {
		int number1 =  (int)(Math.random() * 20) + 1;
		int number2 =  (int)(Math.random() * 15) + 1;

		question = makeQuiz(number1, number2);

		answer = makeAnswer(number1, number2, question);
		
		answerArr = makeSelection(answer);
		
	}
	
	public String makeQuiz(int num1, int num2) {
		String[] sign = new String[] { "+", "-" }; // 부호
		int randomSign = (int)(Math.random() * 100000) % 2; // 부호 결정
		String form = "";
		if (num1 > num2) { // 연산결과가 음수값이 나오지 않게 하기 위해 조건을 걸어줌
			form = String.format("%2d %s %2d", num1, sign[randomSign], num2);
		} else {
			form = String.format("%2d %s %2d", num1, sign[0], num2);
		}
		return form;
	}

	public int makeAnswer(int num1, int num2, String form) {
		int ans = 0;
		if (form.contains("+")) {
			ans = num1 + num2;
		} else {
			ans = num1 - num2;
		}
		return ans;
	}
	
	public int[] makeSelection(int ans) {
		int choice = (int)((Math.random()*100000)%4); // 4개의 선지의 위치를 랜덤으로 하기 위해서
		int index = 0;
		int[] ansArr = new int[4];
		
		for (int i = 0; i < 4; i++) {
			if (choice == i) {
				ansArr[i] = ans;
				index = i;
			} else {
				ansArr[i] = (int) (Math.random() * 1000 % 35);
				if (ansArr[i] == ans) { // 정답 중복제거
					i--;
					continue;
				}
				for (int j = 0; j < i; j++) { // 정답을 제외한 나머지 중복제거
					if (ansArr[j] == ansArr[i]) {
						i--;
						break;
					}
				}
			}
		}
		return ansArr;
	}

}