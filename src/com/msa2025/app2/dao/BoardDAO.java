package ch20.oracle.sec05.app2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ch20.oracle.sec05.app2.dto.Board;

public class BoardDAO {
	Connection conn = null;
	
	public BoardDAO() {
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"scott",
					"1004"
					);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close
	public void close() {
		if(conn != null) {
			try {
				//연결 끊기
				conn.close();
			} catch (SQLException e) {}
		}
	}	
	
	//목록
	public List<Board> list() {
		List<Board> boards = new ArrayList<>();
		
		try {
			String query = "SELECT * FROM BOARDS";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				boards.add(Board.builder()
						.bno(rs.getInt("bno"))
						.btitle(rs.getString("btitle"))
						.bcontent(rs.getString("bcontent"))
						.bwriter(rs.getString("bwriter"))
						.bdate(rs.getDate("bdate"))
						.build()
						);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			
		}
		
		return boards;
	}	

	//상세보기
	public Board getBoard(int bno) {
		Board result = null;
		try {
			String sql = "SELECT * FROM boards WHERE bno=?";
			PreparedStatement selectStmt = conn.prepareStatement(sql);
			selectStmt.setInt(1, bno);
			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				result = Board.builder()
						.bno(rs.getInt("bno"))
						.btitle(rs.getString("btitle"))
						.bcontent(rs.getString("bcontent"))
						.bwriter(rs.getString("bwriter"))
						.bdate(rs.getDate("bdate"))
						.build();
			}
			selectStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//등록
	public Board insert(Board board) {
		try {
			//SQL 구문 객체 생성한다
			String sql = "INSERT INTO boards (bno, btitle, bcontent, bwriter, BDate) VALUES (SEQ_BNO.nextval, ?, ?, ?, sysdate)";
			PreparedStatement insertStmt = conn.prepareStatement(sql);
			
			//인자값 설정 
			insertStmt.setString(1, board.getBtitle());
			insertStmt.setString(2, board.getBcontent());
			insertStmt.setString(3, board.getBwriter());

			//SQL 구문 실행 
			insertStmt.executeUpdate();

			insertStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}	
	//수정
	public void update(Board board) {
		try {
			//SQL 구문 객체 생성한다
			String sql = "UPDATE BOARDS SET BTITLE = ?, BCONTENT = ? WHERE BNO = ?";
			PreparedStatement updateStmt = conn.prepareStatement(sql);
			
			//인자값 설정
			updateStmt.setString(1, board.getBtitle());
			updateStmt.setString(2, board.getBcontent());
			updateStmt.setInt(3, board.getBno());
			
			updateStmt.executeUpdate();

			updateStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	//삭제
	public void delete(int bno) {
		try {
			//SQL 구문 객체 생성한다
			String sql = "delete from boards WHERE bno=?";
			PreparedStatement deleteStmt = conn.prepareStatement(sql);
			
			//인자값 설정
			deleteStmt.setInt(1, bno);
			
			deleteStmt.executeUpdate();

			deleteStmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
}
