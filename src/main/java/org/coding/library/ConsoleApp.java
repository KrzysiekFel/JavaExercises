package org.coding.library;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApp {
    boolean appRunning = true;
    MenuService menuService = new MenuService();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        menuService.showWelcomeMenu();
        LibraryService libraryService = LibraryService.getInstance();

        while (this.appRunning) {
            menuService.showMenuOptions();
            String chosenMenuOption = menuService.getChosenOption();
            switch (chosenMenuOption) {
                case "1":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Birthday (rrrr-mm-dd): ");
                    String birthdayInput = scanner.nextLine();
                    LocalDate birthday = LocalDate.parse(birthdayInput);
                    User user = new User(name, surname, birthday);
                    libraryService.addUser(user);
                    System.out.println("User created: " + user);
                    break;
                case "2":
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("How many pages: ");
                    Integer pages = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Publish date (rrrr-mm-dd): ");
                    String publish = scanner.nextLine();
                    LocalDate publishDate = LocalDate.parse(publish);
                    System.out.print("PAPER or EBOOK?: ");
                    String bookTypeInput = scanner.nextLine();
                    BookType bookType = BookType.valueOf(bookTypeInput);
                    Book book = new Book.Builder().title(title).author(author).pages(pages).publishDate(publishDate).bookType(bookType).build();
                    libraryService.addBookToLibrary(book);
                    System.out.println("Book created: " + book);
                    break;
                case "3":
                    libraryService.showUsers();
                    break;
                case "4":
                    System.out.println("What sort type: ");
                    System.out.println("1. By title.");
                    System.out.println("2. By author.");
                    System.out.println("3. By publish date.");
                    int sortType = scanner.nextInt();
                    scanner.nextLine();
                    SortBookStrategy sortBookStrategy;
                    if (sortType == 1) {
                        sortBookStrategy = new SortBooksByTitle();
                    } else if (sortType == 2) {
                        sortBookStrategy = new SortBooksByAuthor();
                    } else {
                        sortBookStrategy = new SortBooksByPublishDate();
                    }
                    libraryService.setSortBookStrategy(sortBookStrategy);
                    libraryService.showAvailableBooks();
                    break;
                case "5":
                    System.out.print("User id: ");
                    Long userId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Book id: ");
                    Long bookId = scanner.nextLong();
                    scanner.nextLine();
                    libraryService.tryRentBook(libraryService.getUserById(userId), libraryService.getBookById(bookId));
                    break;
                case "6":
                    System.out.print("User id: ");
                    Long userId6 = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Book id: ");
                    Long bookId6 = scanner.nextLong();
                    scanner.nextLine();
                    libraryService.tryReturnBook(libraryService.getUserById(userId6), libraryService.getBookById(bookId6));
                    break;
                case "7":
                    System.out.print("User id: ");
                    Long userId7 = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Current debt: " + libraryService.getUsersCurrentDebt(libraryService.getUserById(userId7)));
                    break;
                case "8":
                    System.out.print("User id: ");
                    Long userId8 = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Debt: ");
                    Integer debt7 = scanner.nextInt();
                    scanner.nextLine();
                    libraryService.increaseUsersDebt(libraryService.getUserById(userId8), debt7);
                    break;
                case "9":
                    System.out.print("User id: ");
                    Long userId9 = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Debt: ");
                    Integer debt8 = scanner.nextInt();
                    scanner.nextLine();
                    libraryService.payOffUsersDebt(libraryService.getUserById(userId9), debt8);
                    break;
                case "10":
                    System.out.print("User id: ");
                    Long userId10 = scanner.nextLong();
                    scanner.nextLine();
                    libraryService.showBooksBorrowedByUser(libraryService.getUserById(userId10));
                    break;
                case "11":
                    System.out.println("Bye Bye");
                    this.appRunning = false;
                default:
                    System.out.println("Wrong option, try again.");
            }
        }
    }
}
