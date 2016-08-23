package com.example.mmulcahy.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by mmulcahy on 8/16/2016.
 */
public class ViewBookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        int bookId = Integer.parseInt(i.getStringExtra("BookID"));
        Log.e("ViewBookActivity", "Book ID value: " + bookId);
        BookHelper helper = new BookHelper(this, null, null, 5);
        Book book = helper.findBook(bookId);
        Log.e("ViewBook", "Book ID: " + book.getId());
        Log.e("ViewBook", "Book Title: " + book.getTitle());
            //main layout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mainLayout.setLayoutParams(mainParams);
            //title layout, child of main layout
        LinearLayout titleLayout = new LinearLayout(this);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleLayout.setLayoutParams(titleParams);
        titleLayout.setPadding(8, 8, 8, 8);
        mainLayout.addView(titleLayout);
            //title textview, child of title layout
        TextView title = new TextView(this);
        title.setText(book.getTitle());
        title.setTextSize(24);
        titleLayout.addView(title);
            //edit button, child of title layout
        final int b = bookId;
        LinearLayout buttonLayout = new LinearLayout(this);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonLayout.setLayoutParams(buttonParams);
        mainLayout.addView(buttonLayout);
        Button edit = new Button(this);
        edit.setText("Edit");
        buttonLayout.addView(edit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                edit(v, b);
            }
        });
            //delete button, child of title layout
        Button delete = new Button(this);
        delete.setText("Delete");
        Log.e("ViewBookActivity", "Delete book id: " + b);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                delete(v, b);
            }
        });
        buttonLayout.addView(delete);
            //back button, child of title layout
        Button back = new Button(this);
        back.setText("Back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
        buttonLayout.addView(back);
            //scroll view, child of main layout. holds single linear layout
        ScrollView scrollBookView = new ScrollView(this);
        mainLayout.addView(scrollBookView);
            //linear layout inside of scroll view. holds all book information
        LinearLayout scrollBookChild = new LinearLayout(this);
        scrollBookChild.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollBookChild.setLayoutParams(scrollParams);
        scrollBookChild.setPadding(8, 8, 8, 8);
        scrollBookView.addView(scrollBookChild);
            //author textview, child of scroll view child
        TextView authorView = new TextView(this);
        if(book.getAuthorMiddle() != null && !book.getAuthorMiddle().equals("")){
            authorView.setText("Author: " + book.getAuthorFirst() + " " + book.getAuthorMiddle() + " " + book.getAuthorLast());
        }else{
            authorView.setText("Author: " + book.getAuthorFirst() + " " + book.getAuthorLast());
        }
        authorView.setTextSize(18);
        scrollBookChild.addView(authorView);
            //publication date text view, child of scroll view child
        TextView publicationDateView = new TextView(this);
        publicationDateView.setText("Publication Date: " + book.getPublicationDate());
        publicationDateView.setTextSize(18);
        scrollBookChild.addView(publicationDateView);
            //pages view, child of scroll view child
        TextView pagesView = new TextView(this);
        pagesView.setText("Pages: " + book.getPages());
        pagesView.setTextSize(18);
        scrollBookChild.addView(pagesView);
            //times read view, child of scroll view child
        TextView timesReadView = new TextView(this);
        int timesRead = book.getTimesRead();
        if(timesRead == 1){
            timesReadView.setText("I have read this book once.");
        }else if(timesRead == 2){
            timesReadView.setText("I have read this book twice.");
        }else if(timesRead == 3){
            timesReadView.setText("I have read this book several times.");
        }else{
            timesReadView.setText("I have not read this book yet.");
        }
        timesReadView.setTextSize(18);
        scrollBookChild.addView(timesReadView);

        //the following views are only added if the user has read the book
        if(timesRead > 0){
            //date finished view, child of scroll view child
            //only add if they've read the book
            TextView dateFinishedView = new TextView(this);
            if(book.getDateFinished() != null) {
                dateFinishedView.setText("I finished reading it on: " + book.getDateFinished());
            }else{
                dateFinishedView.setText("I finished reading it on: ");
            }
            dateFinishedView.setTextSize(18);
            scrollBookChild.addView(dateFinishedView);
            //glad view, child of scroll view child
            TextView gladView = new TextView(this);
            if(book.getGlad() == 1){
                gladView.setText("I'm glad I read this book.");
            }else{
                gladView.setText("I'm not glad I read this book.");
            }
            gladView.setTextSize(18);
            scrollBookChild.addView(gladView);
            //read again view, child of scroll view child
            TextView readAgainView = new TextView(this);
            if(book.getReadAgain() == 0){
                readAgainView.setText("I won't read this book again.");
            }else if(book.getReadAgain() == 1){
                readAgainView.setText("I'll read this book again.");
            }else{
                readAgainView.setText("I might read this book again.");
            }
            readAgainView.setTextSize(18);
            scrollBookChild.addView(readAgainView);
            //comments view, child of scroll view child
            TextView comments = new TextView(this);
            comments.setText(book.getComments());
            comments.setTextSize(18);
            scrollBookChild.addView(comments);
        }



        setContentView(mainLayout);
    }

    public void back(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void edit(View view, int bookId){
        Intent i = new Intent(getApplicationContext(), EditBookActivity.class);
        i.putExtra("BookID", bookId + "");
        startActivity(i);
    }

    public void delete(View view, int bookId){
        BookHelper helper = new BookHelper(this, null, null, 5);
        helper.deleteBook(bookId);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
