package select_color;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gameContainer.GameContainer;
import gameHowTo.GameHowTo;

// 수정
// 2월 7일 - 53줄, 99줄, 151줄, 181줄, 230줄, 270줄

public class SelectColorPanel extends GameContainer implements MouseListener {
	// 배경
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	// 스케치북
	private ImageIcon bgSK;
	private JLabel bgSKPan;

	// 컬러버튼 - (수정)
	public JButton btn1;
	public JButton btn2;
	public JButton btn3;

	private Color color;
	private EmptyBorder b1;

	// 정답, 오답
	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	private Font font1;
	private Font font2;

	// 제목부분 글자와 색깔
	private JLabel txtTitle;
	private JLabel txtColor;

	SelectColorConsole scc;
	// 수정 (추가)
	GameHowTo_sc ght; // 수정끝

	@Override
	public void gamePlay() {
	} // 여기 수정하시면 됩니다^^

	public SelectColorPanel() {
		ght = new GameHowTo_sc(); // 수정 (추가)
		scc = new SelectColorConsole();
		this.setLayout(null);

		// 배경
		bgImg = new ImageIcon("images/backgroundImg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		// 스케치북
		bgSK = new ImageIcon("images/sketchbook_Color.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);

		// 버튼
		btn1 = new JButton("btn1");
		btn2 = new JButton("btn2");
		btn3 = new JButton("btn3");
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
		// 수정 (삭제함)
//		MyMouseListener listener = new MyMouseListener();
//		btn1.addMouseListener(this);
//		btn2.addMouseListener(this);
//		btn3.addMouseListener(this); // 수정끝

		// 정답, 오답
		checkIcon = new ImageIcon("images/checked.png");
		checkLabel = new JLabel(checkIcon);
		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		checkLabel.setBounds(765, 105, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
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

		// 수정 (추가)
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		ght.setBounds(100, 100, 820, 530);
		bgImgPan.add(ght);
		ght.exit.addActionListener(this); // 수정끝
		// 수정 (삭제)
//		ght.exit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				btn1.addMouseListener(SelectColorPanel.this);
//				btn2.addMouseListener(SelectColorPanel.this);
//				btn3.addMouseListener(SelectColorPanel.this);
//				btn1.setEnabled(true);
//				btn2.setEnabled(true);
//				btn3.setEnabled(true);
//
//				ght.setVisible(false);
//			}
//		}); // 수정끝

		// 판넬 붙이기
		bgSKPan.add(txtTitle);
		bgSKPan.add(txtColor);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		bgImgPan.add(bgSKPan);

		this.add(bgImgPan);
	}

	// 정답 판별하기
	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정 - 추가 및 삭제
		if (e.getSource() == ght.exit) {
			btn1.addMouseListener(SelectColorPanel.this);
			btn2.addMouseListener(SelectColorPanel.this);
			btn3.addMouseListener(SelectColorPanel.this);
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);

			ght.setVisible(false);
		} // 수정끝

		JButton btn = (JButton) e.getSource();
		if ("btn1".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[0]) {
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}

		if ("btn2".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[1]) {
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}

		if ("btn3".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[2]) {
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

	// 수정 (삭제함)
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

	// 수정 (추가)
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