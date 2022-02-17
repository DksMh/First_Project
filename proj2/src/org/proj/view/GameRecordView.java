package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.model.GameDataDto;

public class GameRecordView extends GameView {
	ImageIcon backgroundImg;
	JLabel background;
	JButton backBtn = new JButton(new ImageIcon("images/recordData/record.png"));
	GraphPanel graph;
	JPanel graphPane;
	JPanel textPane;
	JLabel noticelbl;
	
	// 0,~ : 잘했을때 1,~ : 중간 2,~ 판단력심각, 3,~ : 기억력심각, 4,~:가사와취미 심각
	private static final String[][] textArr = {{"<html>짝짝짝! 열심히 훈련에 임하셨군요. <br>다음 훈련도 좋은 결과가 있도록 지금처럼만 힘내세요.<html>", 
							"<html>정말 놀라워요. <br>앞으로 얼마나 더 좋아질 수 있는지 기대가 됩니다.<html>",
							"<html>아주 훌륭해요. <br>지금 처럼 열심히 하시면 좋은 성적을 유지하실거에요!<html>"},
						  {"<html>지금까지 아주 훌륭해요. <br>오늘은 간단한 산책은 어떤가요?<html>",
							"<html>대표적인 녹황색 채소 시금치! <br>시금치 된장국에 두부조림에 식사를 드시는게 어떠실까요?<html>",
							"<html>비타민과 뇌의 활동을 활발하게 해주는 조개와 게살! <br>오늘은 바다가 생각나는 식사는 어떠신가요?<html>",
							"<html>뇌세포를 파괴하는 플라크 형성을 억제하는 홍차! <br>홍차를 드시는거 어떠신가요?<html>",
							"<html>머리를 맑게 해주는칼륨이 많이들어간 미역! <br>오늘은 따뜻한 미역국에 식사를 드시는게 어떠실까요?<html>",
							"<html>뇌에 좋은 '커큐민'이 들어간 카레! <br>오늘의 식사 메뉴로 어떠신가요?<html>"},
						  {"<html>사소한일에 화가나거나 짜증이 많아지지 않으셨나요? <br>취미활동이나 여가생활을 가지고 즐기시는건 어떠신가요?<html>",
							"<html>머리가 복잡하신가요? <br>집 근처 공원을 산책하거나 <br>가벼운 운동을 시작해보는것이 어떠실까요?<html>"},
						  {"<html>최근에 물건이 자주 사라지지 않나요? <br>오늘의 식사는 등푸른 생선인 고등어, 꽁치, 장어, 참치는 어떠신가요?<html>",
							"<html>불끄는 것을 잊어버리는 경우가 많아지셨나요? <br>호두가 들어간 견과류는 어떠신가요?<html>"},
						  {"<html>건강한 몸! 건강한 정신! 간단한 운동이나 요가를 해보는게 어떠실까요?<html>",
							"<html>오늘은 자신을 돌아보는 시간을 가지는 하루를 보내는건 어떠실까요?<html>"}
						  };
	int underAvg;
	private List<Double> score;
	private List<String> date;
	private static HashMap<Integer, Integer> avgMap = new HashMap<>();

	public GameRecordView() {
		avgMap.put(50, 60);
		avgMap.put(60, 60);
		avgMap.put(70, 35);
		avgMap.put(80, 35);
		avgMap.put(90, 35);
	}

	@Override
	public void display() {
		backgroundImg = new ImageIcon("images/recordData/recordBackground.png");
		background = new JLabel(backgroundImg);

		this.setLayout(null);
		this.add(background);
		displaySetting();
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		backBtn.addActionListener(this);
	}

