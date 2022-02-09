package org.proj.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.proj.Resource.*;
import javax.swing.JPanel;

public abstract class GameView extends JPanel implements ActionListener{
	public abstract void display();

	public abstract void actionPerformed(ActionEvent e);
	
	public void countNum() {
		if(MiniGame.equals(GameState)) {
			
		}else if(TotGame.equals(GameState)) {
			
		}
	}
}
