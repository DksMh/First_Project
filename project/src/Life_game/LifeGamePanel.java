package Life_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import gameContainer.GameContainer;

// 해결해야하는 부분
// 정답칸에 4개가 다들어가는 오류 잡기 - 해결!!!!!!!!!!!!!!!!!!!!!!!!!
// 드래그앤드롭 드래그하면 오답일시 제자리로 돌리기 (4개 다했을때 판별하기) - 해결
// 드래그앤드롭 드래그하면 정답일시 체크표시 나오게하기 - 해결
// 같은 문제가 연속으로 나오는 중복검사 하기

public class LifeGamePanel extends GameContainer implements MouseListener, MouseMotionListener {
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	// 정답, 오답
	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	// Console 가져온것
	LifeGameConsole lgc;

	// 드래그 앤 드롭
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;

	private JLabel title;

	// 문제의 폰트
	private Font font1;
	private Font font2;
	private Font font3;

	// 문제의 드래그를 개별적으로 주기위하여
	private boolean drag1;
	private boolean drag2;
	private boolean drag3;
	private boolean drag4;

	// 문제의 드래그의 좌표(x,y)
	int mouseX1 = 0;
	int mouseX2 = 0;
	int mouseX3 = 0;
	int mouseX4 = 0;
	int mouseY1 = 0;
	int mouseY2 = 0;
	int mouseY3 = 0;
	int mouseY4 = 0;

	// 정답 칸
	JPanel ans1;
	JPanel ans2;
	JPanel ans3;
	JPanel ans4;

	// 정답 칸의 글씨
	JLabel num1;
	JLabel num2;
	JLabel num3;
	JLabel num4;

	// ans1의 좌표
	int x1 = 600;
	int y1 = 130;
	int w1 = 300;
	int h1 = 80;

	// ans2의 좌표
	int x2 = 600;
	int y2 = 270;
	int w2 = 300;
	int h2 = 80;

	// ans3의 좌표
	int x3 = 600;
	int y3 = 410;
	int w3 = 300;
	int h3 = 80;

	// ans4의 좌표
	int x4 = 600;
	int y4 = 550;
	int w4 = 300;
	int h4 = 80;

	////////////////////////////////////
	// 노란색 a1의 좌표
	int x11 = 100;
	int y11 = 130;
	int w11 = 300;
	int h11 = 80;

	// 노란색 a2의 좌표
	int x22 = 100;
	int y22 = 270;
	int w22 = 300;
	int h22 = 80;

	// 노란색 a3의 좌표
	int x33 = 100;
	int y33 = 410;
	int w33 = 300;
	int h33 = 80;

	// 노란색 a4의 좌표
	int x44 = 100;
	int y44 = 550;
	int w44 = 300;
	int h44 = 80;

	int a1num = 0;
	int a2num = 0;
	int a3num = 0;
	int a4num = 0;
	Color gray = new Color(252, 252, 252);

	JButton submit;
	static Timer timer;
	
