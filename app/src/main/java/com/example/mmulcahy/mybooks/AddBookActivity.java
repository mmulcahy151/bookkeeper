package com.example.mmulcahy.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mmulcahy on 8/16/2016.
 */
public class AddBookActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "AddBookActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_layout);
        //make the fields that pertain to having read the book invisible
        LinearLayout dateRead = (LinearLayout)findViewById(R.id.date_read_layout);
        LinearLayout gladRead = (LinearLayout)findViewById(R.id.glad_layout);
        LinearLayout readAgain = (LinearLayout)findViewById(R.id.read_again_layout);
        TextView commentsView = (TextView)findViewById(R.id.comments_text_view);
        EditText comments = (EditText) findViewById(R.id.comments);
        dateRead.setVisibility(View.INVISIBLE);
        gladRead.setVisibility(View.INVISIBLE);
        readAgain.setVisibility(View.INVISIBLE);
        commentsView.setVisibility(View.INVISIBLE);
        comments.setVisibility(View.INVISIBLE);
    }

    public void cancel(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void save(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        Log.v(ACTIVITY_TAG, "Getting title");
        TextView titleView = (TextView)findViewById(R.id.title);
        String title = titleView.getText().toString();
        Log.v(ACTIVITY_TAG, "Getting author first name");
        TextView authorFirstView = (TextView)findViewById(R.id.author_first);
        String authorFirst = authorFirstView.getText().toString();
        Log.v(ACTIVITY_TAG, "Getting author last name");
        TextView authorLastView = (TextView)findViewById(R.id.author_last);
        String authorLast = authorLastView.getText().toString();
        Log.v(ACTIVITY_TAG, "Getting author middle name");
        TextView authorMiddleView = (TextView)findViewById(R.id.author_middle);
        String authorMiddle = authorMiddleView.getText().toString();
        Log.v(ACTIVITY_TAG, "Getting publication date");
        TextView publicationDateView = (TextView)findViewById(R.id.publication_date);
        int publicationDate = Integer.parseInt(publicationDateView.getText().toString());
        Log.v(ACTIVITY_TAG, "Getting times read");
        RadioGroup timesReadGroup = (RadioGroup)findViewById(R.id.times_read_group);
        int timesRead = 0;
        if(timesReadGroup.getCheckedRadioButtonId()!=-1) {
            RadioButton once = (RadioButton) findViewById(R.id.read_once);
            RadioButton twice = (RadioButton) findViewById(R.id.read_twice);
            RadioButton several = (RadioButton) findViewById(R.id.read_several);
            if (once.isChecked()) {
                timesRead = 1;
            } else if (twice.isChecked()) {
                timesRead = 2;
            } else if (several.isChecked()) {
                timesRead = 3;
            }
        }
        Log.v(ACTIVITY_TAG, "Getting date read");
        EditText monthRead = (EditText)findViewById(R.id.month_read);
        EditText dayRead = (EditText)findViewById(R.id.day_read);
        EditText yearRead = (EditText)findViewById(R.id.year_read);
        java.sql.Date dateFinished = null;
        try{
            dateFinished = new java.sql.Date(sdf.parse(yearRead.getText() + "-" + monthRead.getText() + "-" + dayRead.getText()).getTime());
        }catch (ParseException e){
            dateFinished = null;
        }
        //need to check the text for the radio buttons - id is a random huge number
        Log.v(ACTIVITY_TAG, "Getting glad status");
        RadioGroup gladGroup = (RadioGroup)findViewById(R.id.glad_group);
        int glad = 0; //default is yes
        if(gladGroup.getCheckedRadioButtonId() != -1){
            RadioButton gladYes = (RadioButton)findViewById(R.id.glad_yes);
            if(gladYes.isChecked()){
                glad = 1;
            }
        }
        Log.v(ACTIVITY_TAG, "Getting read again status");
        RadioGroup readAgainGroup = (RadioGroup)findViewById(R.id.read_again_group);
        int readAgain = 2; //default is maybe
        if(readAgainGroup.getCheckedRadioButtonId() != -1){
            RadioButton againYes = (RadioButton)findViewById(R.id.again_yes);
            RadioButton againNo = (RadioButton)findViewById(R.id.again_no);
            if(againYes.isChecked()){
                readAgain = 1;
            }else if(againNo.isChecked()){
                readAgain = 0;
            }
        }
        Log.v(ACTIVITY_TAG, "Getting comments");
        TextView commentsView = (TextView)findViewById(R.id.comments);
        String comments = commentsView.getText().toString();
        Log.v(ACTIVITY_TAG, "Getting pages");
        TextView pagesView = (TextView)findViewById(R.id.pages);
        int pages = Integer.parseInt(pagesView.getText().toString());

        Log.v(ACTIVITY_TAG, "Creating new book");
        Book book = new Book();
        book.setTitle(title);
        book.setDeleted(0);
        book.setComments(comments);
        book.setReadAgain(readAgain);
        book.setAuthorFirst(authorFirst);
        book.setAuthorLast(authorLast);
        book.setAuthorMiddle(authorMiddle);
        book.setDateFinished(dateFinished);
        book.setGlad(glad);
        book.setOwner(0);
        book.setPages(pages);
        book.setPublicationDate(publicationDate);
        book.setTimesRead(timesRead);
        if(book.getTimesRead() == 0){
            book.setDateFinished(null);
            book.setGlad(0);
            book.setReadAgain(0);
            book.setComments("");
        }

        Log.v(ACTIVITY_TAG, "Saving book");
        BookHelper helper = new BookHelper(this, null, null, 5);
        helper.addBook(book);
        this.cancel(view);
    }

    public void onSelectReadStatus(View view){
        Log.v("AddBookActivity", "onSelectReadStatus");
        RadioButton readOnce = (RadioButton)findViewById(R.id.read_once);
        RadioButton readTwice = (RadioButton)findViewById(R.id.read_twice);
        RadioButton readSeveral = (RadioButton)findViewById(R.id.read_several);
        LinearLayout dateRead = (LinearLayout)findViewById(R.id.date_read_layout);
        LinearLayout gladRead = (LinearLayout)findViewById(R.id.glad_layout);
        LinearLayout readAgain = (LinearLayout)findViewById(R.id.read_again_layout);
        TextView commentsView = (TextView)findViewById(R.id.comments_text_view);
        EditText comments = (EditText) findViewById(R.id.comments);

        Log.v("AddBookActivity", "onSelectReadStatus, once: " + readOnce.isChecked());
        Log.v("AddBookActivity", "onSelectReadStatus, once: " + readTwice.isChecked());
        Log.v("AddBookActivity", "onSelectReadStatus, once: " + readSeveral.isChecked());
        if(!readOnce.isChecked() && !readTwice.isChecked() && !readSeveral.isChecked()){
            Log.v("AddBookActivity", "None of the read options are selected.");
            dateRead.setVisibility(View.INVISIBLE);
            gladRead.setVisibility(View.INVISIBLE);
            readAgain.setVisibility(View.INVISIBLE);
            commentsView.setVisibility(View.INVISIBLE);
            comments.setVisibility(View.INVISIBLE);
        }else{
            Log.v("AddBookActivity", "One of the read optiosn was selected");
            dateRead.setVisibility(View.VISIBLE);
            gladRead.setVisibility(View.VISIBLE);
            readAgain.setVisibility(View.VISIBLE);
            commentsView.setVisibility(View.VISIBLE);
            comments.setVisibility(View.VISIBLE);
        }
    }
}
