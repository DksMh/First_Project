package org.proj.game.color;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class MaxColorPanel extends GameView {
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;
	// 스케치북
	private ImageIcon bgSK;
	private JLabel bgSKPan;

	// 정지버튼
	ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	JButton pauseBtn = new JButton(pauseIcon);

	// 정답, 오답
	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	// 3x3
	private JPanel colorPan;

	// 빨, 파, 노
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	private Color color;
	private EmptyBorder b1;

	private JLabel txtTitle;
	private Font font;
	Timer timer;
	int click = 0;
	MaxColorConsole mcc;

	public MaxColorPanel() {
		pauseBtn.addActionListener(this);
	}

	@Override
	public void display() {
		click = 0;
		mcc = new MaxColorConsole();
		this.setLayout(null);

		this.add(resultPane);
		resultPane.setBounds(FRAME_WIDTH / 2 - 300 / 2, FRAME_HEIGHT / 2 - 350 / 2, 300, 350);
		resultPane.setVisible(false);

		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);
		pauseBtn.setContentAreaFilled(false);
		this.add(pauseBtn);

		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);
		bgImgPan.setLayout(null);

		bgSK = new ImageIcon("images/sketchbook_Color.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);

		checkIcon = new ImageIcon("images/checked.png");
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(765, 105, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);

		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(765, 105, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		colorPan = new JPanel();
		colorPan.setLayout(new GridLayout(3, 3));
		colorPan.setBounds(100, 150, 300, 200);

		for (int i = 0; i < 9; i++) {
			JPanel b = new JPanel();
			LineBorder b2 = new LineBorder(new Color(248, 248, 248), 1);
			b.setBorder(b2);
			b.setBackground(mcc.col[mcc.arr[i]]);
			colorPan.add(b);
		}

		// 버튼
		btn1 = new RoundJButton("btn1");
		btn2 = new RoundJButton("btn2");
		btn3 = new RoundJButton("btn3");
		color = new Color(0, 0, 0, 0);
		btn1.setForeground(color);
		btn2.setForeground(color);
		btn3.setForeground(color);
		b1 = new EmptyBorder(5, 3, 5, 0);
		btn1.setBorder(b1);
		btn2.setBorder(b1);
		btn3.setBorder(b1);
		btn1.setBounds(500, 160, 100, 50);
		btn2.setBounds(500, 230, 100, 50);
		btn3.setBounds(500, 300, 100, 50);
		btn1.setBackground(new Color(233, 23, 22));
		btn2.setBackground(new Color(81, 107, 254));
		btn3.setBackground(new Color(254, 228, 55));
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);

		// 제목
		txtTitle = new JLabel("가장 많은 색을 선택해주세요");
		font = new Font("맑은 고딕", Font.BOLD, 25);
		txtTitle.setFont(font);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(215, 50, 500, 100);

		bgSKPan.add(colorPan);
		bgSKPan.add(txtTitle);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		bgImgPan.add(bgSKPan);

		this.add(bgImgPan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (click == 1) {
			return;
		}

		JButton btn = (JButton) e.getSource();
		if ("btn1".equals(btn.getText())) {
			if ("RED".equals(mcc.ans)) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		} else if ("btn2".equals(btn.getText())) {
			if ("BLUE".equals(mcc.ans)) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		} else if ("btn3".equals(btn.getText())) {
			if ("YELLOW".equals(mcc.ans)) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}

		if ((e.getSource() instanceof JButton) && (e.getSource() != pauseBtn)) {
			System.out.println("버튼");
			click++;
			gameNum++;
			next();
		}

		if (e.getSource() == pauseBtn) {
			int yn = JOptionPane.showConfirmDialog(this,
					new JLabel("게임을 종료하시겠습니까? ", javax.swing.SwingConstants.CENTER), "확인", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (yn == 0) {
				Controller c = Controller.getController();
				gameNum = 0;
				gametrue = 0;
				c.Viewchange(MainPage);
			}
		}
	}

	public void next() {
		// 딜레이 1.5초 주고 다음게임 시작
		timer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkLabel.setVisible(false);
				xLabel.setVisible(false);
				if (GameState == MiniGame) {
					if (gameNum == endGameNum) {
						resultPane.display();
					} else {
						Controller c = Controller.getController();
						int n = (int) ((Math.random() * 100000) % 2);

						if (n == 0) {
							c.Viewchange(SelectColor);
						} else {
							c.Viewchange(MaxColor);
						}
					}
				} else {
					if (gameNum == 10) {
						Controller c = Controller.getController();
						c.Viewchange(SelectColor);
					} else {
						Controller c = Controller.getController();
						c.Viewchange(MaxColor);
					}
				}

				timer.stop();
			}
		});
		timer.start();
	}

	@Override
	public String toString() {
		return MaxColor;
	}

}