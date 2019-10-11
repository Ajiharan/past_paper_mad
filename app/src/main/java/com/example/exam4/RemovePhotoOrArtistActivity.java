package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam4.Database.DBHelper;

public class RemovePhotoOrArtistActivity extends AppCompatActivity  implements  AdapterView.OnItemSelectedListener  {

    private Spinner spinner_artist,spinner_category;
    private String []artist_num;
    private DBHelper db;
    private String art_name;
    private Button delete_photo,delete_artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_photo_or_artist);
        spinner_artist=findViewById(R.id.spinner_artist);
        db=new DBHelper(this);
        delete_artist=findViewById(R.id.btn_delete_artist);

        delete_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_artist_details();
            }
        });

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

        spinner_artist.setOnItemSelectedListener(RemovePhotoOrArtistActivity.this);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,artist_names);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_artist.setAdapter(adapter1);


    }

    private void delete_artist_details() {
        boolean isDeleted=db.deleteDetails(art_name,"artist");
        if(isDeleted){
            Toast.makeText(RemovePhotoOrArtistActivity.this,"sucessfullly deleted",Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(RemovePhotoOrArtistActivity.this,RemovePhotoOrArtistActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(RemovePhotoOrArtistActivity.this,"cannot delete",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.spinner_artist){
            art_name=adapterView.getItemAtPosition(i).toString();
            Toast.makeText(RemovePhotoOrArtistActivity.this,art_name,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