	public void displaySetting() {
//		JLabel title = new JLabel("성적확인");
//		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		graphPane = new JPanel();
		graphPane.setLayout(null);
		graphPane.setBackground(new Color(204, 236, 255, 150));

		textPane = new JPanel();
		textPane.setLayout(null);
		textPane.setBackground(new Color(255, 255, 255, 150));

		if (mainData.size() > 2) {
			graphsetting();
			graphPane.add(graph);
			graph.setBounds(15, 15, 700, 400);
			JLabel text = new JLabel();
//			JTextPane text = new JTextPane();
//			text.setEditable(false);
			text.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			text.setHorizontalAlignment(JLabel.CENTER);
			textPane.add(text);
			settingTextMsg(gameRecord, text, underAvg);
			
//			StyledDocument doc = text.getStyledDocument();
//			SimpleAttributeSet center = new SimpleAttributeSet();
//			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
//			doc.setParagraphAttributes(0, doc.getLength(), center, false);
//			text.setText(String.format("%d대 하한치보다 %d번 미달 났습니다!", (mainUser.getAge() / 10) * 10, underAvg));
			text.setBounds(15, 15, 700, 150);
		} else {
			notGraphsetting();

		}

//		background.add(title);
		background.add(backBtn);
		background.add(graphPane);
		background.add(textPane);

		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);

//		title.setBounds(FRAME_WIDTH / 2 - 100, 20, 200, 40);
		backBtn.setBounds(10, FRAME_HEIGHT - 150, 100, 100);
		graphPane.setBounds(20, 60, 730, 430);
		textPane.setBounds(200, 530, 730, 180);

	}
	public void settingTextMsg(String game, JLabel text, int underAvg) {
		// 0,~ : 잘했을때 1,~ : 중간 2,~ 판단력심각, 3,~ : 기억력심각, 4,~:가사와취미 심각
		int index1 = 0;
		int index2 = 0;
		
		if(underAvg>=0 && underAvg <= 3 ) {
			index1 = 0;
			index2 = ((int)(Math.random()*100000000))%3;
		}else if(underAvg>=4 && underAvg < 8){
			index1 = 1;
			index2 = ((int)(Math.random()*100000000))%6;
		}else if(underAvg >=8) {
			switch (game) {
			case PlusMinus:
				index1 = 2;
				index2 = ((int)(Math.random()*100000000))%2;
				break;
			case CARD:
				index1 = 3;
				index2 = ((int)(Math.random()*100000000))%2;
				break;
			case CUP:
				index1 = 3;
				index2 = ((int)(Math.random()*100000000))%2;
				break;
			case LIFE:
				index1 = 4;
				index2 = ((int)(Math.random()*100000000))%2;
				break;
			case COLOR:
				index1 = 2;
				index2 = ((int)(Math.random()*100000000))%2;
				break;
			}
		}
		
		text.setText(textArr[index1][index2]);
		
	}
	

	public void notGraphsetting() {
		JLabel noticelbl = new JLabel("<html>출력할 데이터가 부족합니다.<br>3일 이상 게임을 진행해 주세요!<html>",
				javax.swing.SwingConstants.CENTER);
		JLabel nodatalbl = new JLabel("No Data", javax.swing.SwingConstants.CENTER);
		noticelbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		nodatalbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		graphPane.add(nodatalbl);
		nodatalbl.setBounds(40, 150, 650, 130);
		textPane.add(noticelbl);
		noticelbl.setBounds(15, 15, 700, 150);
	}

	public void graphsetting() {
		int age = (mainUser.getAge() / 10) * 10;
		getGraphData(gameRecord, age);
		if (graph != null) {
			graph.removeAll();
		}
		graph = new GraphPanel(score, date, age, avgMap.get(age));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			gameRecord = null;
			Controller c = Controller.getController();
			c.Viewchange(RECORD);
		}
	}

	public void getGraphData(String game, int age) {
		underAvg = 0;
		score = new ArrayList<>();
		date = new ArrayList<>();
		int start = 0;
		start = (mainData.size() > 20) ? mainData.size() - 20 : 0;
		for (int i = start; i < mainData.size(); i++) {
			double avg = 0.0;
			GameDataDto data;
			data = mainData.get(i);
			switch (game) {
			case PlusMinus:
				if (data.getTotalGame1() != 0) {
					avg = ((double) data.getAnsGame1() / data.getTotalGame1()) * 100;
					if(avg < avgMap.get(age)) {
						underAvg++;
					}
					score.add(avg);
				} else {
					score.add(0.0);
				}
				break;
			case CARD:
				if (data.getTotalGame2() != 0) {
					avg = ((double) data.getAnsGame2() / data.getTotalGame2()) * 100;
					if(avg < avgMap.get(age)) {
						underAvg++;
					}
					score.add(avg);
				} else {
					score.add(0.0);
				}
				break;
			case CUP:
				if (data.getTotalGame3() != 0) {
					avg = ((double) data.getAnsGame3() / data.getTotalGame3()) * 100;
					if(avg < avgMap.get(age)) {
						underAvg++;
					}
					score.add(avg);
				} else {
					score.add(0.0);
				}
				break;
			case LIFE:
				if (data.getTotalGame4() != 0) {
					avg = ((double) data.getAnsGame4() / data.getTotalGame4()) * 100;
					if(avg < avgMap.get(age)) {
						underAvg++;
					}
					score.add(avg);
				} else {
					score.add(0.0);
				}
				break;
			case COLOR:
				if (data.getTotalGame5() != 0) {
					avg = ((double) data.getAnsGame5() / data.getTotalGame5()) * 100;
					if(avg < avgMap.get(age)) {
						underAvg++;
					}
					score.add(avg);
				} else {
					score.add(0.0);
				}
				break;
			}

			date.add(data.getDay().substring(5));
		}
	}

	public String toBGM() {
		return "main.wav";
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