	public LifeGamePanel() {
		lgc = new LifeGameConsole();
		this.setLayout(null);

		// 배경
		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		submit = new JButton("제출");
		submit.setBounds(770, 650, 130, 50);
		font2 = new Font("맑은 고딕", Font.BOLD, 20);
		submit.setFont(font2);
		bgImgPan.add(submit);
		submit.addActionListener(this);

		checkIcon = new ImageIcon("images/o.png");
		checkLabel = new JLabel(checkIcon);
		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		checkLabel.setBounds(670, 65, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(670, 65, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);
		
		title = new JLabel(lgc.ArrLabel[lgc.k]);
		title.setBounds(350, 30, 300, 80);
		font3 = new Font("맑은 고딕", Font.BOLD, 28);
		title.setHorizontalAlignment(JLabel.CENTER);
		LineBorder line = new LineBorder(new Color(254,178,55), 7); // 색깔 바꾸기(주황으로)
		title.setBorder(line);
		title.setFont(font3);
		title.setOpaque(true);
		title.setBackground(gray);
//		title.setBackground(new Color(255, 255, 255));
		bgImgPan.add(title);

		// 드래그 앤 드롭
		a1 = new JLabel(lgc.a[lgc.b[0]]);
		a2 = new JLabel(lgc.a[lgc.b[1]]);
		a3 = new JLabel(lgc.a[lgc.b[2]]);
		a4 = new JLabel(lgc.a[lgc.b[3]]);
		a1.setHorizontalAlignment(JLabel.CENTER);
		a2.setHorizontalAlignment(JLabel.CENTER);
		a3.setHorizontalAlignment(JLabel.CENTER);
		a4.setHorizontalAlignment(JLabel.CENTER);
		font1 = new Font("맑은 고딕", Font.BOLD, 24);
		a1.setFont(font1);
		a2.setFont(font1);
		a3.setFont(font1);
		a4.setFont(font1);
		drag1 = false;
		drag2 = false;
		drag3 = false;
		drag4 = false;

		a1.setOpaque(true);
		a2.setOpaque(true);
		a3.setOpaque(true);
		a4.setOpaque(true);
		a1.setBackground(new Color(254, 228, 55));
		a2.setBackground(new Color(254, 228, 55));
		a3.setBackground(new Color(254, 228, 55));
		a4.setBackground(new Color(254, 228, 55));
		a1.setBounds(x11, y11, w11, h11);
		a2.setBounds(x22, y22, w22, h22);
		a3.setBounds(x33, y33, w33, h33);
		a4.setBounds(x44, y44, w44, h44);
		a1.addMouseMotionListener(this);
		a2.addMouseMotionListener(this);
		a3.addMouseMotionListener(this);
		a4.addMouseMotionListener(this);
		a1.addMouseListener(this);
		a2.addMouseListener(this);
		a3.addMouseListener(this);
		a4.addMouseListener(this);
		bgImgPan.add(a1);
		bgImgPan.add(a2);
		bgImgPan.add(a3);
		bgImgPan.add(a4);

		// 정답칸
		ans1 = new JPanel();
		ans2 = new JPanel();
		ans3 = new JPanel();
		ans4 = new JPanel();
		num1 = new JLabel(lgc.a[0]); // 정답부분 : lgc.a[0] -> 이걸로 정답찾기
		num2 = new JLabel(lgc.a[1]);
		num3 = new JLabel(lgc.a[2]);
		num4 = new JLabel(lgc.a[3]);
		ans1.setBounds(x1, y1, w1, h1);
		ans2.setBounds(x2, y2, w2, h2);
		ans3.setBounds(x3, y3, w3, h3);
		ans4.setBounds(x4, y4, w4, h4);

		ans1.add(num1);
		ans2.add(num2);
		ans3.add(num3);
		ans4.add(num4);
		bgImgPan.add(ans1);
		bgImgPan.add(ans2);
		bgImgPan.add(ans3);
		bgImgPan.add(ans4);

		this.add(bgImgPan);
	}

	// 충돌검사
//	public void crush(int centerX, int centerY, int x, int y, int w, int h, JPanel ans, JLabel a) {
//		if (centerX > x && centerX < x + w) {
//			if (centerY > y && centerY < y + h) {
//				a.setBounds(x, y, w, h);
//				revalidate();
//				repaint();
//			}
//		} else {
//			ans.setBackground(Color.white);
//			revalidate();
//			repaint();
//		}
//	}

	String[] s = new String[4];

	// 드래그 앤 드롭
	@Override
	public void mouseDragged(MouseEvent e) {
		if (drag1 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX1, jc.getY() + e.getY() - mouseY1);

			x11 = jc.getX();
			y11 = jc.getY();

			int centerX = x11 + w11 / 2;
			int centerY = y11 + h11 / 2;

//			crush(centerX, centerY, x1, y1, w1, h1, ans1, a1);
//			crush(centerX, centerY, x2, y2, w2, h2, ans2, a1);
//			crush(centerX, centerY, x3, y3, w3, h3, ans3, a1);
//			crush(centerX, centerY, x4, y4, w4, h4, ans4, a1);

			if ((centerX > x1 && centerX < x1 + w1) && (centerY > y1 && centerY < y1 + h1)) {
				if (!(ans1.getBackground() == Color.white)) {
					ans1.setEnabled(false);
					ans1.setBackground(Color.white);
					a1.setBounds(x1, y1, w1, h1);
					s[0] = a1.getText();
					a1num = 1;
					drag1 = false;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, w1, h1);
				}
			} else if ((centerX > x2 && centerX < x2 + w2) && (centerY > y2 && centerY < y2 + h2)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a1.setBounds(x2, y2, w2, h2);
					s[1] = a1.getText();
					drag1 = false;
					a1num = 2;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, w1, h1);
				}
			} else if ((centerX > x3 && centerX < x3 + w3) && (centerY > y3 && centerY < y3 + h3)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a1.setBounds(x3, y3, w3, h3);
					s[2] = a1.getText();
					drag1 = false;
					a1num = 3;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, w1, h1);
				}
			} else if ((centerX > x4 && centerX < x4 + w4) && (centerY > y4 && centerY < y4 + h4)) {
				if (!(ans4.getBackground() == Color.white)) {

					ans4.setBackground(Color.white);
					a1.setBounds(x4, y4, w4, h4);
					s[3] = a1.getText();
					drag1 = false;
					a1num = 4;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, w1, h1);
				}
			} else {
				if (a1num == 1) {
					ans1.setBackground(gray);
				} else if (a1num == 2) {
					ans2.setBackground(gray);
				} else if (a1num == 3) {
					ans3.setBackground(gray);
				} else if (a1num == 4) {
					ans4.setBackground(gray);
				}
				revalidate();
				repaint();
				a1num = 0;
			}
		}
		if (drag2 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX2, jc.getY() + e.getY() - mouseY2);

			x22 = jc.getX();
			y22 = jc.getY();

			int centerX = x22 + w22 / 2;
			int centerY = y22 + h22 / 2;

