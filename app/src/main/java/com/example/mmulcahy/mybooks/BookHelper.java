package com.example.mmulcahy.mybooks;

/**
 * Created by mmulcahy on 8/15/2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "myBooks4.db";
    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR_FIRST = "authorFirst";
    public static final String COLUMN_AUTHOR_LAST = "authorLast";
    public static final String COLUMN_AUTHOR_MIDDLE = "authorMiddle";
    public static final String COLUMN_PUBLICATION_DATE = "publicationDate";
    public static final String COLUMN_DATE_FINISHED = "dateFinished";
    public static final String COLUMN_PAGES = "pages";
    public static final String COLUMN_TIMES_READ = "timesRead";
    public static final String COLUMN_GLAD = "glad";
    public static final String COLUMN_READ_AGAIN = "readAgain";
    public static final String COLUMN_COMMENTS = "comments";
    public static final String COLUMN_DELETED = "deleted";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat year = new SimpleDateFormat("yyyy");
    public static final String ACTIVITY_TAG = "BookHelper";

    public BookHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_OWNER + " INTEGER, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_AUTHOR_FIRST + " TEXT, "
                + COLUMN_AUTHOR_LAST + " TEXT, "
                + COLUMN_AUTHOR_MIDDLE + " TEXT, "
                + COLUMN_PUBLICATION_DATE + " TEXT, "
                + COLUMN_DATE_FINISHED + " TEXT, "
                + COLUMN_PAGES + " INTEGER, "
                + COLUMN_TIMES_READ + " INTEGER, "
                + COLUMN_GLAD + " TEXT, "
                + COLUMN_READ_AGAIN + " INTEGER, "
                + COLUMN_COMMENTS + " TEXT, "
                + COLUMN_DELETED + " INTEGER"
                + ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public void addBook(Book book){
        Log.v(ACTIVITY_TAG, "Setting content values");
        ContentValues values = new ContentValues();
        values.put(COLUMN_OWNER, book.getOwner());
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_AUTHOR_FIRST, book.getAuthorFirst());
        values.put(COLUMN_AUTHOR_LAST, book.getAuthorLast());
        values.put(COLUMN_AUTHOR_MIDDLE, book.getAuthorMiddle());
        values.put(COLUMN_PUBLICATION_DATE, book.getPublicationDate());
        try{
            values.put(COLUMN_DATE_FINISHED, sdf.format(book.getDateFinished()));
        }catch(Exception e){
            values.put(COLUMN_DATE_FINISHED, "null");
        }
        values.put(COLUMN_PAGES, book.getPages());
        values.put(COLUMN_TIMES_READ, book.getTimesRead());
        values.put(COLUMN_GLAD, book.getGlad());
        values.put(COLUMN_READ_AGAIN, book.getReadAgain());
        values.put(COLUMN_COMMENTS, book.getComments());
        values.put(COLUMN_DELETED, 0);

        Log.v(ACTIVITY_TAG, "Getting database");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(ACTIVITY_TAG, "Inserting book");
        db.insert(TABLE_BOOKS, null, values);
        Log.v(ACTIVITY_TAG, "Closing database");
        db.close();
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<Book>();
        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_DELETED + " = 0 ORDER BY " + COLUMN_TITLE + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor allBooks = db.rawQuery(query, null);
        if(allBooks.moveToFirst()){
            while (!allBooks.isAfterLast()) {
                Book book = new Book();
                String id = allBooks.getString(0);
                Log.v(ACTIVITY_TAG, "ID: " + id);
                book.setId(Integer.parseInt(id));
                String owner = allBooks.getString(1);
                Log.v(ACTIVITY_TAG, "Owner: " + owner);
                book.setOwner(Integer.parseInt(owner));
                String title = allBooks.getString(2);
                Log.v(ACTIVITY_TAG, "Title: " + title);
                book.setTitle(title);
                String authorFirst = allBooks.getString(3);
                book.setAuthorFirst(authorFirst);
                Log.v(ACTIVITY_TAG, "Author First Name: " + authorFirst);
                String authorMiddle = allBooks.getString(5);
                book.setAuthorMiddle(authorMiddle);
                Log.v(ACTIVITY_TAG, "Author Middle Name: " + authorMiddle);
                String authorLast = allBooks.getString(4);
                book.setAuthorLast(authorLast);
                Log.v(ACTIVITY_TAG, "Author Last Name: " + authorLast);
                String publicationDate = allBooks.getString(6);
                book.setPublicationDate(Integer.parseInt(publicationDate));
                Log.v(ACTIVITY_TAG, "Publication Date: " + publicationDate);
                String pages = allBooks.getString(8);
                book.setPages(Integer.parseInt(pages));
                Log.v(ACTIVITY_TAG, "Pages: " + pages);
                String dateFinished = allBooks.getString(7);
                try{
                    book.setDateFinished(new java.sql.Date(sdf.parse(dateFinished).getTime()));
                }catch(ParseException e){
                    book.setDateFinished(null);
                }
                Log.v(ACTIVITY_TAG, "Date Finished: " + dateFinished);
                String timesRead = allBooks.getString(9);
                book.setTimesRead(Integer.parseInt(timesRead));
                Log.v(ACTIVITY_TAG, "Times read: " + timesRead);
                String glad = allBooks.getString(10);
                book.setGlad(Integer.parseInt(glad));
                Log.v(ACTIVITY_TAG, "Glad: " + glad);
                String readAgain = allBooks.getString(11);
                book.setReadAgain(Integer.parseInt(readAgain));
                Log.v(ACTIVITY_TAG, "Read again: " + readAgain);
                String comments = allBooks.getString(12);
                book.setComments(comments);
                Log.v(ACTIVITY_TAG, "Comments: " + comments);
                String deleted = allBooks.getString(13);
                book.setDeleted(Integer.parseInt(deleted));
                Log.v(ACTIVITY_TAG, "Deleted: " + deleted);
                books.add(book);
                allBooks.moveToNext();
            }
        }else{
            allBooks.close();
        }
        db.close();
        return books;
    }

    public Book findBook(int bookId) {
        Log.v("BookHelper", "Find Book ID: " + bookId);
        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_ID + " = " + bookId + " LIMIT 1;";
        Log.v("BookHelper", "Find Book Query: " + query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Book book = new Book();
        if(cursor.moveToFirst()){
            String id = cursor.getString(0);
            Log.v(ACTIVITY_TAG, "ID: " + id);
            book.setId(Integer.parseInt(id));
            String owner = cursor.getString(1);
            Log.v(ACTIVITY_TAG, "Owner: " + owner);
            book.setOwner(Integer.parseInt(owner));
            String title = cursor.getString(2);
            Log.v(ACTIVITY_TAG, "Title: " + title);
            book.setTitle(title);
            String authorFirst = cursor.getString(3);
            book.setAuthorFirst(authorFirst);
            Log.v(ACTIVITY_TAG, "Author First Name: " + authorFirst);
            String authorMiddle = cursor.getString(5);
            book.setAuthorMiddle(authorMiddle);
            Log.v(ACTIVITY_TAG, "Author Middle Name: " + authorMiddle);
            String authorLast = cursor.getString(4);
            book.setAuthorLast(authorLast);
            Log.v(ACTIVITY_TAG, "Author Last Name: " + authorLast);
            String publicationDate = cursor.getString(6);
            book.setPublicationDate(Integer.parseInt(publicationDate));
            Log.v(ACTIVITY_TAG, "Publication Date: " + publicationDate);
            String pages = cursor.getString(8);
            book.setPages(Integer.parseInt(pages));
            Log.v(ACTIVITY_TAG, "Pages: " + pages);
            String dateFinished = cursor.getString(7);
            try{
                book.setDateFinished(new java.sql.Date(sdf.parse(dateFinished).getTime()));
            }catch(ParseException e){
                book.setDateFinished(null);
            }
            Log.v(ACTIVITY_TAG, "Date Finished: " + dateFinished);
            String timesRead = cursor.getString(9);
            book.setTimesRead(Integer.parseInt(timesRead));
            Log.v(ACTIVITY_TAG, "Times read: " + timesRead);
            String glad = cursor.getString(10);
            book.setGlad(Integer.parseInt(glad));
            Log.v(ACTIVITY_TAG, "Glad: " + glad);
            String readAgain = cursor.getString(11);
            book.setReadAgain(Integer.parseInt(readAgain));
            Log.v(ACTIVITY_TAG, "Read again: " + readAgain);
            String comments = cursor.getString(12);
            book.setComments(comments);
            Log.v(ACTIVITY_TAG, "Comments: " + comments);
            String deleted = cursor.getString(13);
            book.setDeleted(Integer.parseInt(deleted));
            Log.v(ACTIVITY_TAG, "Deleted: " + deleted);
            cursor.close();
        }else{
            book = null;
        }
        //db.close();
        return book;
    }

    public boolean deleteBook(int id){
        boolean result = Boolean.FALSE;
        SQLiteDatabase db = this.getWritableDatabase();
        Book book = this.findBook(id);
        if(book != null){
            ContentValues values = new ContentValues();
            values.put(COLUMN_DELETED, 1);
            db.update(TABLE_BOOKS, values, "_id="+id, null);
        }
        db.close();
        return Boolean.TRUE;
    }

    public boolean updateBook(Book book){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Book existingBook = this.findBook(book.getId());
            if(existingBook != null){
                ContentValues values = new ContentValues();
                values.put(COLUMN_ID, book.getId());
                values.put(COLUMN_OWNER, book.getOwner());
                values.put(COLUMN_TITLE, book.getTitle());
                values.put(COLUMN_AUTHOR_FIRST, book.getAuthorFirst());
                values.put(COLUMN_AUTHOR_LAST, book.getAuthorLast());
                values.put(COLUMN_AUTHOR_MIDDLE, book.getAuthorMiddle());
                values.put(COLUMN_PUBLICATION_DATE, book.getPublicationDate());
                try{
                    values.put(COLUMN_DATE_FINISHED, sdf.format(book.getDateFinished()));
                }catch(Exception e){
                    values.put(COLUMN_DATE_FINISHED, "null");
                }
                values.put(COLUMN_PAGES, book.getPages());
                values.put(COLUMN_TIMES_READ, book.getTimesRead());
                values.put(COLUMN_GLAD, book.getGlad());
                values.put(COLUMN_READ_AGAIN, book.getReadAgain());
                values.put(COLUMN_COMMENTS, book.getComments());
                values.put(COLUMN_DELETED, 0);
                db.update(TABLE_BOOKS, values, "_id="+book.getId(), null);
            }
            //db.close();
            return Boolean.TRUE;
        }catch(Exception e){
            Log.v("BookHelper", "Update Book: " + e.toString());
            return Boolean.FALSE;
        }
    }

    public List<Book> findBooksById(List<Integer> ids){
        List<Book> books = new ArrayList<Book>();
        try{
            for(Integer id : ids){
                Book book = this.findBook(id);
                if(book != null){
                    books.add(book);
                }
            }
        }catch(Exception e){
            Log.e(ACTIVITY_TAG, "findBooksById: " + e.toString());
        }
        return books;
    }

    /*public List<Book> getBooks(int owner){
        List<Book> books = new ArrayList<Book>();

    }*/

}
