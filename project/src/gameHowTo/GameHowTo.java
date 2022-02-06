package gameHowTo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameHowTo extends JPanel {

	private ImageIcon bgSK;
	private JLabel bgSkPan;

	private JButton next;
	private JButton prev;
	public JButton exit;

	private ImageIcon gameImg;
	private JLabel gameImgPan;

	private JLabel text;
	private Font font1;

	int count = 0;

	public GameHowTo() {
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setTitle("주위 집중력 훈련 게임");
//		setSize(1024, 768);
		this.setBounds(100, 100, 820, 525);
		this.setBackground(new Color(0, 0, 0, 0));
		comm();
		first();
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 1) {
					mid();
				} else if (count == 2) {
					last();
				}
			}
		});
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				if (count == 0) {
					first();
				} else if (count == 1) {
					mid();
				}
			}
		});
//		exit.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				GameHowTo.this.setVisible(false);
//			}
//		});

	}

	public void comm() {

		bgSK = new ImageIcon("images/sk1.png");
		bgSkPan = new JLabel(bgSK);
		bgSkPan.setBounds(100, 100, 820, 525);

		font1 = new Font("맑은 고딕", Font.PLAIN, 24);

		next = new JButton("다음");
		next.setBounds(720, 440, 80, 60);
		prev = new JButton("이전");
		prev.setBounds(25, 440, 80, 60);
		exit = new JButton("종료");
		exit.setBounds(720, 50, 80, 60);

		next.setBackground(Color.orange);
		prev.setBackground(Color.pink);
		exit.setBackground(Color.red);

		bgSkPan.add(next);
		bgSkPan.add(prev);
		bgSkPan.add(exit);
		this.add(bgSkPan);
	}

	public void first() {
		bgSkPan.removeAll();

		gameImg = new ImageIcon("images/m.png");
		gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(140, 70, 550, 300);

		text = new JLabel("게임 설명 주절주절1");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(140, 380, 550, 120);
		text.setOpaque(true);
		text.setBackground(Color.pink);

		prev.setVisible(false);
		next.setVisible(true);
		bgSkPan.add(next);
		bgSkPan.add(prev);
		bgSkPan.add(exit);
		bgSkPan.add(text);
		bgSkPan.add(gameImgPan);
		revalidate();
		repaint();
	}

	public void mid() {
		bgSkPan.removeAll();

		gameImg = new ImageIcon("images/m.png");
		gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(140, 70, 550, 300);

		text = new JLabel("게임 설명 주절주2");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(140, 380, 550, 120);
		text.setOpaque(true);
		text.setBackground(Color.pink);

		prev.setVisible(true);
		next.setVisible(true);
		bgSkPan.add(next);
		bgSkPan.add(prev);
		bgSkPan.add(exit);
		bgSkPan.add(text);
		bgSkPan.add(gameImgPan);
		revalidate();
		repaint();
	}

	public void last() {
		bgSkPan.removeAll();

		gameImg = new ImageIcon("images/m.png");
		gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(140, 70, 550, 300);

		text = new JLabel("게임 설명 주절주3");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(140, 380, 550, 120);
		text.setOpaque(true);
		text.setBackground(Color.pink);

		next.setVisible(false);
		bgSkPan.add(next);
		bgSkPan.add(prev);
		bgSkPan.add(exit);
		bgSkPan.add(text);
		bgSkPan.add(gameImgPan);
		revalidate();
		repaint();
	}

//	public static void main(String[] args) {
//		new GameHowTo().setVisible(true);
//	}

}