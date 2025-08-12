package org.coding.library;

import java.time.LocalDate;

public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final Integer pages;
    private final LocalDate publishDate;
    private final BookType bookType;

    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.pages = builder.pages;
        this.publishDate = builder.publishDate;
        this.bookType = builder.bookType;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public BookType getBookType() {
        return bookType;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String author;
        private Integer pages;
        private LocalDate publishDate;
        private BookType bookType;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public Builder publishDate(LocalDate publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public Builder bookType(BookType bookType) {
            this.bookType = bookType;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }


}
