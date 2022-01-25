package select_color_swing;

import java.awt.Color;
import java.util.Scanner;

public class test {	// ���� ���
//	private static final int RED = 0;
//	private static final int BLUE = 1;
//	private static final int YELLOW = 2;
//	private static final int ORANGE = 3;

	// ���ڿ� ���� ����(����)
	static Color[] c = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.magenta};
	
	// ����
//	static String[] cs2 = { "RED", "BLUE", "YELLOW", "ORANGE" };

	// ������ �迭
	static int[] arrBtn = new int[3]; // ��ư�� �迭
	static int[] arrTxt = new int[7]; // ������ �迭

	static int ansColor; // ����(���ڿ� ��ư���� ����)
	static int ansLoc; // ������ġ(������ġ�� �������� �����ϱ����ؼ�)
	static int paintColor; // ���ڻ�
	
	// ���ڸ� ���ڷ� �ٲٱ����� ����
	static int num;
	
	static Scanner scan = new Scanner(System.in);

	// ���� �Լ�
	// ����(=����)�� �����ֱ����� �Լ��̴�
	public static void colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 7);
//			System.out.println("arrTxt[i] " + arrTxt[i]);
			ansColor = arrTxt[i];
		}
		// ���ڸ� ���ڷ�(�ѱ���)
		System.out.print("���� : ");
		num = ansColor;
		numToColorKor();
	}
	
	// ������ �� �Լ�
	public static void paintTxt() {
		while(true) {
			paintColor = (int)(Math.random() * 7); // �÷� ������ ��������
			if(ansColor == paintColor) {
				continue;
			} else {
				break;
			}
		}
		// ���ڸ� ���ڷ�
//		System.out.println(paintColor);
		System.out.print("���� �� : ");
		num = paintColor;
		numToColor();
	}

	// ��ġ���� �Լ�
	// �����Լ����� ���� ������ ��ġ�� �����ֱ����� �Լ��̴�
	public static void location() {
		ansLoc = (int) (Math.random() * 3); // ������ġ ���� ������
//		System.out.println("ansLoc : " + ansLoc); // ������ġ Ȯ�� �뵵
		System.out.println("������ġ : " + ansLoc);

		arrBtn[ansLoc] = ansColor;
	}

	// ��ư 2���� �� �������� ������
	// ����� ����2���� �ޱ����� �Լ��̴�
	public static void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// ������ ������ ��ư�� ���������ϴ� �κ�
			// ����� ������ �ٽ� ���� �ö󰡼� for���� ���ƶ�
			if (i == ansLoc) { 
				continue;
			}

			int otherColor = (int) (Math.random() * 7);

			// �������� ������ ������ �ٽ� ���� �ö󰡼� for���� ���ƶ�
			if (ansColor == otherColor) { 
				i--;
				continue;
			}
			arrBtn[i] = otherColor;
			// ���� ������ �װͿ� ���� �ߺ�üũ���ֱ�
			for (int j = 0; j < i; j++) { // j < i => i = ���� ���� ������, j = ���� ���� ������
				if (arrBtn[j] == arrBtn[i]) {
					i--;
					continue;
				}
			}
//			num = otherColor;
//			numToColor();
		}

		// ������ ��ư�� ������ֱ�
		// ���ڸ� ���ڷ�
		System.out.print("���� : ");
		for (int i = 0; i < arrBtn.length; i++) {
//			System.out.print(arrBtn[i] + ":");
			num = arrBtn[i];
			numToColor();
		}
	}
	
	// ���ڸ� ����(����)�� �ٲٴ� �Լ�
	public static void numToColor() {
		switch(num) {
		case 0:
			System.out.print("RED ");
			break;
		case 1:
			System.out.print("ORANGE ");
			break;
		case 2:
			System.out.print("YELLOW ");
			break;
		case 3:
			System.out.print("GREEN ");
			break;
		case 4:
			System.out.print("BLUE ");
			break;
		case 5:
			System.out.print("PINK ");
			break;
		case 6:
			System.out.print("MAGENTA "); // ��������� ������ �������ֱ�
			break;
		}
	}
	
	// ���ڸ� ����(�ѱ���)�� �ٲٴ� �Լ�
	public static void numToColorKor() {
		switch(num) {
		case 0:
			System.out.print("������ ");
			break;
		case 1:
			System.out.print("��Ȳ�� ");
			break;
		case 2:
			System.out.print("����� ");
			break;
		case 3:
			System.out.print("�ʷϻ� ");
			break;
		case 4:
			System.out.print("�Ķ��� ");
			break;
		case 5:
			System.out.print("��ȫ�� ");
			break;
		case 6:
			System.out.print("���ֻ� "); // ��������� ������ �������ֱ�
			break;
		}
	}

	public static void main(String[] args) {
		// ���ڰ� ����Ǵ� �κ� - �ϳ��� ���� �ߺ����Ű� �ʿ����
		colorTxt();
		System.out.println();
		
		// ���ڻ��� ����Ǵ� �κ�
		paintTxt();
		System.out.println();

		// ������ġ ����
		location();
		System.out.println();

		// ��ư�� ����Ǵ� �κ�
		button();
		System.out.println();
		
		// ���� �ޱ�
		System.out.print("���� : ");
		int answer = scan.nextInt();
		
		// �ܼ�â Ȯ�ο�
		// �츮�� swing���� ���콺 �̺�Ʈ�� ������ ���� ������ �Ű� �Ƚᵵ �ȴ�~
		if(answer == ansLoc) {
			System.out.println("�����Դϴ�~!!");
		} else {
			System.out.println("�����Դϴ٤�");
		}
	}
}