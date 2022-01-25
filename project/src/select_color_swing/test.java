package select_color_swing;

import java.awt.Color;
import java.util.Scanner;

public class test {	// 색깔 상수
//	private static final int RED = 0;
//	private static final int BLUE = 1;
//	private static final int YELLOW = 2;
//	private static final int ORANGE = 3;

	// 글자에 입힐 색깔(스윙)
	static Color[] c = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.magenta};
	
	// 글자
//	static String[] cs2 = { "RED", "BLUE", "YELLOW", "ORANGE" };

	// 색깔의 배열
	static int[] arrBtn = new int[3]; // 버튼의 배열
	static int[] arrTxt = new int[7]; // 글자의 배열

	static int ansColor; // 정답(글자와 버튼색이 같음)
	static int ansLoc; // 정답위치(정답위치가 일정함을 방지하기위해서)
	static int paintColor; // 글자색
	
	// 숫자를 글자로 바꾸기위한 변수
	static int num;
	
	static Scanner scan = new Scanner(System.in);

	// 글자 함수
	// 글자(=정답)를 정해주기위한 함수이다
	public static void colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 7);
//			System.out.println("arrTxt[i] " + arrTxt[i]);
			ansColor = arrTxt[i];
		}
		// 숫자를 글자로(한국어)
		System.out.print("글자 : ");
		num = ansColor;
		numToColorKor();
	}
	
	// 글자의 색 함수
	public static void paintTxt() {
		while(true) {
			paintColor = (int)(Math.random() * 7); // 컬러 수여서 변동가능
			if(ansColor == paintColor) {
				continue;
			} else {
				break;
			}
		}
		// 숫자를 글자로
//		System.out.println(paintColor);
		System.out.print("글자 색 : ");
		num = paintColor;
		numToColor();
	}

	// 위치선정 함수
	// 글자함수에서 나온 정답의 위치를 정해주기위한 함수이다
	public static void location() {
		ansLoc = (int) (Math.random() * 3); // 저장위치 랜덤 돌리기
//		System.out.println("ansLoc : " + ansLoc); // 저장위치 확인 용도
		System.out.println("정답위치 : " + ansLoc);

		arrBtn[ansLoc] = ansColor;
	}

	// 버튼 2개의 색 랜덤으로 돌리기
	// 정답과 오답2개를 받기위한 함수이다
	public static void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// 정답을 제외한 버튼의 색깔지정하는 부분
			// 정답과 같으면 다시 위로 올라가서 for문을 돌아라
			if (i == ansLoc) { 
				continue;
			}

			int otherColor = (int) (Math.random() * 7);

			// 랜덤수와 정답이 같으면 다시 위로 올라가서 for문을 돌아라
			if (ansColor == otherColor) { 
				i--;
				continue;
			}
			arrBtn[i] = otherColor;
			// 값이 나오면 그것에 대한 중복체크해주기
			for (int j = 0; j < i; j++) { // j < i => i = 전에 뽑은 랜덤수, j = 지금 뽑은 랜덤수
				if (arrBtn[j] == arrBtn[i]) {
					i--;
					continue;
				}
			}
//			num = otherColor;
//			numToColor();
		}

		// 결정된 버튼을 출력해주기
		// 숫자를 글자로
		System.out.print("보기 : ");
		for (int i = 0; i < arrBtn.length; i++) {
//			System.out.print(arrBtn[i] + ":");
			num = arrBtn[i];
			numToColor();
		}
	}
	
	// 숫자를 글자(영어)로 바꾸는 함수
	public static void numToColor() {
		switch(num) {
		case 0:
			System.out.print("RED ");
			break;
		case 1:
			System.out.print("ORANGE ");
			break;
		case 2:
			System.out.print("YELLOW ");
			break;
		case 3:
			System.out.print("GREEN ");
			break;
		case 4:
			System.out.print("BLUE ");
			break;
		case 5:
			System.out.print("PINK ");
			break;
		case 6:
			System.out.print("MAGENTA "); // 보라색으로 수정시 수정해주기
			break;
		}
	}
	
	// 숫자를 글자(한국어)로 바꾸는 함수
	public static void numToColorKor() {
		switch(num) {
		case 0:
			System.out.print("빨간색 ");
			break;
		case 1:
			System.out.print("주황색 ");
			break;
		case 2:
			System.out.print("노란색 ");
			break;
		case 3:
			System.out.print("초록색 ");
			break;
		case 4:
			System.out.print("파란색 ");
			break;
		case 5:
			System.out.print("분홍색 ");
			break;
		case 6:
			System.out.print("자주색 "); // 보라색으로 수정시 수정해주기
			break;
		}
	}

	public static void main(String[] args) {
		// 글자가 실행되는 부분 - 하나만 나와 중복제거가 필요없다
		colorTxt();
		System.out.println();
		
		// 글자색이 실행되는 부분
		paintTxt();
		System.out.println();

		// 저장위치 선정
		location();
		System.out.println();

		// 버튼이 실행되는 부분
		button();
		System.out.println();
		
		// 정답 받기
		System.out.print("정답 : ");
		int answer = scan.nextInt();
		
		// 콘솔창 확인용
		// 우리는 swing으로 마우스 이벤트로 정답을 고르기 때문에 신경 안써도 된다~
		if(answer == ansLoc) {
			System.out.println("정답입니다~!!");
		} else {
			System.out.println("오답입니다ㅜ");
		}
	}
}