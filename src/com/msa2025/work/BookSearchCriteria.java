package work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookSearchCriteria {
	
	// 검색 조건에 맞는 책을 찾는 메서드
    public static boolean isMatch(Book book, String searchKey, String value) {
        switch (searchKey) {
            case "isbn":
                return isIsbnMatch(book, value);
            case "title":
                return isTitleMatch(book, value);
            case "author":
                return isAuthorMatch(book, value);
            case "publicationDate":
                return isPublicationDateMatch(book, value);
            default:
                return false;
        }
    }

    // ISBN 검색 (3자 이상 입력 시 부분 일치)
    public static boolean isIsbnMatch(Book book, String value) {
        if (value.length() >= 3) {
            return book.getIsbn().contains(value);
        }
        return false;
    }

    // 도서 제목 검색 (3자 이상 입력 시 부분 일치)
    public static boolean isTitleMatch(Book book, String value) {
        if (value.length() >= 3) {
            return book.getTitle().contains(value);
        }
        return false;
    }

    // 저자 검색 (2자 이상 입력 시 부분 일치)
    public static boolean isAuthorMatch(Book book, String value) {
        if (value.length() >= 2) {
            return book.getAuthor().contains(value);
        }
        return false;
    }

    // 출판일 범위 검색
    public static boolean isPublicationDateMatch(Book book, String value) {
        String[] dateRange = value.split("~");
        if (dateRange.length == 2) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date startDate = sdf.parse(dateRange[0].trim());
                Date endDate = sdf.parse(dateRange[1].trim());
                Date bookDate = sdf.parse(book.getPublicationDate());

                // 출판일이 범위 안에 있는지 확인
                return !bookDate.before(startDate) && !bookDate.after(endDate);
            } catch (ParseException e) {
                System.out.println("날짜 범위 형식이 잘못되었습니다.");
                return false;
            }
        }
        return false;
    }
}