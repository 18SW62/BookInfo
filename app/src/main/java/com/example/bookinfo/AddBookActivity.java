package com.example.bookinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {
    EditText bookName, author, pages, price;
    Button add;
    public boolean isEdit;
    public int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookName = findViewById(R.id.bookName);
        author = findViewById(R.id.author);
        pages = findViewById(R.id.pages);
        price = findViewById(R.id.price);
        add = findViewById(R.id.addBook);
        Intent i = getIntent();

        isEdit = i.getBooleanExtra("isEdit", false);
        if (isEdit) {
            add.setText("Update");
            Intent in = getIntent();
            bookName.setText(in.getStringExtra("bookname"));
            author.setText(in.getStringExtra("author"));
            pages.setText(in.getStringExtra("pages"));
            price.setText(in.getStringExtra("price"));
            bookId = in.getIntExtra("id", -1);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
                if (fieldsAreEmpty()) {

                } else {
                    if (!isEdit) {
                        Books book = new Books(bookName.getText().toString(), author.getText().toString(),pages.getText().toString(),price.getText().toString());
                        BooksDatabase.getDbInstance(getApplicationContext()).booksDao().insertBook(book);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else if (isEdit) {
                        BooksDatabase.getDbInstance(getApplicationContext()).booksDao().updateExistingRow(
                                bookId, bookName.getText().toString(), author.getText().toString()
                                ,pages.getText().toString(),price.getText().toString()
                        );
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
            }
        });
}

    public void validateFields(){
        if(bookName.getText().toString().isEmpty())bookName.setError("Write book name");
        if(author.getText().toString().isEmpty())author.setError("Write author name");
        if(pages.getText().toString().isEmpty())pages.setError("Write book pages");
        if(price.getText().toString().isEmpty())price.setError("Write book price");
    }
    public boolean fieldsAreEmpty(){
        return (bookName.getText().toString().isEmpty()||
                author.getText().toString().isEmpty()||
                pages.getText().toString().isEmpty()||
                price.getText().toString().isEmpty());
    }
}