package org.proj.controller;

import static org.proj.Resource.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proj.model.GameDataDto;
import org.proj.model.UserDto;
import org.proj.view.MainView;

public class ClientSocket {
	private Socket socket = null;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public String req;
	public String resp;
	public String msg;

	public ClientSocket() {
		connectServer();

		ClientThread th = new ClientThread();
		th.start();
	}

	public void connectServer() {
		try {
			// localHost ip, 9999 port에 접속
			socket = new Socket(InetAddress.getLocalHost(), 9999);

			// 서버에 접속하고 입출력 스트림 생성
			// 반드시 oos 부터 생성해줘야한다.
			// ois먼저 생성하면 에러발생
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Request
	// 로그인
	public boolean reqLogin(UserDto dto) {
		req = LOGIN;

		if (dto.getId().length() != 0 && dto.getPassword().length() != 0) {
			try {
				oos.writeUTF(req);
				oos.flush();
				oos.writeObject(dto);
				oos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			return false;
		}

		return true;
	}

	// 회원가입
	public boolean reqSignUp(UserDto dto) {

		if (dto == null) {
			return false;
		}

		req = SIGNUP;
		try {
			oos.writeUTF(req);
			oos.flush();
			oos.writeObject(dto);
			oos.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return true;
	}

	// 수정
	public boolean reqUpdate(UserDto dto) {
		if (dto == null) {
			return false;
		}

		req = UserUPDATE;
		try {
			oos.writeUTF(req);
			oos.flush();
			oos.writeObject(dto);
			oos.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	// 로그아웃
	// 게임데이터를 데이터베이스에 저장
	public void reqLogout(GameDataDto dto) {
		if (dto == null) {
			return;
		}
		req = LOGOUT;
		try {
			oos.writeUTF(req);
			oos.flush();
			oos.writeObject(dto);
			oos.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	class ClientThread extends Thread {
		private boolean stop;

		public ClientThread() {
			stop = false;
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					// 서버로부터 메세지가 오면 클라이언트의 어떤 요청에 대한 결과인지 먼저 판단하기위해
					// resp에 저장하고 switch로 어떤 요청인지 판단.
					resp = ois.readUTF();
					System.out.println("server response >> " + resp);
					switch (resp) {
					case LOGIN:
						login();
						break;
//					case NEWLOGIN:
//						newlogin();
//						break;
					case SIGNUP:
						signup();
						break;
					case IDCHECK:
						idCheck();
						break;
					case UserUPDATE:
						userUpdate();
						break;
					}
				} catch (SocketException e) {
					stop = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				System.out.println("서버 연결 해제");
				if (ois != null)
					ois.close();
				if (oos != null)
					oos.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		private void userUpdate() {
			try {
				String result = ois.readUTF();
				if ("complete".equals(result)) {
					// 정보수정 완료
					// 프로필 새로 셋팅
					JOptionPane.showMessageDialog(NowView, new JLabel("수정 완료!", javax.swing.SwingConstants.CENTER),
							"프로필 수정", JOptionPane.PLAIN_MESSAGE);
				} else {
					// 실패
					JOptionPane.showMessageDialog(NowView, new JLabel("수정 실패!", javax.swing.SwingConstants.CENTER),
							"프로필 수정", JOptionPane.PLAIN_MESSAGE);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		private void newlogin() {
//			try {
//				UserDto user = (UserDto) ois.readObject();
//
//				if (user.getNo() != -1) {
//					mainUser = user;
//					mainData = new Vector<GameDataDto>();
//					System.out.println(mainUser);
//					System.out.println("vector size >> " + mainData.size());
//
//					// 정상로그인
//					JOptionPane.showMessageDialog(NowView, "로그인 성공!");
//					Controller c = Controller.getController();
//					c.mainframe.changeView(new MainView());
//				} else {
//					// 로그인 실패
//					mainUser = null;
//					JOptionPane.showMessageDialog(NowView, "아이디와 비밀번호를 확인해 주세요!");
//				}
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		private void login() {
			try {
				UserDto user = (UserDto) ois.readObject();
				if ((user.getNo() != -1) && (user.getNo() != 0)) {
					Vector<GameDataDto> vector = (Vector) ois.readObject();
					mainUser = user;
					mainData = vector;

					System.out.println(mainUser);
					String day = LocalDate.now().toString();
					for (GameDataDto data : vector) {
						System.out.println(data);
						if (day.equals(data.getDay())) {
							mainGameData = data;
							System.out.println("데이터 있음");
						}
					}

					if (mainGameData == null) {
						mainGameData = new GameDataDto(user.getId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, day);
					}
					System.out.println("===============");
					System.out.println(mainGameData);
					// 정상로그인
					JOptionPane.showMessageDialog(NowView, new JLabel("로그인 성공!", javax.swing.SwingConstants.CENTER),
							"로그인", JOptionPane.PLAIN_MESSAGE);
					Controller c = Controller.getController();
					c.mainframe.changeView(new MainView());
				} else if (user.getNo() == 0) {
					mainUser = null;
					JOptionPane.showMessageDialog(NowView,
							new JLabel("이미 접속된 아이디 입니다!", javax.swing.SwingConstants.CENTER), "로그인",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					mainUser = null;
					// 로그인 실패
					JOptionPane.showMessageDialog(NowView,
							new JLabel("아이디와 비밀번호를 확인해 주세요!", javax.swing.SwingConstants.CENTER), "로그인",
							JOptionPane.PLAIN_MESSAGE);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void signup() {
			try {
				String result = ois.readUTF();
				if ("complete".equals(result)) {
					// 회원가입 성공
					JOptionPane.showMessageDialog(NowView, new JLabel("회원가입 성공!", javax.swing.SwingConstants.CENTER),
							"회원가입", JOptionPane.PLAIN_MESSAGE);
				} else {
					// 실패
					JOptionPane.showMessageDialog(NowView, new JLabel("회원가입 실패!", javax.swing.SwingConstants.CENTER),
							"회원가입", JOptionPane.PLAIN_MESSAGE);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void idCheck() {
			try {
				String tf = ois.readUTF();
				if ("approval".equals(tf)) {
					// 사용할 수 있는 ID

				} else {
					// 사용할 수 없는 ID

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}