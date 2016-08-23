package com.example.mmulcahy.mybooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmulcahy on 8/22/2016.
 */
public class CategoryBookLinkHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "myBooks4.db";
    public static final String TABLE_CATEGORY_BOOK_LINK = "category_book_link";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_BOOK = "book";
    public static final String ACTIVITY_TAG = "CategoryBookLinkHelper";

    public CategoryBookLinkHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CATEGORY_BOOK_LINK_TABLE = "CREATE TABLE " + TABLE_CATEGORY_BOOK_LINK + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_CATEGORY + " INTEGER, "
                + COLUMN_BOOK + " INTEGER"
                + ")";
        db.execSQL(CREATE_CATEGORY_BOOK_LINK_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY_BOOK_LINK);
        onCreate(db);
    }

    public void addCategoryBookLink(CategoryBookLink link){
        Log.v(ACTIVITY_TAG, "Setting content values");
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, link.getCategory());
        values.put(COLUMN_BOOK, link.getBook());

        Log.v(ACTIVITY_TAG, "Getting database");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(ACTIVITY_TAG, "Inserting category_book link");
        db.insert(TABLE_CATEGORY_BOOK_LINK, null, values);
        Log.v(ACTIVITY_TAG, "Closing database");
        db.close();
    }

    public void deleteCategoryBookLink(int category, int book){
        CategoryBookLink link = this.findCategoryBookLink(category, book);
        if(link != null){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CATEGORY_BOOK_LINK, "_id = " + link.getId(), null);
            db.close();
        }
    }

    public CategoryBookLink findCategoryBookLink(int category, int book){
        String query = "SELECT * FROM " + TABLE_CATEGORY_BOOK_LINK + " WHERE " + COLUMN_CATEGORY + " = " + category + " AND " + COLUMN_BOOK + " = " + book + ";";
        Log.v("BookHelper", "Find Book Query: " + query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CategoryBookLink link = new CategoryBookLink();
        if(cursor.moveToFirst()){
            link.setId(Integer.parseInt(cursor.getString(0)));
            link.setCategory(Integer.parseInt(cursor.getString(1)));
            link.setBook(Integer.parseInt(cursor.getString(2)));
        }else{
            link = null;
        }
        return link;
    }


    public List<CategoryBookLink> getCategoriesByBookId(int book){
        List<CategoryBookLink> links = new ArrayList<CategoryBookLink>();
        try{
            String query = "SELECT * FROM " + TABLE_CATEGORY_BOOK_LINK + " WHERE " + COLUMN_BOOK + " = " + book + ";";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    CategoryBookLink link = new CategoryBookLink();
                    link.setId(Integer.parseInt(cursor.getString(0)));
                    link.setCategory(Integer.parseInt(cursor.getString(1)));
                    link.setBook(Integer.parseInt(cursor.getString(2)));
                    links.add(link);
                    cursor.moveToNext();
                }
            }
            db.close();
        }catch(Exception e){
            Log.e(ACTIVITY_TAG, "getCategoriesByBookId: " + e.toString());
        }
        return links;
    }

    public List<CategoryBookLink> getBooksByCategoryId(int category){
        List<CategoryBookLink> links = new ArrayList<CategoryBookLink>();
        try{
            String query = "SELECT * FROM " + TABLE_CATEGORY_BOOK_LINK + " WHERE " + COLUMN_CATEGORY + " = " + category + ";";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    CategoryBookLink link = new CategoryBookLink();
                    link.setId(Integer.parseInt(cursor.getString(0)));
                    link.setCategory(Integer.parseInt(cursor.getString(1)));
                    link.setBook(Integer.parseInt(cursor.getString(2)));
                    links.add(link);
                    cursor.moveToNext();
                }
            }
        }catch(Exception e){
            Log.e(ACTIVITY_TAG, "getBooksByCategoryId: " + e.toString());
        }
        return links;
    }
}
