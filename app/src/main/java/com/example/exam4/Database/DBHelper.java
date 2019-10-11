package com.example.exam4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME="photodesign.db";
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_ARTIST_ENTRIES="CREATE TABLE "+ArtistMaster.Artists.TABLE_NAME +"( "+
                ArtistMaster.Artists.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ArtistMaster.Artists.COLUMN_NAME_ARTIST_NAME +" TEXT)";


        String CREATE_PHOTOGRAPH_ENTRIES="CREATE TABLE " + ArtistMaster.Photographs.TABLE_NAME + "("+
                ArtistMaster.Photographs.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ArtistMaster.Photographs.COLUMN_NAME_PHOTO_NAME + " TEXT," +
                ArtistMaster.Photographs.PHOTO_CATEGORY + " TEXT,"+
                ArtistMaster.Photographs.IMAGE + " LONGBLOB,"+
                ArtistMaster.Photographs.COLUMN_ARTIST_ID + " INTEGER,"+
                " FOREIGN KEY ("+ArtistMaster.Photographs.COLUMN_ARTIST_ID + ") REFERENCES " +
                ArtistMaster.Artists.TABLE_NAME + "( " + ArtistMaster.Artists.COLUMN_NAME_ID
                + ") ON DELETE CASCADE ON UPDATE CASCADE)";

        sqLiteDatabase.execSQL(CREATE_ARTIST_ENTRIES);
        sqLiteDatabase.execSQL(CREATE_PHOTOGRAPH_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +ArtistMaster.Photographs.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +ArtistMaster.Artists.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean addArtist(String name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ArtistMaster.Artists.COLUMN_NAME_ARTIST_NAME,name);

        long isAddeed=db.insert(ArtistMaster.Artists.TABLE_NAME,null,values);
        return(isAddeed > 0);
    }

    public String[] getArtist_Names(){
        SQLiteDatabase db=getReadableDatabase();

        String [] selection={ArtistMaster.Artists.COLUMN_NAME_ID,ArtistMaster.Artists.COLUMN_NAME_ARTIST_NAME};

       Cursor cu= db.query(ArtistMaster.Artists.TABLE_NAME,selection,null,null,null,null,null);
       int i = 0;
       while (cu.moveToNext()){
          // ArtistNames[i]=cu.getString(1);
           i++;
       }
        String []ArtistNames=new String[i];
       cu.close();
        cu= db.query(ArtistMaster.Artists.TABLE_NAME,selection,null,null,null,null,null);

        int j = 0;
        while (cu.moveToNext()){
            ArtistNames[j]=cu.getString(1);
            j++;
        }

       return  ArtistNames;
    }
}
