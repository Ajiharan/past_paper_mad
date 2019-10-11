package com.example.exam4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam4.Database.DBHelper;

public class AddPhographActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText photo_name;
    private Spinner spinner_artist,spinner_category;
    private ImageView imageView;
    private DBHelper db;
    private Button add_btn_details;
    private String art_name;
    private String cat_name;
    String []categoryDetails={"Landscape","WildLife","Wedding","Fashion","Black and White"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phograph);
        photo_name=findViewById(R.id.txt_photo_name);
        spinner_artist=findViewById(R.id.txt_artist_name);
        spinner_category=findViewById(R.id.txt_photo_category);
        imageView=findViewById(R.id.img_photograph);
        add_btn_details=findViewById(R.id.button_add);
        db=new DBHelper(this);


        spinner_category.setOnItemSelectedListener(AddPhographActivity.this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categoryDetails);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_category.setAdapter(adapter);

        spinner_artist.setOnItemSelectedListener(AddPhographActivity.this);
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,db.getArtist_Names());
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_artist.setAdapter(adapter1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId()==R.id.txt_artist_name){
            art_name=adapterView.getItemAtPosition(i).toString();
            //Toast.makeText(AddPhographActivity.this,art_name,Toast.LENGTH_SHORT).show();
        }
        else if(adapterView.getId()==R.id.txt_photo_category){
            cat_name=adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(AddPhographActivity.this,cat_name,Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
