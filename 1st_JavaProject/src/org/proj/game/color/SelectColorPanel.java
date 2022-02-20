package org.proj.game.color;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class SelectColorPanel extends GameView{
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	private ImageIcon bgSK;
	private JLabel bgSKPan;

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	private Color color;
	private EmptyBorder b1;

	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	private Font font1;
	private Font font2;

	private JLabel txtTitle;
	private JLabel txtColor;
	boolean howtoState = true;
	int click=0;
	SelectColorConsole scc;

	Timer timer;
	ImageIcon pauseIcon = new ImageIcon("images/comm/pause.png");
	JButton pauseBtn = new JButton(pauseIcon);
	
	ImageIcon howtoIcon = new ImageIcon("images/comm/HowTo_Btn.png");
	JButton howtoBtn = new JButton(howtoIcon); 
	
	GameHowTo_sc ght = new GameHowTo_sc();
	
	public SelectColorPanel() {
		pauseBtn.addActionListener(this);
		howtoBtn.addActionListener(this);
	}

	@Override
	public void display() {
		click = 0;
		scc = new SelectColorConsole();
		this.setLayout(null);
		
		// 결과 알려주는 곳
		this.add(resultPane);
		resultPane.setVisible(false);
		
		// 정지 버튼
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false); 
		pauseBtn.setContentAreaFilled(false);
		
		
		// 배경
		bgImg = new ImageIcon("images/comm/backgroundImg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);
		
		// 스케치북
		bgSK = new ImageIcon("images/color/sketchbook_Color.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);
		
		howtoBtn.setBounds(850,30,50,50);
		howtoBtn.setBorderPainted(false);
		howtoBtn.setContentAreaFilled(false);
		
		
		// 버튼
		btn1 = new RoundJButton("btn1");
		btn2 = new RoundJButton("btn2");
		btn3 = new RoundJButton("btn3");
		btn1.setFocusPainted(false);
		btn2.setFocusPainted(false);
		btn3.setFocusPainted(false);
		color = new Color(0, 0, 0, 0);
		btn1.setForeground(color);
		btn2.setForeground(color);
		btn3.setForeground(color);
		b1 = new EmptyBorder(5, 3, 5, 0);
		btn1.setBorder(b1);
		btn2.setBorder(b1);
		btn3.setBorder(b1);
		btn1.setBounds(100, 200, 115, 150);
		btn2.setBounds(303, 200, 115, 150);
		btn3.setBounds(503, 200, 115, 150);
		btn1.setBackground(scc.col[scc.arrBtn[0]]);
		btn2.setBackground(scc.col[scc.arrBtn[1]]);
		btn3.setBackground(scc.col[scc.arrBtn[2]]);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		if(howtoState) {
			btn1.setEnabled(false);
			btn2.setEnabled(false);
			btn3.setEnabled(false);
		}
		
		checkIcon = new ImageIcon("images/comm/checked.png");
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(765, 105, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);

		xIcon = new ImageIcon("images/comm/x.png");
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(765, 105, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);
		
		// 제목 글자 색깔정하기
		if (scc.ansColor == 0) {
			txtColor = new JLabel("빨간색");
		} else if (scc.ansColor == 1) {
			txtColor = new JLabel("주황색");
		} else if (scc.ansColor == 2) {
			txtColor = new JLabel("노란색");
		} else if (scc.ansColor == 3) {
			txtColor = new JLabel("초록색");
		} else if (scc.ansColor == 4) {
			txtColor = new JLabel("파란색");
		} else if (scc.ansColor == 5) {
			txtColor = new JLabel("분홍색");
		} else {
			txtColor = new JLabel("보라색");
		}
		
		font1 = new Font("맑은 고딕", Font.BOLD, 44);
		txtColor.setFont(font1);
		txtColor.setForeground(scc.paintTxt());
		txtColor.setBounds(300, 75, 500, 100);

		txtTitle = new JLabel("알맞은 색을 선택해주세요");
		font2 = new Font("맑은 고딕", Font.BOLD, 25);
		txtTitle.setFont(font2);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(220, 25, 500, 100);
		
		
		ght.setBounds(100, 100, 820, 530);
		bgImgPan.add(ght);
		ght.exit.addActionListener(this);
		
		bgSKPan.add(txtTitle);
		bgSKPan.add(txtColor);
		bgImgPan.add(pauseBtn);
		bgImgPan.add(howtoBtn);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);

		bgImgPan.add(bgSKPan);
		this.add(bgImgPan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(click == 1) {
			return;
		}
		
		if(e.getSource() == howtoBtn) {
			howtoState = true;
			ght.setVisible(true);
		}
		
		if(e.getSource() == ght.exit) {
			howtoState = false;
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			
			ght.setVisible(false);
	}
		
		JButton btn = (JButton) e.getSource();

		if ("btn1".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[0]) {
				gametrue++;
				bgm.playEffect("true.wav");
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				bgm.playEffect("false.wav");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
		if ("btn2".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[1]) {
				bgm.playEffect("true.wav");
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				bgm.playEffect("false.wav");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}

		if ("btn3".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[2]) {
				bgm.playEffect("true.wav");
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				bgm.playEffect("false.wav");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
		if((e.getSource()!= pauseBtn&&e.getSource() != ght.exit)&&(e.getSource()!=howtoBtn)) {
			click++;
			gameNum++;
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
				}else {
					if (gameNum == 12) {
						Controller c = Controller.getController();
						resultPane.display();
					} else {
						Controller c = Controller.getController();
						c.Viewchange(SelectColor);
					}
				}
				timer.stop();
			}
		});
		timer.start();
	}

	@Override
	public String toString() {
		return SelectColor;
	}
	
	public String toBGM() {
		return "color.wav";
	}
}
