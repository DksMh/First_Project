package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Test extends JFrame {
	JLabel b1;

	Test() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		b1 = new JLabel("11111111");
		b1.setBorder(new LineBorder(Color.red,2, true));
		b1.setBackground(Color.white);
		add(b1);
	}

	public static void main(String[] args) {
		Test t = new Test();
		t.setSize(400, 400);
		t.setVisible(true);
	}
}