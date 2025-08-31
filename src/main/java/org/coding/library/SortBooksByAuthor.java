package org.coding.library;

import java.util.Comparator;
import java.util.List;

public class SortBooksByAuthor implements SortBookStrategy {

    @Override
    public void sort(List<Book> books) {
        books.sort(Comparator.comparing(Book::getAuthor)); // referencja do metody (z Book referuje do getauthor)
    }
}
