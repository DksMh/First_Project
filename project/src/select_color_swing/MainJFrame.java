package select_color_swing;

import java.awt.Container;

import javax.swing.JFrame;

// select color�� mainJFrame

public class MainJFrame extends JFrame {
	public static SelectColor selectpan;
	Container c;

	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("���� ���߷� �Ʒ� ����");
		setSize(1024, 768);
		
		c = getContentPane();
		selectpan = new SelectColor();
		
		c.add(selectpan);
	}
	

	public static void main(String[] args) {
		new MainJFrame().setVisible(true);
	}

}