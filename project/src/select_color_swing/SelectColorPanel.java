package select_color_swing;


import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectColorPanel extends JPanel {
//	JPanel jPan = new JPanel();
	
	public SelectColorPanel() {
		this.setLayout(null);
		
		ImageIcon bgSK = new ImageIcon("F:\\java_project\\Project\\src\\select_color_swing\\sketch.png");
		JLabel bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(0, 0, 720, 425);
		
		JButton btn1 = new JButton("0번째 버튼");
		JButton btn2 = new JButton("1번째 버튼");
		JButton btn3 = new JButton("2번째 버튼");
		btn1.setBounds(300, 300, 100, 100);
		btn2.setBounds(400, 300, 100, 100);
		btn3.setBounds(500, 300, 100, 100);
		
		JLabel txt = new JLabel("파란색");
		txt.setBounds(500, 50, 100, 100);
		
		this.add(txt);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(bgSKPan);
	}

}