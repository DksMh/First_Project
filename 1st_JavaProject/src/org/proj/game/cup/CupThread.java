package org.proj.game.cup;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CupThread extends Thread {
	int r;
	JLabel manualJLabel;
	Cup[] cup = new Cup[3];
	
	public CupThread(Cup[] cBtn, int r, JLabel manualJLabel) {
		this.cup = cBtn;
		this.r = r;
		this.manualJLabel = manualJLabel;
	}

	@Override
	public void run() {

		int i = 0;
		int cup1x = cup[0].x;
		int cup2x = cup[1].x;
		int cup3x = cup[2].x;

		int y = 300;

		int roop = 0;
		boolean stop = false;
		cup[0].now = cup[0].road[0];
		cup[0].next = cup[0].road[1];
		cup[1].now = cup[1].road[0];
		cup[1].next = cup[1].road[1];
		cup[2].now = cup[2].road[0];
		cup[2].next = cup[2].road[1];

		int d1 = cup[0].next - cup[0].now;
		int d2 = cup[1].next - cup[1].now;
		int d3 = cup[2].next - cup[2].now;

		while (!stop) {

			if (i < 181) {
				
				if (d1 != 0) {
					cup[0].x = (cup1x + d1 * r) - (int) (d1 * r * Math.cos(Math.toRadians(i)));
					cup[0].y = (y - (int) (d1 * r * 0.8 * Math.sin(Math.toRadians(i))));
				} else {
					cup[0].y = (y - (int) (r * Math.sin(i * Math.PI / 180)));
				}
				if (d2 != 0) {
					cup[1].x = (cup2x + d2 * r) - (int) (d2 * r * Math.cos(Math.toRadians(i)));
					cup[1].y = (y - (int) (d2 * r  * 0.8 * Math.sin(Math.toRadians(i))));
				} else {
					cup[1].y = (y - (int) (r * Math.sin(i * Math.PI / 180)));
				}
				if (d3 != 0) {
					cup[2].x = (cup3x + d3 * r) - (int) (d3 * r * Math.cos(Math.toRadians(i)));
					cup[2].y = (y - (int) (d3 * r  * 0.8 * Math.sin(Math.toRadians(i))));
				} else {
					cup[2].y = (y - (int) (r * Math.sin(Math.toRadians(i))));
				}
				
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cup[0].setBounds(cup[0].x, cup[0].y, cup[0].w, cup[0].h);
				cup[1].setBounds(cup[1].x, cup[1].y, cup[1].w, cup[1].h);
				cup[2].setBounds(cup[2].x, cup[2].y, cup[2].w, cup[2].h);
			}

			if (roop == 8) {
				manualJLabel.setVisible(true);
				for(int j=0; j<3; j++) {
					cup[j].setEnabled(true);
				}
				stop = true;
				break;
			}
			if (i < 181) {
				i++;
			} else if (i == 181) {
				roop++;
				
				cup1x = cup[0].x;
				cup2x = cup[1].x;
				cup3x = cup[2].x;
				
				cup[0].now = cup[0].road[roop];
				cup[0].next = cup[0].road[roop + 1];

				cup[1].now = cup[1].road[roop];
				cup[1].next = cup[1].road[roop + 1];

				cup[2].now = cup[2].road[roop];
				cup[2].next = cup[2].road[roop + 1];

				d1 = cup[0].next - cup[0].now;
				d2 = cup[1].next - cup[1].now;
				d3 = cup[2].next - cup[2].now;
				
				i = 0;
			}
		} // end of while
	} // end of run
	
}
