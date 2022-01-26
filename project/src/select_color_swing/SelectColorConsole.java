package select_color_swing;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.Icon;

public class SelectColorConsole {
	// ���� ���
//	private static final int RED = 0;
//	private static final int BLUE = 1;
//	private static final int YELLOW = 2;
//	private static final int ORANGE = 3;

	// ���ڿ� ���� ����(����)
	Color[] col = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.magenta};
	
	// ����
//	static String[] cs2 = { "RED", "BLUE", "YELLOW", "ORANGE" };

	// ������ �迭
	 int[] arrBtn = new int[3]; // ��ư�� �迭
	 int[] arrTxt = new int[7]; // ������ �迭

	  int ansColor; // ����(���ڿ� ��ư���� ����)
	  int otherColor; // ������ �ƴ� ��ư����
	  int ansLoc; // ������ġ(������ġ�� �������� �����ϱ����ؼ�)
	  int paintColor; // ���ڻ�
	
	public SelectColorConsole() {
		run();
	}
	
	// �ܼ�â�� ������ �κ����� �ʿ��� �ּ�ó��
	// ���ڸ� ���ڷ� �ٲٱ����� ����
	static int num;
	static int num1;
//	static Scanner scan = new Scanner(System.in);

	// ���� �Լ�
	// ����(=����)�� �����ֱ����� �Լ��̴�
	public String colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 7);
//			System.out.println("arrTxt[i] " + arrTxt[i]);
			ansColor = arrTxt[i];
		}
		
		// �ܼ�â�� ������ �κ����� �ʿ��� �ּ�ó��
		// ���ڸ� ���ڷ�(�ѱ���)
//		System.out.print("���� : ");
		num = ansColor;
		numToColorKor(num);
		return numToColorKor(num);
	}
	
	// ������ �� �Լ�
	public Color paintTxt() {
		while(true) {
			paintColor = (int)(Math.random() * 7); // �÷� ������ ��������
			if(ansColor == paintColor) {
				continue;
			} else {
				break;
			}
		}
		num1 = paintColor;
		return col[num1];
		
		// �ܼ�â�� ������ �κ����� �ʿ��� �ּ�ó��
		// ���ڸ� ���ڷ�
//		System.out.println(paintColor);
//		System.out.print("���� �� : ");
//		num = paintColor;
//		numToColor();
	}

	// ��ġ���� �Լ�
	// �����Լ����� ���� ������ ��ġ�� �����ֱ����� �Լ��̴�
	public void location() {
		ansLoc = (int) (Math.random() * 3); // ������ġ ���� ������
//		System.out.println("ansLoc : " + ansLoc); // ������ġ Ȯ�� �뵵
		System.out.println("������ġ : " + ansLoc);

		arrBtn[ansLoc] = ansColor;
	}

	// ��ư 2���� �� �������� ������
	// ����� ����2���� �ޱ����� �Լ��̴�
	public void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// ������ ������ ��ư�� ���������ϴ� �κ�
			// ����� ������ �ٽ� ���� �ö󰡼� for���� ���ƶ�
			if (i == ansLoc) { 
				continue;
			}

			otherColor = (int) (Math.random() * 7);

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
					break;
				}
			}
//			num = otherColor;
//			numToColor();
		}

		// �ܼ�â�� ������ �κ����� �ʿ��� �ּ�ó��
		// ������ ��ư�� ������ֱ�
		// ���ڸ� ���ڷ�
//		System.out.print("���� : ");
//		for (int i = 0; i < arrBtn.length; i++) {
//			System.out.print(arrBtn[i] + ":");
//			num = arrBtn[i];
//			numToColor();
//		}
	}
	
	// ���ڸ� ����(����)�� �ٲٴ� �Լ�
//	public String numToColor() {
//		if(num == 0) {
//			return "RED";
//		} else if(num == 1) {
//			return "ORANGE";
//		} else if(num == 2) {
//			return "YELLOW";
//		} else if(num == 3) {
//			return "GREEN";
//		} else if(num == 4) {
//			return "BLUE";
//		} else if(num == 5) {
//			return "PINK";
//		} else  {
//			return "MAGENTA"; // ��������� ������ �������ֱ�
//		}
//	}
	
	// ���ڸ� ����(�ѱ���)�� �ٲٴ� �Լ�
	public String numToColorKor(int num) {
		if(num == 0) {
			return "������";
		} else if(num == 1) {
			return "��Ȳ��";
		} else if(num == 2) {
			return "�����";
		} else if(num == 3) {
			return "�ʷϻ�";
		} else if(num == 4) {
			return "�Ķ���";
		} else if(num == 5) {
			return "��ȫ��";
		} else  {
			return "���ֻ�"; // ��������� ������ �������ֱ�
		} 
	}

	public void run() {
		// ���ڰ� ����Ǵ� �κ� - �ϳ��� ���� �ߺ����Ű� �ʿ����
		colorTxt();
		
		// ���ڻ��� ����Ǵ� �κ�
		paintTxt();

		// ������ġ ����
		location();
		
		// ��ư�� ����Ǵ� �κ�
		button();
		
		// �ܼ�â�� ������ �κ����� �ʿ��� �ּ�ó��
		// ���� �ޱ�
//		System.out.print("���� : ");
//		int answer = scan.nextInt();
		
		// �ܼ�â Ȯ�ο�
		// �츮�� swing���� ���콺 �̺�Ʈ�� ������ ���� ������ ���� �Ű� �Ƚᵵ�ȴ�~
//		if(answer == ansLoc) {
//			System.out.println("�����Դϴ�~!!");
//		} else {
//			System.out.println("�����Դϴ٤�");
//		}
	}
	
}