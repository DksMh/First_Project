package select_color;

import java.awt.Color;
import java.util.Arrays;
import java.util.Scanner;

public class test3 {
   static Color[] c = { Color.red, Color.blue, Color.yellow, Color.orange };
   
   static String[] cs = { "RED", "BLUE", "YELLOW", "ORANGE" }; // ���� Color
   static String[] cs2 = { "������", "�Ķ���", "�����", "��������" }; // ���� Text
   Scanner sc= null;
   int[] arr = new int[3];
   
   public test3() {
      sc = new Scanner(System.in);
      
      // ����� �� �迭 �̱�
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
      
      
      // �迭 arr���� ����
      int Arrindex =  (int)(Math.random()*3);
      
      int ����ColorIndex = arr[Arrindex];

      while(arr[Arrindex] == ����ColorIndex) {
         ����ColorIndex = (int)(Math.random()*4);
      }
      
      System.out.println("����>> " + Arrays.toString(arr));
      System.out.println("Arrindex = " + Arrindex);
   
      System.out.println("���� index = " + arr[Arrindex]);
      System.out.println("���� text = "+cs2[arr[Arrindex]]);
      
      System.out.println("���� Color = " + ����ColorIndex);
      System.out.println("���� text = "+cs[����ColorIndex]);
      
      String ���� = cs2[arr[Arrindex]];
      String ����Color = cs[����ColorIndex];
      
      String[] ���� = {cs2[arr[0]], cs2[arr[1]], cs2[arr[2]]};
      
      System.out.println("=====================================");
      System.out.println("���� >> "+ ����+"("+����Color+")");
      System.out.println("���� (1."+����[0] + " 2."+����[1] + " 3."+����[2]+")");
      
      System.out.print("������ �Է��� �ּ��� >> ");
      int ans = sc.nextInt();
      
      if(����.equals(����[ans-1])) {
         System.out.println("�����Դϴ�!");
      }else {
         System.out.println("�����Դϴ�!");
      }
   }
   
   public static void main(String[] args) {
      new test3();
   }
}
