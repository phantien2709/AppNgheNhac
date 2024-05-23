package com.example.nhaconline.SQLite;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nhaconline.Model.Baihat;

import java.util.ArrayList;

public class FavoriteDB  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME=Utils.DATABASE_NAME;
    private static final int DATABASE_VERSION=1;
    Context context;

    public FavoriteDB(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    //
    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
    }

    // tao bang
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ Utils.TABLE_BAIHAT + "(" +
                Utils.COLUMN_BAIHAT_ID + " INTEGER PRIMARY KEY," +
                Utils.COLUMN_BAIHAT_NAME + " TEXT, " +
                Utils.COLUMN_BAIHAT_CASI+ " TEXT, " +
                Utils.COLUMN_BAIHAT_HINBAIHAT + " TEXT, " +
                Utils.COLUMN_BAIHAT_LINKBAIHAT + " TEXT " +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLE_BAIHAT);
        onCreate(db);
    }


}
