package org.proj.game.life;

import java.util.Arrays;
import java.util.HashMap;

public class LifeGameConsole {
	// 답 순서를 담아놓은 배열
		String[][] Arr = { { "전원을 킨다", "음식을 넣는다", "동작 버튼을 누른다", "음식을 꺼낸다" },
				{ "플러그를 꼽는다", "리모컨으로 화면을 킨다", "원하는 채널로 이동한다", "전원을 끈다" }, 
				{ "코드를 꼽는다", "전원을 킨다", "풍속을 조절한다", "전원을 끈다" },
				{ "벨브를 연다", "가스를 킨다", "가스를 끈다", "벨브를 잠근다" },
				{ "전원을 킨다", "청소를 한다", "전원을 끈다", "원래 자리로 돌려 놓는다" },
				{ "전원을 킨다", "빨래를 집어 넣는다", "빨래 시작을 누른다", "빨래를 말린다" },
				{ "전원을 킨다", "풍속을 조절한다", "머리를 말린다", "전원을 끈다" },
				{ "전원을 킨다", "분무기로 물을 뿌린다", "옷을 다린다", "전원을 끈다" },
				{ "재료를 넣는다", "전원을 킨다", "음식을 간다", "전원을 끈다" }, 
				{ "그릇을 넣는다", "전원을 누른다", "그릇을 뺀다", "제자리에 놓는다" },
				{ "뚜껑을 연다", " 볼일을 본다", "레버로 물을 내린다", "손을 씻는다"} }; // 12개
		
		String[] ArrLabel = {"전자레인지", "TV", "선풍기", "가스레인지", "청소기", "세탁기", "드라이어기", "다리미", "믹서기", "식기세척기", "변기"};
		
		
		
		HashMap<String, String[]> map = new HashMap<>();
	
		// 문항을 담는 변수
//		String[] answer = new String[11];

//		int k = 1; // 문항 번호을 결정하는 변수(랜덤으로 돌면 될거같다)
		int k = (int) (Math.random() * 11); // 문항을 랜덤으로 뽑는다.
		int count = 0;
		
		// 중복 체크 - 만약 Arr[0]이 나왔는데 다음 문제도 Arr[0]이 나오는 경우 생각하기
//		String[] showAnswer = new String[11];
		int[] showQuiz = new int[4];
		String cate;
		
		String[] ansArr = new String[4];
		public LifeGameConsole() {
			for(int i = 0; i<ArrLabel.length; i++ ) {
				map.put(ArrLabel[i], Arr[i]);
			}
			
			run();
		}

		public void run() {
			answerArr();

			quiz();
		}
		
		public void answerArr() { // 정답를 뽑기위한 함수
//			// 이중 for문을 쓴 이유
//			// Arr[][]가 이차원 배열이여서
//			int n = 0;
//			for (int i = k; i < k + 1; i++) { // 문항뽑기
//				for (int j = 0; j < 4; j++) { // 문항안 정답 뽑기
//					showAnswer[count++] = Arr[i][j]; // 문항 정답을 차례대로 보여준다
//				}
//			}
			
			int r = (((int)(Math.random() *1000000)) % map.size());
			
			cate = ArrLabel[r];
			ansArr = map.get(cate);
			System.out.println("cate : "+cate);
			System.out.println(Arrays.toString(ansArr));
		}


		public void quiz() {
			for (int i = 0; i < 4; i++) {
				int r = (int) (Math.random() * 4);
				showQuiz[i] = r;
				for (int j = 0; j < i; j++) {
					if (showQuiz[j] == showQuiz[i]) {
						i--;
						break;
					} 
				}
			}
		}
		public static void main(String[] args) {
			new LifeGameConsole();
		}

}
