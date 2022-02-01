package Life_game;

import java.awt.Container;

import javax.swing.JFrame;

// life game의 mainJFrame

public class MainJFrame extends JFrame {
	public static LifeGamePanel lifepan;
	Container c;

	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("실행활 순서 찾기 게임");
		setSize(1024, 768);
		
		c = getContentPane();
		lifepan = new LifeGamePanel();
		
		c.add(lifepan);
	}
	

	public static void main(String[] args) {
		new MainJFrame().setVisible(true);
	}

}
