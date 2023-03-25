package com.example.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MemoDataSource {
    private SQLiteDatabase database;
    private MemoDBHelper dbHelper;

    public MemoDataSource(Context context){
        dbHelper =  new MemoDBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

public boolean insertMemo(Memo m){
        boolean didSucceed = false;
      try{
          ContentValues initialValues = new ContentValues();
          initialValues.put(dbHelper. COLUMN_MEMO_TITLE , m.getMemoTitle());
          initialValues.put(dbHelper.COLUMN_MEMO_CONTENT, m.getMemoContent());
          initialValues.put(dbHelper.COLUMN_MEMO_IMPORTANCE,m.getMemoImportance());
         initialValues.put(dbHelper.COLUMN_MEMO_DATE, m.getMemoDate());

          didSucceed = database.insert(MemoDBHelper.MEMO_TABLE, null, initialValues) > 0;


      }
      catch(Exception e){
          // Do nothing -will return false if there is an exception
      }
      return didSucceed;
}

public boolean updateMemo(Memo m){
        boolean didSucceed = false;
        try{
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            Long rowId = (long) m.getMemoID();
            ContentValues updateValues = new ContentValues();

            updateValues.put(dbHelper.COLUMN_MEMO_TITLE, m.getMemoTitle());
            updateValues.put(dbHelper.COLUMN_MEMO_CONTENT, m.getMemoContent());
             updateValues.put(dbHelper.COLUMN_MEMO_IMPORTANCE, m.getMemoImportance());
            updateValues.put(dbHelper.COLUMN_MEMO_DATE, m.getMemoDate());


            didSucceed = database.update(MemoDBHelper.MEMO_TABLE, updateValues, MemoDBHelper.COLUMN_MEMO_ID + rowId,null) > 0;




        }
        catch(Exception e){
            // Do nothing -will return false if there is an exception
        }
    return didSucceed;
}

public int getLastMemoID(){
int lastMemo;
try{
    String query = "Select MAX("+ dbHelper.COLUMN_MEMO_ID + ") from " + dbHelper.MEMO_TABLE;
    Cursor cursor = database.rawQuery(query,null);

    cursor.moveToFirst();
    lastMemo= cursor.getInt(0);
    cursor.close();
}
catch (Exception e){
    lastMemo = -1;
}
return lastMemo;

}

public boolean deleteMemo(int memoId){
        boolean didDelete = false;
        try{
            System.out.println("delete");

            didDelete = database.delete(MemoDBHelper.MEMO_TABLE,MemoDBHelper.COLUMN_MEMO_ID+" = "+memoId,null)>0;

        }catch (Exception e){


        }
        return didDelete;

}



}




