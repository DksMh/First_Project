package org.proj.game.cup;

import static org.proj.Resource.MainPage;
import static org.proj.Resource.MiniGame;
import static org.proj.Resource.PlusMinus;
import static org.proj.Resource.bgm;
import static org.proj.Resource.endGameNum;
import static org.proj.Resource.gameNum;
import static org.proj.Resource.gametrue;
import static org.proj.Resource.nextGameNum;
import static org.proj.Resource.resultPane;
import static org.proj.Resource.CARD;
import static org.proj.Resource.CUP;
import static org.proj.Resource.LIFE;
import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class CupGamePanel extends GameView {

	ImageIcon backIcon = new ImageIcon("images/comm/backgroundImg.png");
	ImageIcon pauseIcon = new ImageIcon("images/comm/pause.png");
	ImageIcon checkIcon = new ImageIcon("images/comm/checked.png");
	ImageIcon xIcon = new ImageIcon("images/comm/x.png");
	
	ImageIcon gameBagIcon = new ImageIcon("images/cup/sketchbook_Cup.png");
	ImageIcon cupIcon = new ImageIcon("images/cup/cup.png");
	ImageIcon ballIcon = new ImageIcon("images/cup/ball.png");
	ImageIcon cupBorderIcon = new ImageIcon("images/cup/cup_stroke.png"); // border있는 컵 그림

	 JLabel backLabel;
	 JLabel gameBackLabel;
	 JLabel checkLabel;
	 JLabel xLabel;
	 JLabel manualLabel;
	 JButton pauseBtn = new JButton(pauseIcon);
	 JButton playBtn = new RoundJButton("시작하기");

	 Timer timer;
	 javax.swing.Timer timer2;
	 javax.swing.Timer otherCupTtimer;

	 int startBtn;

	 int click = 0;

	 boolean flag;

	 JLabel[] balls = new JLabel[3];

	 Cup[] cups = new Cup[3];

	public CupGamePanel() {
		
		playBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
	}

	@Override
	public void display() {
		click = 0;
		startBtn = 0;
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		this.add(resultPane);
//		resultPane.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);
		resultPane.setVisible(false);
		
		// 엑스 이미지
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(750, 20, 150, 150);
		xLabel.setVisible(false);

		// 체크 이미지
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(750, 20, 150, 150);
		checkLabel.setVisible(false);
		
		manualLabel = new JLabel("공이 들어있는 컵을 선택하세요");
		manualLabel.setBounds(230, 150, 550, 50);
		manualLabel.setHorizontalAlignment(JLabel.CENTER);
		manualLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		// 일시정지 버튼
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);
		pauseBtn.setContentAreaFilled(false);

		// 시작하기 버튼
		playBtn.setBounds(435, 530, 150, 50);
		playBtn.setBackground(Color.ORANGE);
		playBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		playBtn.setVisible(true);

		backLabel = new JLabel(backIcon);
		backLabel.setBounds(0, 0, 1024, 768);

		for (int i = 0; i < cups.length; i++) {
			cups[i] = new Cup();
			cups[i].setIcon(cupIcon);
			cups[i].addActionListener(this);
			cups[i].setDisabledIcon(cupIcon);
			cups[i].setEnabled(false);
			cups[i].setBorderPainted(false); // 버튼 외곽 없애기
			cups[i].setContentAreaFilled(false);
			backLabel.add(cups[i]);
		}
		
		cups[0].x = 230;
		cups[1].x = 430;
		cups[2].x = 630;
		
		cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
		cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);
		cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);
		
		// 공 생성
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new JLabel(ballIcon);
			backLabel.add(balls[i]);
			if (!(i == 1)) {
				balls[i].setVisible(false);
			}
		}

		// 공 위치
		balls[0].setBounds(280, 350, 50, 50);
		balls[1].setBounds(480, 350, 50, 50);
		balls[2].setBounds(680, 350, 50, 50);

		// 흰색 배경
		gameBackLabel = new JLabel(gameBagIcon);
		gameBackLabel.setBounds(130, 60, 750, 580);

		// 초록 배경
		

		backLabel.add(manualLabel);
		backLabel.add(xLabel);
		backLabel.add(checkLabel);
		backLabel.add(pauseBtn);
		backLabel.add(playBtn);
		backLabel.add(gameBackLabel);
		this.add(backLabel);
	}

	// 컵 내리기
	public void cupUpDown() {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 8;

			public void run() {

				if (i > 4) {

					cups[1].y -= 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i <= 4 && i > 0) {

					cups[1].y += 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i == 0) {

					timer.cancel();

					balls[1].setVisible(false); // 컵이 내려간 후 공 지우기

					changeCup();
				}

			}
		}, 0, 50);
	}

	// 컵 올리기
	public void cupUp(int index) {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 3;

			public void run() {

				// 들어오는 인덱스에 따른 컵 올리기
				if (index == 0) {
					cups[0].y -= 25;
					cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
				}

				else if (index == 1) {
					cups[1].y -= 25;
					cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);

				}

				else if (index == 2) {
					cups[2].y -= 25;
					cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);

				}

				i--;

				if (i < 0) {

					timer.cancel();

					if (index == 0) {
						click++;
					}

					// 1번 컵의 x 위치에 맞는 공 보여주기
					else if (index == 1) {
						click++;
						if (cups[1].getX() == 230) {
							balls[0].setVisible(true);
						} else if (cups[1].getX() == 430) {
							balls[1].setVisible(true);
						} else if (cups[1].getX() == 630) {
							balls[2].setVisible(true);
						}
					}

					else if (index == 2) {
						click++;
					}

				}
			}
		}, 0, 50);
	}

	//
	public void otherCupUp(int index1, int index2) {
		otherCupTtimer = new javax.swing.Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (index1 == 1) {
					if (cups[1].getX() == 230) {
						balls[0].setVisible(true);
					} else if (cups[1].getX() == 430) {
						balls[1].setVisible(true);
					} else if (cups[1].getX() == 630) {
						balls[2].setVisible(true);
					}
				}

				cups[index1].y -= 25;
				cups[index1].setBounds(cups[index1].x, cups[index1].y, cups[index1].w, cups[index1].h);

				cups[index2].y -= 25;
				cups[index2].setBounds(cups[index2].x, cups[index2].y, cups[index2].w, cups[index2].h);

				if (cups[index1].y == 200) {
					otherCupTtimer.stop();
				}

			}
		});
		otherCupTtimer.start();
	}

	// 컵 섞기
	public void changeCup() {

		int r = 100;

		CupRoad cr = new CupRoad();

		cups[0].road = cr.cupRoadArr[0];
		cups[1].road = cr.cupRoadArr[1];
		cups[2].road = cr.cupRoadArr[2];

		CupThread ct = new CupThread(cups, r, manualLabel);

		ct.start();
	}
	
	public void labelBorder(boolean flag, JLabel JLabel, JButton JButton) {
		flag = true;
		
		
		JLabel.setVisible(flag);
		JButton.setIcon(cupBorderIcon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (click == 1) {
			return;
		}
		if (cups[0] == btn) {
			bgm.playEffect("false.wav");
			cupUp(0);
			labelBorder(flag, xLabel, cups[0]);
			otherCupUp(1, 2);
		} else if (cups[1].equals(btn)) {
			if (click == 1) {
				return;
			}
			cupUp(1);
			bgm.playEffect("true.wav");
			labelBorder(flag, checkLabel, cups[1]);
			otherCupUp(0, 2);
			gametrue++;
		} else if (cups[2].equals(btn)) {
			if (click == 1) {
				return;
			}
			bgm.playEffect("false.wav");
			cupUp(2);
			labelBorder(flag, xLabel, cups[2]);
			otherCupUp(1, 0);
		} else if(playBtn == btn) {
			if (startBtn == 1) {
				return;
			}
			playBtn.setVisible(false);
			manualLabel.setVisible(false);
			cupUpDown();

			startBtn++;
		} 
		if(!((e.getSource()== pauseBtn)||(e.getSource()==playBtn))) {
			gameNum++;
			next();
		}
		
		if(e.getSource() == pauseBtn) {
			int yn = JOptionPane.showConfirmDialog(this,  new JLabel("게임을 종료하시겠습니까? ", javax.swing.SwingConstants.CENTER),"확인",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
			
			if(yn==0) {
				Controller c = Controller.getController();
				gameNum = 0;
				gametrue = 0;
				c.Viewchange(MainPage);
			}
		}
	}
	
	public void next() {
		// 딜레이 1.5초 주고 다음게임 시작
		timer2 = new  javax.swing.Timer(2500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (GameState == MiniGame) {

							if (gameNum == endGameNum) {
								resultPane.display();
							} else {
								Controller c = Controller.getController();
								c.Viewchange(CUP);
							}
						}else {
							if (gameNum == 6) {
								Controller c = Controller.getController();
								c.Viewchange(LIFE);
							} else {
								Controller c = Controller.getController();
								c.Viewchange(CUP);
							}
						}
						timer2.stop();
					}
				});
		timer2.start();
	}
	
	@Override
	public String toString() {
		return CUP;
	}
	
	public String toBGM() {
		return "cup.wav";
	}
}
