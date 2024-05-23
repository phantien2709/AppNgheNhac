package com.example.nhaconline.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhaconline.Fragment.Fragment_Favarite;
import com.example.nhaconline.Model.Baihat;

import java.util.ArrayList;

public class APP {

    public static  long insert(Context context , Baihat baihat){
        FavoriteDB db = new FavoriteDB( context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Utils.COLUMN_BAIHAT_ID, baihat.idbaihat);
        values.put(Utils.COLUMN_BAIHAT_NAME , baihat.tenbaihat);
        values.put(Utils.COLUMN_BAIHAT_CASI, baihat.casi);
        values.put(Utils.COLUMN_BAIHAT_HINBAIHAT, baihat.hinhbaihat);
        values.put(Utils.COLUMN_BAIHAT_LINKBAIHAT, baihat.getLinkbaihat());
        long rs=sqLiteDatabase.insert(Utils.TABLE_BAIHAT,null,values);
        return (rs);
    }
    public static ArrayList<Baihat> getBaiHatFav(Context context){
        ArrayList<Baihat> tmp = new ArrayList<>();
        FavoriteDB favoriteDB = new FavoriteDB( context);
        SQLiteDatabase sqLiteDatabase = favoriteDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+Utils.TABLE_BAIHAT,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String tenBaiHat = cursor.getString(1);
            String casi = cursor.getString(2);
            String hinh = cursor.getString(3);
            String linkbaihat = cursor.getString(4);
            tmp.add(new Baihat(id,tenBaiHat,casi,hinh,linkbaihat));
        }
        sqLiteDatabase.close();
        cursor.close();
        return tmp;
    }
    public static boolean delete(Context context,String id)
    {
        FavoriteDB favoriteDB = new FavoriteDB(context);
        SQLiteDatabase sqLiteDatabase=favoriteDB.getWritableDatabase();
        int rs=sqLiteDatabase.delete(Utils.TABLE_BAIHAT,Utils.COLUMN_BAIHAT_ID+"=?",new String[]{String.valueOf(id)});
        return (rs>0);
    }

}
