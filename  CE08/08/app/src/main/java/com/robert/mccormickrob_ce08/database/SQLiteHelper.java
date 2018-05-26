package com.robert.mccormickrob_ce08.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.robert.mccormickrob_ce08.model_class.Model_Monster;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "Monster_Database.db";
    private static final String TABLE_NAME = "Monster_Table";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String FUR = "fur";
    private static final String LEGS = "leg";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT, " + FUR + " INTEGER, " + LEGS + " INTEGER)";
        db.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int addMonster(String name, int fur, int leg) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(FUR, fur);
        cv.put(LEGS, leg);
        int id=(int)database.insert(TABLE_NAME, null, cv);
        database.close();
        return id;
    }

    public void deleteMonster(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    }

    public List<Model_Monster> getAllMonster() {

        List<Model_Monster> monsterList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Model_Monster modelMonster = new Model_Monster();

                modelMonster.setId(cursor.getInt(0));
                modelMonster.setMonster_name(cursor.getString(1));
                modelMonster.setMonster_fur(cursor.getInt(2));
                modelMonster.setMonster_leg(cursor.getInt(3));

                monsterList.add(modelMonster);
            } while (cursor.moveToNext());
        }

        return monsterList;
    }
}
