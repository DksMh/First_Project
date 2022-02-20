package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.proj.RoundJButton;
import org.proj.controller.Controller;

public class GameEndPane extends GameView {
	JButton goMainBtn = new RoundJButton("메인화면으로");
	JButton replayBtn = new RoundJButton("다시시작");
	JPanel result;
	public GameEndPane() {
		replayBtn.addActionListener(this);
		goMainBtn.addActionListener(this);
	}
	
	@Override
	public void display() {
		this.setLayout(null);
		this.setBounds(FRAME_WIDTH / 2 - 320 / 2, FRAME_HEIGHT / 2 - 370 / 2, 320, 370);
		
		result = new JPanel();
		result.setBackground(new Color(255, 255, 255, 255));
		result.setLayout(null);
		
		JLabel gameNumlbl = new JLabel("총 도전 횟수 : " + gameNum);
		JLabel gametruelbl = new JLabel("정답 : " + gametrue);
		JLabel gameEndmsg = new JLabel("게임결과");

		gameEndmsg.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		gameNumlbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		gametruelbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		result.add(gameEndmsg);
		result.add(gameNumlbl);
		result.add(gametruelbl);
		result.add(goMainBtn);

		goMainBtn.setBackground(new Color(82, 206, 105));
		goMainBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		goMainBtn.setForeground(Color.white);
		goMainBtn.setBorderPainted(false);
		goMainBtn.setContentAreaFilled(false);

		if(GameState == MiniGame) {
		result.add(replayBtn);
		replayBtn.setBackground(new Color(82, 206, 105));
		replayBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		replayBtn.setForeground(Color.white);
		replayBtn.setBorderPainted(false);
		replayBtn.setContentAreaFilled(false);
		replayBtn.setBounds(20, 290, 120, 50);
		
		goMainBtn.setBounds(160, 290, 120, 50);
		}
		else {
			goMainBtn.setBounds(20, 290, 260, 50);
		}

		gameEndmsg.setBounds(75, 55, 200, 40);
		gameNumlbl.setBounds(85, 140, 200, 30);
		gametruelbl.setBounds(120, 200, 100, 30);
		
		result.setBounds(10,10, 300, 350);
		
		this.add(result);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == goMainBtn) {
			recordGameData(NowView);
			resultPane.setVisible(false);
			JOptionPane.showMessageDialog(NowView, new JLabel("게임을 종료합니다!", javax.swing.SwingConstants.CENTER),"게임 종료",JOptionPane.PLAIN_MESSAGE);
			resultPane.removeAll();
			Controller c = Controller.getController();
			c.Viewchange(MainPage);
		}
		
		if(e.getSource() == replayBtn) {
			recordGameData(NowView);
			resultPane.setVisible(false);
			JOptionPane.showMessageDialog(NowView, new JLabel("다시 시작합니다!", javax.swing.SwingConstants.CENTER),"Replay",JOptionPane.PLAIN_MESSAGE);
			resultPane.removeAll();
			NowView.removeAll();
			Controller c = Controller.getController();
			c.Viewchange(NowView.toString());
		}
	}
	
	public void recordGameData(GameView view) {
		String state = view.toString();
		System.out.println(view.toString());
		
		if(GameState == MiniGame) {
			
		switch(state) {
		case PlusMinus:
			mainGameData.setTotalGame1(mainGameData.getTotalGame1()+gameNum);
			mainGameData.setAnsGame1(mainGameData.getAnsGame1()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		case CARD:
			mainGameData.setTotalGame2(mainGameData.getTotalGame2()+gameNum);
			mainGameData.setAnsGame2(mainGameData.getAnsGame2()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		case CUP:
			mainGameData.setTotalGame3(mainGameData.getTotalGame3()+gameNum);
			mainGameData.setAnsGame3(mainGameData.getAnsGame3()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		case LIFE:
			mainGameData.setTotalGame4(mainGameData.getTotalGame4()+gameNum);
			mainGameData.setAnsGame4(mainGameData.getAnsGame4()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		case SelectColor:
			mainGameData.setTotalGame5(mainGameData.getTotalGame5()+gameNum);
			mainGameData.setAnsGame5(mainGameData.getAnsGame5()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		case MaxColor:
			mainGameData.setTotalGame5(mainGameData.getTotalGame5()+gameNum);
			mainGameData.setAnsGame5(mainGameData.getAnsGame5()+gametrue);
			gameNum = 0;
			gametrue = 0;
			break;
		}
	}else {
		gameNum = 0;
		gametrue = 0;
	}
	}
	@Override
	public String toBGM() {
		return null;
	}
}
