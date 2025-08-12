package org.coding.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryService {
    private LibraryService instance;
    private SortBookStrategy sortBookStrategy;
    private final List<Book> availableBooks;
    private final List<User> users;
    private final Map<User, List<Book>> rentedBooksMap;
    private final Map<User, Integer> userFeeMap;

    private LibraryService() {
        this.availableBooks = new ArrayList<>();
        this.users = new ArrayList<>();
        this.rentedBooksMap = new HashMap<>();
        this.userFeeMap = new HashMap<>();
    }

    public LibraryService getInstance() {
        if (this.instance == null) {
            return new LibraryService();
        }
        return this.instance;
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

    public void increaseUsersDebt(User user, Integer amount) {
        Integer currentDebt = this.userFeeMap.get(user);
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
}
