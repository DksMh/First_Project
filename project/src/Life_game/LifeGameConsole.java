package Life_game;

public class LifeGameConsole {
	// 답 순서를 담아놓은 배열
	String[][] Arr = { { "전원을 킨다", "음식을 넣는다", "동작 버튼을 누른다", "음식을 꺼낸다" },
			{ "플러그를 꽂는다", "리모컨으로 화면을 킨다", "원하는 채널로 이동한다", "전원을 끈다" }, 
			{ "코드를 꽂는다", "전원을 킨다", "풍속을 조절한다", "전원을 끈다" },
			{ "벨브를 연다", "가스를 킨다", "가스를 끈다", "벨브를 잠근다" },
			{ "전원을 킨다", "청소를 한다", "전원을 끈다", "원래 자리로 돌려 놓는다" },
			{ "전원을 킨다", "빨래를 집어 넣는다", "빨래 시작을 누른다", "빨래를 말린다" },
			{ "전원을 킨다", "풍속을 조절한다", "머리를 말린다", "전원을 끈다" },
			{ "전원을 킨다", "분무기로 물을 뿌린다", "옷을 다린다", "전원을 끈다" },
			{ "재료를 넣는다", "전원을 킨다", "음식을 간다", "전원을 끈다" }, 
			{ "그릇을 넣는다", "전원을 누른다", "그릇을 뺀다", "제자리에 놓는다" },
			{ "뚜껑을 연다", " 볼일을 본다", "레버로 물을 내린다", "손을 씻는다"} }; // 11개
	
	// 게임의 제목
	String[] ArrLabel = {"전자레인지", "TV", "선풍기", "가스레인지", "청소기", "세탁기", "드라이어기", "다리미", "믹서기", "식기세척기", "변기"};

//	int k = 1; // 문제 번호을 결정하는 변수(랜덤으로 돌면 될거같다)
	int k = (int) (Math.random() * 11); // 문제을 랜덤으로 뽑는다.
	
	// 답이 2차원 배열이여서 한문제을 뽑아내기 위해 선언한 1차원배열 a
	String[] a = new String[11];
	// 랜덤으로 순서를 바꾸기 위한 b
	int[] b = new int[4];

	public LifeGameConsole() {
		run();
	}

	public void run() {
		answerArr();

		quiz();
	}
	
	public void answerArr() { // 정답를 뽑기위한 함수
		int count = 0;
		for (int i = k; i < k + 1; i++) { // 문제뽑기
			for (int j = 0; j < 4; j++) { // 문제안 정답 뽑기
				a[count++] = Arr[i][j]; // 문제 정답을 차례대로 보여준다
//				System.out.println(a[j]);
			}
//			System.out.println();
		}
	}

	public void quiz() { // 문제를 뽑기위한 함수
		for (int i = 0; i < 4; i++) {
			int r = (int) (Math.random() * 4);
			b[i] = r;
			for (int j = 0; j < i; j++) {
				if (b[j] == b[i]) {
					i--;
					break;
				} 
			}
//			System.out.println(a[b[0]]);
		}
//		for(int i = 0; i < 4; i++) {
//			System.out.println(a[b[i]]);
//		}
	}

	public static void main(String[] args) {
		new LifeGameConsole();
	}
}