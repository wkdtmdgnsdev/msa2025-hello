package ch20.oracle.sec05.app2.service;

import java.util.List;

import ch20.oracle.sec05.app2.dao.BoardDAO;
import ch20.oracle.sec05.app2.dto.Board;

public class BoardService {
	private BoardDAO dao = new BoardDAO();
	
	public List<Board> list() {
		return dao.list();
	}
	
	public Board read(int bno) {
		return dao.getBoard(bno);
	}
		
	public Board insert(Board board) {
		return dao.insert(board);
	}
	
	public void update(int bno, String title, String content) {
		dao.update(Board.builder().bno(bno).btitle(title).bcontent(content).build());
	}
	
	public void delete(int bno) {
		dao.delete(bno);
	}
	
	public void close() {
		dao.close();
	}
}
