package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;

import data.UserInformationData;

public class DBManager {
	private static Connection conn = null;
	private static boolean coonInit = false; // 이 변수를 왜 쓰는지 고려하시오.

	// 연결 확인 하는 부분
	public static boolean init(String ip, String dbName, String id, String password) {
		boolean result = false;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbName + "?characterEncoding=UTF-8",
					id, password);
			result = true;
		} catch (ClassNotFoundException ex) {
			System.out.println("Couldn't load database driver: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} finally {
			coonInit = result; // 연결 성공하면 cooInit을 트루로 설정해주고 실패하면 여전히 false로 실행
								// 그 이유는?
		}
		return result;
	}

	// ID 중복 체크 부분
	public static boolean UserIDCheck(String id) {
		String query = "SELECT * FROM tbl_user_list WHERE ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		Object[] obj = new Object[] { id };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			rs = pstmt.executeQuery();

			if (!rs.next()) {
				result = true;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// 로그인 체크
	public static UserInformationData UserIDLogin(String id, String pw) {
		String query = "SELECT * FROM tbl_user_list WHERE ID = ? AND Password = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInformationData uid = null;

		Object[] obj = new Object[] { id, pw };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				uid = new UserInformationData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5));
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return uid;
	}

	// 비번 까뭇을때
	public static String UserForgetPW(String id, String name) {
		String query = "SELECT us_pw FROM tbl_user_list WHERE ID = ? AND Name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		Object[] obj = new Object[] { id, name };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	public static boolean InsertFollow(String pname, String content, String cost, String date) {
		String query = "INSERT INTO tbl_follow VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		boolean result = false;

		Object[] obj = new Object[] { pname, content, cost, date };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				result = true;
			}

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;

	}

	// 읽기
	public static String[][] ReadFollowInfo() {
		String query = "SELECT * FROM tbl_follow";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String result[][] = null;

		Object[] obj = new Object[] {};

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int i = 0;
			// int colCnt = rsmd.getColumnCount(); 속성 개수 알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[rowCnt][4];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				result[i][2] = rs.getString(3);
				result[i][3] = rs.getString(4);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// 회원가입된 유저 삽입
	public static boolean InsertUser(String id, String pw, String name, String phoneNum, int level) {
		String query = "INSERT INTO tbl_user_list VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		boolean result = false;

		Object[] obj = new Object[] { id, pw, name, phoneNum, level };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				result = true;
			}

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// self테이블에 삽입
	public static boolean InsertSelfInfo(String area, String position, String name, String content) {
		String query = "INSERT INTO tbl_self VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		boolean result = false;

		Object[] obj = new Object[] { area, position, name, content };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				result = true;
			}

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// 유저 등급 변경
	public static boolean UpdateUserGrade(String id, String rank) {
		String query = "UPDATE tbl_user_list SET Rank = ? WHERE id = ?";
		// 사용자의 ID에 대해 rank 변경하는 쿼리문
		PreparedStatement pstmt = null; // 질의문 수행을 위한 기본 객체
		boolean result = false;

		Object[] obj = new Object[] { rank, id }; // obj에 id, rank 넣음

		try {
			pstmt = conn.prepareStatement(query);
			// Connection의 prepareStatement 메소드를 이용하여 쿼리 실행 객체 생성
			for (int i = 0; i < obj.length; i++) {
				// obj에 rank, id 넣으므로써 obj.length=2
				pstmt.setObject(i + 1, obj[i]);
				// obj에서 받은 배열 값을 i+1에 set
			}

			int rowCount = pstmt.executeUpdate();
			// update는 입력된 row 개수를 리턴해줌
			if (rowCount > 0) {
				result = true; // 회원 정보 저장 테이블의 행이 1개 이상일 경우 true
			}

			pstmt.close(); // 연결된 상태를 해제하고 접속을 종료
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// user테이블 그냥 읽기
	public static String[][] ReadUserList() {
		String query = "SELECT id, name, phone, rank FROM tbl_user_list";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result[][] = null;
		ResultSetMetaData rsmd = null;

		Object[] obj = new Object[] {};

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int i = 0;
			int cnt = rsmd.getColumnCount();// 속성개수알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[rowCnt][4];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				result[i][2] = rs.getString(3);
				result[i][3] = rs.getString(4);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// my-page위해 user테이블 id로 읽기
	public static String[] ReadUserInfo(String str) {
		String query = "SELECT id,name,phone,rank FROM tbl_user_list where id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result[] = null;
		ResultSetMetaData rsmd = null;

		Object[] obj = new Object[] { str };

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int i = 0;
			int cnt = rsmd.getColumnCount();// 속성개수알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[4];
			rs.beforeFirst();
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// self테이블 읽기
	public static String[][] ReadSelfInfo() {
		String query = "SELECT area, position, name, content FROM tbl_self";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result[][] = null;
		ResultSetMetaData rsmd = null;

		Object[] obj = new Object[] {};

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int i = 0;
			int cnt = rsmd.getColumnCount();// 속성개수알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[rowCnt][4];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				result[i][2] = rs.getString(3);
				result[i][3] = rs.getString(4);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// self테이블 원하는 레코드만 읽기 //key를 부산, 대구 이런식으로 검색하게 하였음.
	public static String[][] ReadSelfInfo(String str) {
		String query = "SELECT area, position, name, content FROM tbl_self WHERE position = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result[][] = null;
		ResultSetMetaData rsmd = null;

		Object[] obj = new Object[] { str };

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int i = 0;
			int cnt = rsmd.getColumnCount();// 속성개수알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[rowCnt][4];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				result[i][2] = rs.getString(3);
				result[i][3] = rs.getString(4);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// Help 테이블 원하는 레코드만 읽기 //key를 부산, 대구 이런식으로 검색하게 하였음.
	public static String[][] ReadHelpInfo(String str) {
		String query = "SELECT priority,name1,name2,name3,name4 FROM tbl_help WHERE position = ? and priority = ?";
		Random random = new Random();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result[][] = null;
		ResultSetMetaData rsmd = null;

		Object[] obj = new Object[] { str };
		int i, rowCnt;
		try {

			while (true) {
				pstmt = conn.prepareStatement(query);
				for (int j = 0; j < obj.length; j++) {
					pstmt.setObject(j + 1, obj[j]);
				}

				pstmt.setObject(2, random.nextInt(10) + 1);
				rs = pstmt.executeQuery();

				rsmd = rs.getMetaData();
				i = 0;
				rs.last();
				rowCnt = rs.getRow(); // 레코드 개수 알기
				System.out.println("테스트" + rowCnt);
				if (rowCnt == 0) {
					continue;
				} else
					break;
			}
			result = new String[rowCnt][5];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				result[i][2] = rs.getString(3);
				result[i][3] = rs.getString(4);
				result[i][4] = rs.getString(5);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// Mypage테이블 읽기
	public static String[][] ReadMypageInfo() {
		String query = "SELECT * FROM tbl_mypage";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String result[][] = null;

		Object[] obj = new Object[] {};

		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int i = 0;
			// int colCnt = rsmd.getColumnCount(); 속성 개수 알기
			rs.last();
			int rowCnt = rs.getRow(); // 레코드 개수 알기
			result = new String[rowCnt][2];
			rs.beforeFirst();
			while (rs.next()) {
				result[i][0] = rs.getString(1);
				result[i][1] = rs.getString(2);
				i++;
			}

			pstmt.close();
			rs.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("sql말고 다른 오류 ㅅㅂ");
		}

		return result;
	}

	// Mypage테이블에 삽입
	public static boolean InsertMypageInfo(String title, String epilogue) {
		String query = "INSERT INTO tbl_mypage VALUES(?,?)";
		PreparedStatement pstmt = null;
		boolean result = false;

		Object[] obj = new Object[] { title, epilogue };

		try {
			pstmt = conn.prepareStatement(query);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				result = true;
			}

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// Mypage 테이블에서 정보 삭제
	public static boolean RmMypageInfo(String title) {
		String query = "DELETE FROM tbl_mypage WHERE title = ?";
		// name에 따라 삭제하는 쿼리문
		PreparedStatement pstmt = null; // 질의문 수행을 위한 기본 객체
		boolean result = false;

		Object[] obj = new Object[] { title };
		// obj에 name을 넣음
		try {
			pstmt = conn.prepareStatement(query);
			// Connection의 prepareStatement 메소드를 이용하여 쿼리 실행 객체 생성
			for (int i = 0; i < obj.length; i++) {
				// obj에 name을 넣으므로써 obj.length=1
				pstmt.setObject(i + 1, obj[i]);
				// obj에서 받은 배열 값을 i+1에 set
			}

			int rowCount = pstmt.executeUpdate();
			// update는 입력된 row 개수를 리턴해줌
			if (rowCount > 0) {
				result = true;
				// tbl_self 테이블의 행이 1개 이상일 경우 true
			}

			pstmt.close(); // 연결된 상태를 해제하고 접속을 종료
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// Follow 테이블에서 정보 삭제
	public static boolean RmFollowInfo(String pname) {
		String query = "DELETE FROM tbl_follow WHERE pname = ?";
		// name에 따라 삭제하는 쿼리문
		PreparedStatement pstmt = null; // 질의문 수행을 위한 기본 객체
		boolean result = false;

		Object[] obj = new Object[] { pname };
		// obj에 name을 넣음
		try {
			pstmt = conn.prepareStatement(query);
			// Connection의 prepareStatement 메소드를 이용하여 쿼리 실행 객체 생성
			for (int i = 0; i < obj.length; i++) {
				// obj에 name을 넣으므로써 obj.length=1
				pstmt.setObject(i + 1, obj[i]);
				// obj에서 받은 배열 값을 i+1에 set
			}

			int rowCount = pstmt.executeUpdate();
			// update는 입력된 row 개수를 리턴해줌
			if (rowCount > 0) {
				result = true;
				// tbl_self 테이블의 행이 1개 이상일 경우 true
			}

			pstmt.close(); // 연결된 상태를 해제하고 접속을 종료
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	// self 테이블에서 정보 삭제
	public static boolean RmSelfInfo(String name) {
		String query = "DELETE FROM tbl_self WHERE name = ?";
		// name에 따라 삭제하는 쿼리문
		PreparedStatement pstmt = null; // 질의문 수행을 위한 기본 객체
		boolean result = false;

		Object[] obj = new Object[] { name };
		// obj에 name을 넣음
		try {
			pstmt = conn.prepareStatement(query);
			// Connection의 prepareStatement 메소드를 이용하여 쿼리 실행 객체 생성
			for (int i = 0; i < obj.length; i++) {
				// obj에 name을 넣으므로써 obj.length=1
				pstmt.setObject(i + 1, obj[i]);
				// obj에서 받은 배열 값을 i+1에 set
			}

			int rowCount = pstmt.executeUpdate();
			// update는 입력된 row 개수를 리턴해줌
			if (rowCount > 0) {
				result = true;
				// tbl_self 테이블의 행이 1개 이상일 경우 true
			}

			pstmt.close(); // 연결된 상태를 해제하고 접속을 종료
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

	public static boolean RmMember(String id) {
		String query = "DELETE FROM tbl_user_list WHERE id = ?";
		// id에 따라 삭제하는 쿼리문
		PreparedStatement pstmt = null; // 질의문 수행을 위한 기본 객체
		boolean result = false;

		Object[] obj = new Object[] { id };
		// obj에 id을 넣음
		try {
			pstmt = conn.prepareStatement(query);
			// Connection의 prepareStatement 메소드를 이용하여 쿼리 실행 객체 생성
			for (int i = 0; i < obj.length; i++) {
				// obj에 id을 넣으므로써 obj.length=1
				pstmt.setObject(i + 1, obj[i]);
				// obj에서 받은 배열 값을 i+1에 set
			}

			int rowCount = pstmt.executeUpdate();
			// update는 입력된 row 개수를 리턴해줌
			if (rowCount > 0) {
				result = true;
				// tbl_user_list 테이블의 행이 1개 이상일 경우 true
			}
			pstmt.close(); // 연결된 상태를 해제하고 접속을 종료
		} catch (SQLException ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		}

		return result;
	}

}
