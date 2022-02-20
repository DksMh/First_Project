package org.proj;

import java.awt.Container;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class BGM{
	Clip clip;
	public Clip clipNarr;
	public void Play(String fileName, boolean loop) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm/"+fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if(loop) clip.loop(-1);
		} catch (Exception ex) {
		}
	}
	
	public void stop() {
		if(clip != null) {
			clip.stop();
		}
	}
	
	public void changeBGM(String fileName, boolean loop) {
		if(clip != null) {
			clip.stop();
		}
		Play(fileName,loop);
	}
	
	public void playEffect(String effectName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/effect/"+effectName));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception ex) {
		}
	}
	
	public void playNar(String narName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/"+narName));
			clipNarr = AudioSystem.getClip();
			clipNarr.open(ais);
			clipNarr.start();
		} catch (Exception ex) {
		}
	}
}
