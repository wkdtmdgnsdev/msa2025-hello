package work;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
    private int price;
    private String author;
    private String publicationDate;

    @Override
    public String toString() {
        return "ISBN: " + getIsbn() + ", Title: " + getTitle() + ", Price: " + getPrice() + ", Author: " + getAuthor() + ", Publication Date: " + getPublicationDate();
    }
}