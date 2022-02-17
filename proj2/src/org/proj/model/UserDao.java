package org.proj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import org.proj.db.ConnectionDB;

public class UserDao {
	// user table의 모든 데이터 갖고오기
//	public static final String SELECT_ALL = "SELECT * FROM USER";
	public static final String SELECT_ALL = "select * from user";
	// user table에서 한명의 모든 정보를 가져옴
	public static final String SELECT_ONE = "SELECT * FROM USER WHERE ID = ?";
	// 새로운 user가 회원가입을 하면 새로운 유저값을 user table에 저장
	public static final String INSERT = "INSERT INTO USER (NAME, ID, PASSWORD, AGE) VALUES(?,?,?,?)";
	// user data를 수정할 경우 id는 변경할 수 없으므로 id로 검색하여 각각의 값을 수정
	public static final String UPDATE = "UPDATE USER SET NAME =?, PASSWORD =?, AGE =? WHERE ID = ?";
	// 회원탈퇴를 할 경우 table에서 데이터 삭제
	public static final String DELETE = "DELETE FROM USER WHERE ID = ?";

	// gamedata table에서 userID로 검색하여 해당 유저의 모든 게임 데이터를 가져옴.
	public static final String SELECT_DATA_ALL = "select ansgame1, totalgame1, ansgame2, totalgame2, ansgame3, totalgame3, ansgame4, totalgame4, ansgame5, totalgame5, day from gamedata where id = ?";
	public static final String SELECT_DATA_ONE = "select ansgame1, totalgame1, ansgame2, totalgame2, ansgame3, totalgame3, ansgame4, totalgame4, ansgame5, totalgame5, day from gamedata where id = ?";
	// game을 종료할 때 모든 당일의 게임 정답률 data를 gamedata table에 저장.
	public static final String INSERT_DATA = "INSERT INTO GAMEDATA (id, ansgame1, totalgame1, ansgame2, totalgame2, ansgame3, totalgame3, ansgame4, totalgame4, ansgame5, totalgame5, DAY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_DATA = "UPDATE gamedata set ansgame1 = ? , totalgame1 = ?, ansgame2 = ?, totalgame2 = ?, ansgame3=?, totalgame3=?, ansgame4=?, totalgame4=?, ansgame5=?, totalgame5=? where id = ? and day = ?";
	public static final String DELETE_DATA = "delete from gamedata where id =? ";
	
	public static Connection conn = null;
	public static ResultSet rs = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	// 모든 user의 정보를 저장할 Vector
	public static Vector<UserDto> userVector = new Vector<>();
	// key : userID, value : 날짜로 구분한 game별 정답률 저장할 HashMap
	public static HashMap<String, Vector<GameDataDto>> userData = null;
	
	public UserDao() {
		init();
	}
	
	// 초기화 (database에 저장된 userData와 gameData를 읽어옴.
	public void init() {
		roadUser();
		roadGameData();
	}
	
