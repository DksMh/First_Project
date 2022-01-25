package select_color;

import java.awt.Color;

public class test1 {
	// 색깔 상수
	private static final int RED = 0;
	private static final int BLUE = 1;
	private static final int YELLOW = 2;
	private static final int ORANGE = 3;

	static Color[] c = { Color.red, Color.blue, Color.yellow, Color.orange };
	static String[] cs = { "RED", "BLUE", "YELLOW", "ORANGE" };

	// 색의 배열
	static int[] arrBtn = new int[3]; // 버튼의 배열
	static int[] arrTxt = new int[4]; // 글자의 배열

	static int ansColor; // 정답(글자와 버튼색이 같음)
	static int ansLoc; // 정답위치(정답위치가 일정함을 방지하기위해서)

	static int btn1; // 정답이 아닌 위치버튼
	static int btn2;

	// 색배열, 글자배열 => 일단 4개로 해서 테스트 하기(나중에는 7개정도)

	// 버튼 색도 랜덤으로 돌리기
	// 중복제거

	// 글씨를 랜덤으로 돌리기
	// 단, 글씨의 색이 버튼 3개중에 한개여야한다
	// 버튼색이 같으면 안된다(중복체크)
	// 글씨와 색이 맞는거 검사하기

	// 글씨랑 색을 상수로 선언을 한뒤에 출력된 값에 대입하기
	// 랜덤으로 뽑은 글씨를 같은 색의 버튼으로 지정, 2개만 랜덤으로 돌리기
	// 글자색은 정답인 색이 아니면 들어갈수있게

	// 글자
	public static void colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 4);
			System.out.println("arrTxt[i] " + arrTxt[i]);
			ansColor = arrTxt[i];
		}
	}

	// 위치선정하기
	public static void location() {
		ansLoc = (int) (Math.random() * 3); // 저장위치 랜덤 돌리기
		System.out.println("ansLoc : " + ansLoc); // 저장위치 확인 용도

		arrBtn[ansLoc] = ansColor;
	}

	// 버튼 2개의 색 랜덤으로 돌리기
	public static void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// 정답을 제외한 버튼의 색깔지정하는 부분
			if (i == ansLoc) {
				continue;
			}

			int a = (int) (Math.random() * 4);

			if (ansColor == a) {
				i--;
				continue;
			}
			arrBtn[i] = a;
			for (int j = 0; j < i; j++) {
				if (arrBtn[j] == arrBtn[i]) {
					i--;
					continue;
				}
			}

		}

		for (int i = 0; i < arrBtn.length; i++) {
			System.out.print(arrBtn[i] + " ");
		}

	}

	public static void main(String[] args) {
		// 글자가 실행되는 부분 - 하나만 나와 중복제거가 필요없다
		colorTxt();
		System.out.println();

		// 저장위치 선정
		location();
		System.out.println();

		// 버튼이 실행되는 부분
		button();
	}
}
