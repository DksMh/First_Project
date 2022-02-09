package org.proj.game.plusminus;

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
import javax.swing.Timer;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class PlusMinus extends GameView {
	GamePlayMain gp;
	JLabel bgLabel; // 가장 밑배경(초록잔디)
	JLabel pmbgLabel; // 게임 배경(하얀색 메모장)
	JLabel quizLabel; // 문제 나오는 곳 라벨( num + num )

	JButton[] choiceBtn = new JButton[4]; // 4개의 선지
	JLabel checkLabel; // 정답일 경우 나오는 체크표시
	JLabel xLabel; // 오답일 경우 나오는 엑스표시

	ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	JButton pauseBtn = new JButton(pauseIcon);

	JLabel manualLabel; // 설명부분
	Timer timer;
	int click;

	public PlusMinus() {
//		choiceBtn[0] = new RoundJButton();
//		choiceBtn[1] = new RoundJButton();
//		choiceBtn[2] = new RoundJButton();
//		choiceBtn[3] = new RoundJButton();

		pauseBtn.addActionListener(this);
//		display();
	}

	@Override
	public void display() {
		click = 0;
		gp = new GamePlayMain();

		this.setLayout(null);

		this.add(resultPane);
		resultPane.setBounds(FRAME_WIDTH / 2 - 300 / 2, FRAME_HEIGHT / 2 - 350 / 2, 300, 350);
		resultPane.setVisible(false);

		ImageIcon bgicon = new ImageIcon("images/backgroundImg.png");
		bgLabel = new JLabel(bgicon);
		bgLabel.setBounds(0, 0, 1024, 768);

		ImageIcon pmicon = new ImageIcon("images/sketchbook_PlusMinus.png");
		pmbgLabel = new JLabel(pmicon);
		pmbgLabel.setBounds(155, 60, 700, 600);

		manualLabel = new JLabel("알맞은 숫자를 누르세요");
		manualLabel.setBounds(320, 310, 400, 50);
		manualLabel.setHorizontalAlignment(JLabel.CENTER);
		manualLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		this.add(manualLabel);

		quizLabel = new JLabel(gp.question);
		quizLabel.setFont(new Font("Gothic", Font.BOLD, 115));
		quizLabel.setForeground(new Color(255, 127, 0));
		quizLabel.setBounds(315, 140, 400, 200);

		choiceBtn = new JButton[4];
		for (int i = 0; i < gp.answerArr.length; i++) {
			choiceBtn[i] = new RoundJButton();
			choiceBtn[i].setText(Integer.toString(gp.answerArr[i]));
			choiceBtn[i].setBackground(Color.orange);
			choiceBtn[i].setFont(new Font("Gothic", Font.BOLD, 70));
			choiceBtn[i].setForeground(Color.WHITE);
		}

		choiceBtn[0].setBounds(320, 380, 150, 70);
		choiceBtn[1].setBounds(540, 380, 150, 70);
		choiceBtn[2].setBounds(320, 480, 150, 70);
		choiceBtn[3].setBounds(540, 480, 150, 70);

		ImageIcon checkIcon = new ImageIcon("images/checked.png");
		checkLabel = new JLabel(checkIcon);
		ImageIcon xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);

		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false); // 버튼의 외곽 투명하게
		pauseBtn.setContentAreaFilled(false); // 만들어 주는 것

		checkLabel.setBounds(700, 20, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(700, 20, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		this.add(pauseBtn);

		this.add(choiceBtn[0]);
		this.add(choiceBtn[1]);
		this.add(choiceBtn[2]);
		this.add(choiceBtn[3]);

		this.add(quizLabel);

		this.add(pmbgLabel);

		this.add(bgLabel);

		choiceBtn[0].addActionListener(this);
		choiceBtn[1].addActionListener(this);
		choiceBtn[2].addActionListener(this);
		choiceBtn[3].addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (click == 1) {
			return;
		}

		if (e.getSource() == choiceBtn[0]) {
			gameNum++;
			if (gp.answer == Integer.parseInt(choiceBtn[0].getText())) {
				gametrue++;
				choiceBtn[0].setBackground(new Color(33, 139, 34));
				checkLabel.setVisible(true);
				revalidate();
				repaint();

			} else {
				choiceBtn[0].setBackground(new Color(233, 23, 22));
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}

			next();
		}
		if (e.getSource() == choiceBtn[1]) {
			gameNum++;
			if (gp.answer == Integer.parseInt(choiceBtn[1].getText())) {
				gametrue++;
				choiceBtn[1].setBackground(new Color(33, 139, 34));
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				choiceBtn[1].setBackground(new Color(233, 23, 22));
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}
		if (e.getSource() == choiceBtn[2]) {
			gameNum++;
			if (gp.answer == Integer.parseInt(choiceBtn[2].getText())) {
				gametrue++;
				choiceBtn[2].setBackground(new Color(33, 139, 34));
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				choiceBtn[2].setBackground(new Color(233, 23, 22));
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}
		if (e.getSource() == choiceBtn[3]) {
			gameNum++;
			if (gp.answer == Integer.parseInt(choiceBtn[3].getText())) {
				gametrue++;
				choiceBtn[3].setBackground(new Color(33, 139, 34));
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				choiceBtn[3].setBackground(new Color(233, 23, 22));
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}

		if (e.getSource() == pauseBtn) {
			int yn = JOptionPane.showConfirmDialog(this,  new JLabel("게임을 종료하시겠습니까? ", javax.swing.SwingConstants.CENTER),"확인",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

			if (yn == 0) {
				Controller c = Controller.getController();
				gameNum = 0;
				gametrue = 0;
				c.Viewchange(MainPage);
			}
		}
		click++;
	}

	public void next() {
		// 딜레이 1.5초 주고 다음게임 시작
		timer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameNum == endGameNum) {
					resultPane.display();
				} else {
					Controller c = Controller.getController();
					c.Viewchange(PlusMinus);
				}
				timer.stop();
			}
		});
		timer.start();
	}

	@Override
	public String toString() {
		return PlusMinus;
	}
}