package Life_game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class LifeGame {
	HashMap<String, String[]> quiz = new HashMap<String, String[]>();
	Set<String> s = quiz.keySet();
	Iterator<String> it = s.iterator();
	Vector<String> v = new Vector<String>();
//	String[] arr = arr[3];
	
	public LifeGame() {
		run();
	}
	
	public void run() {
		while(it.hasNext()) {
			v.add(it.next());
		}
	}
	
	public static void main(String[] args) {
		
	}

}
