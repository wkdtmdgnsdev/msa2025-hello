package ch20.oracle.sec05.app2;

import java.util.Scanner;

import ch20.oracle.sec05.app2.dto.Board;
import ch20.oracle.sec05.app2.service.BoardService;

/*
 * Model : B/L  -> DB 연동 -> Service 로직  
 * View : 출력(JSP : vue, react, js) 
 * Controller : 제어  => Model <- Controller -> View
 * 
 * 
 * Controller : ConnectionExample9
 * Model : Service
 * DB 관련 로직 : 저장소(Repository, DAO : Data Access Object )
 * 
 * DTO : Data Transfer Object : DB Table -> Java Object 
 * VO  : Value Object  
 */

public class ConnectionExample9 {
	static Scanner scanner = new Scanner(System.in);
	static BoardService boardService = new BoardService();
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("1. 글 목록");
			System.out.println("2. 글 등록");
			System.out.println("3. 글 보기");
			System.out.println("4. 글 수정");
			System.out.println("5. 글 삭제");
			System.out.println("6. 종료");
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
				read(); //보기
				break;
			case "4":
				update(); //수정
				break;
			case "5":
				delete();
				break;
			case "6":
				boardService.close();
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			default:
				System.out.println("입력이 잘못되었습니다");
				break;
			}
		}
	}

	private static void update() {
		System.out.print("글번호 : ");
		int bno = Integer.valueOf(scanner.nextLine());
		System.out.print("글제목 : ");
		String title = scanner.nextLine();
		System.out.print("글제목 : ");
		String content = scanner.nextLine();
		
		boardService.update(bno, title, content);
	}

	private static void read() {
		System.out.print("글번호 : ");
		int bno = Integer.valueOf(scanner.nextLine());
		
		Board board = boardService.read(bno);
		System.out.println(board.getBno() + " : " + board.getBtitle() + " : " + board.getBwriter() +" : " +board.getBcontent() +" : " +board.getBdate());
	}

	private static void delete() {
		System.out.print("계정번호 : ");
		int bno = Integer.parseInt(scanner.nextLine());
		
		boardService.delete(bno);
	}

	private static void insert() {
		System.out.print("제목 : ");
		String title = scanner.nextLine();
		
		System.out.print("내용 : ");
		String content = scanner.nextLine();
		
		System.out.print("작성자 : ");
		String writer = scanner.nextLine();

		//객체생성 
		Board board = Board.builder()
				.btitle(title)
				.bcontent(content)
				.bwriter(writer)
				.build();

		//등록
		boardService.insert(board);
	}

	private static void list() {
		for(var board : boardService.list()) {
			System.out.println(board.getBno() + " : " + board.getBtitle() + " : " + board.getBwriter() +" : " +board.getBdate());
		}
	}
}		