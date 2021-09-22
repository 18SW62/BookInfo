package com.example.bookinfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Books {
    @PrimaryKey(autoGenerate = true)
    public int bookId;

    @ColumnInfo(name = "book_name")
    String bookName;
    @ColumnInfo(name = "author")
    String author;
    @ColumnInfo(name = "pages")
    String pages;
    @ColumnInfo(name = "price")
    String price;

    public Books(String bookName, String author, String pages, String price) {
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
