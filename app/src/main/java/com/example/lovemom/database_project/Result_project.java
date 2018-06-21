package com.example.lovemom.database_project;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

public class Result_project extends SQLiteOpenHelper {

    public static final String DATABASEE_NAME = "Academic.db";
    public static final String TABLE_NAME = "Academic";
    public static final String STUDENT_NAME = "NAME";
    public static final String STUDENT_ROLL = "ROLL";
    public static final String STUDENT_CONTACT = "CONTACT";
    public static final String STUDENT_AGE = "AGE";
    public static final String STUDENT_FATHER = "FATHER";
    public static final String STUDENT_MOTHER = "MOTHER";

    public Result_project(Context context) {
        super(context, DATABASEE_NAME, null, 3);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ROLL integer primary key Autoincrement,NAME TEXT,FATHER text," +
                "MOTHER TEXT,AGE TEXT,CONTACT Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIsTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insert(String name, String father, String mother, String age, String contact) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, name);
        contentValues.put(STUDENT_FATHER, father);
        contentValues.put(STUDENT_MOTHER, mother);
        contentValues.put(STUDENT_AGE, age);
        contentValues.put(STUDENT_CONTACT, contact);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getdata(String roll) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery("select * from Academic where "+STUDENT_ROLL+ "=" +roll,null);
        return cursor;
    }
}

