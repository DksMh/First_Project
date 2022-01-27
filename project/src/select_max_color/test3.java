package select_max_color;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

public class test3 {
   static Color[] c = { Color.red, Color.blue, Color.yellow, Color.orange };
   
   static String[] cs = { "RED", "BLUE", "YELLOW", "ORANGE" }; // 정답 Color
   static String[] cs2 = { "빨간색", "파란색", "노란색", "오렌지색" }; // 정답 Text
   Scanner sc= null;
   int[] arr = new int[3];
   
   public test3() {
      sc = new Scanner(System.in);
      
      // 보기로 쓸 배열 뽑기
      for(int i=0; i<arr.length; i++) {
         int n = (int)(Math.random()*4);
         
         arr[i] = n;
         for(int j = 0; j<i; j++) {
            if(n == arr[j]) {
               i--;
               break;
            }
         }
      }
      
      
      // 배열 arr에서 뽑음
      int Arrindex =  (int)(Math.random()*3);
      
      int 정답ColorIndex = arr[Arrindex];

      while(arr[Arrindex] == 정답ColorIndex) {
         정답ColorIndex = (int)(Math.random()*4);
      }
      
      System.out.println("보기>> " + Arrays.toString(arr));
      System.out.println("Arrindex = " + Arrindex);
   
      System.out.println("정답 index = " + arr[Arrindex]);
      System.out.println("정답 text = "+cs2[arr[Arrindex]]);
      
      System.out.println("정답 Color = " + 정답ColorIndex);
      System.out.println("정답 text = "+cs[정답ColorIndex]);
      
      String 정답 = cs2[arr[Arrindex]];
      String 정답Color = cs[정답ColorIndex];
      
      String[] 보기 = {cs2[arr[0]], cs2[arr[1]], cs2[arr[2]]};
      
      System.out.println("=====================================");
      System.out.println("문제 >> "+ 정답+"("+정답Color+")");
      System.out.println("보기 (1."+보기[0] + " 2."+보기[1] + " 3."+보기[2]+")");
      
      System.out.print("정답을 입력해 주세요 >> ");
      int ans = sc.nextInt();
      
      if(정답.equals(보기[ans-1])) {
         System.out.println("정답입니다!");
      }else {
         System.out.println("오답입니다!");
      }
   }
   
   public static void main(String[] args) {
      new test3();
   }
}