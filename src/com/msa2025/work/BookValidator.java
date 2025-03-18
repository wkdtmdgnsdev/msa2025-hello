package work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BookValidator {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public void validator(String isbn, int price, String publicationDate, Map<String, Book> bookMap) throws BookException {
    	validateIsbn(isbn);
    	validateUniqueIsbn(bookMap, isbn);
    	validatePrice(price);
    	validatePublicationDate(publicationDate);
    }
    
    public void validateIsbn(String isbn) throws BookException {
        if (isbn.length() != 10) {
            throw new BookException("ISBN은 10자리여야 합니다.");
        }
    }

    public void validateUniqueIsbn(Map<String, Book> bookMap, String isbn) throws BookException {
        if (bookMap.containsKey(isbn)) {
            throw new BookException("이미 존재하는 ISBN입니다.");
        }
    }

    public void validatePrice(int price) throws BookException {
        if (price < 5000 || price > 50000) {
            throw new BookException("도서 가격은 5000원 이상, 50000원 이하여야 합니다.");
        }
    }

    public void validatePublicationDate(String publicationDate) throws BookException {
        try {
            Date date = sdf.parse(publicationDate);
            Date currentDate = new Date();
            if (date.after(currentDate)) {
                throw new BookException("출판일은 현재일을 초과할 수 없습니다.");
            }
        } catch (ParseException e) {
            throw new BookException("날짜 형식이 잘못되었습니다. yyyy-MM-dd 형식으로 입력하세요.");
        }
    }
}