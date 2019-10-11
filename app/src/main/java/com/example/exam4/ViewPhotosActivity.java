package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam4.Adapters.PhotoAdapter;
import com.example.exam4.Database.DBHelper;
import com.example.exam4.model.Photograph;

import java.util.ArrayList;

public class ViewPhotosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DBHelper db;
    private GridView gridView;
    private ArrayList<Photograph> photo_list;
    private PhotoAdapter adapter;
    private Button search;
    private Spinner spinner;
    private Photograph photograph;
    private String []artist_num;
    private String art_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);
        photo_list=new ArrayList<>();
        search=findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrive_image_details();
            }
        });
        spinner=findViewById(R.id.art_name);

        gridView=findViewById(R.id.grid_view_photos);
        db=new DBHelper(this);
        


        Cursor cu=db.getArtist_Names();
        int j = 0;
        while(cu.moveToNext()){
            j++;
        }
        cu.close();
        String[] artist_names=new String[j];
        artist_num=new String[j];


        int i = 0;
        cu=db.getArtist_Names();
        while(cu.moveToNext()){
            artist_num[i]=cu.getString(0);
            artist_names[i]=cu.getString(1);
            i++;
        }

        spinner.setOnItemSelectedListener(ViewPhotosActivity.this);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,artist_names);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter1);

    }

    private void retrive_image_details() {
        photo_list=db.searchphotographs(art_name);
        Toast.makeText(ViewPhotosActivity.this,String.valueOf(photo_list.size()),Toast.LENGTH_SHORT).show();
        adapter=new PhotoAdapter(ViewPhotosActivity.this,photo_list);
        gridView.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.art_name){
            art_name=artist_num[i];
            Toast.makeText(ViewPhotosActivity.this,art_name,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
