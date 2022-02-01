package Life_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameContainer.GameContainer;

// 해결해야하는 부분
// 정답칸에 4개가 다들어가는 오류 잡기
// 드래그앤드롭 드래그하면 오답일시 제자리로 돌리기 (4개 다했을때 판별하기)
// 드래그앤드롭 드래그하면 정답일시 체크표시 나오게하기
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
	
	// 문제의 폰트
	private Font font1;

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
	// 빨간색 a1의 좌표
	int x11 = 100;
	int y11 = 130;
	int w11 = 300;
	int h11 = 80;
	
	// 노란색 a2의 좌표
	int x22 = 100;
	int y22 = 270;
	int w22 = 300;
	int h22 = 80;
	
	// 초록색 a3의 좌표
	int x33 = 100;
	int y33 = 410;
	int w33 = 300;
	int h33 = 80;
	
	// 파란색 a4의 좌표
	int x44 = 100;
	int y44 = 550;
	int w44 = 300;
	int h44 = 80;

	public LifeGamePanel() {
		lgc = new LifeGameConsole();
		this.setLayout(null);

		// 배경을 선언하고 add까지 했지만 들어가지 않음...
		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

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

		// 정답칸 만들기
		// 문제점 : 문제의 지문의 길이가 길면 정답칸도 길어져서 순서를 맞출때 크기가 애매해질수있다
		// 어떻게 해결할지 고민하기
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
	public void crush(int centerX, int centerY, int x, int y, int w, int h, JPanel ans, JLabel a) {
		if (centerX > x && centerX < x + w) {
			if (centerY > y && centerY < y + h) {
				a.setBounds(x, y, w, h);
//				ans.setBackground(new Color(255,127,0));
				revalidate();
				repaint();
			}
		} else {
			ans.setBackground(Color.white);
			revalidate();
			repaint();
		}
	}

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
			
			crush(centerX, centerY, x1, y1, w1, h1, ans1, a1);
			crush(centerX, centerY, x2, y2, w2, h2, ans2, a1);
			crush(centerX, centerY, x3, y3, w3, h3, ans3, a1);
			crush(centerX, centerY, x4, y4, w4, h4, ans4, a1);
		}
		if (drag2 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX2, jc.getY() + e.getY() - mouseY2);
			
			x22 = jc.getX();
			y22 = jc.getY();
			
			int centerX = x22 + w22 / 2;
			int centerY = y22 + h22 / 2;
			
			crush(centerX, centerY, x1, y1, w1, h1, ans1, a2);
			crush(centerX, centerY, x2, y2, w2, h2, ans2, a2);
			crush(centerX, centerY, x3, y3, w3, h3, ans3, a2);
			crush(centerX, centerY, x4, y4, w4, h4, ans4, a2);
		}
		if (drag3 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX3, jc.getY() + e.getY() - mouseY3);
			
			x33 = jc.getX();
			y33 = jc.getY();
			
			int centerX = x33 + w33 / 2;
			int centerY = y33 + h33 / 2;
			
			crush(centerX, centerY, x1, y1, w1, h1, ans1, a3);
			crush(centerX, centerY, x2, y2, w2, h2, ans2, a3);
			crush(centerX, centerY, x3, y3, w3, h3, ans3, a3);
			crush(centerX, centerY, x4, y4, w4, h4, ans4, a3);
		}
		if (drag4 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX4, jc.getY() + e.getY() - mouseY4);
			
			x44 = jc.getX();
			y44 = jc.getY();
			
			int centerX = x44 + w44 / 2;
			int centerY = y44 + h44 / 2;
			
			crush(centerX, centerY, x1, y1, w1, h1, ans1, a4);
			crush(centerX, centerY, x2, y2, w2, h2, ans2, a4);
			crush(centerX, centerY, x3, y3, w3, h3, ans3, a4);
			crush(centerX, centerY, x4, y4, w4, h4, ans4, a4);
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
