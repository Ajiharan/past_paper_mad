package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_add_artist,button_add_phograph,button_delete_photograph,button_view_photograph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add_artist=findViewById(R.id.btn_addArtist);
        button_add_phograph=findViewById(R.id.btn_add_photograph);
        button_delete_photograph=findViewById(R.id.btn_delete_artist);
        button_view_photograph=findViewById(R.id.btn_view_photograph);

        button_view_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ViewPhotosActivity.class);
                startActivity(intent);
            }
        });

        button_add_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddArtistActivity.class);
                startActivity(intent);
            }
        });


        button_add_phograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddPhographActivity.class);
                startActivity(intent);
            }
        });
        button_delete_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RemovePhotoOrArtistActivity.class);
                startActivity(intent);
            }
        });
    }
}