	// 모든 user 정보 가져오기
	public void roadUser() {
		userVector = new Vector<>();
		conn = ConnectionDB.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				UserDto user = null;
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String id = rs.getString(3);
				String password = rs.getString(4);
				int age = rs.getInt(5);

				user = new UserDto(no, name, id, password, age);
				userVector.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//SELECT_ONE = "SELECT * FROM USER WHERE ID = ?";
	public UserDto selectOneUser (UserDto dto) {
		UserDto user = null;
		conn = ConnectionDB.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
				
			while (rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String id = rs.getString(3);
				String password = rs.getString(4);
				int age = rs.getInt(5);
				
				user = new UserDto(no,name, id, password, age);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	
	// 새로운 user 회원가입 시 user 정보 추가
	public boolean insertUser(UserDto dto) {
		conn = ConnectionDB.getConnection();

		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			pstmt.setInt(4, dto.getAge());
			int cnt = pstmt.executeUpdate();

			if (cnt == 0) {
				conn.rollback();
				return false;
			} else {
				roadUser();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// user 정보 변경
	// UPDATE = "UPDATE USER SET NAME =?, PASSWORD =?, AGE =? WHERE ID = ?";
	public boolean updateUser(UserDto dto) {
		conn = ConnectionDB.getConnection();

		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setInt(3, dto.getAge());
			pstmt.setString(4, dto.getId());
			int cnt = pstmt.executeUpdate();

			if (cnt == 0) {
				conn.rollback();
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	// user 정보 삭제
		// DELETE = "DELETE FROM USER WHERE ID = ?";
		public boolean deleteUser(UserDto dto) {
			conn = ConnectionDB.getConnection();

			try {
				pstmt = conn.prepareStatement(DELETE);
				pstmt.setString(1, dto.getId());
				int cnt = pstmt.executeUpdate();

				if (cnt == 0) {
					conn.rollback();
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						ConnectionDB.close(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
		// 로그인 승인
		// 아이디 비밀번호 일치하는 객체가 있으면 true 반환
		
		public boolean loginApproval(UserDto dto) {
			init();
			if(userVector.contains(dto)) {
				return true;
			}
			return false;
		}
		
		// ID 중복체크
		public boolean checkID(String userID) {
			init();
			for(int i= 0; i<userVector.size(); i++) {
				if(userID.equals(userVector.get(i).getId())){
					return false;
				}
			}
			return true;
		}
		
	// 모든 user의 gameData 읽어옴.
	public void roadGameData() {
		userData = new HashMap<>();
		conn = ConnectionDB.getConnection();

		for (int i = 0; i < userVector.size(); i++) {
			Vector<GameDataDto> vector = new Vector<>();
			try {
				pstmt = conn.prepareStatement(SELECT_DATA_ALL);
				pstmt.setString(1, userVector.get(i).getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					GameDataDto data = null;
					int agame1 = rs.getInt(1);
					int tgame1 = rs.getInt(2);
					int agame2 = rs.getInt(3);
					int tgame2 = rs.getInt(4);
					int agame3 = rs.getInt(5);
					int tgame3 = rs.getInt(6);
					int agame4 = rs.getInt(7);
					int tgame4 = rs.getInt(8);
					int agame5 = rs.getInt(9);
					int tgame5 = rs.getInt(10);

					String day = rs.getString(11);
					// GameDataDto 객체 만들고
					data = new GameDataDto(userVector.get(i).getId(), agame1, tgame1, agame2, tgame2, agame3, tgame3,
							agame4, tgame4, agame5, tgame5, day);
					// 벡터에 저장
					vector.add(data);
				}
				// userVector의 목록에서 userID를 key로하여 game별 data가 담긴 Vector를 HashMap userData에 저장.
				userData.put(userVector.get(i).getId(), vector);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Vector<GameDataDto> roadOneGameData(UserDto dto) {
		conn = ConnectionDB.getConnection();

			Vector<GameDataDto> vector = new Vector<>();
			try {
				pstmt = conn.prepareStatement(SELECT_DATA_ONE);
				pstmt.setString(1, dto.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					GameDataDto data = null;
					int agame1 = rs.getInt(1);
					int tgame1 = rs.getInt(2);
					int agame2 = rs.getInt(3);
					int tgame2 = rs.getInt(4);
					int agame3 = rs.getInt(5);
					int tgame3 = rs.getInt(6);
					int agame4 = rs.getInt(7);
					int tgame4 = rs.getInt(8);
					int agame5 = rs.getInt(9);
					int tgame5 = rs.getInt(10);

					String day = rs.getString(11);
					// GameDataDto 객체 만들고
					data = new GameDataDto(dto.getId(), agame1, tgame1, agame2, tgame2, agame3, tgame3,
							agame4, tgame4, agame5, tgame5, day);
					// 벡터에 저장
					vector.add(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vector;
		}
	
	// 게임 종료 시 게임data 입력
	public boolean insertGameData(GameDataDto dto) {
		conn = ConnectionDB.getConnection();
		try {
			pstmt = conn.prepareStatement(INSERT_DATA);

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getAnsGame1());
			pstmt.setInt(3, dto.getTotalGame1());
			pstmt.setInt(4, dto.getAnsGame2());
			pstmt.setInt(5, dto.getTotalGame2());
			pstmt.setInt(6, dto.getAnsGame3());
			pstmt.setInt(7, dto.getTotalGame3());
			pstmt.setInt(8, dto.getAnsGame4());
			pstmt.setInt(9, dto.getTotalGame4());
			pstmt.setInt(10, dto.getAnsGame5());
			pstmt.setInt(11, dto.getTotalGame5());
			pstmt.setString(12, dto.getDay());

			int cnt = pstmt.executeUpdate();
			if (cnt == 0) {
				conn.rollback();
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// String UPDATE_DATA = "UPDATE gamedata set
	// ansgame1 = ? , totalgame1 = ?,
	// ansgame2 = ?, totalgame2 = ?,
	// ansgame3=?, totalgame3=?,
	// ansgame4=?, totalgame4=?,
	// ansgame5=?, totalgame5=?)
	// where id = ? and day = ?)"
	
	public boolean updateGameData(GameDataDto dto) {
		conn = ConnectionDB.getConnection();
		try {
			pstmt = conn.prepareStatement(UPDATE_DATA);

			pstmt.setInt(1, dto.getAnsGame1());
			pstmt.setInt(2, dto.getTotalGame1());
			pstmt.setInt(3, dto.getAnsGame2());
			pstmt.setInt(4, dto.getTotalGame2());
			pstmt.setInt(5, dto.getAnsGame3());
			pstmt.setInt(6, dto.getTotalGame3());
			pstmt.setInt(7, dto.getAnsGame4());
			pstmt.setInt(8, dto.getTotalGame4());
			pstmt.setInt(9, dto.getAnsGame5());
			pstmt.setInt(10, dto.getTotalGame5());
			pstmt.setString(11, dto.getId());
			pstmt.setString(12, dto.getDay());

			int cnt = pstmt.executeUpdate();
			if (cnt == 0) {
				conn.rollback();
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
	
	//DELETE_DATA = "delete from gamedata where id =? ";
	public boolean deleteGameData(GameDataDto dto) {
		conn = ConnectionDB.getConnection();
		try {
			pstmt = conn.prepareStatement(DELETE_DATA);

			pstmt.setString(1, dto.getId());

			int cnt = pstmt.executeUpdate();
			if (cnt == 0) {
				conn.rollback();
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					ConnectionDB.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
}
