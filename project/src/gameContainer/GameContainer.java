package gameContainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class GameContainer extends JPanel implements ActionListener {

	public abstract void gamePlay();

	public abstract void actionPerformed(ActionEvent e);
}