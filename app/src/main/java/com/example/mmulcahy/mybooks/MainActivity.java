package com.example.mmulcahy.mybooks;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout category = new LinearLayout(this);
        LinearLayout.LayoutParams categoryParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        category.setLayoutParams(categoryParams);
        mainLayout.addView(category);
        category.setBackgroundColor(getResources().getColor(R.color.primary_dark));
        category.setPadding(8, 8, 8, 8);
        //add layoutparams to everything
        TextView categoryNameView = new TextView(this);
        categoryNameView.setText("All Books");
        categoryNameView.setTextColor(Color.parseColor("#ffffff"));
        categoryNameView.setTextSize(36);
        category.addView(categoryNameView);
        Button newBookButton = new Button(this);
        newBookButton.setText("+");
        newBookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addBook(v);
            }
        });
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        newBookButton.setLayoutParams(buttonParams);

        category.addView(newBookButton);
        ScrollView scrollListView = new ScrollView(this);
        mainLayout.addView(scrollListView);
        LinearLayout scrollViewChild = new LinearLayout(this);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollViewChild.setBackgroundColor(getResources().getColor(R.color.primary_light));
        scrollViewChild.setLayoutParams(scrollParams);
        scrollViewChild.setOrientation(LinearLayout.VERTICAL);
        scrollListView.addView(scrollViewChild);

        //all of the books will be added to LinearLayout views within the scrollViewChild
        BookHelper helper = new BookHelper(this, null, null, 5);
        List<Book> books = helper.getBooks();
        Log.e("MainActivity", "Book Count: " + books.size());
        try{
            for(Book book : books){
                Log.e("MainActivity", "Creating layout for a book record");
                LinearLayout bookLayout = new LinearLayout(this);
                bookLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams bookParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                bookLayout.setLayoutParams(bookParams);
                bookLayout.setPadding(12, 12, 12, 12);
                bookLayout.setBackgroundColor(getResources().getColor(R.color.primary_light));

                TextView title = new TextView(this);
                title.setTextSize(24);
                title.setTextColor(getResources().getColor(R.color.primary_text));
                Log.e("MainActivity", "Setting title information");
                title.setText(book.getTitle());
                bookLayout.addView(title);
                LinearLayout bookInfo = new LinearLayout(this);
                bookInfo.setOrientation(LinearLayout.HORIZONTAL);
                TextView author = new TextView(this);
                Log.e("MainActivity", "Setting author information");
                if(book.getAuthorMiddle() != null && !book.getAuthorMiddle().equals("")){
                    author.setText(book.getAuthorFirst() + " " + book.getAuthorMiddle() + " " + book.getAuthorLast());
                }else{
                    author.setText(book.getAuthorFirst() + " " + book.getAuthorLast());
                }
                author.setTextSize(16);
                author.setTextColor(getResources().getColor(R.color.primary_text));
                bookInfo.addView(author);
                TextView publicationDate = new TextView(this);
                Log.e("MainActivity", "Setting publication date: " + book.getPublicationDate());
                publicationDate.setText(", " + book.getPublicationDate() + "");
                publicationDate.setTextSize(16);
                publicationDate.setTextColor(getResources().getColor(R.color.primary_text));
                Log.e("MainActivity", "Adding the publication date view to the book info view");
                bookInfo.addView(publicationDate);
                if(book.getTimesRead() == 0){
                    bookLayout.setBackgroundColor(getResources().getColor(R.color.primary));
                    title.setTextColor(Color.parseColor("#ffffff"));
                    author.setTextColor(Color.parseColor("#ffffff"));
                    publicationDate.setTextColor(Color.parseColor("#ffffff"));
                }
                Log.e("MainActivity", "Adding the book info view to the book layout view");
                bookLayout.addView(bookInfo);
                Log.e("MainActivity", "Setting book id");
                int bookId = book.getId();
                bookLayout.setTag(bookId);
                Log.e("Main Activity", "Book Info Tag: " + bookLayout.getTag());
                bookLayout.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i = new Intent(getApplicationContext(), ViewBookActivity.class);
                        Log.v("MainActivity", "Inside onClick Tag values: " + v.getTag());
                        i.putExtra("BookID", v.getTag() + "");
                        startActivity(i);
                    }
                });
                TextView divider = new TextView(this);
                LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
                divider.setLayoutParams(dividerParams);
                divider.setBackgroundColor(getResources().getColor(R.color.divider));
                bookLayout.addView(divider);
                scrollViewChild.addView(bookLayout);
            }

        }catch(Exception e){
            Log.e("MainActivity", "Error: " + e.toString());
        }finally{
            setContentView(mainLayout);
        }


    }

    public void addBook(View view){
        Intent i = new Intent(getApplicationContext(), AddBookActivity.class);
        startActivity(i);
    }

    public void viewBook(View view, int bookId){
        Intent i = new Intent(getApplicationContext(), ViewBookActivity.class);
        i.putExtra("BookID", bookId + "");
        startActivity(i);
    }
}