//			crush(centerX, centerY, x1, y1, w1, h1, ans1, a2);
//			crush(centerX, centerY, x2, y2, w2, h2, ans2, a2);
//			crush(centerX, centerY, x3, y3, w3, h3, ans3, a2);
//			crush(centerX, centerY, x4, y4, w4, h4, ans4, a2);

			if ((centerX > x1 && centerX < x1 + w1) && (centerY > y1 && centerY < y1 + h1)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a2.setBounds(x1, y1, w1, h1);
					s[0] = a2.getText();
					drag2 = false;
					a2num = 1;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, w1, h1);
				}
			} else if ((centerX > x2 && centerX < x2 + w2) && (centerY > y2 && centerY < y2 + h2)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a2.setBounds(x2, y2, w2, h2);
					s[1] = a2.getText();
					drag2 = false;
					a2num = 2;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, w1, h1);
				}
			} else if ((centerX > x3 && centerX < x3 + w3) && (centerY > y3 && centerY < y3 + h3)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a2.setBounds(x3, y3, w3, h3);
					s[2] = a2.getText();
					drag2 = false;
					a2num = 3;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, w1, h1);
				}
			} else if ((centerX > x4 && centerX < x4 + w4) && (centerY > y4 && centerY < y4 + h4)) {
				if (!(ans4.getBackground() == Color.white)) {

					ans4.setBackground(Color.white);
					a2.setBounds(x4, y4, w4, h4);
					s[3] = a2.getText();
					drag2 = false;
					a2num = 4;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, w1, h1);
				}
			} else {
				if (a2num == 1) {
					ans1.setBackground(gray);
				} else if (a2num == 2) {
					ans2.setBackground(gray);
				} else if (a2num == 3) {
					ans3.setBackground(gray);
				} else if (a2num == 4) {
					ans4.setBackground(gray);
				}
				revalidate();
				repaint();
				a2num = 0;
			}
		}
		if (drag3 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX3, jc.getY() + e.getY() - mouseY3);

			x33 = jc.getX();
			y33 = jc.getY();

			int centerX = x33 + w33 / 2;
			int centerY = y33 + h33 / 2;

//			crush(centerX, centerY, x1, y1, w1, h1, ans1, a3);
//			crush(centerX, centerY, x2, y2, w2, h2, ans2, a3);
//			crush(centerX, centerY, x3, y3, w3, h3, ans3, a3);
//			crush(centerX, centerY, x4, y4, w4, h4, ans4, a3);

			if ((centerX > x1 && centerX < x1 + w1) && (centerY > y1 && centerY < y1 + h1)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a3.setBounds(x1, y1, w1, h1);
					s[0] = a3.getText();
					drag3 = false;
					a3num = 1;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, w1, h1);
				}
			} else if ((centerX > x2 && centerX < x2 + w2) && (centerY > y2 && centerY < y2 + h2)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a3.setBounds(x2, y2, w2, h2);
					s[1] = a3.getText();
					drag3 = false;
					a3num = 2;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, w1, h1);
				}
			} else if ((centerX > x3 && centerX < x3 + w3) && (centerY > y3 && centerY < y3 + h3)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a3.setBounds(x3, y3, w3, h3);
					s[2] = a3.getText();
					drag3 = false;
					a3num = 3;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, w1, h1);
				}
			} else if ((centerX > x4 && centerX < x4 + w4) && (centerY > y4 && centerY < y4 + h4)) {
				if (!(ans4.getBackground() == Color.white)) {

					ans4.setBackground(Color.white);
					a3.setBounds(x4, y4, w4, h4);
					s[3] = a3.getText();
					drag3 = false;
					a3num = 4;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, w1, h1);
				}
			} else {
				if (a3num == 1) {
					ans1.setBackground(gray);
				} else if (a3num == 2) {
					ans2.setBackground(gray);
				} else if (a3num == 3) {
					ans3.setBackground(gray);
				} else if (a3num == 4) {
					ans4.setBackground(gray);
				}
				revalidate();
				repaint();
				a3num = 0;
			}
		}
		if (drag4 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX4, jc.getY() + e.getY() - mouseY4);

			x44 = jc.getX();
			y44 = jc.getY();

			int centerX = x44 + w44 / 2;
			int centerY = y44 + h44 / 2;

