package org.proj;

import java.util.Vector;

import javax.swing.JLabel;

import org.proj.game.card.CardGame;
import org.proj.game.color.MaxColorPanel;
import org.proj.game.color.SelectColorPanel;
import org.proj.game.cup.CupGamePanel;
import org.proj.game.life.LifeGamePanel;
import org.proj.game.plusminus.PlusMinus;
import org.proj.model.GameDataDto;
import org.proj.model.UserDto;
import org.proj.view.GameEndPane;
import org.proj.view.GameRecordView;
import org.proj.view.GameView;
import org.proj.view.LoginView;
import org.proj.view.MainView;
import org.proj.view.RecordView;

public class Resource {
	public static final int FRAME_WIDTH = 1024, FRAME_HEIGHT = 768;
	public static final String LOGIN = "login";
	public static final String NEWLOGIN = "newlogin";
	public static final String SIGNUP = "signup";
	public static final String IDCHECK = "idcheck";
	public static final String UserUPDATE = "update";
	public static final String LOGOUT = "logout";
	
	public static final String LoginPage = "login";
	public static final String MainPage = "main";
	public static final String PlusMinus = "plusminus";
	public static final String CARD = "card";
	public static final String CUP = "cup";
	public static final String LIFE = "life";
	public static final String COLOR = "color";
	public static final String SelectColor = "selectcolor";
	public static final String MaxColor = "maxcolor";
	public static final String RECORD = "record";
	public static final String GameRecordPage = "gamerecord";
	
	public static final String PlisMinusRECORD = "plusminusRecord";
	public static final String CardRECORD = "cardRecord";
	public static final String LifeRECORD = "lifeRecord";
	public static final String CupRECORD = "cupRecord";
	public static final String ColorRECORD = "colorRecord";

	
	public static BGM bgm = new BGM();
	// sound
	public static final String MainBGM = "sound/bgm/main.wav";
	public static final String RecordBGM = "sound/bgm/record.wav";
	public static final String PlusminusBGM = "sound/bgm/plusminus.wav";
	public static final String CardBGM = "sound/bgm/card.wav";
	public static final String CupBGM = "sound/bgm/cup.wav";
	public static final String LifeBGM = "sound/bgm/life.wav";
	public static final String ColorBGM = "sound/bgm/color.wav";
	public static final String AllGameBGM = "sound/bgm/AllGame.wav";

	public static final String ButtonEffect = "sound/effect/button.wav";
	public static final String TrueEffect = "sound/effect/true.wav";
	public static final String FalseEffect = "sound/effect/false.wav";
	public static final String CountDownEffect = "sound/effect/countdown.wav";
	public static final String SetLabelEffect = "sound/effect/setlabel.wav";
	
	
	public static String gameRecord;

	public static boolean loginSucess =false;
	public static boolean updateBtnStatedb = false;
	public static int gameNum =0;
	public static int gametrue=0;
	public static int endGameNum = 5;
	public static int cardendNum = 2;
	
	// 게임이 개별게임인지 종합게임인지 판단 초기값 미니게임
	public static final String MiniGame = "minigame";
	public static final String AllGame = "alllgame";
	public static String GameState = MiniGame;
	
	public static int underAvgNum;
	
	public static UserDto mainUser;
	public static GameDataDto mainGameData;
	public static Vector<GameDataDto> mainData;
	public static GameView resultPane = new GameEndPane();
	
//	// View
	public static GameView NowView;
	public static GameView LoginView = new LoginView();
	public static GameView MainView = new MainView();	
	public static GameView RecordView = new RecordView();
	public static GameView PlusMinusGAME = new PlusMinus();
	public static GameView LifeGame = new LifeGamePanel();
	public static GameView CupGame = new CupGamePanel();
	public static GameView CardGame = new CardGame();
	public static GameView SelectColorGame = new SelectColorPanel();
	public static GameView MaxColorGame = new MaxColorPanel();
	public static GameView GameRecord = new GameRecordView();
}




