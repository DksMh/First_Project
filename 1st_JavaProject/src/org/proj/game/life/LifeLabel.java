package org.proj.game.life;

import javax.swing.JLabel;

public class LifeLabel extends JLabel{
	int x;
	int y;
	int w;
	int h;
	
	int num;
	boolean flag;
	
	public LifeLabel(String str, int x, int y, int w, int h) {
		super(str);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		num = 0;
		flag = false;
	}
}
