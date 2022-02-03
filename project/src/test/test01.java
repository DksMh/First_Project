package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class test01 extends JComponent implements MouseListener, MouseMotionListener {
	Rectangle box;
	boolean isDragged;
	int X, Y;
	
	// 사각형 영역
	public test01() {
		box = new Rectangle(0, 0, 100, 100);
		isDragged = false;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1024, 768);
		f.add(new test01());
		f.setVisible(true);
	}

	// 사각형 색
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(box.x, box.y, box.width, box.height); // 이동하기 위해 사각형 좌표와 마우스 좌표 필요!!
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isDragged) {
			box.x = e.getX() - X;
			box.y = e.getY() - Y;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	// 사각형 안에서 클릭시 움직이게
	@Override
	public void mousePressed(MouseEvent e) {
		if(box.contains(new Point(e.getX(), e.getY()))) {
			X = e.getX() - box.x;
			Y = e.getY() - box.y;
			isDragged = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isDragged = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
