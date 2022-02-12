package org.proj.controller;

import static org.proj.Resource.LoginPage;
import static org.proj.Resource.LoginView;
import static org.proj.Resource.MainPage;
import static org.proj.Resource.MainView;
import static org.proj.Resource.PlusMinus;
import static org.proj.Resource.PlusMinusGAME;
import static org.proj.Resource.RECORD;
import static org.proj.Resource.RecordView;
import static org.proj.Resource.*;

import java.util.HashMap;

import org.proj.model.GameDataDto;
import org.proj.model.UserDao;
import org.proj.model.UserDto;
import org.proj.view.GameView;
import org.proj.view.MainFrame;
import org.proj.view.MainView;
import org.proj.view.RecordView;

public class Controller {
	private static Controller controller;
	MainFrame mainframe;
	private static HashMap<String, GameView> map = new HashMap<>();
	public ClientSocket clientsocket;
	UserDao dao;
	static {
		
		map.put(LoginPage, LoginView);
		map.put(MainPage, MainView);
		map.put(RECORD, RecordView);
		map.put(PlusMinus, PlusMinusGAME);
		map.put(CARD, CardGame);
		map.put(CUP, CupGame);
		map.put(LIFE, LifeGame);
		map.put(SelectColor, SelectColorGame);
		map.put(MaxColor, MaxColorGame);
		map.put(GameRecordPage, GameRecord);
		
	}
	public Controller() {
		mainframe = new MainFrame();
		clientsocket = new ClientSocket();
		dao = new UserDao();
	}
	
	public static Controller getController() {
		return controller;
	}
	
	public boolean idcheck(String userID) {
		return dao.checkID(userID);
	}
	
	public boolean signup(UserDto dto) {
		return clientsocket.reqSignUp(dto);
	}
	
	public void login(UserDto dto) {
		clientsocket.reqLogin(dto);
	}
	public void update(UserDto dto) {
		clientsocket.reqUpdate(dto);
		
	}
	public void logout(GameDataDto gamedto) {
		clientsocket.reqLogout(gamedto);
	}
	public static void main(String[] args) {
		controller = new Controller();
	}

	public void Viewchange(String viewName) {
			mainframe.changeView(map.get(viewName));
		}
	}
	

