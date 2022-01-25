//package select_color;
//
//public class test2 {// 1. 3칸짜리 배열을 만들어요
//	int[] arr = int[3];
//	// 2. 숫자 3개를 뽑는데 중복없이 0~3 왜냐면 선택지가 4개니까.
//	for(int i =0; i<3; i++) {
//	   int n =  (int)(Math.random()*4);
//	   
//	   for(int j =0; j<i; j++) {
//	      if(arr[i] != arr[j]) {
//	         arr[i] = n;         
//	      }
//	   }
//	}
//	// 2번결과
//	// 얘네가 cs인덱스 (보기) arr은 보기 
//	arr = [1, 0, 2];
//
//
//	// 정답결정
//	// 텍스트를 먼저 뽑아요 (실질적 정답)
//	int num2 = (int)(Math.random()*3); // 3에서뽑는 이유는 arr에서 뽑으려고
//	예)
//	num2 = 1
//	arr[1](==arr[num2])의 값이 정답이에요. => arr[1] = 0;
//	===> 답은 0번이고 cs[0]( = cs[arr[num2]]) 번 인덱스에 있는 값이 정답.
//
//
//	// 칼라결정
//	int num1 = (int)(Math.random()*4);
//	// num2와 일치하면 다시 결정하고 아니면 탈출
//	while(true) {
//	   if (num2 != num1) {
//	      break;
//	   }
//	   num1 = (int)(Math.random()*4);
//	}
//
//	// 보기 배열 완성(인덱스), 정답(인덱스) 뽑앗고, 정답 색깔(인덱스)도 나옴....
//
//	// 이제 할건 인덱스로 뽑은애들을 우리가쓸 텍스트나 색갈로 바꿔줘요;
//
//	// 정답 버튼 색
//	int ansColor = c[num2];
//	// 그외 보기색
//
//	// 보기 버튼을 결정
//	for (int i =0 i<3; i++) {
//	   arrBtn[i] = arr[i];
//	}
//
//	// 정답을 항상 기억해야돼요. 정답에 들어갈 색갈 기억해야되고
//
//	만약 버튼을 구현할때 arr[i]가 보기였잖아요
//	// 위에서 정답위치라고 뽑은애가 있고, 보기 배열이 있으니까 하나로 만들어주려고
//	// 정답이 뭔지를 어떤걸로표시해야되고 그때의 값을 알아야되잖아요.
//
//	Button이나
//	int 이나
//	String 으로
//	cs[0]( = cs[arr[num2]])이걸 보기쉽게 만들어주는거죠
//
//	명확하게 정답을 기억하기 쉽게 만들어주라는거죠
//
//	int ansNum = cs[arr[num2]];
//	JButton 이면 이 버튼이 눌리면 정답이고 아니면 오답이잖아요
//	int면 이숫자랑 내가 선택한 숫자랑 같으면 답 아니면 오답
//	String equals 써서 일치하면 답 아니면 오답
//
//
//	// 정답을 정하는 함수
//	public static void colorTxt() {
//	   for (int i = 0; i < 1; i++) {
//	      arrTxt[i] = (int) (Math.random() * 4);
//	      System.out.println("arrTxt[i] "+arrTxt[i]);
//	      ansColor = arrTxt[i];
//	   }
//	}
//
//	// 위치를 정하는 함수.
//	public static void location() {
//	   ansLoc = (int) (Math.random() * 3); // 저장위치 랜덤 돌리기
//	   System.out.println("ansLoc : " + ansLoc); // 저장위치 확인 용도
//	   
//	   // 정답이 아닐때를 생각해요.
//	   for (int i = 0; i < arrBtn.length; i++) {
//	      // 정답의 위치를 선정하기위한 부분
//	      
//	      // 예를들어 ansLoc = 1
//	      if (ansLoc == i) {
//	         arrBtn[i] = ansColor; // 정답위치 설정
//	         a = i;
//	      } else {
//	         // 여기가 정답이 아닌 위치에 값을 넣어줘야한다.
//	         // i = 0, 2 일때 답을 넣어줘야함.
//	         // 위에 보기에 들어갈 색들이 있었다. 여기서 정답을 제외한 색이
//	         // 들어가되잖아요
//	         // arrBtn[] << 보기가 들어갈 배열
//	         // arrBtn[0],  arrBtn[1] = ansColor,   arrBtn[2]
//	         // for문 0부터 시작
//	         // i == 0일때 생각할게 color가 ansColor 랑 다르면되요.
//	         
//	         
//	      }
//	   }
//	}
//	}
//}