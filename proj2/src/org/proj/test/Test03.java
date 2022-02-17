package org.proj.test;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test03 {
	public static void audio() {
		try {
			File file = new File("sound/card01.wav");
//			File file = new File("D:\\beep.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			//clip.loop(3);
			clip.start();
		} catch (Exception e) {
			System.err.println("Put the music.wav file in the sound folder!");
		}
	}

	private static String arg;

	public static void main(String[] args) {
		audio();
		arg = "background.gif";
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JLabel l = new JLabel();
		ImageIcon icon = new ImageIcon(arg);
		f.setSize(480, 360);
		f.setVisible(true);
		l.setIcon(icon);
		p.add(l);
		f.getContentPane().add(p);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
