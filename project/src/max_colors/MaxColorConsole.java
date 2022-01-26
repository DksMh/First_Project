package max_colors;

public class MaxColorConsole {
	// ���� ���
	private static final int RED = 0;
	private static final int BLUE = 1;
	private static final int YELLOW = 2;

	private static final int MIN = 1;
	private static final int MAX = 7;

	// ���� �ܼ�â�� ���� �� (= count)
	static int c0 = 0;
	static int c1 = 0;
	static int c2 = 0;
	// ���� �迭 (3X3)
	static int[][] arr = new int[3][3];

	public static void main(String[] args) {
		// 3*3�� 2���� �迭�� �����
		// ������ ������ ���򺯼� ���� ����
		// ������ �����ϰ� ������ ����
		setArr();
		// 3x3 arr �����ư�, c0, c1, c2�� ������ ��Ÿ���� ���� ��������.

		while (true) {
			// ���� �߻� ó��
			// 3�� �ϳ��� �ѹ��� �ȳ����� ��
			// 3 3 3 �� ��������
			// 4 4 1 �� �������� (3������ ��찡 �߻�)
			if (!((c0 == 0 || c1 == 0 || c2 == 0) || ((c0 == c1) && (c1 == c2)) || ((c0 == 1) && (c1 == c2))
					|| ((c1 == 1) && (c0 == c2)) || ((c2 == 1) && (c0 == c1)))) {
				break;
			}
			// �ٽ� arr �����.
			setArr();

		}
		// ���� Ȯ��
		System.out.println("RED�� ���� : " + c0);
		System.out.println("BLUE�� ���� : " + c1);
		System.out.println("YELLOW�� ���� : " + c2);

		// ���� ���
		int max = c0;
		String ans = "RED";
		if (max < c1) {
			max = c1;
			ans = "BLUE";
		}

		if (max < c2) {
			max = c2;
			ans = "YELLOW";
		}

		System.out.println("���� : " + ans);
	} 
	// �迭 ����� ���� ����
	public static void setArr() {
		// �ʱ�ȭ ���ֱ�
		c0 = 0;
		c1 = 0;
		c2 = 0;
		// �迭 ������ �ޱ�
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (int) (Math.random() * 3);

				if (arr[i][j] == 0) {
					c0++;
				} else if (arr[i][j] == 1) {
					c1++;
				} else {
					c2++;
				}
				System.out.print(arr[i][j]);
			}
			System.out.println(" ");

		}
	}

}