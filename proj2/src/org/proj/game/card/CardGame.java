package org.proj.game.card;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

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

public class CardGame extends GameView {

	ImageIcon backIcon = new ImageIcon("images/comm/backgroundImg.png");
	ImageIcon pauseIcon = new ImageIcon("images/comm/pause.png");
	ImageIcon checkIcon = new ImageIcon("images/comm/checked.png");
	ImageIcon xIcon = new ImageIcon("images/comm/x.png");
	ImageIcon howtoIcon = new ImageIcon("images/comm/HowTo_btn.png");

	ImageIcon gameBackIcon = new ImageIcon("images/card/sketchbook_Card.png");
	ImageIcon startBackIcon = new ImageIcon("images/card/startback.png");
	JLabel Title; // 횟수 보여주기
	JLabel gameBack; // 흰색 배경
	JLabel back; // 초록 배경
	JPanel cardBack; // 카드 넣는 패널
	JLabel startCardBack;
	JLabel checkLabel;
	JLabel xLabel;
	RoundJButton bottomBtn01 = new RoundJButton("시작하기"); // 시작하기
	JButton pauseBtn = new JButton(pauseIcon); 
	JButton howtoBtn = new JButton(howtoIcon); 
	JButton[] Btn = new JButton[12]; // 카드 12개
	String[] img = { // 카드 이미지 주소 배열
			"card1.png", "card2.png", "card3.png", "card4.png", "card5.png", "card6.png", "card1.png", "card2.png",
			"card3.png", "card4.png", "card5.png", "card6.png" };

	int sucessCount = 0;
	int tryCount = 12;
	int buttonIndexSave1 = 0; // 먼저 선택된 카드 인덱스 저장
	int buttonIndexSave2 = 0; // 두번째 선택된 카드 인덱스 저장
	int openCount = 0; // 카드가 2개 뒤집히면 닫히기 전까지 다음 카드 안열리게 하는 변수
	Timer timer;
	Timer timer2;
	java.util.Timer countTimer;
	int startCount;
	boolean end = false;
	GameHowTo_card ght = new GameHowTo_card(bottomBtn01, Btn);

	public CardGame() {
		pauseBtn.addActionListener(this);
		howtoBtn.addActionListener(this);
		bottomBtn01.addActionListener(this);
	}
	
	@Override
	public void display() {
		end = false;
		sucessCount = 0;
		tryCount = 12;
		startCount = 0;
		this.add(resultPane);
//		resultPane.setBounds(FRAME_WIDTH / 2 - 300 / 2, FRAME_HEIGHT / 2 - 350 / 2, 300, 350);
		resultPane.setVisible(false);
		
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(710, 20, 150, 150);
		checkLabel.setVisible(false);
		
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(710, 20, 150, 150);
		xLabel.setVisible(false);
		
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);
		pauseBtn.setContentAreaFilled(false);
		
		howtoBtn.setBounds(850,30,50,50);
		howtoBtn.setBorderPainted(false);
		howtoBtn.setContentAreaFilled(false);
		
		
		// 시작시 정지화면
		startCardBack = new JLabel(startBackIcon);
		startCardBack.setBounds(250, 170, 510, 450);
		
		// 시작하기 버튼
		
		bottomBtn01.setBorderPainted(false);
		bottomBtn01.setContentAreaFilled(false);
		bottomBtn01.setBackground(Color.orange);
		bottomBtn01.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		bottomBtn01.setBounds(430, 630, 150, 40);
		
		cardBack = new JPanel(new GridLayout(4, 3));
		
		cardBack.setBounds(251, 170, 505, 450);
		cardBack.setBackground(Color.white);
		
		Title = new JLabel("Card Game");
		Title.setLayout(null);
		Title.setForeground(Color.black);
		Title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		Title.setHorizontalAlignment(JLabel.CENTER);
		Title.setBounds(250, 120, 510, 50);
		
		gameBack = new JLabel(gameBackIcon);
		gameBack.setLayout(null);
		gameBack.setBounds(220, 50, 570, 650);
		
		back = new JLabel(backIcon);
		
		
		back.setLayout(null);
		back.setBounds(0, 0, 1024, 768);
		
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		
		// 카드 붙이기
		for (int i = 0; i < 12; i++) {
			Btn[i] = new JButton();
			Btn[i].setPreferredSize(new Dimension(100, 150));
			Btn[i].setFocusPainted(false);
			Btn[i].setBorder(null);
			Btn[i].setBackground(Color.white);
//			Btn[i].addActionListener(this);
			Btn[i].setIcon(chageImage("card_Leaf.png"));
			Btn[i].setEnabled(false);
			cardBack.add(Btn[i]);
		}
		
		back.add(ght);
		
		back.add(bottomBtn01);
		back.add(xLabel);
		back.add(checkLabel);
		back.add(pauseBtn);
		back.add(howtoBtn);
		back.add(startCardBack);
		back.add(cardBack);
		back.add(Title);
		back.add(gameBack);
		
