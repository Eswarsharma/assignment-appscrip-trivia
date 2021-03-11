package trivia.assignment.appscrip.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import trivia.assignment.appscrip.Modal.Quiz_Modal;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String QUIZ_TABLE_NAME = "quiz";
    public static final String QUIZ_COLUMN_ID = "id";
    public static final String QUIZ_COLUMN_NAME = "quiz_user_name";
    public static final String QUIZ_COLUMN_CRICKETER = "quiz_cricketer";
    public static final String QUIZ_COLUMN_COLOR = "quiz_color";
    public static final String QUIZ_DATE = "date";
    public static final String QUIZ_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table quiz " +
                        "(id integer primary key, quiz_user_name text, quiz_cricketer text,quiz_color text, date text,time text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS quiz");
        onCreate(db);
    }

    public boolean insert_Quiz (String userName, String best_cricketer, String colors, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUIZ_COLUMN_NAME, userName);
        contentValues.put(QUIZ_COLUMN_CRICKETER, best_cricketer);
        contentValues.put(QUIZ_COLUMN_COLOR, colors);
        contentValues.put(QUIZ_DATE, date);
        contentValues.put(QUIZ_TIME, time);
        db.insert(QUIZ_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from quiz where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, QUIZ_TABLE_NAME);
        return numRows;
    }

    public ArrayList<Quiz_Modal> getAllData() {
        ArrayList<Quiz_Modal> array_list = new ArrayList<Quiz_Modal>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from quiz", null );
        res.moveToFirst();

        // looping through all rows and adding to list
        if (res.moveToFirst()) {
            do {
                Quiz_Modal quiz_modal = new Quiz_Modal(
                        res.getInt(res.getColumnIndex(QUIZ_COLUMN_ID)),
                        res.getString(res.getColumnIndex(QUIZ_COLUMN_NAME)),
                        res.getString(res.getColumnIndex(QUIZ_COLUMN_CRICKETER)),
                        res.getString(res.getColumnIndex(QUIZ_COLUMN_COLOR)),
                        res.getString(res.getColumnIndex(QUIZ_DATE)),
                        res.getString(res.getColumnIndex(QUIZ_TIME)));

                array_list.add(quiz_modal);
            } while (res.moveToNext());
        }
        return array_list;
    }


}
