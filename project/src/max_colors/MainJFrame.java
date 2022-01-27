package max_colors;

import java.awt.Container;

import javax.swing.JFrame;

// max color의 mainJFrame

public class MainJFrame extends JFrame {
	public static MaxColorPanel maxpan;
	Container c;

	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("주위 집중력 훈련 게임");
		setSize(1024, 768);
		
		c = getContentPane();
		maxpan = new MaxColorPanel();
		
		c.add(maxpan);
	}
	

	public static void main(String[] args) {
		new MainJFrame().setVisible(true);
	}

}
