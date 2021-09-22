package com.example.bookinfo;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Books.class},version = 1,exportSchema = false)
abstract class BooksDatabase extends RoomDatabase {
    public abstract BooksDao booksDao();
    private static BooksDatabase INSTANCE;
    public static BooksDatabase getDbInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),BooksDatabase.class,"Books.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}

