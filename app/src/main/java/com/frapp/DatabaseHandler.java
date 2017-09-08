package com.frapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

      private DataModel dataModel;

      private static final int DATABASE_VERSION=1;

      private static  final String DATABASE_NAME="frapp.db";

      private static final String TABLE_FAVOURITE="table_favourite";

      private static final String KEY_ID ="id";
      private static final String KEY_IMAGE_URL ="image_url";
      private static final String KEY_TITLE ="title";
      private static final String KEY_DESCRIPTION ="description";
      private static final String KEY_TYPE ="type";
      private static final String KEY_VIEWCOUNT ="view_count";
      private static final String KEY_TIMESTAMP="timestamp";


 	public DatabaseHandler(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " +TABLE_FAVOURITE+ "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_IMAGE_URL + " TEXT,"
                + KEY_TITLE + " TEXT,"  + KEY_DESCRIPTION+ " TEXT,"
                + KEY_TYPE + " TEXT,"+ KEY_VIEWCOUNT+ " TEXT,"
                + KEY_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

        db.execSQL(CREATE_CONTACTS_TABLE);


        Log.d("Table create..........", "TABLE CREATE SUCCESSFULLY");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        if (newVersion > oldVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITE);
        }
        onCreate(db);

    }  // method body finish

    public void addCart(String image_url,String title,String description, String type,String view_count) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_IMAGE_URL,image_url);
        values.put(KEY_TITLE,title);
        values.put(KEY_DESCRIPTION,description);
        values.put(KEY_TYPE,type);
        values.put(KEY_VIEWCOUNT,view_count);

        db.insert(TABLE_FAVOURITE, null, values);
        db.close();
    } // addcontact body complete

    public ArrayList<DataModel> getFav_Item() {

        ArrayList<DataModel> mlist =  new ArrayList<DataModel>();

        String selectQuery=  "SELECT * FROM " + TABLE_FAVOURITE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println( "helo..........................................."+cursor.getCount());
        int serialNo=cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                dataModel = new DataModel();
                dataModel.setId(cursor.getString(0));
                dataModel.setImage_url(cursor.getString(1));
                dataModel.setTitle(cursor.getString(2));
                dataModel.setDescription(cursor.getString(3));
                dataModel.setType(cursor.getString(4));
                dataModel.setView_count(cursor.getString(5));

                mlist.add(dataModel);
                serialNo--;

            } while (cursor.moveToNext());
        }// if complete
        db.close();

        return mlist;
    }// method body finish


    public ArrayList<DataModel> checkWishlist(String view_count) {

        ArrayList<DataModel> mlist =  new ArrayList<DataModel>();

        String selectQuery=  "SELECT * FROM " + TABLE_FAVOURITE + " where "+ KEY_VIEWCOUNT + " = '" + view_count+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println( "helo..........................................."+cursor.getCount());
        int serialNo=cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                dataModel =new DataModel();
                dataModel.setId(cursor.getString(0));
                dataModel.setImage_url(cursor.getString(1));
                dataModel.setTitle(cursor.getString(2));
                dataModel.setDescription(cursor.getString(3));
                dataModel.setType(cursor.getString(4));
                dataModel.setView_count(cursor.getString(5));

                mlist.add(dataModel);
                serialNo--;

            } while (cursor.moveToNext());
        }// if complete

        db.close();
        return mlist;
    }// method body finish

    public void deleteFavouriteItem(String view_count) {
        SQLiteDatabase db = this.getWritableDatabase();
       /* db.delete(TABLE_FAVORITE, KEY_PRODUCT_ID + " = ?",
                new String[] {product_id});*/
        db.delete(TABLE_FAVOURITE, KEY_VIEWCOUNT + " = ?",
                new String[] {view_count});

        db.close();
    }  //method body finish
}


