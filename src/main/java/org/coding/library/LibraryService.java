package org.coding.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {
    private static LibraryService instance;
    private SortBookStrategy sortBookStrategy;
    private final List<Book> allBooks;
    private final List<Book> availableBooks;
    private final List<User> users;
    private final Map<User, List<Book>> rentedBooksMap;
    private final Map<User, Integer> userFeeMap;

    private LibraryService() {
        this.allBooks = new ArrayList<>();
        this.availableBooks = new ArrayList<>();
        this.users = new ArrayList<>();
        this.rentedBooksMap = new HashMap<>();
        this.userFeeMap = new HashMap<>();
    }

    public static LibraryService getInstance() {
        if (instance == null) {
            return new LibraryService();
        }
        return instance;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void showUsers() {
        for (User user : this.users) {
            System.out.println(user);
        }
    }

    public User getUserById(Long id) {
        User userFound = null;
        for (User user : this.users) {
            if (user.getId() == id) {
                userFound = user;
                break;
            }
        }
        return userFound;
    }

    public Book getBookById(Long id) {
        Book bookFound = null;
        for (Book book : this.allBooks) {
            if (book.getId() == id) {
                bookFound = book;
                break;
            }
        }
        return bookFound;
    }

    public void setSortBookStrategy(SortBookStrategy sortStrategy) {
        this.sortBookStrategy = sortStrategy;
    }

    public void addUser(User user) {
        this.users.add(user);
        this.rentedBooksMap.put(user, new ArrayList<>());
        this.userFeeMap.put(user, 0);
    }

    public void addBookToLibrary(Book book) {
        this.allBooks.add(book);
        this.availableBooks.add(book);
    }

    public boolean tryRentBook(User user, Book book) {
        if (user == null || book == null) {
            return false;
        }
        if (this.availableBooks.contains(book)) {
            List<Book> userBooks = this.rentedBooksMap.get(user);
            userBooks.add(book);
            this.availableBooks.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean tryReturnBook(User user, Book book) {
        if (user == null || book == null) {
            return false;
        }
        List<Book> userBooks = this.rentedBooksMap.get(user);
        if (userBooks.contains(book)) {
            userBooks.remove(book);
            this.availableBooks.add(book);
            return true;
        } else {
            return false;
        }
    }

    public Integer getUsersCurrentDebt(User user) {
        return this.userFeeMap.get(user);
    }

    public void increaseUsersDebt(User user, Integer amount) {
        Integer currentDebt = this.getUsersCurrentDebt(user);
        this.userFeeMap.put(user, currentDebt + amount);
    }

    public void payOffUsersDebt(User user, Integer amount) {
        Integer currentDebt = this.userFeeMap.get(user);
        this.userFeeMap.put(user, currentDebt - amount);
    }

    public List<Book> getAvailableBooks() {
        if (this.sortBookStrategy != null) {
            this.sortBookStrategy.sort(this.availableBooks);
        }
        return this.availableBooks;
    }
    public void showAvailableBooks() {
        if (this.sortBookStrategy != null) {
            this.sortBookStrategy.sort(this.availableBooks);
        }
        for (Book book : this.availableBooks) {
            System.out.println(book);
        }
    }
    public void showBooksBorrowedByUser(User user) {
        List<Book> books = this.rentedBooksMap.get(user);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
