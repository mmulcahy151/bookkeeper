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
public class CategoryHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "myBooks4.db";
    public static final String TABLE_CATEGORY = "categories";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat year = new SimpleDateFormat("yyyy");
    public static final String ACTIVITY_TAG = "CategoryHelper";

    public CategoryHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);
    }

    public void addCategory(Category category){
        Log.v(ACTIVITY_TAG, "Setting content values");
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());

        Log.v(ACTIVITY_TAG, "Getting database");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(ACTIVITY_TAG, "Inserting category");
        db.insert(TABLE_CATEGORY, null, values);
        Log.v(ACTIVITY_TAG, "Closing database");
        db.close();
    }

    public Category findCategory(int id){
        Category category = null;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_CATEGORY + " WHERE " + COLUMN_ID + " = " + id + ";";
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                category = new Category();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setName(cursor.getString(1));
            }
            db.close();
        }catch(Exception e){
            Log.e(ACTIVITY_TAG, "findCategory: " + e.toString());
        }
        return category;
    }

    public List<Category> getCategoriesById(List<Integer> ids){
        List<Category> categories = new ArrayList<Category>();
        try{
            for(Integer id : ids){
                Category category = this.findCategory(id);
                if(category != null){
                    categories.add(category);
                }
            }
        }catch(Exception e){
            Log.e(ACTIVITY_TAG, "getCategoriesById: " + e.toString());
        }
        return categories;
    }
}