		this.add(back);
		mixCard();
	}

	// 카드 이미지 변환
	public ImageIcon chageImage(String filname) {
		ImageIcon icon = new ImageIcon("images/card/" + filname);
		Image originImage = icon.getImage();
		Image changeImage = originImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon icon_new = new ImageIcon(changeImage);
		return icon_new;
	}
	
	
	// 시작 시 전체 카드 보여주기
	public void showCardAll() {

		countDown();

		for (int i = 0; i < Btn.length; i++) {
			Btn[i].setIcon(chageImage(img[i]));
		}

		timer = new Timer(3000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Btn.length; i++) {
					Btn[i].setIcon(chageImage("card_Leaf.png"));
					Btn[i].addActionListener(CardGame.this); // 여기
				}
				timer.stop();
			}
		});
		timer.start();
	}

	public void countDown() {

		countTimer = new java.util.Timer();
		// 3초 카운트
		countTimer.scheduleAtFixedRate(new TimerTask() {

			int i = 3;

			public void run() {

				Title.setText("GAME START : " + i);

				i--;

				if (i < 0) {
					countTimer.cancel();

					Title.setText("시도 횟수 : " + tryCount);

				}
			}
		}, 0, 1000);
	}

	// 카드 섞기
	private void mixCard() {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			int random = rand.nextInt(11) + 1;

			// swap
			String temp = img[0];
			img[0] = img[random];
			img[random] = temp;
		}
	}


	// 그림 맞추기 실패시 카드 되돌리기
	public void backToQuestion() {

		timer = new Timer(300, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("timer");

				openCount = 0;

				Btn[buttonIndexSave1].setIcon(chageImage("card_Leaf.png"));
				Btn[buttonIndexSave2].setIcon(chageImage("card_Leaf.png"));

				timer.stop();
			}

		});
		timer.start();
	}

	// 뒤집힌 카드 확인
	private boolean checkCard(int index1, int index2) {
		if (index1 == index2) {
			return false;
		}

		if (img[index1].equals(img[index2])) {
			return true;
		} else {
			return false;
		}
	}

	// 누른 카드 인덱스 번호 가져오기
	private int getIndex(JButton btn) {
		int index = 0;
		for (int i = 0; i < Btn.length; i++) {
			if (Btn[i] == btn) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(end) {
			return;
		}
		JButton btn = (JButton) e.getSource();
		if(e.getSource() == howtoBtn) {
			ght.setVisible(true);
			bottomBtn01.setVisible(false);

			for(int i = 0; i < Btn.length; i++) {
				Btn[i].setVisible(false);
			}
		}
		
		if (e.getSource() == pauseBtn) { // 정지버튼
			int yn = JOptionPane.showConfirmDialog(this,  new JLabel("게임을 종료하시겠습니까? ", javax.swing.SwingConstants.CENTER),"확인",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);

			if (yn == 0) {
				Controller c = Controller.getController();
				gameNum = 0;
				gametrue = 0;
				c.Viewchange(MainPage);
			}
		} 
		
		if (e.getSource() == bottomBtn01) { // 시작하기 버튼
			if (startCount == 1) {
				return;
			}

			startCardBack.setVisible(false);

			for (int i = 0; i < Btn.length; i++) {
				Btn[i].setEnabled(true);
			}

			showCardAll();

			startCount++;
		} 
		
		if(!((pauseBtn.equals(btn)||bottomBtn01.equals(btn)||(e.getSource() == howtoBtn)))) {
			
			if (openCount == 2) {
				return;
			}

			int index = getIndex(btn);
			System.out.println("index " + index);
			btn.setIcon(chageImage(img[index]));

			openCount++;

			if (openCount == 1) {
				buttonIndexSave1 = index;

			} else if (openCount == 2) {
				buttonIndexSave2 = index;

				if (Btn[buttonIndexSave1] == Btn[buttonIndexSave2]) {
//					tryCount = tryCount;
				} else {
					tryCount--;
				}
				Title.setText("남은 횟수 : " + tryCount);

				boolean isBingo = checkCard(buttonIndexSave1, buttonIndexSave2);
				if (isBingo == true) {
					Btn[buttonIndexSave1].setEnabled(false);
					Btn[buttonIndexSave2].setEnabled(false);
					openCount = 0;
					sucessCount++;
					if (sucessCount == 6) {
						end = true;
						gameNum++;
						gametrue++;
						bgm.playEffect("true.wav");
						checkLabel.setVisible(true);
						next();
					} else if (tryCount == 0) {
						end = true;
						gameNum++;
						gametrue++;
						bgm.playEffect("false.wav");
						xLabel.setVisible(true);
						next();
					}
				} else {
					if (tryCount == 0) {
						end = true;
						gameNum++;
						bgm.playEffect("false.wav");
						xLabel.setVisible(true);
						for (int i = 0; i < 12; i++) {
							Btn[i].setEnabled(false);
						}
						next();
					}
					backToQuestion();
				}
				
			}

		}

	}

	public void next() {
		// 딜레이 1.5초 주고 다음게임 시작
		timer2 = new Timer(1500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (GameState == MiniGame) {

							if (gameNum == endGameNum) {
								resultPane.display();
							} else {
								Controller c = Controller.getController();
								c.Viewchange(CARD);
							}
						}else {
							if (gameNum == 4) {
								Controller c = Controller.getController();
								c.Viewchange(CUP);
							} else {
								Controller c = Controller.getController();
								c.Viewchange(CARD);
							}
						}
						timer2.stop();
					}
				});
		timer2.start();
			}

	@Override
	public String toString() {
		return CARD;
	}
	
	public String toBGM() {
		return "card.wav";
	}
}
