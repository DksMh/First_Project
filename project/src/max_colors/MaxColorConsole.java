package max_colors;

public class MaxColorConsole {
	// 색깔 상수
	private static final int RED = 0;
	private static final int BLUE = 1;
	private static final int YELLOW = 2;

	private static final int MIN = 1;
	private static final int MAX = 7;

	// 색이 콘솔창에 찍힌 수 (= count)
	static int c0 = 0;
	static int c1 = 0;
	static int c2 = 0;
	// 색의 배열 (3X3)
	static int[][] arr = new int[3][3];

	public static void main(String[] args) {
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
		// 갯수 확인
		System.out.println("RED의 개수 : " + c0);
		System.out.println("BLUE의 개수 : " + c1);
		System.out.println("YELLOW의 개수 : " + c2);

		// 정답 출력
		int max = c0;
		String ans = "RED";
		if (max < c1) {
			max = c1;
			ans = "BLUE";
		}

		if (max < c2) {
			max = c2;
			ans = "YELLOW";
		}

		System.out.println("정답 : " + ans);
	} 
	// 배열 만들고 갯수 세기
	public static void setArr() {
		// 초기화 해주기
		c0 = 0;
		c1 = 0;
		c2 = 0;
		// 배열 랜덤수 받기
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (int) (Math.random() * 3);

				if (arr[i][j] == 0) {
					c0++;
				} else if (arr[i][j] == 1) {
					c1++;
				} else {
					c2++;
				}
				System.out.print(arr[i][j]);
			}
			System.out.println(" ");

		}
	}

}