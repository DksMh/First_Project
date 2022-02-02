package Life_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameContainer.GameContainer;

public class crushTest extends GameContainer implements MouseListener, MouseMotionListener {
	// 빨간색(빨간색과 팥죽색)
	private JLabel a1;
	private JLabel a2;
	
//	private JPanel b1;
//	private JPanel b2;
	
	// 드래그앤드롭
	private boolean drag1;
	private boolean drag2;
	int mouseX1 = 0;
	int mouseX2 = 0;
	int mouseY1 = 0;
	int mouseY2 = 0;
	
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	// 흰색 부분 (정답)
	private JPanel ans1;
	private JPanel ans2;

	// 흰색1
	int x = 150;
	int y = 550;
	int w = 100;
	int h = 100;
	
	// 흰색2
	int x0 = 350;
	int y0 = 550;
	int w0 = 100;
	int h0 = 100;

	// 빨간색
	int x1 = 362;
	int y1 = 130;
	int w1 = 100;
	int h1 = 100;

	// 팥죽색
	int x2 = 402;
	int y2 = 130;
	int w2 = 100;
	int h2 = 100;

	boolean flag = false;

	public crushTest() {
		this.setLayout(null);

		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		a1 = new JLabel();
		drag1 = false;
		a2 = new JLabel();
		drag2 = false;

//		a1 = new JLabel();
//		drag1 = false;
//		a1.setBounds(x1, y1, w1, h1);
//		bgImgPan.add(a1);
//		b2.setBounds(x2, y2, w2, h2);
//		a1.addMouseMotionListener(this);
//		a1.addMouseListener(this);

		a1.setOpaque(true);
		a1.setBackground(new Color(233, 23, 22));
		a1.setBounds(x1, y1, w1, h1);
		a1.addMouseMotionListener(this);
		a1.addMouseListener(this);
		bgImgPan.add(a1);
		
		a2.setOpaque(true);
		a2.setBackground(new Color(133, 23, 22));
		a2.setBounds(x2, y2, w2, h2);
		a2.addMouseMotionListener(this);
		a2.addMouseListener(this);
		bgImgPan.add(a2);

		ans1 = new JPanel();
		ans1.setBounds(x, y, w, h);
		bgImgPan.add(ans1);
		ans2 = new JPanel();
		ans2.setBounds(x0, y0, w0, h0);
		bgImgPan.add(ans2);

		this.add(bgImgPan);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1024, 768);
		f.add(new crushTest());
		f.setVisible(true);
	}
	
	public void crush(int centerX, int centerY, int x, int y, int w, int h, JPanel ans, JLabel a) {
		if (centerX > x && centerX < x + w) {
			if (centerY > y && centerY < y + h) {
				a.setBounds(x, y, w, h); // 흰색에 딱붙어라
//				if(flag == true) {
//					System.out.println("나가");
//				}
//				if(centerX == 200 && centerY == 600) {
//					flag = true;
//				} else {
//					flag = false;
//				}
				
//				a = a.setBounds(x, y, w, h);
//				if(ans == ) {
//					
//				}
//				flag = true;
//				if(flag == true) {
//					System.out.println("나가");
//				}
				revalidate();
				repaint();
			}
		} else {
//			flag = false;
			ans.setBackground(Color.white);
			revalidate();
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (drag1 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX1, jc.getY() + e.getY() - mouseY1);

//			x = jc.getX();
//			y = jc.getY();
//			
//			if(x < b1 && (x + w) > b1) {
//				if(y < b2 && (y + h) > b2){
//					ans1.setBackground(Color.black);
//					System.out.println("충돌했습니다");
//				}
//			}
			x1 = jc.getX();
			y1 = jc.getY();
			
			int centerX = x1 + w1 / 2;
			int centerY = y1 + h1 / 2;
			
			crush(centerX, centerY, x, y, w, h, ans1, a1);
			crush(centerX, centerY, x0, y0, w0, h0, ans2, a1); 
			
			

//			if (centerX > x && centerX < x + w) {
//				if (centerY > y && centerY < y + h) {
//					ans1.setBackground(Color.black);
//					revalidate();
//					repaint();
//				}
//			} else {
//				ans1.setBackground(Color.white);
//				revalidate();
//				repaint();
//			}
		}
		if (drag2 == true) {
			JComponent jc = (JComponent) e.getSource();
			jc.setLocation(jc.getX() + e.getX() - mouseX2, jc.getY() + e.getY() - mouseY2);
			
			x2 = jc.getX();
			y2 = jc.getY();
			
			int centerX = x2 + w2 / 2;
			int centerY = y2 + h2 / 2;
			
			crush(centerX, centerY, x, y, w, h, ans1, a2);
			crush(centerX, centerY, x0, y0, w0, h0, ans2, a2);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		if (e.getSource() == b1) {
//			drag1 = true;
//			mouseX1 = e.getX();
//			mouseY1 = e.getY();
//		}
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drag1 = false;
		drag2 = false;
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
