package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam4.Database.DBHelper;

public class AddArtistActivity extends AppCompatActivity {

    private EditText artist_name;
    private Button add_details;
    private DBHelper db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        artist_name=findViewById(R.id.txt_artistname);
        add_details=findViewById(R.id.btn_add_artists);
        db=new DBHelper(this);
        add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_Artist_Details();
            }
        });
    }

    private void Add_Artist_Details() {
        if(artist_name.getText().toString().isEmpty()){
            Toast.makeText(AddArtistActivity.this,"Artist name is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            boolean isInserted=db.addArtist(artist_name.getText().toString());
            if(isInserted){
                Toast.makeText(AddArtistActivity.this,"Sucessfully inserted",Toast.LENGTH_SHORT).show();
                artist_name.setText("");
            }
            else{
                Toast.makeText(AddArtistActivity.this,"cannot inserted",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
