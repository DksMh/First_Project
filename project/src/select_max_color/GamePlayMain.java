package select_max_color;

import java.awt.Container;

import javax.swing.JFrame;

import max_colors.MaxColorPanel;
import select_color_swing.SelectColorPanel;

public class GamePlayMain extends JFrame {
	public static MaxColorPanel maxpan;
	public static SelectColorPanel selectpan;
	
	Container c;
	
	private int r;
	
	public GamePlayMain() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("주위 집중력 훈련 게임");
		setSize(1024, 768);
		
		r = (int)(Math.random() * 2);
		if(r == 0) {
			c = getContentPane();
			maxpan = new MaxColorPanel();
			
			c.add(maxpan);
		} else if(r == 1) {
			c = getContentPane();
			selectpan = new SelectColorPanel();
			
			c.add(selectpan);
		}
	}
	
	public static void main(String[] args) {
		new GamePlayMain().setVisible(true);
	}

}
