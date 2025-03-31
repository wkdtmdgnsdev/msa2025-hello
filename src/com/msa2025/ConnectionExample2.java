package ch20.oracle.sec05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectionExample2 {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("1. 글목록출력");
			System.out.println("2. 글등록");
			System.out.println("3. 글수정");
			System.out.println("4. 글삭제");
			System.out.println("5. 종료");
			System.out.print("원하는 작업은 ?");
			String choice = scanner.nextLine();
			switch(choice) {
			case "1":
				list();
				break;
			case "2":
				insert();
				break;
			case "3":
				update();
				break;
			case "4":
				delete();
				break;
			case "5":
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			default:
				System.out.println("입력이 잘못되었습니다");
				break;
			}
		}
	}

	private static void delete() {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 ");

//			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"scott",
					"1004"
					);
			
			//SQL 구문 객체 생성한다 
			Statement stmt = conn.createStatement();

			//select 구문을 실행한다 
			System.out.print("삭제할 번호 : ");
			String bno = scanner.nextLine();
			String query = "DELETE FROM BOARDS WHERE BNO ='" +bno +"'";
			stmt.executeQuery(query);
			stmt.close();
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}		
	}

	private static void update() {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 ");

//			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"scott",
					"1004"
					);
			
			//SQL 구문 객체 생성한다 
			Statement stmt = conn.createStatement();

			//select 구문을 실행한다 
			System.out.print("변경할 번호 : ");
			String bno = scanner.nextLine();
			System.out.print("변경할 제목 : ");
			String subject = scanner.nextLine();
			System.out.print("변경할 내용 : ");
			String content = scanner.nextLine();
			System.out.print("변경할 작성자 : ");
			String writer = scanner.nextLine();
			String query = "UPDATE BOARDS SET BTITLE = '" +subject +"', BCONTENT = '" +content +"', BWRITER = '" +writer +"'\r\n"
					+ "WHERE BNO = '" +bno +"'";
			stmt.executeQuery(query);
			stmt.close();
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}		
	}

	private static void insert() {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 ");

//			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"scott",
					"1004"
					);
			
			//SQL 구문 객체 생성한다 
			Statement insertStmt = conn.createStatement();
			//insert 구문 실행
			
			System.out.print("제목  : ");
			String subject = scanner.nextLine();
			System.out.print("내용 : ");
			String content = scanner.nextLine();
			System.out.print("작성자 : ");
			String writer = scanner.nextLine();
			
			String query = "INSERT INTO boards (bno, btitle, bcontent, bwriter, BDate) \r\n"
					+ "VALUES (SEQ_BNO.nextval, '" +subject  +"', '" +content +"', '" +writer +"', sysdate)";
			
			insertStmt.execute(query);

			insertStmt.close();
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}		
	}

	private static void list() {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 ");

//			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"scott",
					"1004"
					);
			
			//SQL 구문 객체 생성한다 
			Statement stmt = conn.createStatement();

			//select 구문을 실행한다 
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOARDS");
			while(rs.next()) {
				System.out.println(rs.getString(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4) + " : " + rs.getString(5)
				+ " : " + rs.getString(6));
			}
			rs.close();
			stmt.close();
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}		
	}
}		