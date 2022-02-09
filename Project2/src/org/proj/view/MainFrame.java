package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Container;
import java.awt.Font;
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


public class MainFrame extends JFrame{
	
	Container contentPane;

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		contentPane = getContentPane();
		contentPane.setLayout(null);

		displayView(LoginView);

	}
	
	public void displayView(GameView gc) {
		gc.display();
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		NowView = gc;
		contentPane.add(gc);
		this.setVisible(true);
	}
	
	
	public void changeView(GameView gc) {
		contentPane.removeAll();
		gc.removeAll();
		contentPane.add(gc);
		gc.display();
		NowView = gc;
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		revalidate();
		repaint();
	}

}
