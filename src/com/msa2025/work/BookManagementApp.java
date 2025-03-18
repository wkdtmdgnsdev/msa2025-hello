package work;

import java.util.Scanner;

public class BookManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    printAllBooks();
                    break;
                case 5:
                    exitProgram();
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. 도서 추가");
        System.out.println("2. 도서 삭제");
        System.out.println("3. 도서 검색");
        System.out.println("4. 도서 목록");
        System.out.println("5. 종료");
        System.out.print("선택: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        return choice;
    }

    private static void addBook() {
        System.out.print("ISBN (10자리): ");
        String isbn = scanner.nextLine();
        System.out.print("도서 제목: ");
        String title = scanner.nextLine();
        System.out.print("도서 가격: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        System.out.print("저자: ");
        String author = scanner.nextLine();
        System.out.print("출판일 (yyyy-MM-dd): ");
        String publicationDate = scanner.nextLine();
        bookManager.addBook(isbn, title, price, author, publicationDate);
    }

    private static void deleteBook() {
    	String isbn = searchBooks();
    	System.out.print("해당 책을 삭제 하시겠습니까? [Y/N]");
    	if(scanner.nextLine().equals("Y")) {
    		bookManager.deleteBook(isbn);
    		System.out.println("삭제 되었습니다.");
    	}
    }

    private static String searchBooks() {
        System.out.print("검색할 도서의 조건 (isbn/title/author/publicationDate): ");
        String searchCriterion = scanner.nextLine();
        System.out.print("검색 값: ");
        String searchValue = scanner.nextLine();
        return bookManager.searchBooks(searchCriterion, searchValue);
    }

    private static void printAllBooks() {
        bookManager.printAllBooks();
    }

    private static void exitProgram() {
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }
}
