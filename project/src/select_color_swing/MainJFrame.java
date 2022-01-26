package select_color_swing;

import java.awt.Container;

import javax.swing.JFrame;

public class MainJFrame extends JFrame {
	public static SelectColorPanel selectpan;
	Container c;

	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("색 선택하기");
		setSize(1080, 720);
		
		c = getContentPane();
		selectpan = new SelectColorPanel();
		
		c.add(selectpan);
	}
	

	public static void main(String[] args) {
		new MainJFrame().setVisible(true);
	}

}