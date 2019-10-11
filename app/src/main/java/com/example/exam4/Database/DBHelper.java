package com.example.exam4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.exam4.model.Artist;
import com.example.exam4.model.Photograph;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public Cursor getArtist_Names(){
        SQLiteDatabase db=getReadableDatabase();

        String [] selection={ArtistMaster.Artists.COLUMN_NAME_ID,ArtistMaster.Artists.COLUMN_NAME_ARTIST_NAME};

       Cursor cu= db.query(ArtistMaster.Artists.TABLE_NAME,selection,null,null,null,null,null);
        return cu;



    }

    public Cursor getPhotos_Names(){
        SQLiteDatabase db=getReadableDatabase();

        String [] selection={ArtistMaster.Photographs.COLUMN_NAME_ID,ArtistMaster.Photographs.COLUMN_NAME_PHOTO_NAME};

        Cursor cu= db.query(ArtistMaster.Photographs.TABLE_NAME,selection,null,null,null,null,null);
        return cu;



    }

    public ArrayList<Photograph> searchphotographs(String artname){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Photograph> list=new ArrayList<>();

        String [] projection={ArtistMaster.Photographs.COLUMN_ARTIST_ID,ArtistMaster.Photographs.IMAGE};
        String selection = ArtistMaster.Photographs.COLUMN_ARTIST_ID + " = ?";
        String[] selectionArgs = { artname };

        Cursor cu= db.query(ArtistMaster.Photographs.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

        while(cu.moveToNext()){
            Photograph photograph=new Photograph();
            Bitmap bitmap;
            bitmap= BitmapFactory.decodeByteArray(cu.getBlob(1),0,cu.getBlob(1).length);
            photograph.setBitmap(bitmap);
            list.add(photograph);

        }
        return list;


    }

    public boolean addPhotograph(String photo_name,String artist_id,String photo_cat,byte[] image ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ArtistMaster.Photographs.COLUMN_NAME_PHOTO_NAME,photo_name);
        values.put(ArtistMaster.Photographs.PHOTO_CATEGORY,photo_cat);
        values.put(ArtistMaster.Photographs.IMAGE,image);
        values.put(ArtistMaster.Photographs.COLUMN_ARTIST_ID,artist_id);

        long isAddeed=db.insert(ArtistMaster.Photographs.TABLE_NAME,null,values);
        return(isAddeed > 0);
    }

    public boolean deleteDetails(String name,String value){
        // Define 'where' part of query.
        if(value.equals("artist")) {
            SQLiteDatabase db = getWritableDatabase();
            String selection = ArtistMaster.Artists.COLUMN_NAME_ARTIST_NAME + " = ?";
// Specify arguments in placeholder order.
            String[] selectionArgs = {name};
// Issue SQL statement.
            int deletedRows = db.delete(ArtistMaster.Artists.TABLE_NAME, selection, selectionArgs);
            return (deletedRows > 0);
        }
        else{
            SQLiteDatabase db = getWritableDatabase();
            String selection = ArtistMaster.Photographs.COLUMN_NAME_PHOTO_NAME + " = ?";
// Specify arguments in placeholder order.
            String[] selectionArgs = {name};
// Issue SQL statement.
            int deletedRows = db.delete(ArtistMaster.Photographs.TABLE_NAME, selection, selectionArgs);
            return (deletedRows > 0);
        }
    }



}