//			crush(centerX, centerY, x1, y1, w1, h1, ans1, a4);
//			crush(centerX, centerY, x2, y2, w2, h2, ans2, a4);
//			crush(centerX, centerY, x3, y3, w3, h3, ans3, a4);
//			crush(centerX, centerY, x4, y4, w4, h4, ans4, a4);

			if ((centerX > x1 && centerX < x1 + w1) && (centerY > y1 && centerY < y1 + h1)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a4.setBounds(x1, y1, w1, h1);
					s[0] = a4.getText();
					drag4 = false;
					a4num = 1;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, w1, h1);
				}
			} else if ((centerX > x2 && centerX < x2 + w2) && (centerY > y2 && centerY < y2 + h2)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a4.setBounds(x2, y2, w2, h2);
					s[1] = a4.getText();
					drag4 = false;
					a4num = 2;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, w1, h1);
				}
			} else if ((centerX > x3 && centerX < x3 + w3) && (centerY > y3 && centerY < y3 + h3)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a4.setBounds(x3, y3, w3, h3);
					s[2] = a4.getText();
					drag4 = false;
					a4num = 3;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, w1, h1);
				}
			} else if ((centerX > x4 && centerX < x4 + w4) && (centerY > y4 && centerY < y4 + h4)) {
				if (!(ans4.getBackground() == Color.white)) {
					ans4.setBackground(Color.white);
					a4.setBounds(x4, y4, w4, h4);
					s[3] = a4.getText();
					drag4 = false;
					a4num = 4;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, w1, h1);
				}
			} else {
				if (a4num == 1) {
					ans1.setBackground(gray);
				} else if (a4num == 2) {
					ans2.setBackground(gray);
				} else if (a4num == 3) {
					ans3.setBackground(gray);
				} else if (a4num == 4) {
					ans4.setBackground(gray);
				}

				a4num = 0;
				revalidate();
				repaint();
			}
		}
		repaint();
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
		if (e.getSource() == a1) {
			drag1 = true;
			mouseX1 = e.getX();
			mouseY1 = e.getY();
		}
		if (e.getSource() == a2) {
			drag2 = true;
			mouseX2 = e.getX();
			mouseY2 = e.getY();
		}
		if (e.getSource() == a3) {
			drag3 = true;
			mouseX3 = e.getX();
			mouseY3 = e.getY();
		}
		if (e.getSource() == a4) {
			drag4 = true;
			mouseX4 = e.getX();
			mouseY4 = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drag1 = false;
		drag2 = false;
		drag3 = false;
		drag4 = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void gamePlay() {

	}

	boolean q = false;
	int w = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			for (int i = 0; i < 4; i++) {
				if (s[i] == lgc.a[i]) {
//					System.out.println("같습니다");
					w++;
				} else {
//					System.out.println("다릅니다");
//					return;
				}
//				q = true;
			}
		}
		System.out.println(w);
		if(w == 4) {
			checkLabel.setVisible(true);
			revalidate();
			repaint();
		} else {
			xLabel.setVisible(true);
			a1.setBounds(100, 130, w11, h11);
			a2.setBounds(100, 270, w22, h22);
			a3.setBounds(100, 410, w33, h33);
			a4.setBounds(100, 550, w44, h44);
			ans1.setBackground(gray);
			ans2.setBackground(gray);
			ans3.setBackground(gray);
			ans4.setBackground(gray);
			w = 0;
			revalidate();
			repaint();
		}

		timer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(w == 4) {
					checkLabel.setVisible(false);
					revalidate();
					repaint();
					w = 0;
				}else {
					xLabel.setVisible(false);
					revalidate();
					repaint();				
				}
				timer.stop();
			}
		});
		timer.start();
	}

}
