package org.proj.game.card;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import static org.proj.Resource.*;

public class GameHowTo_card extends JPanel implements ActionListener {
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();

	private ImageIcon bgSK;
	private JLabel bgSkPan;

	private JButton next;
	private JButton prev;
	public JButton exit;
	private JButton cardBtn;
	private JButton[] Btn;

	private JToggleButton soundBtn;

	private Font font1;

	int count = 0;

	public GameHowTo_card(JButton cardBtn, JButton[] Btn) {
		this.setBackground(new Color(37, 9, 9));
		this.setLayout(null);
		this.setBounds(100, 100, 820, 530);
		this.cardBtn = cardBtn;
		this.Btn = Btn;
		cardBtn.setVisible(false);
		comm();
		first();
		mid();
		last();

		exit.addActionListener(this);
		prev.addActionListener(this);
		next.addActionListener(this);
		soundBtn.addActionListener(this);
	}

	public void comm() {
		bgSK = new ImageIcon("images/comm/HowTo_background.png");
		bgSkPan = new JLabel(bgSK);
		bgSkPan.setBounds(0, 0, 820, 525);
		bgSkPan.setLayout(null);

		font1 = new Font("맑은 고딕", Font.PLAIN, 24);

//		next = new JButton("다음");
		next = new JButton(new ImageIcon("images/comm/HowTo_right.png"));
		next.setFocusPainted(false);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		next.setBounds(720, 230, 80, 80);

//		prev = new JButton("이전");
		prev = new JButton(new ImageIcon("images/comm/HowTo_left.png"));
		prev.setFocusPainted(false);
		prev.setBorderPainted(false);
		prev.setContentAreaFilled(false);
		prev.setBounds(25, 230, 80, 80);

//		exit = new JButton("종료");
		exit = new JButton(new ImageIcon("images/comm/HowTo_exit.png"));
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setBounds(720, 20, 80, 80);

		soundBtn = new JToggleButton(new ImageIcon("images/comm/HowTo_sound.png"));
		soundBtn.setFocusPainted(false);
		soundBtn.setBorderPainted(false);
		soundBtn.setContentAreaFilled(false);
		soundBtn.setBounds(20, 20, 80, 80);

		prev.setVisible(false); // 수정 (추가)
		bgSkPan.add(soundBtn);
		bgSkPan.add(next);
		bgSkPan.add(prev);
		bgSkPan.add(exit);
		this.add(bgSkPan);
	}

	public void first() {
		pan1.setLayout(null);
		pan1.setBounds(130, 10, 570, 505);
		pan1.setBackground(Color.white);

		ImageIcon gameImg = new ImageIcon("images/card/HowTo_Card1.png");
		JLabel gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(10, 10, 550, 390);

		JLabel text = new JLabel("<html>3초동안 카드를 보여줍니다.<br>카드의 그림을 기억해주세요.<html>");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(10, 410, 550, 80);
		text.setOpaque(true);
		text.setBackground(Color.white);
		Border c = new LineBorder(new Color(137, 170, 108), 7);
		text.setBorder(c);

		pan1.add(text);
		pan1.add(gameImgPan);
		bgSkPan.add(pan1);
	}

	public void mid() {
		pan2.setLayout(null);
		pan2.setBounds(130, 10, 570, 505);
		pan2.setBackground(Color.white);

		ImageIcon gameImg = new ImageIcon("images/card/HowTo_Card2.png");
		JLabel gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(10, 10, 550, 390);

		JLabel text = new JLabel("<html>같은 그림의 카드 2장을 찾아서 클릭해주세요.<br>카드 2장을 클릭하면 시도횟수가 1회 줄어듭니다.</html>");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(10, 410, 550, 80);

		text.setOpaque(true);
		text.setBackground(Color.white); // 추가
		Border c = new LineBorder(new Color(137, 170, 108), 7);
		text.setBorder(c);

		pan2.setVisible(false);
		pan2.add(text);
		pan2.add(gameImgPan);
		bgSkPan.add(pan2);
	}

	public void last() {
		pan3.setLayout(null);
		pan3.setBounds(130, 10, 570, 505);
		pan3.setBackground(Color.white);

		ImageIcon gameImg = new ImageIcon("images/card/HowTo_Card3.png");
		JLabel gameImgPan = new JLabel(gameImg);
		gameImgPan.setBounds(10, 10, 550, 390);

		JLabel text = new JLabel("<html>같은 그림을 맞추면 카드가 회색으로 변합니다.<br>&nbsp&nbsp 12회 안에 모든 카드의 짝을 맞춰주세요.</html>");
		text.setFont(font1);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setBounds(10, 410, 550, 80);
		text.setOpaque(true);
		text.setBackground(Color.white); // 추가
		Border c = new LineBorder(new Color(137, 170, 108), 7);
		text.setBorder(c);

		pan3.setVisible(false);
		pan3.add(text);
		pan3.add(gameImgPan);
		bgSkPan.add(pan3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == prev) {
			if (bgm.clipNarr != null) {
				bgm.clipNarr.stop();
			}
			soundBtn.setSelected(false);
			count--;
		}
		if (e.getSource() == next) {
			if (bgm.clipNarr != null) {
				bgm.clipNarr.stop();
			}
			soundBtn.setSelected(false);
			count++;
		}

		if (e.getSource() == exit) {
			this.setVisible(false);
			if (bgm.clipNarr != null) {
				bgm.clipNarr.stop();
			}
			soundBtn.setSelected(false);
			cardBtn.setVisible(true);
			for (int i = 0; i < Btn.length; i++) {
				Btn[i].setVisible(true);
			}
			
		}
		
		if (e.getSource() == soundBtn) {
			if (soundBtn.isSelected() == true) {
				if (count == 0) {
					bgm.playNar("card/card01.wav");
				} else if (count == 1) {
					bgm.playNar("card/card02.wav");
				} else if (count == 2) {
					bgm.playNar("card/card03.wav");
				}
			} else {
				bgm.clipNarr.stop();
			}
		}
		
		if (e.getSource() == prev || e.getSource() == next) {
			if (count == 0) {
				prev.setVisible(false);
				next.setVisible(true);
				pan1.setVisible(true);
				pan2.setVisible(false);
				pan3.setVisible(false);
				revalidate();
				repaint();
			} else if (count == 1) {
				prev.setVisible(true);
				next.setVisible(true);
				pan1.setVisible(false);
				pan2.setVisible(true);
				pan3.setVisible(false);
				revalidate();
				repaint();
			} else if (count == 2) {
				prev.setVisible(true);
				next.setVisible(false);
				pan1.setVisible(false);
				pan2.setVisible(false);
				pan3.setVisible(true);
				revalidate();
				repaint();
			}
		}
	}
}
