package org.coding.library;

import java.util.Comparator;
import java.util.List;

public class SortBooksByTitle implements SortBookStrategy {

    @Override
    public void sort(List<Book> books) {
        books.sort(Comparator.comparing(Book::getTitle));
    }
}
