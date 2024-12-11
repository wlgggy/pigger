package Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	// 생성자
	public UpdateDAO() {
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

	// update (업데이트)
	public void Update(UpdateDTO updatedto) {
		System.out.println("notice_no before : " + updatedto.getNotice_no());

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update notice set title = ?, name = ?, content = ? where notice_no = ?";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedto.getTitle());
			pstmt.setString(2, updatedto.getName());
			pstmt.setString(3, updatedto.getContent());
			pstmt.setString(4, updatedto.getNotice_no());

			System.out.println("notice_no af : " + updatedto.getNotice_no());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// select
	public UpdateDTO getDataByNoticeNo(String notice_no) {
		UpdateDTO updatedto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from notice where notice_no = ?";

		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice_no);

			rs = pstmt.executeQuery();
			
			// 1개의 데이터를 받을 경우(if문 사용)
			if (rs.next()) {
				updatedto = new UpdateDTO();
				updatedto.setNotice_no(rs.getString("notice_no"));
				updatedto.setTitle(rs.getString("title"));
				updatedto.setName(rs.getString("name"));
				updatedto.setContent(rs.getString("content"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return updatedto;
	}

	public static void main(String[] args) {
		UpdateDAO dao = new UpdateDAO();

	}
}

//Connection : DB 연결
//PreparedStatement : 파라미터화 된 Sql를 실행
//ResultSet : sql 실행 후 반환된 데이터를 처리하는 객체