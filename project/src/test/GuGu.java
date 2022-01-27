package test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

class 문제 {
	int[] 문제배열 = new int[4];
	int 정답;
	boolean tf = false;
	int[] 보기배열 = new int[4];
	char[] 연산 = new char[3];
	int 보기인덱스 = (int) (Math.random() * 4);
	String 식;

	public 문제() {
		// TODO Auto-generated constructor stub
		// 문제에 들어갈 숫자를 선택(1 ~ 20까지의 수)
		for (int i = 0; i < 4; i++) {
			int num = (int) (Math.random() * 20 + 1);
			문제배열[i] = num;
		}

		int 횟수 = 0;
		for (int i = 0; i < 3; i++) {
			int n = (int) (Math.random() * 1000 + 1);
			if (n % 2 == 0) {
				tf = true;
				횟수++;
			} else {
				tf = false;
				횟수--;
			}

			연산[i] = (tf) ? '+' : '-';

			if (횟수 == 3 || 횟수 == -3) {
				i = -1;
				횟수 = 0;
			}
		}

		정답 = 문제배열[0];
		for (int i = 0; i < 3; i++) {
			if (연산[i] == '+')
				정답 += 문제배열[i + 1];
			else
				정답 -= 문제배열[i + 1];
		}

		식 = String.format("%d %c %d %c %d %c %d = ?", 문제배열[0], 연산[0], 문제배열[1], 연산[1], 문제배열[2], 연산[2], 문제배열[3]);

		int p = (int) (Math.random() * 4);
		for (int i = 0; i < 4; i++) {
			if (p == i) {
				보기배열[i] = 정답;
			} else {
				보기배열[i] = (int) (Math.random() * 100000 % 40);
			}
		}
	}

}

public class GuGu {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Vector 문제백터 = null;

		문제백터 = 문제생성();

