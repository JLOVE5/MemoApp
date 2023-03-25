package com.example.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class MemoDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "memos.db";
    private static final int DATABASE_VERSION = 1;
    public static final String MEMO_TABLE = "MEMO_TABLE";
    public static final String COLUMN_MEMO_ID = "COLUMN_MEMOID";
    public static final String COLUMN_MEMO_TITLE = "COLUMN_MEMO_TITLE";
    public static final String COLUMN_MEMO_CONTENT="COLUMN_MEMO_CONTENT";
    public static final String COLUMN_MEMO_DATE="COLUMN_MEMO_DATE";
    public static final String COLUMN_MEMO_IMPORTANCE="COLUMN_MEMO_IMPORTANCE";
    private static final String CREATE_TABLE=" CREATE TABLE "
            + MEMO_TABLE +
            " (" + COLUMN_MEMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MEMO_TITLE+ " TEXT, "
            + COLUMN_MEMO_CONTENT+ " TEXT, "
            + COLUMN_MEMO_DATE+ " TEXT, "
            +COLUMN_MEMO_IMPORTANCE + " TEXT )";

    public MemoDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        Log.w(MemoDBHelper.class.getName(), "Upgrading database from version " + i + " To" + i1 + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS "+ COLUMN_MEMO_TITLE);
         onCreate(database);
    }

    Cursor readAllData(){
String query = "SELECT * FROM " + MEMO_TABLE;
SQLiteDatabase db = this.getReadableDatabase();

Cursor cursor = null;
if(db != null){
    cursor = db.rawQuery(query,null);
}
return cursor;
    }

    void updateData(String row_id, String title, String date, String content, String importance){
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MEMO_TITLE, title);
        cv.put(COLUMN_MEMO_DATE, date);
        cv.put(COLUMN_MEMO_CONTENT, content);
        cv.put(COLUMN_MEMO_IMPORTANCE, importance);

       long result =  db.update(MEMO_TABLE, cv,"COLUMN_MEMO_ID=?", new String[]{row_id});
      //if(result == -1){
         //Toast.makeText(, "Failed to update", Toast.LENGTH_SHORT).show();

       //}else{

         //  Toast.makeText(null, "Successfully updated ", Toast.LENGTH_SHORT).show();
     //  }







    }


}
