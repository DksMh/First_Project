package org.proj.view;

import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.*;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.proj.RoundJButton;
import org.proj.controller.Controller;


public class RecordView extends GameView{
	ImageIcon backgroundImg;
	JLabel background;
	
	JButton pulusminusRecordBtn = new JButton(new ImageIcon("images/record/plusminus.png"));
	JButton cardRecordBtn = new JButton(new ImageIcon("images/record/card.png"));
	JButton cupRecordBtn = new JButton(new ImageIcon("images/record/cup.png"));
	JButton lifeRecordBtn = new JButton(new ImageIcon("images/record/life.png"));
	JButton colorRecordBtn = new JButton(new ImageIcon("images/record/color.png"));
	JButton backBtn = new JButton(new ImageIcon("images/record/home.png"));
	JButton calBtn = new JButton(new ImageIcon("images/record/calendar_btn.png"));
	
	CalenderView cal = new CalenderView();
	public RecordView() {
		backBtn.addActionListener(this);
		pulusminusRecordBtn.addActionListener(this);
		cardRecordBtn.addActionListener(this);
		cupRecordBtn.addActionListener(this);
		lifeRecordBtn.addActionListener(this);
		colorRecordBtn.addActionListener(this);
		calBtn.addActionListener(this);
		cal.exit.addActionListener(this);
	}
	
	@Override
	public void display() {
		backgroundImg = new ImageIcon("images/record/mainBackground.png");
		background = new JLabel(backgroundImg);
		
		this.setLayout(null);
		this.add(background);
		
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		displaySetting();
		
	}
	
	
	public void displaySetting() {
		JLabel title = new JLabel("성적확인");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		
		cal.setVisible(false);
		
		background.add(cal);
		background.add(pulusminusRecordBtn);
		background.add(cardRecordBtn);
		background.add(cupRecordBtn);
		background.add(lifeRecordBtn);
		background.add(colorRecordBtn);
		background.add(backBtn);
		background.add(calBtn);
		background.add(title);
		title.setBounds(30, 20, 200,40);
		
		pulusminusRecordBtn.setBorderPainted(false);    
		pulusminusRecordBtn.setContentAreaFilled(false);
		cardRecordBtn.setBorderPainted(false);    
		cardRecordBtn.setContentAreaFilled(false);
		cupRecordBtn.setBorderPainted(false);    
		cupRecordBtn.setContentAreaFilled(false);
		lifeRecordBtn.setBorderPainted(false);    
		lifeRecordBtn.setContentAreaFilled(false);
		colorRecordBtn.setBorderPainted(false);    
		colorRecordBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);    
		backBtn.setContentAreaFilled(false);
		calBtn.setBorderPainted(false);    
		calBtn.setContentAreaFilled(false);
		
		calBtn.setBounds(FRAME_WIDTH-120, 10,100,100);
		pulusminusRecordBtn.setBounds(FRAME_WIDTH/2-150/2-380, FRAME_HEIGHT/2-160,150,256);
		cardRecordBtn.setBounds(FRAME_WIDTH/2-150/2-190, FRAME_HEIGHT/2-160,150,256);
		cupRecordBtn.setBounds(FRAME_WIDTH/2-150/2, FRAME_HEIGHT/2-160,150,256);
		lifeRecordBtn.setBounds(FRAME_WIDTH/2-150/2+190, FRAME_HEIGHT/2-160,150,256);
		colorRecordBtn.setBounds(FRAME_WIDTH/2-150/2+380, FRAME_HEIGHT/2-160,150,256);
		
		backBtn.setBounds(10, FRAME_HEIGHT-150,100,100);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			Controller c = Controller.getController();
			c.Viewchange(MainPage);
		}
		
		if(e.getSource() == pulusminusRecordBtn) {
			gameRecord = PlusMinus;
			Controller c = Controller.getController();
			c.Viewchange(GameRecordPage);
		}
		if(e.getSource() == cardRecordBtn) {
			gameRecord = CARD;
			Controller c = Controller.getController();
			c.Viewchange(GameRecordPage);
		}
		if(e.getSource() == cupRecordBtn) {
			gameRecord = CUP;
			Controller c = Controller.getController();
			c.Viewchange(GameRecordPage);
		}
		if(e.getSource() == lifeRecordBtn) {
			gameRecord = LIFE;
			Controller c = Controller.getController();
			c.Viewchange(GameRecordPage);
		}
		if(e.getSource() == colorRecordBtn) {
			gameRecord = COLOR;
			Controller c = Controller.getController();
			c.Viewchange(GameRecordPage);
		}
		if(e.getSource() == calBtn) {
			cal.display();
			
			backBtn.setEnabled(false);
			pulusminusRecordBtn.setEnabled(false);
			cardRecordBtn.setEnabled(false);
			cupRecordBtn.setEnabled(false);
			lifeRecordBtn.setEnabled(false);
			colorRecordBtn.setEnabled(false);
		}
		
		if (e.getSource() == cal.exit) {
			cal.setVisible(false);
			backBtn.setEnabled(true);
			pulusminusRecordBtn.setEnabled(true);
			cardRecordBtn.setEnabled(true);
			cupRecordBtn.setEnabled(true);
			lifeRecordBtn.setEnabled(true);
			colorRecordBtn.setEnabled(true);
		}
	}
	
	public String toBGM() {
		return "main.wav";
	}
}
