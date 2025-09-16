package org.coding.library;

import java.util.Comparator;
import java.util.List;

public class SortBooksByPublishDate implements SortBookStrategy {

    @Override
    public void sort(List<Book> books) {
        books.sort(Comparator.comparing(Book::getPublishDate));
    }
}
