package org.proj.server;

import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.IDCHECK;
import static org.proj.Resource.LOGIN;
import static org.proj.Resource.LOGOUT;
import static org.proj.Resource.NEWLOGIN;
import static org.proj.Resource.SIGNUP;
import static org.proj.Resource.UserUPDATE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.proj.model.GameDataDto;
import org.proj.model.UserDao;
import org.proj.model.UserDto;

public class GameServer extends JFrame {

	public static UserDao dao = new UserDao();

	public static GameDataDto gameDto;
	public static Vector<GameDataDto> vector;
	private JTextArea serverState;
	JPanel rightPane;
	JPanel[] pArr;
	JLabel[] idArr;
	JLabel[] loginStateArr;
	private static Vector<UserDto> loginUser = new Vector<>();

	public GameServer() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("서버");
		setSize(500, 768);

		rightPane = new JPanel();
		rightPane.setBackground(Color.white);
		rightPane.setLayout(new GridLayout(20, 1, 13, 13));
		pArr = new JPanel[10];
		idArr = new JLabel[10];
		loginStateArr = new JLabel[10];

		for (int i = 0; i < dao.userVector.size(); i++) {
			pArr[i] = new JPanel(new GridLayout(1, 2));
			pArr[i].setBackground(Color.white);
			idArr[i] = new JLabel(dao.userVector.get(i).getId(), javax.swing.SwingConstants.CENTER);
			idArr[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			loginStateArr[i] = new JLabel("비접속");
			loginStateArr[i].setFont(new Font("맑은 고딕", Font.BOLD, 13));
			pArr[i].add(idArr[i]);
			pArr[i].add(loginStateArr[i]);

			rightPane.add(pArr[i]);
		}
		rightPane.setPreferredSize(new Dimension(140, 900));
		Container contentPane = getContentPane();
		serverState = new JTextArea();
		serverState.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		serverState.setEditable(false);
		contentPane.add(new JScrollPane(serverState), BorderLayout.CENTER);
		contentPane.add(new JScrollPane(rightPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);
		setVisible(true);
//		this.setLocation(500, 200);
		position();
		// 서버 쓰레드 동작
		ConnectClient connect = new ConnectClient();
		connect.start();
	}
	
	public void position() {
		Toolkit tk = this.getToolkit().getDefaultToolkit();
		int scrWidth = (int)tk.getScreenSize().getWidth();
		int scrHeight = (int)tk.getScreenSize().getHeight();
		
		int x = scrWidth/2 + 350;
		int y = scrHeight/2 - 768/2;
		this.setLocation(x,y);
	}
	
	public void loginUpdate(String user) {
		for (int i = 0; i < dao.userVector.size(); i++) {
			if (user.equals(idArr[i].getText())) {
				loginStateArr[i].setText("접속중");
				pArr[i].setBackground(Color.green);
			}
		}
		revalidate();
		repaint();

	}

	public void logoutUpdate(String user) {
		for (int i = 0; i < dao.userVector.size(); i++) {
			if (user == null) {
				return;
			}
			if (user.equals(idArr[i].getText())) {
				loginStateArr[i].setText("비접속");
				pArr[i].setBackground(Color.white);
			}
		}
		revalidate();
		repaint();
	}

	public void addUserList(UserDto user) {
		int size = dao.userVector.size();
		pArr[size - 1] = new JPanel(new GridLayout(1, 2));
		pArr[size - 1].setBackground(Color.white);
		idArr[size - 1] = new JLabel(dao.userVector.get(size-1).getId(),javax.swing.SwingConstants.CENTER);
		idArr[size - 1].setFont(new Font("맑은 고딕", Font.BOLD, 13));
		loginStateArr[size - 1] = new JLabel("비접속");
		loginStateArr[size - 1].setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pArr[size - 1].add(idArr[size - 1]);
		pArr[size - 1].add(loginStateArr[size - 1]);

		rightPane.add(pArr[size - 1]);

		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		new GameServer();
	}

	class ConnectClient extends Thread {
		private ServerSocket listener = null;
		private Socket socket = null;

		@Override
		public void run() {
			try {
				// 서버는 한번만 생성
				listener = new ServerSocket(9999);
				serverState.append("접속대기중...\n");
				while (true) {
					// 접속은 계속 유지해야되므로 wile문 안에서 무한반복
					socket = listener.accept();
					serverState.append("클라이언트 접속 완료...\n");

					// 클라이언트가 서버에 접속 시 해당 클라이언트와 메세지를 주고 받을 쓰레드 생성
					ServerThread sth = new ServerThread(socket);
					sth.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ServerThread extends Thread {
		Socket socket = null;
		private boolean stop;
		private String userID;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private String guest = "client" + (int) (Math.random() * 100 + 1);
		private UserDto dto;

		public ServerThread(Socket socket) {
			this.stop = false;
			this.socket = socket;
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {

			}
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					String req = ois.readUTF();
					serverState.append(guest + " >> " + req + "\n");
					switch (req) {
					case LOGIN:
						login();
						break;
					case SIGNUP:
						userSignUp();
						break;
					case IDCHECK:
						idCheck();
						break;
					case UserUPDATE:
						userUpdate();
						break;
					case LOGOUT:
						logout();
						break;
					}

				} catch (SocketException e) {
					stop = true;
				} catch (IOException e) {
					stop = true;
				}

			}
			try {
				serverState.append(guest + " >> disconnect" + "\n");
				loginUser.remove(dto);
				logoutUpdate(userID);
				if (ois != null)
					ois.close();
				if (oos != null)
					oos.close();

			} catch (IOException e1) {
			}

		}

		private void userUpdate() {
			String resp = UserUPDATE;
			try {
				dto = (UserDto) ois.readObject();
				boolean approval = dao.updateUser(dto);

				if (approval) {

					oos.writeUTF(resp);
					oos.flush();
					oos.writeUTF("complete");
					oos.flush();
					serverState.append(userID + " >> Update Complete " + "\n");
				} else {
					oos.writeUTF(resp);
					oos.flush();
					oos.writeUTF("fail");
					oos.flush();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private boolean loginCheck(UserDto dto) {
			if (loginUser.contains(dto)) {
				return false;
			}
			return true;
		}

		private void login() {
			String resp = LOGIN;
			try {
				dto = (UserDto) ois.readObject();
				boolean approval = dao.loginApproval(dto);
				if (approval) {
					boolean duplication = loginCheck(dto);
					if (duplication) {
						dto = dao.selectOneUser(dto);
						vector = dao.roadOneGameData(dto);
						int n = 0;
						String day = LocalDate.now().toString();
						for (GameDataDto data : vector) {
							if (day.equals(data.getDay())) {
								n++;
							}
						}
						if (n == 0) {
							GameDataDto data = new GameDataDto(dto.getId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, day);
							dao.insertGameData(data);
							vector.add(data);
						}
						if (dto != null) {
							if (vector.size() != 0) {
								oos.writeUTF(resp);
								oos.flush();
								oos.writeObject(dto);
								oos.flush();
								oos.writeObject(vector);
								oos.flush();
								loginUser.add(dto);
								userID = dto.getId();
								serverState.append(userID + " >> Login Success! \n");
								loginUpdate(userID);
							}
						}
					} else {
						dto = new UserDto(0, null, null, null, 0);
						oos.writeUTF(resp);
						oos.flush();
						oos.writeObject(dto);
						oos.flush();
						serverState.append(guest + " >> Login False! (Login Duplication) \n");
					}
				} else {
					dto = new UserDto(-1, null, null, null, 0);
					oos.writeUTF(resp);
					oos.flush();
					oos.writeObject(dto);
					oos.flush();
					serverState.append(guest + " >> Login Fail \n");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private void userSignUp() {
			String resp = SIGNUP;
			try {
				dto = (UserDto) ois.readObject();
				boolean state = dao.insertUser(dto);
				String date = LocalDate.now().toString();

				boolean state2 = dao.insertGameData(new GameDataDto(dto.getId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, date));
				if (state && state2) {
					oos.writeUTF(resp);
					oos.flush();
					oos.writeUTF("complete");
					oos.flush();
					serverState.append(guest + " >> Sign Up Complete " + "\n");
					addUserList(dto);
				} else {
					oos.writeUTF(resp);
					oos.flush();
					oos.writeUTF("fail");
					oos.flush();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void idCheck() {
			String resp = IDCHECK;
			try {
				// userID를 넘겨 받아서 userID에 저장
				String userID = ois.readUTF();
				// swing 출력
				serverState.append(guest + " idckek>> " + userID + "\n");
				// dao에 그 값을 넣고 id 중복여부 확인하고 결과를 check에 받음 true : id사용가능, false : id 중복
				boolean check = dao.checkID(userID);
				if (check) {
					serverState.append(guest + " >> true\n");
					// idcheck 요청의 결과임을 알려주는 메세지
					oos.writeUTF(resp);
					oos.flush();
					// idcheck 요청의 결과값
					oos.writeUTF("approval");
					oos.flush();
					serverState.append(guest + " >> ID Check Complete" + "\n");
				} else {
					serverState.append(guest + " >> false" + "\n");
					// idcheck 요청의 결과임을 알려주는 메세지
					oos.writeUTF(resp);
					oos.flush();
					// idcheck 요청의 결과값
					oos.writeUTF("fail");
					oos.flush();
					serverState.append(guest + " >> ID Check Complete" + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void logout() {
			try {
				gameDto = (GameDataDto) ois.readObject();
				dao.updateGameData(gameDto);
				loginUser.remove(dto);
				logoutUpdate(userID);
				serverState.append(userID + " >> Logout" + "\n");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
