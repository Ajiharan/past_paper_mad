package com.example.exam4.Database;

import android.provider.BaseColumns;

public final class ArtistMaster {

    private ArtistMaster(){}

    public final class Photographs implements BaseColumns {
        public static final String TABLE_NAME="photograph";
        public static final String COLUMN_NAME_ID="id";
        public static  final String COLUMN_NAME_PHOTO_NAME="photo_name";
        public static final String COLUMN_ARTIST_ID="artist_id";
        public static final String PHOTO_CATEGORY="category";
        public static final  String IMAGE="image";
    }

    public final class Artists implements BaseColumns{
        public static final String TABLE_NAME="artist";
        public static final String COLUMN_NAME_ID="id";
        public static final String COLUMN_NAME_ARTIST_NAME="arrtist_name";
    }
}
