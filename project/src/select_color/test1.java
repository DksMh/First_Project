package select_color;

import java.awt.Color;

public class test1 {
	// ���� ���
	private static final int RED = 0;
	private static final int BLUE = 1;
	private static final int YELLOW = 2;
	private static final int ORANGE = 3;

	static Color[] c = { Color.red, Color.blue, Color.yellow, Color.orange };
	static String[] cs = { "RED", "BLUE", "YELLOW", "ORANGE" };

	// ���� �迭
	static int[] arrBtn = new int[4]; // ��ư�� �迭
	static int[] arrTxt = new int[4]; // ������ �迭

	static int ansColor; // ����(���ڿ� ��ư���� ����)
	static int ansLoc; // ������ġ(������ġ�� �������� �����ϱ����ؼ�)
	
	static int a =0; // ���������ϴ� ��
	static int btn1; // ������ �ƴ� ��ġ��ư
	static int btn2;

	// ���迭, ���ڹ迭 => �ϴ� 4���� �ؼ� �׽�Ʈ �ϱ�(���߿��� 7������)

	// ��ư ���� �������� ������
	// �ߺ�����

	// �۾��� �������� ������
	// ��, �۾��� ���� ��ư 3���߿� �Ѱ������Ѵ�
	// ��ư���� ������ �ȵȴ�(�ߺ�üũ)
	// �۾��� ���� �´°� �˻��ϱ�

	// �۾��� ���� ����� ������ �ѵڿ� ��µ� ���� �����ϱ�
	// �������� ���� �۾��� ���� ���� ��ư���� ����, 2���� �������� ������
	// ���ڻ��� ������ ���� �ƴϸ� �����ְ�

	// ����
	public static void colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 4);
			System.out.println("arrTxt[i] "+arrTxt[i]);
			ansColor = arrTxt[i];
		}
	}
	
	// ��ġ�����ϱ�
	public static void location() {
		ansLoc = (int) (Math.random() * 3); // ������ġ ���� ������
		System.out.println("ansLoc : " + ansLoc); // ������ġ Ȯ�� �뵵
		
		for (int i = 0; i < arrBtn.length; i++) {
			// ������ ��ġ�� �����ϱ����� �κ�
			if (ansLoc == i) {
				arrBtn[i] = ansColor; // ������ġ ����
				a = i;
			} else {
//				System.out.println("else arrBtn");
			}
		}
	}

	// ��ư 2���� �� �������� ������
	public static void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// ������ ������ ��ư�� ���������ϴ� �κ�
			if(arrBtn[i] == arrBtn[a]) {
				continue;
			}
			arrBtn[i] = (int) (Math.random() * 4); // 0~3
			System.out.print(arrBtn[i] + " ");
//			if(ansColor == arrBtn[i]) {
//				arrBtn[i] = (int) (Math.random() * 4); // 0~3
//			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// ���ڰ� ����Ǵ� �κ� - �ϳ��� ���� �ߺ����Ű� �ʿ����
		colorTxt();
		System.out.println();

		// ������ġ ����
		location();
		System.out.println();
		
		// ��ư�� ����Ǵ� �κ�
		button();

		while (true) {
			// ��ư�� �ߺ� ó��
			if ((ansColor == arrBtn[0]) || (ansColor == arrBtn[1]) || (arrBtn[0] == arrBtn[1])) {
				button();
			} else {
				break;
			}
		}

	}
}