package com.example.mmulcahy.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mmulcahy on 8/16/2016.
 */
public class EditBookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int bookId = Integer.parseInt(i.getStringExtra("BookID"));
        BookHelper helper = new BookHelper(this, null, null, 5);
        Book book = helper.findBook(bookId);
        setContentView(R.layout.edit_book_layout);

        TextView idView = (TextView)findViewById(R.id.book_id);
        idView.setText(bookId + "");
        Log.i("EditBookActivity", "Creating main layout");
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        Log.i("EditBookActivity", "Creating edit layout");
        LinearLayout editLayout = (LinearLayout)findViewById(R.id.edit_layout);
        Log.i("EditBookActivity", "Creating edit text view");
        TextView editTextView = (TextView)findViewById(R.id.edit_text_view);
        Log.i("EditBookActivity", "Creating Cancel Button");
        Button cancel = (Button)findViewById(R.id.cancel);
        final int b = bookId;
        cancel.setTag(b);
        Log.i("EditBookActivity", "Creating ScrollView");
        ScrollView editScrollView = (ScrollView)findViewById(R.id.edit_scroll_view);
        Log.i("EditBookActivity", "Creating ScrollView child");
        LinearLayout scrollViewChild = (LinearLayout)findViewById(R.id.edit_scroll_view_child);
            //Linear Layout to hold title, child of scroll view child
        Log.i("EditBookActivity", "Creating title layout");
        LinearLayout titleLayout = (LinearLayout)findViewById(R.id.title_layout);
            //TextView to hold title label, child of titleLayout
        Log.i("EditBookActivity", "Creating title text view");
        TextView titleView = (TextView)findViewById(R.id.title_text_view);
            //EditText to edit the title, child of titleLayout
        Log.i("EditBookActivity", "Creating title edit text");
        EditText editTitle = (EditText)findViewById(R.id.title);
        editTitle.setText(book.getTitle());
            //Linear Layout to hold author info, child of scroll view child
        Log.i("EditBookActivity", "Creating author layout");
        LinearLayout authorLayout = (LinearLayout)findViewById(R.id.author_layout);
            //TextView to label author text edits, child of authorLayout
        Log.i("EditBookActivity", "Creating author text view");
        TextView authorView = (TextView)findViewById(R.id.author_text_view);
            //Edit views for author name, child of authorLayout
        Log.i("EditBookActivity", "Creating author edit texts");
        EditText authorFirst = (EditText)findViewById(R.id.author_first);
        authorFirst.setText(book.getAuthorFirst());
        EditText authorMiddle = (EditText)findViewById(R.id.author_middle);
        authorMiddle.setText(book.getAuthorMiddle());
        EditText authorLast = (EditText)findViewById(R.id.author_last);
        authorLast.setText(book.getAuthorLast());
            //Linear Layout to hold publication date, child of scroll child
        Log.i("EditBookActivity", "Creating publication date layout");
        LinearLayout publishedLayout = (LinearLayout)findViewById(R.id.published_layout);
            //Text View for publication date label, child of publishedLayout
        Log.i("EditBookActivity", "Creating publication date text view");
        TextView publishedView = (TextView)findViewById(R.id.published_text_view);
            //Edit View for publication date, child of publishedLayout
        Log.i("EditBookActivity", "Creating publication date edit text");
        EditText publishedDate = (EditText)findViewById(R.id.published_date);
        Log.i("EditBookActivity", "Setting publication date text: " + book.getPublicationDate());
        publishedDate.setText(book.getPublicationDate() + "");
            //pages
        EditText pages = (EditText)findViewById(R.id.pages);
        pages.setText(book.getPages() + "");
            //TextView to hold the times read label, child of scroll view child
        Log.i("EditBookActivity", "Creating times read text view");
        TextView timesReadView = (TextView)findViewById(R.id.times_read_text_view);
            //Radio Group for times read, child of scroll view child
        Log.i("EditBookActivity", "Creating times read radio group");
        RadioGroup timesReadGroup = (RadioGroup)findViewById(R.id.times_read_group);
            //Radio buttons for times read group, child of timesReadGroup
        Log.i("EditBookActivity", "Creating times read radio buttons");
        RadioButton once = (RadioButton)findViewById(R.id.read_once);
        RadioButton twice = (RadioButton)findViewById(R.id.read_twice);
        RadioButton several = (RadioButton)findViewById(R.id.read_several);
        RadioButton none = (RadioButton)findViewById(R.id.read_none);
        int timesRead = book.getTimesRead();
        Log.i("EditBookActivity", "Setting times read radio buttons");
        switch(timesRead){
            case 0: once.setChecked(Boolean.FALSE);
                    twice.setChecked(Boolean.FALSE);
                    several.setChecked(Boolean.FALSE);
                    none.setChecked(Boolean.TRUE);
                    break;
            case 1: once.setChecked(Boolean.TRUE);
                    twice.setChecked(Boolean.FALSE);
                    several.setChecked(Boolean.FALSE);
                    none.setChecked(Boolean.FALSE);
                    break;
            case 2: once.setChecked(Boolean.FALSE);
                    twice.setChecked(Boolean.TRUE);
                    several.setChecked(Boolean.FALSE);
                    none.setChecked(Boolean.FALSE);
                    break;
            case 3: once.setChecked(Boolean.FALSE);
                    twice.setChecked(Boolean.FALSE);
                    several.setChecked(Boolean.TRUE);
                    none.setChecked(Boolean.FALSE);
                    break;
            default: once.setChecked(Boolean.FALSE);
                    twice.setChecked(Boolean.FALSE);
                    several.setChecked(Boolean.FALSE);
                    none.setChecked(Boolean.TRUE);
                    break;
        }

        Log.i("EditBookActivity", "Setting times read onClick listeners");

        //none of the following views are needed if the user hasn't read the book
        //we'll create them, but they'll be invisible if not read is selected
        //their data will clear if the user selects "not read"

        //LinearLayout to hold date read information, child of scroll child
        Log.i("EditBookActivity", "Creating date read layout");
        LinearLayout dateReadLayout = (LinearLayout)findViewById(R.id.date_read_layout);
        //TextView to hold date read label, child of dateReadLayout
        Log.i("EditBookActivity", "Creating date finished text view");
        TextView dateReadView = (TextView)findViewById(R.id.date_read_text_view);
        //EditText views to hold the date read, childred of datereadlayout
        Log.i("EditBookActivity", "Creating date read edit text");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        EditText monthRead = (EditText)findViewById(R.id.month_read);
        EditText dayRead = (EditText)findViewById(R.id.day_read);
        EditText yearRead = (EditText)findViewById(R.id.year_read);
        try{
            monthRead.setText(month.format(book.getDateFinished()));
            dayRead.setText(day.format(book.getDateFinished()));
            yearRead.setText(year.format(book.getDateFinished()));
        }catch(Exception e){
            monthRead.setText("");
            dayRead.setText("");
            yearRead.setText("");
        }


        //LinearLayout to hold glad information, child of scroll child
        Log.i("EditBookActivity", "Creating glad layout");
        LinearLayout gladLayout = (LinearLayout)findViewById(R.id.glad_layout);
        //Textview to hold glad label
        Log.i("EditBookActivity", "Creating glad text view");
        TextView gladView = (TextView)findViewById(R.id.glad_text_view);
        //RadioGroup to hold glad options
        Log.i("EditBookActivity", "Creating glad radio group");
        RadioGroup gladGroup = (RadioGroup)findViewById(R.id.glad_group);
        //RadioButtons to hold glad options
        Log.i("EditBookActivity", "Creating glad radio buttons");
        RadioButton gladYes = (RadioButton)findViewById(R.id.glad_yes);
        RadioButton gladNo = (RadioButton)findViewById(R.id.glad_no);
        Log.i("EditBookActivity", "Setting glad radio buttons");
        if(book.getGlad() == 1){
            gladNo.setChecked(Boolean.FALSE);
            gladYes.setChecked(Boolean.TRUE);
        }else{
            gladNo.setChecked(Boolean.TRUE);
            gladYes.setChecked(Boolean.FALSE);
        }


        //LinearLayout to hold read again information, child of scroll child
        Log.i("EditBookActivity", "Creating read again layout");
        LinearLayout readAgainLayout = (LinearLayout)findViewById(R.id.read_again_layout);
        //TextView to hold read again label, child of readAgainLayout
        Log.i("EditBookActivity", "Creating read again text view");
        TextView readAgainView = (TextView)findViewById(R.id.read_again_text_view);
        //RadioGroup to hold read again options, child of readAgainLayout
        Log.i("EditBookActivity", "Creating read again radio group");
        RadioGroup readAgainGroup = (RadioGroup)findViewById(R.id.read_again_group);
        //Radio Buttons for readAgainGroup
        Log.i("EditBookActivity", "Creating read again radio buttons");
        RadioButton readAgainYes = (RadioButton)findViewById(R.id.again_yes);
        RadioButton readAgainNo = (RadioButton)findViewById(R.id.again_no);
        RadioButton readAgainMaybe = (RadioButton)findViewById(R.id.again_maybe);
        int readAgain = book.getReadAgain();
        Log.i("EditBookActivity", "Setting read again radio buttons");
        if(readAgain == 0){
            readAgainYes.setChecked(Boolean.FALSE);
            readAgainMaybe.setChecked(Boolean.FALSE);
            readAgainNo.setChecked(Boolean.TRUE);
        }else if(readAgain == 1){
            readAgainYes.setChecked(Boolean.TRUE);
            readAgainMaybe.setChecked(Boolean.FALSE);
            readAgainNo.setChecked(Boolean.FALSE);
        }else{
            readAgainYes.setChecked(Boolean.FALSE);
            readAgainMaybe.setChecked(Boolean.TRUE);
            readAgainNo.setChecked(Boolean.FALSE);
        }

        //TextView to hold comments label, child of scroll child
        Log.i("EditBookActivity", "Creating comments view");
        TextView commentsView = (TextView)findViewById(R.id.comments_text_view);
        //EditView to hold comments, child of scroll child
        Log.i("EditBookActivity", "Creating comments text edit");
        EditText editComments = (EditText)findViewById(R.id.comments);
        editComments.setText(book.getComments());

        Boolean notRead = none.isChecked();
        Log.i("EditBookActivity", "Setting visibility");
        if(notRead){
            monthRead.setText("");
            dayRead.setText("");
            yearRead.setText("");
            dateReadLayout.setVisibility(View.INVISIBLE);
            gladYes.setChecked(Boolean.FALSE);
            gladNo.setChecked(Boolean.FALSE);
            gladLayout.setVisibility(View.INVISIBLE);
            readAgainYes.setChecked(Boolean.FALSE);
            readAgainNo.setChecked(Boolean.FALSE);
            readAgainMaybe.setChecked(Boolean.FALSE);
            readAgainLayout.setVisibility(View.INVISIBLE);
            commentsView.setVisibility(View.INVISIBLE);
            editComments.setText("");
            editComments.setVisibility(View.INVISIBLE);
        }
        //Save button
        Log.i("EditBookActivity", "Creating save button");
        Button save = (Button)findViewById(R.id.save);
        save.setTag(b);
    }

    public void cancel(View view){
        Intent i = new Intent(getApplicationContext(), ViewBookActivity.class);
        i.putExtra("BookID", view.getTag() + "");
        startActivity(i);
    }

    public void onUpdateReadStatus(View v){
        RadioButton readOnce = (RadioButton)findViewById(R.id.read_once);
        RadioButton readTwice = (RadioButton)findViewById(R.id.read_twice);
        RadioButton readSeveral = (RadioButton)findViewById(R.id.read_several);
        RadioButton readNone = (RadioButton)findViewById(R.id.read_none);
        LinearLayout dateRead = (LinearLayout)findViewById(R.id.date_read_layout);
        LinearLayout gladRead = (LinearLayout)findViewById(R.id.glad_layout);
        LinearLayout readAgain = (LinearLayout)findViewById(R.id.read_again_layout);
        TextView commentsView = (TextView)findViewById(R.id.comments_text_view);
        EditText comments = (EditText) findViewById(R.id.comments);

        if(!readOnce.isChecked() && !readTwice.isChecked() && !readSeveral.isChecked()){
            dateRead.setVisibility(View.INVISIBLE);
            gladRead.setVisibility(View.INVISIBLE);
            readAgain.setVisibility(View.INVISIBLE);
            commentsView.setVisibility(View.INVISIBLE);
            comments.setVisibility(View.INVISIBLE);
        }else{
            dateRead.setVisibility(View.VISIBLE);
            gladRead.setVisibility(View.VISIBLE);
            readAgain.setVisibility(View.VISIBLE);
            commentsView.setVisibility(View.VISIBLE);
            comments.setVisibility(View.VISIBLE);
        }
    }

    public void updateBook(View view){
        Book book = new Book();
        TextView id = (TextView)findViewById(R.id.book_id);

        EditText title = (EditText)findViewById(R.id.title);
        EditText authorFirst = (EditText)findViewById(R.id.author_first);
        EditText authorMiddle = (EditText)findViewById(R.id.author_middle);
        EditText authorLast = (EditText)findViewById(R.id.author_last);
        EditText publishedDate = (EditText)findViewById(R.id.published_date);
        EditText pages = (EditText)findViewById(R.id.pages);
        RadioButton readOnce = (RadioButton)findViewById(R.id.read_once);
        RadioButton readTwice = (RadioButton)findViewById(R.id.read_twice);
        RadioButton readSeveral = (RadioButton)findViewById(R.id.read_several);
        EditText monthRead = (EditText)findViewById(R.id.month_read);
        EditText dayRead = (EditText)findViewById(R.id.day_read);
        EditText yearRead = (EditText)findViewById(R.id.year_read);
        RadioButton gladYes = (RadioButton)findViewById(R.id.glad_yes);
        RadioButton againYes = (RadioButton)findViewById(R.id.again_yes);
        RadioButton againNo = (RadioButton)findViewById(R.id.again_no);
        EditText comments = (EditText)findViewById(R.id.comments);

        book.setId(Integer.parseInt(id.getText().toString()));
        book.setTitle(title.getText().toString());
        book.setAuthorFirst(authorFirst.getText().toString());
        book.setAuthorMiddle(authorMiddle.getText().toString());
        book.setAuthorLast(authorLast.getText().toString());
        try{
            book.setPublicationDate(Integer.parseInt(publishedDate.getText().toString()));
            book.setPages(Integer.parseInt(pages.getText().toString()));
        }catch(Exception e){
            book.setPublicationDate(0);
            book.setPages(0);
        }

        if(readOnce.isChecked()){
            book.setTimesRead(1);
        }else if(readTwice.isChecked()){
            book.setTimesRead(2);
        }else if(readSeveral.isChecked()){
            book.setTimesRead(3);
        }else{
            book.setTimesRead(0);
        }
        java.sql.Date dateRead = null;
        String dateReadString = yearRead.getText() + "-" + monthRead.getText() + "-" + dayRead.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dateRead = new java.sql.Date(sdf.parse(dateReadString).getTime());
        }catch(ParseException e){
            dateRead = null;
        }
        book.setDateFinished(dateRead);
        if(gladYes.isChecked()){
            book.setGlad(1);
        }else{
            book.setGlad(0);
        }
        if(againYes.isChecked()){
            book.setReadAgain(1);
        }else if(againNo.isChecked()){
            book.setReadAgain(0);
        }else{
            book.setReadAgain(2);
        }
        book.setComments(comments.getText().toString());
        if(book.getTimesRead() == 0){
            book.setDateFinished(null);
            book.setGlad(0);
            book.setReadAgain(0);
            book.setComments("");
        }

        Log.v("EditBookActivity", "About to update the book");
        BookHelper helper = new BookHelper(this, null, null, 5);
        helper.updateBook(book);

        Log.v("EditBookActivity", "About to return to the view book screen");
        Intent i = new Intent(getApplicationContext(), ViewBookActivity.class);
        i.putExtra("BookID", view.getTag() + "");
        startActivity(i);
    }
}