		게임시작(문제백터);
	}

	static Vector 문제생성() {
		Vector<문제> qVector = new Vector<>();
		for (int i = 0; i < 10; i++) {
			문제 quest = new 문제();
			if (quest.정답 < 0) {
				i--;
				continue;
			}
			qVector.add(quest);
		}

		return qVector;
	}

	static void 게임시작(Vector 문제뭉치) {
		String[] sArr = new String[10];
		int ok = 0;
		Vector<문제> vector = new Vector<>();

		Iterator it = 문제뭉치.iterator();
		int i = 0;
		while (it.hasNext()) {
			문제 quest = (문제) it.next();

			System.out.println("==============================");
			System.out.println((i + 1) + "번째 문제!");
			System.out.println("==============================");
			System.out.println("식 >> " + quest.식);
			System.out.println("보기 >> " + Arrays.toString(quest.보기배열));
			System.out.print("정답은 >> ");
			int num = sc.nextInt();

			if (quest.정답 == quest.보기배열[num - 1]) {
				System.out.println("정답입니다.");
				sArr[i] = "정답";
				ok++;
			} else {
				System.out.println("오답입니다!");
				sArr[i] = "오답";
				vector.add(quest);
			}
			i++;
			System.out.println();

		}
		System.out.println("정답표\n" + Arrays.toString(sArr));
		System.out.println("정답률은 " + (ok * 10) + "%입니다!\n");

		boolean flag = 다시();

		if (flag)
			게임재시작(vector);
		else
			System.out.println("게임종료!");

	}

	static void 게임재시작(Vector 문제뭉치) {
		System.out.println("오탑 노트입니다!");

		String[] sArr = new String[문제뭉치.size()];
		Vector<문제> vector = new Vector<>();

		Iterator it = 문제뭉치.iterator();
		int i = 0;
		while (it.hasNext()) {
			문제 quest = (문제) it.next();

			System.out.println("==============================");
			System.out.println((i + 1) + "번째 문제!");
			System.out.println("==============================");
			System.out.println("식 >> " + quest.식);
			System.out.println("보기 >> " + Arrays.toString(quest.보기배열));
			System.out.print("정답은 >> ");
			int num = sc.nextInt();

			if (quest.정답 == quest.보기배열[num - 1]) {
				System.out.println("정답입니다.");
				sArr[i] = "정답";
			} else {
				System.out.println("오답입니다!");
				sArr[i] = "오답";
				vector.add(quest);
			}
			i++;
			System.out.println();
		}

		System.out.println("정답표\n" + Arrays.toString(sArr));

		boolean flag = 다시();

		if (flag)
			게임재시작(vector);
		else
			System.out.println("게임종료!");

	}

	static boolean 다시() {
		System.out.print("틀린문제를 다시 풀겠습니까?? (y or n) >> ");
		char c = sc.next().charAt(0);
		while (!(c == 'y' || c == 'Y' || c == 'n' || c == 'N')) {
			System.out.print("y 또는 n만 선택하세요! 다시 입력>>");
			c = sc.next().charAt(0);
		}
		if (c == 'y' || c == 'Y')
			return true;
		else
			return false;
	}

	public static void test02(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] sArr = new String[10];
		int ok = 0;
		Vector<문제> vector = new Vector<>();

		for (int i = 0; i < 10; i++) {

			문제 quest = new 문제();
			if (quest.정답 < 0) {
				i--;
				continue;
			}
			System.out.println("==============================");
			System.out.println((i + 1) + "번째 문제!");
			System.out.println("==============================");
//      System.out.println("문제 배열 >> "+Arrays.toString(quest.문제배열));
			System.out.println("식 >> " + quest.식);
			System.out.println("보기 >> " + Arrays.toString(quest.보기배열));
			System.out.print("정답은 >> ");
			int num = sc.nextInt();

			if (quest.정답 == quest.보기배열[num - 1]) {
				System.out.println("정답입니다.");
				sArr[i] = "정답";
				ok++;
			} else {
				System.out.println("오답입니다!");
				sArr[i] = "오답";
				vector.add(quest);
			}
			System.out.println();
		}

		System.out.println("정답표\n" + Arrays.toString(sArr));
		System.out.println("정답률은 " + (ok * 10) + "%입니다!\n");

		Iterator it = vector.iterator();
		int ii = 1;
		System.out.println("오답문제 입니다!");
		while (it.hasNext()) {

			문제 q = (문제) it.next();
			System.out.println("=====================");
			System.out.println((ii++) + "번째 문제");
			System.out.println(q.식);
		}
	}

	public static void test01() {
		// 문제 최대 숫자 4개
		// 숫자의 범위 : 0~10
		// 정답
		// 보기

		int[] qArr = new int[4];
		int ans;
		boolean tf = false;
		int[] lookArr = new int[4];
		char[] pm = new char[3];
		for (int i = 0; i < 4; i++) {
			int num = (int) (Math.random() * 10 + 1);
			qArr[i] = num;
		}
		int 횟수 = 0;
		for (int i = 0; i < 3; i++) {
			int n = (int) (Math.random() * 1000 + 1);
			if (n % 2 == 0) {
				tf = true;
				횟수++;
			} else {
				tf = false;
				횟수--;
			}

			pm[i] = (tf) ? '+' : '-';

			if (횟수 == 3 || 횟수 == -3) {
				i = -1;
				횟수 = 0;
			}
		}

		System.out.println(Arrays.toString(qArr));
		System.out.println(Arrays.toString(pm));
		System.out.println(횟수);

		int sum = qArr[0];
		for (int i = 0; i < 3; i++) {
			if (pm[i] == '+')
				sum += qArr[i + 1];
			else
				sum -= qArr[i + 1];
		}
		String str;
		str = String.format("%d %c %d %c %d %c %d = %d\n", qArr[0], pm[0], qArr[1], pm[1], qArr[2], pm[2], qArr[3],
				sum);
		System.out.printf("%d %c %d %c %d %c %d = %d\n", qArr[0], pm[0], qArr[1], pm[1], qArr[2], pm[2], qArr[3], sum);
		System.out.println(str);
		int p = (int) (Math.random() * 4);
		for (int i = 0; i < 4; i++) {
			if (p == i) {
				lookArr[i] = sum;
			} else {
				lookArr[i] = (int) (Math.random() * 100000 % 40);
			}
		}

		System.out.println(Arrays.toString(lookArr));
	}
}