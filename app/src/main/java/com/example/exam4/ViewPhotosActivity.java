package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exam4.Adapters.PhotoAdapter;
import com.example.exam4.Database.DBHelper;
import com.example.exam4.model.Photograph;

import java.util.ArrayList;

public class ViewPhotosActivity extends AppCompatActivity {

    private DBHelper db;
    private GridView gridView;
    private ArrayList<Photograph> photo_list;
    private PhotoAdapter adapter;
    private Photograph photograph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);
        photo_list=new ArrayList<>();
        gridView=findViewById(R.id.grid_view_photos);
        db=new DBHelper(this);
        
        retrive_image_details();

    }

    private void retrive_image_details() {
        photo_list=db.get_photographs();
        Toast.makeText(ViewPhotosActivity.this,String.valueOf(photo_list.size()),Toast.LENGTH_SHORT).show();
        adapter=new PhotoAdapter(ViewPhotosActivity.this,photo_list);
        gridView.setAdapter(adapter);


    }
}
