package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.model.GameDataDto;

public class GameRecordView extends GameView {
	ImageIcon backgroundImg;
	JLabel background;
	JButton backBtn = new JButton(new ImageIcon("images/icon_record2.png"));
	GraphPanel graph;
	JPanel graphPane;
	JPanel textPane;
	
	private List<Double> score;
	private List<String> date;
	@Override
	public void display() {
		backgroundImg = new ImageIcon("images/cloud.png");
		background = new JLabel(backgroundImg);
		
		this.setLayout(null);
		this.add(background);
		displaySetting();
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		backBtn.addActionListener(this);
	}

	public void displaySetting() {
		JLabel title = new JLabel("성적확인");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		graphsetting();
		graphPane = new JPanel();
		graphPane.setLayout(null);
		graphPane.setBackground(new Color(204,236,255,150));
		
		textPane = new JPanel();
		textPane.setLayout(null);
		textPane.setBackground(new Color(255,255,255,150));
		
		JLabel text = new JLabel();
		text.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		graphPane.add(graph);
		graph.setBounds(15, 15, 700, 400);
//		graphPane.add(fx);
//		fx.setBounds(15, 15, 700, 400);
		
		text.setHorizontalAlignment(JLabel.CENTER);
		textPane.add(text);
		text.setBounds(15,15,700,150);
		
		background.add(title);
		background.add(backBtn);
		background.add(graphPane);
		background.add(textPane);
		
		backBtn.setBorderPainted(false);    
		backBtn.setContentAreaFilled(false);
		
		title.setBounds(FRAME_WIDTH / 2 - 100, 20, 200, 40);
		backBtn.setBounds(10, FRAME_HEIGHT - 150, 100, 100);
		graphPane.setBounds(20,80, 730,430);
		textPane.setBounds(200,530, 730,180);
		text.setText(String.format("현재 %d대 평균보다 %d번 미달 났습니다!", (mainUser.getAge()/10)*10,underAvgNum));
	}

	public void graphsetting() {
		getGraphData(gameRecord);
		if(graph!= null) {
			graph.removeAll();			
		}
		int age = (mainUser.getAge()/10)*10;
		graph = new GraphPanel(score, date, age);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			gameRecord = null;
			Controller c = Controller.getController();
			c.Viewchange(RECORD);
		}
	}
	
	public void getGraphData(String game) {
		score  = new ArrayList<>();
		date  = new ArrayList<>();
		for(GameDataDto data : mainData) {
			switch(game) {
			case PlusMinus:
				if(data.getTotalGame1()!=0) {
					score.add(((double)data.getAnsGame1()/data.getTotalGame1())*100);
				}else {
					score.add(0.0);
				}
				break;
			case CARD:
				if(data.getTotalGame2()!=0) {
					score.add(((double)data.getAnsGame2()/data.getTotalGame2())*100);
				}else {
					score.add(0.0);
				}
				break;
			case CUP:
				if(data.getTotalGame3()!=0) {
					score.add(((double)data.getAnsGame3()/data.getTotalGame3())*100);
				}else {
					score.add(0.0);
				}
				break;
			case LIFE:
				if(data.getTotalGame4()!=0) {
					score.add(((double)data.getAnsGame4()/data.getTotalGame4())*100);
				}else {
					score.add(0.0);
				}
				break;
			case COLOR:
				if(data.getTotalGame5()!=0) {
					score.add(((double)data.getAnsGame5()/data.getTotalGame5())*100);
				}else {
					score.add(0.0);
				}
				break;
			}
			date.add(data.getDay());
		}
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("DrawGraph");
//	    Container contentPane;
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		contentPane = frame.getContentPane();
//		contentPane.setLayout(null);
//	    GameRecordView pm = new GameRecordView();
//	    pm.display();
//	    
//	    pm.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
//	    contentPane.add(pm);
//	    frame.setVisible(true);
//	}
}
