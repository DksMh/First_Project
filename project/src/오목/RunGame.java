package 오목;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RunGame extends JFrame {
	private Container c;
	MapSize size = new MapSize();
	GameMethod gm = new GameMethod();

	public RunGame(String title) {
		setTitle(title);
		createMenu();
		setBounds(200, 20, 650, 750);
		c = getContentPane();
		c.setLayout(null);
		ShowMap m = new ShowMap(size, gm);
		setContentPane(m);
		MouseAction Mc = new MouseAction(gm, size, m, this);
		addMouseListener(Mc);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) { // 메뉴 아이템의 종류 구분
			case "2인":
				gm.setGameMode(2);
				gm.init();
				break;
			case "3인":
				gm.setGameMode(3);
				gm.init();
				break;
			}
		}
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar(); // 메뉴바 생성
		JMenuItem[] menuItem = new JMenuItem[2];
		String[] itemTitle = { "2인", "3인" };
		JMenu screenMenu = new JMenu("게임모드선택");
		MenuActionListener listener = new MenuActionListener();
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
	}

}