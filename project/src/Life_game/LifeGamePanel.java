package Life_game;

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

import gameContainer.GameContainer;

// 해결해야하는 부분
// 정답칸에 4개가 다들어가는 오류 잡기 - 해결!!!!!!!!!!!!!!!!!!!!!!!!!
// 드래그앤드롭 드래그하면 오답일시 제자리로 돌리기 (4개 다했을때 판별하기) - 해결
// 드래그앤드롭 드래그하면 정답일시 체크표시 나오게하기 - 해결
// 같은 문제가 연속으로 나오는 중복검사 하기 -- 나중에 하기 (여기서 해결불가 합치고 해결하기)

public class LifeGamePanel extends GameContainer implements MouseListener, MouseMotionListener {
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	// 정답, 오답 이미지
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

	// 문제 제목
	private JButton title;

	// 폰트
	private Font font1; // 사이즈 : 24
	private Font font2; // 사이즈 : 20
	private Font font3; // 사이즈 : 28
	private Font font4; // 사이즈 : 50

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

	// 불변의 사이즈(공통)
	int width = 300;
	int height = 80;

	// ans1의 좌표
	int x1 = 600;
	int y1 = 130;

	// ans2의 좌표
	int x2 = 600;
	int y2 = 270;

	// ans3의 좌표
	int x3 = 600;
	int y3 = 410;

	// ans4의 좌표
	int x4 = 600;
	int y4 = 550;

	////////////////////////////////////
	// 노란색 a1의 좌표
	int x11 = 100;
	int y11 = 130;

	// 노란색 a2의 좌표
	int x22 = 100;
	int y22 = 270;

	// 노란색 a3의 좌표
	int x33 = 100;
	int y33 = 410;

	// 노란색 a4의 좌표
	int x44 = 100;
	int y44 = 550;

	// 노란색이 하얀색 어디에 붙었나 확인용도
	int a1num = 0;
	int a2num = 0;
	int a3num = 0;
	int a4num = 0;

	Color gray = new Color(252, 252, 252);

	// 제출 버튼
	JButton submit;

	int lifeRemaining = 2;

	static Timer timer;

	public void gamePlay() {
	} // 여기 수정하시면 됩니다^^

