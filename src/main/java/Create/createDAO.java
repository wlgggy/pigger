package Create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class createDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	// 생성자
	public createDAO() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// connection (데이터 베이스 연결)
	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, "hr", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	// insert(생성)
	public void CreateInsert(createDTO createdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into notice values ((select NVL(max(notice_no), 0) + 1 from notice), ?, ?, ?)";

		conn = getConnection();

		try {
			// ?에 대한 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, createdto.getTitle());
			pstmt.setString(2, createdto.getName());
			pstmt.setString(3, createdto.getContent());

			// update
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// select
	public ArrayList<createDTO> getAllDatas() {
		ArrayList<createDTO> createlist = new ArrayList<createDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from notice order by notice_no asc";

		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 여러 개의 데이터를 받는 경우 (while문 사용)
			while (rs.next()) {
				createDTO createdto = new createDTO();

				createdto.setNotice_no(rs.getString("notice_no"));
				createdto.setTitle(rs.getString("title"));
				createdto.setName(rs.getString("name"));
				createdto.setContent(rs.getString("content"));

				createlist.add(createdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return createlist;
	}

	public static void main(String[] args) {
		createDAO createdao = new createDAO();

	}
}

// Connection : DB 연결
// PreparedStatement : 파라미터화 된 Sql를 실행
// ResultSet : sql 실행 후 반환된 데이터를 처리하는 객체