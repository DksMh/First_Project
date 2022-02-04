package max_colors;

import java.awt.Color;

public class MaxColorConsole {
	// 색깔배열
	Color[] col = { new Color(233, 23, 22), new Color(81, 107, 254), new Color(254, 228, 55) };

	// 색이 콘솔창에 찍힌 수 (= count)
	int c0 = 0;
	int c1 = 0;
	int c2 = 0;

	// 정답
	String ans;

	// 색의 배열 (3X3)
	int[] arr = new int[9];

	public MaxColorConsole() {
		run();
	}

	public void run() {
		// 3*3인 2차원 배열을 만들고
		// 변수를 선언후 색깔변수 갯수 세기
		// 정수를 랜덤하게 생성해 저장
		setArr();

		// 3x3 arr 결정됐고, c0, c1, c2의 갯수를 나타내는 값이 정해졌다.

		while (true) {
			// 예외 발생 처리
			// 3중 하나가 한번도 안나왔을 때
			// 3 3 3 이 나왔을때
			// 4 4 1 이 나왔을때 (3가지의 경우가 발생)
			if (!((c0 == 0 || c1 == 0 || c2 == 0) || ((c0 == c1) && (c1 == c2)) || ((c0 == 1) && (c1 == c2))
					|| ((c1 == 1) && (c0 == c2)) || ((c2 == 1) && (c0 == c1)))) {
				break;
			}

			// 다시 arr 만든다.
			setArr();
		}

		MaxAns();

		// 갯수 확인
//		System.out.println("RED의 개수 : " + c0);
//		System.out.println("BLUE의 개수 : " + c1);
//		System.out.println("YELLOW의 개수 : " + c2);

	}

	public void MaxAns() {
		int max;
		
		// 정답 출력
		max = c0;

		ans = "RED";
		if (max < c1) {
			max = c1;
			ans = "BLUE";
		}

		if (max < c2) {
			max = c2;
			ans = "YELLOW";
		}
//		System.out.println("정답 : " + ans);
	}

	// 배열 만들고 갯수 세기
	public void setArr() {
		// 초기화 해주기
		c0 = 0;
		c1 = 0;
		c2 = 0;

		// 배열 랜덤수 받기
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);

			if (arr[i] == 0) {
				c0++;
			} else if (arr[i] == 1) {
				c1++;
			} else {
				c2++;
			}
//			System.out.print(arr[i]);
		}
//		System.out.println(" ");
	}

}