	public LifeGamePanel() {
		lgc = new LifeGameConsole();
		this.setLayout(null);

		// 배경
		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		// 제출버튼
		submit = new JButton("제출");
		submit.setBounds(770, 650, 130, 50);
		font2 = new Font("맑은 고딕", Font.BOLD, 20);
		submit.setFont(font2);
		bgImgPan.add(submit);
		submit.addActionListener(this);

		// 정답, 오답
		checkIcon = new ImageIcon("images/o.png");
		checkLabel = new JLabel(checkIcon);
		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		checkLabel.setBounds(427, 284, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(427, 284, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		// 문제 제목
		MyMouseListener listener = new MyMouseListener();
		title = new JButton(lgc.ArrLabel[lgc.k]);
		title.setBounds(350, 30, 300, 80);
		font3 = new Font("맑은 고딕", Font.BOLD, 28);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(font3);
		title.setOpaque(true);
		title.setBackground(gray);
		Border c = new LineBorder(new Color(254, 178, 55), 7);
		title.setBorder(c);
		title.addMouseListener(listener);
		title.setFocusPainted(false);
		bgImgPan.add(title);
		// new Color(254,178,55) 주황 컬러

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
		a1.setBounds(100, 130, width, height);
		a2.setBounds(100, 270, width, height);
		a3.setBounds(100, 410, width, height);
		a4.setBounds(100, 550, width, height);
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
		num1 = new JLabel("1");
		num2 = new JLabel("2");
		num3 = new JLabel("3");
		num4 = new JLabel("4");
		font4 = new Font("맑은 고딕", Font.BOLD, 50);
		num1.setFont(font4);
		num2.setFont(font4);
		num3.setFont(font4);
		num4.setFont(font4);
//		num1 = new JLabel(lgc.a[0]);
//		num2 = new JLabel(lgc.a[1]);
//		num3 = new JLabel(lgc.a[2]);
//		num4 = new JLabel(lgc.a[3]);
		ans1.setBounds(x1, y1, width, height);
		ans2.setBounds(x2, y2, width, height);
		ans3.setBounds(x3, y3, width, height);
		ans4.setBounds(x4, y4, width, height);
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

	// 문제 제목의 버튼리스너
	class MyMouseListener implements MouseListener {
		@Override
		public void mouseEntered(MouseEvent e) {
			title = (JButton) e.getSource();
			Border c = new LineBorder(new Color(254, 178, 55), 7);
			title.setBorder(c);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			title = (JButton) e.getSource();
			Border c = new LineBorder(new Color(254, 178, 55), 7);
			title.setBorder(c);
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	// 정답 판별을 위한 문제 글자 보관함
	String[] s = new String[4];

	// 드래그 앤 드롭
	@Override
	public void mouseDragged(MouseEvent e) {
		if (drag1 == true) {
			// 실시간으로 드래그의 좌표를 알고싶어서
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX1, jc.getY() + e.getY() - mouseY1);

			x11 = jc.getX();
			y11 = jc.getY();

			// 노란색의 중앙 좌표
			int centerX = x11 + width / 2;
			int centerY = y11 + height / 2;

			// 노란색과 흰색 충돌검사 & 흰색에 노란색이 하나씩 들어가도록 하는 코드(4번 반복)
			if ((centerX > x1 && centerX < x1 + width) && (centerY > y1 && centerY < y1 + height)) {
				if (!(ans1.getBackground() == Color.white)) {
					ans1.setEnabled(false);
					ans1.setBackground(Color.white);
					a1.setBounds(x1, y1, width, height);
					s[0] = a1.getText();
					a1num = 1;
					drag1 = false;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, width, height);
				}
			} else if ((centerX > x2 && centerX < x2 + width) && (centerY > y2 && centerY < y2 + height)) {
				if (!(ans2.getBackground() == Color.white)) {
					ans2.setBackground(Color.white);
					a1.setBounds(x2, y2, width, height);
					s[1] = a1.getText();
					drag1 = false;
					a1num = 2;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, width, height);
				}
			} else if ((centerX > x3 && centerX < x3 + width) && (centerY > y3 && centerY < y3 + height)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a1.setBounds(x3, y3, width, height);
					s[2] = a1.getText();
					drag1 = false;
					a1num = 3;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, width, height);
				}
			} else if ((centerX > x4 && centerX < x4 + width) && (centerY > y4 && centerY < y4 + height)) {
				if (!(ans4.getBackground() == Color.white)) {
					ans4.setBackground(Color.white);
					a1.setBounds(x4, y4, width, height);
					s[3] = a1.getText();
					drag1 = false;
					a1num = 4;
					revalidate();
					repaint();
				} else {
					a1.setBounds(100, 130, width, height);
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

			int centerX = x22 + width / 2;
			int centerY = y22 + height / 2;

			if ((centerX > x1 && centerX < x1 + width) && (centerY > y1 && centerY < y1 + height)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a2.setBounds(x1, y1, width, height);
					s[0] = a2.getText();
					drag2 = false;
					a2num = 1;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, width, height);
				}
			} else if ((centerX > x2 && centerX < x2 + width) && (centerY > y2 && centerY < y2 + height)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a2.setBounds(x2, y2, width, height);
					s[1] = a2.getText();
					drag2 = false;
					a2num = 2;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, width, height);
				}
			} else if ((centerX > x3 && centerX < x3 + width) && (centerY > y3 && centerY < y3 + height)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a2.setBounds(x3, y3, width, height);
					s[2] = a2.getText();
					drag2 = false;
					a2num = 3;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, width, height);
				}
			} else if ((centerX > x4 && centerX < x4 + width) && (centerY > y4 && centerY < y4 + height)) {
				if (!(ans4.getBackground() == Color.white)) {

					ans4.setBackground(Color.white);
					a2.setBounds(x4, y4, width, height);
					s[3] = a2.getText();
					drag2 = false;
					a2num = 4;
					revalidate();
					repaint();
				} else {
					a2.setBounds(100, 270, width, height);
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

			int centerX = x33 + width / 2;
			int centerY = y33 + height / 2;

			if ((centerX > x1 && centerX < x1 + width) && (centerY > y1 && centerY < y1 + height)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a3.setBounds(x1, y1, width, height);
					s[0] = a3.getText();
					drag3 = false;
					a3num = 1;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, width, height);
				}
			} else if ((centerX > x2 && centerX < x2 + width) && (centerY > y2 && centerY < y2 + height)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a3.setBounds(x2, y2, width, height);
					s[1] = a3.getText();
					drag3 = false;
					a3num = 2;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, width, height);
				}
			} else if ((centerX > x3 && centerX < x3 + width) && (centerY > y3 && centerY < y3 + height)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a3.setBounds(x3, y3, width, height);
					s[2] = a3.getText();
					drag3 = false;
					a3num = 3;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, width, height);
				}
			} else if ((centerX > x4 && centerX < x4 + width) && (centerY > y4 && centerY < y4 + height)) {
				if (!(ans4.getBackground() == Color.white)) {

					ans4.setBackground(Color.white);
					a3.setBounds(x4, y4, width, height);
					s[3] = a3.getText();
					drag3 = false;
					a3num = 4;
					revalidate();
					repaint();
				} else {
					a3.setBounds(100, 410, width, height);
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

			int centerX = x44 + width / 2;
			int centerY = y44 + height / 2;

			if ((centerX > x1 && centerX < x1 + width) && (centerY > y1 && centerY < y1 + height)) {
				if (!(ans1.getBackground() == Color.white)) {

					ans1.setBackground(Color.white);
					a4.setBounds(x1, y1, width, height);
					s[0] = a4.getText();
					drag4 = false;
					a4num = 1;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, width, height);
				}
			} else if ((centerX > x2 && centerX < x2 + width) && (centerY > y2 && centerY < y2 + height)) {
				if (!(ans2.getBackground() == Color.white)) {

					ans2.setBackground(Color.white);
					a4.setBounds(x2, y2, width, height);
					s[1] = a4.getText();
					drag4 = false;
					a4num = 2;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, width, height);
				}
			} else if ((centerX > x3 && centerX < x3 + width) && (centerY > y3 && centerY < y3 + height)) {
				if (!(ans3.getBackground() == Color.white)) {
					ans3.setBackground(Color.white);
					a4.setBounds(x3, y3, width, height);
					s[2] = a4.getText();
					drag4 = false;
					a4num = 3;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, width, height);
				}
			} else if ((centerX > x4 && centerX < x4 + width) && (centerY > y4 && centerY < y4 + height)) {
				if (!(ans4.getBackground() == Color.white)) {
					ans4.setBackground(Color.white);
					a4.setBounds(x4, y4, width, height);
					s[3] = a4.getText();
					drag4 = false;
					a4num = 4;
					revalidate();
					repaint();
				} else {
					a4.setBounds(100, 550, width, height);
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
		// 노란색이 흰색에 한번 들어갔다가 나오면 발생하는 오류를 잡아주는 코드
		if (e.getSource() == a1) {
			drag1 = true;
			if (a1num == 1) {
				a1.setBounds(100, 130, width, height);
				drag1 = false;
				a1num = 0;
				s[0] = null;
				ans1.setBackground(gray);
			} else if (a1num == 2) {
				a1.setBounds(100, 130, width, height);
				drag1 = false;
				a1num = 0;
				s[1] = null;
				ans2.setBackground(gray);
			} else if (a1num == 3) {
				a1.setBounds(100, 130, width, height);
				drag1 = false;
				a1num = 0;
				s[2] = null;
				ans3.setBackground(gray);
			} else if (a1num == 4) {
				a1.setBounds(100, 130, width, height);
				drag4 = false;
				a1num = 0;
				s[3] = null;
				ans4.setBackground(gray);
			}
			mouseX1 = e.getX();
			mouseY1 = e.getY();
		}
		if (e.getSource() == a2) {
			drag2 = true;
			if (a2num == 1) {
				a2.setBounds(100, 270, width, height);
				drag2 = false;
				a2num = 0;
				s[0] = null;
				ans1.setBackground(gray);
			} else if (a2num == 2) {
				a2.setBounds(100, 270, width, height);
				drag2 = false;
				a2num = 0;
				s[1] = null;
				ans2.setBackground(gray);
			} else if (a2num == 3) {
				a2.setBounds(100, 270, width, height);
				drag2 = false;
				a2num = 0;
				s[2] = null;
				ans3.setBackground(gray);
			} else if (a2num == 4) {
				a2.setBounds(100, 270, width, height);
				drag2 = false;
				a2num = 0;
				s[3] = null;
				ans4.setBackground(gray);
			}
			mouseX2 = e.getX();
			mouseY2 = e.getY();
		}
		if (e.getSource() == a3) {
			drag3 = true;
			if (a3num == 1) {
				a3.setBounds(100, 410, width, height);
				drag3 = false;
				a3num = 0;
				s[0] = null;
				ans1.setBackground(gray);
			} else if (a3num == 2) {
				a3.setBounds(100, 410, width, height);
				drag3 = false;
				a3num = 0;
				s[1] = null;
				ans2.setBackground(gray);
			} else if (a3num == 3) {
				a3.setBounds(100, 410, width, height);
				drag3 = false;
				a3num = 0;
				s[2] = null;
				ans3.setBackground(gray);
			} else if (a3num == 4) {
				a3.setBounds(100, 410, width, height);
				drag3 = false;
				a3num = 0;
				s[3] = null;
				ans4.setBackground(gray);
			}
			mouseX3 = e.getX();
			mouseY3 = e.getY();
		}
		if (e.getSource() == a4) {
			drag4 = true;
			if (a4num == 1) {
				a4.setBounds(100, 550, width, height);
				drag4 = false;
				a4num = 0;
				s[0] = null;
				ans1.setBackground(gray);
			} else if (a4num == 2) {
				a4.setBounds(100, 550, width, height);
				drag4 = false;
				a4num = 0;
				s[1] = null;
				ans2.setBackground(gray);
			} else if (a4num == 3) {
				a4.setBounds(100, 550, width, height);
				drag4 = false;
				a4num = 0;
				s[2] = null;
				ans3.setBackground(gray);
			} else if (a4num == 4) {
				a4.setBounds(100, 550, width, height);
				drag4 = false;
				a4num = 0;
				s[3] = null;
				ans4.setBackground(gray);
			}
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

	int w = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		// 정답, 오답 판별
		if (e.getSource() == submit) {
			for (int i = 0; i < 4; i++) {
				if (s[i] == null) {
					JOptionPane.showMessageDialog(bgImgPan, "빈칸이 있어요!");
					return;
				}
			}
			for (int i = 0; i < 4; i++) {
				if (s[i] == lgc.a[i]) {
					w++;
				}
			}
		}
//		System.out.println(w);
		if (w == 4) {
			checkLabel.setVisible(true);
			revalidate();
			repaint();
			w = 0;
			a1num = 0;
			a2num = 0;
			a3num = 0;
			a4num = 0;
		} else {
			lifeRemaining--;
			if (lifeRemaining == 0) {
//				JOptionPane.showMessageDialog(bgImgPan, "게임 종료합니다!");
				lifeRemaining = 2;
				xLabel.setVisible(true);
			} else if (lifeRemaining == 1) {
				JOptionPane.showMessageDialog(bgImgPan, "곰곰히 생각해보세요!");
			}
			a1num = 0;
			a2num = 0;
			a3num = 0;
			a4num = 0;
			a1.setBounds(100, 130, width, height);
			a2.setBounds(100, 270, width, height);
			a3.setBounds(100, 410, width, height);
			a4.setBounds(100, 550, width, height);
			ans1.setBackground(gray);
			ans2.setBackground(gray);
			ans3.setBackground(gray);
			ans4.setBackground(gray);
			w = 0;
			revalidate();
			repaint();
		}

		// 정답,오답 이미지 1.5초간 보이게하기
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (w == 4) {
					checkLabel.setVisible(false);
					revalidate();
					repaint();
					w = 0;
				} else {
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
