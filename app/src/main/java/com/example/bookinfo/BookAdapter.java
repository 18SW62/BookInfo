package com.example.bookinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class BookAdapter extends RecyclerView.Adapter {
    List<Books> list;
    Context context;

    public BookAdapter(List<Books> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        BooksViewHolder viewHolderClass = new BooksViewHolder(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BooksViewHolder vHClass = (BooksViewHolder) holder;
        Books book = list.get(position);
        vHClass.bookname.setText(book.getBookName());
        vHClass.author.setText("Author: " + book.getAuthor().toUpperCase());
        vHClass.pages.setText("Pages: "+book.getPages());
        vHClass.price.setText("PKR. "+book.getPrice());
        vHClass.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context,AddBookActivity.class);
                i.putExtra("bookname",book.getBookName());
                i.putExtra("author",book.getAuthor());
                i.putExtra("pages",book.getPages());
                i.putExtra("price",book.getPrice());
                i.putExtra("isEdit",true);
                i.putExtra("id",book.getBookId());
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });
        vHClass.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BooksDatabase.getDbInstance(context).booksDao().deleteFromStudentId(book.getBookId());
                list.remove(position);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class BooksViewHolder extends RecyclerView.ViewHolder{
        TextView bookname,author,pages,price;
        ImageView delete,edit;
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.bookName);
            author = itemView.findViewById(R.id.author);
            pages = itemView.findViewById(R.id.pages);
            price =itemView.findViewById(R.id.price);
            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.edit);
        }
    }
}
