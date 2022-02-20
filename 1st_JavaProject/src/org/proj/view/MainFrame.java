package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proj.BGM;


public class MainFrame extends JFrame{
	
	Container contentPane;
	String nowBGM;
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("치매 예방 게임");
	
		position();
		contentPane = getContentPane();
		contentPane.setLayout(null);
		
		displayView(LoginView);
		
		bgm.Play("main.wav", true);
	}
	public void position() {
		Toolkit tk = this.getToolkit().getDefaultToolkit();
		int scrWidth = (int)tk.getScreenSize().getWidth();
		int scrHeight = (int)tk.getScreenSize().getHeight();
		
		int x = scrWidth/2 - FRAME_WIDTH/2-200;
		int y = scrHeight/2 - FRAME_HEIGHT/2;
		this.setLocation(x,y);
	}
	
	public void displayView(GameView gc) {
		gc.display();
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		NowView = gc;
		nowBGM = gc.toBGM();
		contentPane.add(gc);
		this.setVisible(true);
	}
	
	
	public void changeView(GameView gc) {
		contentPane.removeAll();
		gc.removeAll();
		contentPane.add(gc);
		gc.display();
		NowView = gc;
		bgm(gc.toBGM());
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		revalidate();
		repaint();
	}
	
	public void bgm(String nextBGM) {
		if(!(nowBGM.equals(nextBGM))) {
			bgm.changeBGM(nextBGM, true);
			nowBGM = nextBGM;
		}
	}
}
