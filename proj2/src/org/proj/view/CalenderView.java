package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CalenderView extends JPanel implements ActionListener {

	JButton exit = new JButton(new ImageIcon("images/record/exit.png"));
	JButton prev = new JButton(new ImageIcon("images/comm/HowTo_left.png"));
	JButton next = new JButton(new ImageIcon("images/comm/HowTo_right.png"));
	JLabel[] dataLabel = new JLabel[6];
	int index;

	public CalenderView() {
		next.addActionListener(this);
		prev.addActionListener(this);
	}

	public void display() {
		this.removeAll();
		this.setBackground(Color.white);
		index = mainData.size() - 1;

		this.setLayout(null);
		this.setBounds(150, 100, 720, 530);
		this.setVisible(true);
		exit.setBounds(670, 10, 40, 40);
		exit.setBorderPainted(false);    
		exit.setContentAreaFilled(false);
		
		next.setFocusPainted(false);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		next.setBounds(620, 230, 80, 80);

		prev.setFocusPainted(false);
		prev.setBorderPainted(false);
		prev.setContentAreaFilled(false);
		prev.setBounds(20, 230, 80, 80);
		
		int[] yArr = {50,160,230,300,370,440}; 
		for (int i = 0; i < dataLabel.length; i++) {
			dataLabel[i] = new JLabel();
			Font font = new Font("맑은 고딕", Font.BOLD, 20);
			dataLabel[i].setFont(font);
			dataLabel[i].setOpaque(true);
			dataLabel[i].setBackground(Color.gray);
			dataLabel[i].setBackground(new Color(216,236,254));
			dataLabel[i].setBounds(165, yArr[i], 400, 50);
			this.add(dataLabel[i]);
		}

		dataLabel[0].setHorizontalAlignment(JLabel.CENTER);

		setData(index);

		dataLabel[0].setFont(new Font("맑은 고딕", Font.BOLD, 30));

		this.add(prev);
		this.add(next);
		this.add(exit);
	}
	
	public void setData(int index) {

		if (index == mainData.size()-1) {
			dataLabel[0].setText(mainGameData.getDay());
			dataLabel[1].setText(String.format("        더하기 빼기   :   %3d / %3d", mainGameData.getAnsGame1(),
					mainGameData.getTotalGame1()));
			dataLabel[2].setText(String.format("        카드 맞추기   :   %3d / %3d", mainGameData.getAnsGame2(),
					mainGameData.getTotalGame2()));
			dataLabel[3].setText(String.format("        구 슬 찾 기   :   %3d / %3d", mainGameData.getAnsGame3(),
					mainGameData.getTotalGame3()));
			dataLabel[4].setText(String.format("        실생활 순서   :   %3d / %3d", mainGameData.getAnsGame4(),
					mainGameData.getTotalGame4()));
			dataLabel[5].setText(String.format("        색깔 맞추기   :   %3d / %3d", mainGameData.getAnsGame5(),
					mainGameData.getTotalGame5()));
		} else {
			dataLabel[0].setText(mainData.get(index).getDay());
			dataLabel[1].setText(String.format("        더하기 빼기   :   %3d / %3d", mainData.get(index).getAnsGame1(),
					mainData.get(index).getTotalGame1()));
			dataLabel[2].setText(String.format("        카드 맞추기   :   %3d / %3d", mainData.get(index).getAnsGame2(),
					mainData.get(index).getTotalGame2()));
			dataLabel[3].setText(String.format("        구 슬 찾 기   :   %3d / %3d", mainData.get(index).getAnsGame3(),
					mainData.get(index).getTotalGame3()));
			dataLabel[4].setText(String.format("        실생활 순서   :   %3d / %3d", mainData.get(index).getAnsGame4(),
					mainData.get(index).getTotalGame4()));
			dataLabel[5].setText(String.format("        색깔 맞추기   :   %3d / %3d", mainData.get(index).getAnsGame5(),
					mainData.get(index).getTotalGame5()));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == prev) {

			if (index == 0) {
				JOptionPane.showMessageDialog(this, new JLabel("마지막 페이지입니다.", javax.swing.SwingConstants.CENTER), "",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				index--;
				setData(index);
			}

		}

		if (e.getSource() == next) {
			if (index == mainData.size() - 1) {
				JOptionPane.showMessageDialog(this, new JLabel("마지막 페이지입니다.", javax.swing.SwingConstants.CENTER), "",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				index++;
				setData(index);
			}
		}
	}

}