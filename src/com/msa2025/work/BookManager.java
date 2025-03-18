package work;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookManager {
    private Map<String, Book> bookMap;
    private BookValidator bookValidator;

    public BookManager() {
        bookMap = new HashMap<>();
        bookValidator = new BookValidator();
    }

    // 도서 추가
    public boolean addBook(String isbn, String title, int price, String author, String publicationDate) {
        try {
            // 유효성 검사
        	bookValidator.validator(isbn, price, publicationDate, bookMap);

            Book book = new Book(isbn, title, price, author, publicationDate);
            bookMap.put(isbn, book);
            System.out.println("도서가 추가되었습니다.");
            return true;
        } catch (BookException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // 도서 삭제
    public boolean deleteBook(String isbn) {
    	bookMap.remove(isbn);
    	return true;
    }

    // 도서 검색
    public String searchBooks(String searchKey, String value) {
        boolean found = false;
        for (Book book : bookMap.values()) {
            if (BookSearchCriteria.isMatch(book, searchKey, value)) {
                System.out.println(book);
                found = true;
                return book.getIsbn();
            }
        }
        if (!found) {
            System.out.println("해당하는 도서를 찾을 수 없습니다.");
        }
        return null;
    }

    // 도서 목록 출력
    public void printAllBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
        } else {
            for (Book book : bookMap.values()) {
                System.out.println(book);
            }
        }
    }
}
