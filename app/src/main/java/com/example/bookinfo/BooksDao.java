package com.example.bookinfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BooksDao {

    @Query("SELECT * FROM Books")
    List<Books> getAllBooks();

    @Insert
    void insertBook(Books book);

    @Query("DELETE FROM Books")
    void truncateTable();

    @Query("DELETE FROM Books WHERE bookId=:studentId")
    void deleteFromStudentId(int studentId);

    @Query("UPDATE Books SET book_name=:bookname , author=:author , pages=:pages , price=:price WHERE bookId=:bookId")
    void updateExistingRow(int bookId, String bookname,String author, String pages, String price);

}
