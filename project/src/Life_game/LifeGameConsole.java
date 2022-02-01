package Life_game;

public class LifeGameConsole {
	// 답 순서를 담아놓은 배열
	String[][] Arr = { { "전자레인지를 킨다", "음식을 넣는다", "동작 버튼을 누른다", "음식을 꺼낸다" },
			{ "TV의 플러그를 킨다", "리모컨으로 화면을 킨다", "원하는 채널로 이동한다", "전원을 끈다" }, 
			{ "선풍기의 코드를 꼽는다", "전원을 킨다", "풍속을 조절한다", "전원을 끈다" },
			{ "가스레인지 벨브를 연다", "가스를 킨다", "가스를 끈다", "벨브를 잠근다" },
			{ "청소기의 전원을 킨다", "청소를 한다", "전원을 끈다", "원래 자리로 돌려 놓는다" },
			{ "세탁기의 전원을 킨다", "빨래를 집어 넣는다", "빨래 시작을 누른다", "빨래를 말린다" },
			{ "드라이어기의 전원을 킨다", "풍속을 조절한다", "머리를 말린다", "전원을 끈다" },
			{ "다리미의 전원을 킨다", "분무기로 물을 뿌린다", "옷을 다린다", "전원을 끈다" },
			{ "믹서기 안에 재료를 넣는다", "전원을 킨다", "음식을 간다", "전원을 끈다" }, 
			{ "선풍기의 전원을 킨다", "풍속을 조절한다", "전원을 끈다", "플러그를 뺀다" },
			{ "식기세척기안에 그릇을 넣는다", "전원을 누른다", "그릇을 뺀다", "제자리에 놓는다" },
			{ "변기의 뚜껑을 연다", " 볼일을 본다", "레버로 물을 내린다", "손을 씻는다"} }; // 12개

	// 문항을 담는 변수
	String[] answer = new String[12];

	// int k = 1; // 문항 번호을 결정하는 변수(랜덤으로 돌면 될거같다)
	int k = (int) (Math.random() * 12); // 문항을 랜덤으로 뽑는다.
	int count = 0;
	
	// 중복 체크 - 만약 Arr[0]이 나왔는데 다음 문제도 Arr[0]이 나오는 경우 생각하기
	String[] a = new String[12];
	int[] b = new int[4];
	int r;

	public LifeGameConsole() {
		run();
	}

	public void run() {
		answer();

		quiz();
	}
	
	public void answerArr() { // 정답를 뽑기위한 함수
		// 이중 for문을 쓴 이유
		// Arr[][]가 이차원 배열이여서
		int n = 0;
		for (int i = k; i < k + 1; i++) { // 문항뽑기
			for (int j = 0; j < 4; j++) { // 문항안 정답 뽑기
				a[count++] = Arr[i][j]; // 문항 정답을 차례대로 보여준다
				System.out.println(a[j]);
			}
			System.out.println();
		}
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
		case 4:
			answerArr();
			answer[4] = a[4];
			break;
		case 5:
			answerArr();
			answer[5] = a[5];
			break;
		case 6:
			answerArr();
			answer[6] = a[6];
			break;
		case 7:
			answerArr();
			answer[7] = a[7];
			break;
		case 8:
			answerArr();
			answer[8] = a[8];
			break;
		case 9:
			answerArr();
			answer[9] = a[9];
			break;
		case 10:
			answerArr();
			answer[10] = a[10];
			break;
		}
	}

	public void quiz() {
//		int[] b = new int[4];

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
		new LifeGameConsole();
	}

}
