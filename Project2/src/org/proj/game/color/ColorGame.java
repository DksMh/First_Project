package org.proj.game.color;

import java.awt.event.ActionEvent;
import static org.proj.Resource.*;
import org.proj.view.GameView;

public class ColorGame{
	GameView game;
	
	public ColorGame() {
		setColor();
	}
	
	public void setColor() {
		int n = (int)((Math.random()*10000)%2);
		if(n == 0) {
			game = MaxColorGame;
		}
		else {
			game = SelectColorGame;
		}
	}
	
	public GameView getColor() {
		
		return game;
	}
	
	public String getGameName() {
		return game.toString();
	}

}
