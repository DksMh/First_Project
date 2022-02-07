package max_colors;

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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gameContainer.GameContainer;
import gameHowTo.GameHowTo;

// 수정
// 2월 7일 - 125줄, 145줄, 189줄, 228줄

public class MaxColorPanel extends GameContainer implements MouseListener {
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	// 스케치북
	private ImageIcon bgSK;
	private JLabel bgSKPan;

	// 정답, 오답
	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	// 3 x 3
	private JPanel colorPan;

	// 빨강, 파랑, 노랑
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	private Color color;
	private EmptyBorder b1;

	// 제목
	private JLabel txtTitle;

	private Font font;

	MaxColorConsole mcc;

	@Override
	public void gamePlay() {
	} // 여기 수정하시면 됩니다^^

	public MaxColorPanel() {
		mcc = new MaxColorConsole();
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		// 배경
		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);
		bgImgPan.setLayout(null);

		// 스케치북
		bgSK = new ImageIcon("images/sk.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);

		// 정답, 오답
		checkIcon = new ImageIcon("images/o.png");
		checkLabel = new JLabel(checkIcon);
		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		checkLabel.setBounds(765, 105, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(765, 105, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		// 3 x 3
		colorPan = new JPanel();
		colorPan.setLayout(new GridLayout(3, 3));
		colorPan.setBounds(100, 150, 300, 200);
		// 3 x 3 컬러 배열 출력
		for (int i = 0; i < 9; i++) {
			JPanel b = new JPanel();
			LineBorder b2 = new LineBorder(new Color(248, 248, 248), 1);
			b.setBorder(b2);
			b.setBackground(mcc.col[mcc.arr[i]]);
			colorPan.add(b);
		}

		// 버튼
		btn1 = new JButton("btn1");
		btn2 = new JButton("btn2");
		btn3 = new JButton("btn3");
		color = new Color(0, 0, 0, 0);
		btn1.setForeground(color);
		btn2.setForeground(color);
		btn3.setForeground(color);
		btn1.setFocusPainted(false);
		btn2.setFocusPainted(false);
		btn3.setFocusPainted(false);
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
		// 수정 (삭제함)
//		MyMouseListener listener = new MyMouseListener();
		btn1.addMouseListener(this);
		btn2.addMouseListener(this);
		btn3.addMouseListener(this); // 수정끝

		// 제목
		txtTitle = new JLabel("가장 많은 색을 선택해주세요");
		font = new Font("맑은 고딕", Font.BOLD, 25);
		txtTitle.setFont(font);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(215, 50, 500, 100);

		// 판넬 붙이기
		bgSKPan.add(colorPan);
		bgSKPan.add(txtTitle);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		bgImgPan.add(bgSKPan);
//		btn1.setEnabled(false); // 수정 (삭제함)
//		btn2.setEnabled(false);
//		btn3.setEnabled(false); // 수정끝

		this.add(bgImgPan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼의 정답판별
		JButton btn = (JButton) e.getSource();
		if ("btn1".equals(btn.getText())) {
			if ("RED".equals(mcc.ans)) {
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
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
	}

	// 수정 (주석부분 삭제함)
	// 버튼 위에 마우스를 올리면 보더생기기
//	class MyMouseListener implements MouseListener {
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			btn1 = (JButton) e.getSource();
//			btn1.setBorder(new LineBorder(Color.black, 2));
//			btn2 = (JButton) e.getSource();
//			btn2.setBorder(new LineBorder(Color.black, 2));
//			btn3 = (JButton) e.getSource();
//			btn3.setBorder(new LineBorder(Color.black, 2));
//		}
//		
//		@Override 
//		public void mouseExited(MouseEvent e) {
//			btn1 = (JButton) e.getSource();
//			btn1.setBorder(new LineBorder(Color.black, 0));
//			btn2 = (JButton) e.getSource();
//			btn2.setBorder(new LineBorder(Color.black, 0));
//			btn3 = (JButton) e.getSource();
//			btn3.setBorder(new LineBorder(Color.black, 0));
//		}
//		
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			
//		}
//		
//		@Override
//		public void mousePressed(MouseEvent e) {
//			
//		}
//		
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			
//		}
//	} // 수정끝

	// 수정 (코드추가)
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	// 버튼 위에 마우스를 올리면 보더생기기
	@Override
	public void mouseEntered(MouseEvent e) {
		btn1 = (JButton) e.getSource();
		btn1.setBorder(new LineBorder(Color.black, 2));
		btn2 = (JButton) e.getSource();
		btn2.setBorder(new LineBorder(Color.black, 2));
		btn3 = (JButton) e.getSource();
		btn3.setBorder(new LineBorder(Color.black, 2));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		btn1 = (JButton) e.getSource();
		btn1.setBorder(new LineBorder(Color.black, 0));
		btn2 = (JButton) e.getSource();
		btn2.setBorder(new LineBorder(Color.black, 0));
		btn3 = (JButton) e.getSource();
		btn3.setBorder(new LineBorder(Color.black, 0));
	} // 수정끝
}