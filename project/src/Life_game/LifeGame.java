package Life_game;

public class LifeGame {
	String[][] Arr = { { "전자레인지를 킨다", "전자레인지에 음식을 넣는다", "동작 버튼을 누른다", "음식을 꺼낸다" },
			{ "TV의 전원을 킨다", "리모컨 빨간버튼을 누른다", "원하는 채널로 이동한다", "전원을 끈다" }, 
			{ "2번 0", "2번 1", "2번 2", "2번 3" },
			{ "3번 0", "3번 1", "3번 2", "3번 3" } }; // 답 순서를 담아놓은 배열

	// 문항을 담는 변수
	String[] answer = new String[4];

	int k = (int) (Math.random() * 4); // 문항을 랜덤으로 뽑는다.
	// int k = 1; // 문항 번호을 결정하는 변수(랜덤으로 돌면 될거같다)
	int count = 0;
	// 중복 체크 - 만약 Arr[0]이 나왔는데 다음 문제도 Arr[0]이 나오는 경우 생각하기
	String[] a = new String[4];
	int r;

	public LifeGame() {
		run();
	}

	public void run() {
		answer();

		quiz();
	}

	public void answer() {
		switch (k) {
		case 0:
			answerArr();
			answer[0] = a[0];
			break;
		case 1:
			answerArr();
			answer[1] = a[1];
			break;
		case 2:
			answerArr();
			answer[2] = a[2];
			break;
		case 3:
			answerArr();
			answer[3] = a[3];
			break;
		}
	}

	public void answerArr() { // 정답를 뽑기위한 함수
		// 이중 for문을 쓴 이유
		// Arr[][]가 이차원 배열이여서
		for (int i = k; i < k + 1; i++) { // 문항뽑기
			for (int j = 0; j < 4; j++) { // 문항안 정답 뽑기
				a[count++] = Arr[i][j]; // 문항 정답을 차례대로 보여준다
				System.out.println(a[j]);
			}
			System.out.println();
		}
	}

	public void quiz() {
		int[] b = new int[4];

		for (int i = 0; i < 4; i++) {
			r = (int) (Math.random() * 4);
			b[i] = r;
			for (int j = 0; j < i; j++) {
				if (b[j] == b[i]) {
					i--;
					break;
				} 
			}
		}
		for(int i = 0; i < 4; i++) {
			System.out.println(a[b[i]]);
		}
	}

	public static void main(String[] args) {
		new LifeGame();
	}

}
