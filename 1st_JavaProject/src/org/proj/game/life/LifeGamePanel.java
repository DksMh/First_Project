package org.proj.game.life;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class LifeGamePanel extends GameView implements MouseListener, MouseMotionListener {
	// 배경
	private ImageIcon bgImg = new ImageIcon("images/comm/backgroundImg.png");
	private ImageIcon pauseIcon = new ImageIcon("images/comm/pause.png");
	private ImageIcon howtoIcon = new ImageIcon("images/comm/HowTo_btn.png");
	private ImageIcon checkIcon = new ImageIcon("images/comm/checked.png");
	private ImageIcon xIcon = new ImageIcon("images/comm/x.png");

	private JLabel bgImgPan;

	private JButton pauseBtn = new JButton(pauseIcon);
	private JButton howtoBtn = new JButton(howtoIcon);
	private JLabel checkLabel;
	private JLabel xLabel;

	// Console 가져온것
	LifeGameConsole lgc;

	private JLabel title;

	// 문제의 드래그의 좌표(x,y)
	int mouseX = 0;
	int mouseY = 0;

	// 정답 칸
	JPanel[] ans = new JPanel[4];

	int width = 300;
	int height = 80;

	// 도전 횟수
	int lifeRemaining = 2;
	JLabel life;

	Color gray = new Color(252, 252, 252);

	JButton submit;
	static Timer timer;

	boolean howtoState = true;
	GameHowTo_lg ght = new GameHowTo_lg();

	LifeLabel[] label = new LifeLabel[4];
	String[] answerBox = new String[4];
	int click;

	public LifeGamePanel() {
		pauseBtn.addActionListener(this);
		howtoBtn.addActionListener(this);
	}

	@Override
	public void display() {
		click = 0;
		lifeRemaining = 2;
		// 중복제거
		String prev = null;
		String now = null;
		int count = 0;
		// 중복제거
		while (true) {
			lgc = new LifeGameConsole();
			if (count == 0) {
				prev = lgc.ArrLabel[lgc.k];
				now = lgc.ArrLabel[lgc.k];
				count++;
				break;
			} else {
				prev = now;
				now = lgc.ArrLabel[lgc.k];
				count++;
			}

			if (prev.equals(now)) {
				continue;
			} else {
				break;
			}
		}

		this.setLayout(null);

		this.add(resultPane);
		resultPane.setVisible(false);

		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false); // 버튼의 외곽 투명하게
		pauseBtn.setContentAreaFilled(false); // 만들어 주는 것
		this.add(pauseBtn);

		// 배경
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		submit = new RoundJButton("제출");
		submit.setBounds(770, 650, 130, 50);
		submit.setBackground(new Color(254, 178, 55));
		Font font2 = new Font("맑은 고딕", Font.BOLD, 20);
		submit.setFocusPainted(false);

		if (howtoState) {
			submit.setVisible(false);
		} else {
			submit.setVisible(true);
		}

		submit.setFont(font2);
		bgImgPan.add(submit);
		submit.addActionListener(this);

		ght.setBounds(100, 100, 820, 530); 
		bgImgPan.add(ght); // 추가
		ght.exit.addActionListener(this);

		checkLabel = new JLabel(checkIcon);
		xLabel = new JLabel(xIcon);
		checkLabel.setBounds(427, 284, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(427, 284, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		title = new JLabel(lgc.cate);
		title.setBounds(350, 30, 300, 80);
		title.setBackground(Color.white);
		Font font3 = new Font("맑은 고딕", Font.BOLD, 28);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(font3);
		title.setOpaque(true);
		title.setBackground(gray);
		Border c = new LineBorder(new Color(254, 178, 55), 7);
		title.setBorder(c);
		title.addMouseListener(this);
		bgImgPan.add(title);

		howtoBtn.setBounds(850, 30, 50, 50);
		howtoBtn.setBorderPainted(false);
		howtoBtn.setContentAreaFilled(false);
		int[] yArr = { 130, 270, 410, 550 };

		// 문제
		for (int i = 0; i < label.length; i++) {
			label[i] = new LifeLabel(lgc.ansArr[lgc.showQuiz[i]], 100, yArr[i], width, height);
			label[i].setHorizontalAlignment(JLabel.CENTER);
			label[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
			label[i].setOpaque(true);
			label[i].setBackground(new Color(254, 228, 55));
			label[i].addMouseMotionListener(this);
			label[i].addMouseListener(this);
			label[i].setBounds(label[i].x, label[i].y, width, height);
			bgImgPan.add(label[i]);
			if(howtoState) {
				label[i].setVisible(false);
			}else {
				label[i].setVisible(true);
			}
		}

		// 정답
		for (int i = 0; i < 4; i++) {
			ans[i] = new JPanel();
			JLabel num = new JLabel((i + 1) + "");
			num.setFont(new Font("맑은 고딕", Font.BOLD, 50));
			ans[i].setBounds(600, yArr[i], width, height);
			ans[i].add(num);
			bgImgPan.add(ans[i]);
		}

		bgImgPan.add(howtoBtn);

		life = new JLabel("도전횟수 : " + lifeRemaining);
		life.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		life.setBounds(100, 50, 150, 50);
		life.setBackground(Color.white);
		life.setOpaque(true);
		life.setHorizontalAlignment(JLabel.CENTER);
		bgImgPan.add(life);

		this.add(bgImgPan);
	}

	// 충돌검사
	public void move(LifeLabel a, MouseEvent e, JPanel[] ans) {

		if (a.flag == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX, jc.getY() + e.getY() - mouseY);

			int centerX = jc.getX() + width / 2;
			int centerY = jc.getY() + height / 2;

			if ((centerX > ans[0].getX() && centerX < ans[0].getX() + width)
					&& (centerY > ans[0].getY() && centerY < ans[0].getY() + height)) {
				if (!(ans[0].getBackground() == Color.white)) {
					ans[0].setEnabled(false);
					ans[0].setBackground(Color.white);
					a.setBounds(ans[0].getX(), ans[0].getY(), width, height);
					answerBox[0] = a.getText();
					a.num = 1;
					a.flag = false;
					revalidate();
					repaint();
				} else {
					a.setBounds(a.x, a.y, width, height);
				}
			} else if ((centerX > ans[1].getX() && centerX < ans[1].getX() + width)
					&& (centerY > ans[1].getY() && centerY < ans[1].getY() + height)) {
				if (!(ans[1].getBackground() == Color.white)) {
					ans[1].setBackground(Color.white);
					a.setBounds(ans[1].getX(), ans[1].getY(), width, height);
					answerBox[1] = a.getText();
					a.flag = false;
					a.num = 2;
					revalidate();
					repaint();
				} else {
					a.setBounds(a.x, a.y, width, height);
				}
			} else if ((centerX > ans[2].getX() && centerX < ans[2].getX() + width)
					&& (centerY > ans[2].getY() && centerY < ans[2].getY() + height)) {
				if (!(ans[2].getBackground() == Color.white)) {
					ans[2].setBackground(Color.white);
					a.setBounds(ans[2].getX(), ans[2].getY(), width, height);
					answerBox[2] = a.getText();
					a.flag = false;
					a.num = 3;
					revalidate();
					repaint();
				} else {
					a.setBounds(a.x, a.y, width, height);
				}
			} else if ((centerX > ans[3].getX() && centerX < ans[3].getX() + width)
					&& (centerY > ans[3].getY() && centerY < ans[3].getY() + height)) {
				if (!(ans[3].getBackground() == Color.white)) {
					ans[3].setBackground(Color.white);
					a.setBounds(ans[3].getX(), ans[3].getY(), width, height);
					answerBox[3] = a.getText();
					a.flag = false;
					a.num = 4;
					revalidate();
					repaint();
				} else {
					a.setBounds(a.x, a.y, width, height);

				}
			} else {
				if (a.num == 1) {
					ans[0].setBackground(gray);
				} else if (a.num == 2) {
					ans[1].setBackground(gray);
				} else if (a.num == 3) {
					ans[2].setBackground(gray);
				} else if (a.num == 4) {
					ans[3].setBackground(gray);
				}
				revalidate();
				repaint();
				a.num = 0;
			}
		}
	}

	// 드래그 앤 드롭
	@Override
	public void mouseDragged(MouseEvent e) {
		move(label[0], e, ans);
		move(label[1], e, ans);
		move(label[2], e, ans);
		move(label[3], e, ans);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	// 사각형 안에서 클릭시 움직이게
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof LifeLabel) {
			LifeLabel lifeLabel = (LifeLabel) e.getSource();

			lifeLabel.flag = true;

			if (lifeLabel.num == 1) {
				lifeLabel.setBounds(lifeLabel.x, lifeLabel.y, width, height);
				lifeLabel.flag = false;
				lifeLabel.num = 0;
				answerBox[0] = null;
				ans[0].setBackground(gray);
			} else if (lifeLabel.num == 2) {
				lifeLabel.setBounds(lifeLabel.x, lifeLabel.y, width, height);
				lifeLabel.flag = false;
				lifeLabel.num = 0;
				answerBox[1] = null;
				ans[1].setBackground(gray);
			} else if (lifeLabel.num == 3) {
				lifeLabel.setBounds(lifeLabel.x, lifeLabel.y, width, height);
				lifeLabel.flag = false;
				lifeLabel.num = 0;
				answerBox[2] = null;
				ans[2].setBackground(gray);
			} else if (lifeLabel.num == 4) {
				lifeLabel.setBounds(lifeLabel.x, lifeLabel.y, width, height);
				lifeLabel.flag = false;
				lifeLabel.num = 0;
				answerBox[3] = null;
				ans[3].setBackground(gray);
			}
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() instanceof LifeLabel) {
			LifeLabel lifeLabel = (LifeLabel) e.getSource();

			lifeLabel.flag = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	int answerCount = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (click == 1) {
			return;
		}
		if (e.getSource() == howtoBtn) {
			ght.setVisible(true);
			submit.setVisible(false);
			for(int i = 0; i<label.length; i++) {
				label[i].setVisible(false);
			}
		}

		if (e.getSource() == ght.exit) {
			howtoState = false;
			submit.setVisible(true);
			ght.setVisible(false);
			for(int i = 0; i<label.length; i++) {
				label[i].setVisible(true);
			}
		}

		if (e.getSource() == submit) {
			for (int i = 0; i < 4; i++) {
				if (answerBox[i] == null) {
					JOptionPane.showMessageDialog(this, new JLabel("빈칸이 있어요!", javax.swing.SwingConstants.CENTER), "알림",
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}

			for (int i = 0; i < 4; i++) {
				if (lgc.ansArr[i].equals(answerBox[i])) {
					answerCount++;
				}
			}

			if (answerCount == 4) {
				click++;
				bgm.playEffect("true.wav");
				gameNum++;
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
				answerCount = 0;
				label[0].num = 0;
				label[1].num = 0;
				label[2].num = 0;
				label[3].num = 0;
				answerBox[0] = null;
				answerBox[1] = null;
				answerBox[2] = null;
				answerBox[3] = null;

				next();
			} else {
				lifeRemaining--;
				if (lifeRemaining == 0) {
					bgm.playEffect("false.wav");
					life.setText("도전횟수 : " + lifeRemaining);
					gameNum++;
					click++;
					xLabel.setVisible(true);
					next();
				} else if (lifeRemaining == 1) {
					life.setText("도전횟수 : " + lifeRemaining);
					JOptionPane.showMessageDialog(this, new JLabel("곰곰히 생각해보세요!", javax.swing.SwingConstants.CENTER),
							"알림", JOptionPane.PLAIN_MESSAGE);
				}

				for (int i = 0; i < 4; i++) {
					label[i].num = 0;
					label[i].setBounds(label[i].x, label[i].y, width, height);
					ans[i].setBackground(gray);
					answerBox[i] = null;
				}
				answerCount = 0;
				revalidate();
				repaint();
			}
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
				if (GameState == MiniGame) {

					if (gameNum == endGameNum) {
						resultPane.display();
					} else {
						Controller c = Controller.getController();
						c.Viewchange(LIFE);
					}
				} else {
					if (gameNum == 8) {
						Controller c = Controller.getController();
						c.Viewchange(MaxColor);
					} else {
						Controller c = Controller.getController();
						c.Viewchange(LIFE);
					}
				}
				timer.stop();
			}
		});
		timer.start();
	}

	@Override
	public String toString() {
		return LIFE;
	}

	public String toBGM() {
		return "life.wav";
	}
}
