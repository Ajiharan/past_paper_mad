package com.example.exam4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam4.Database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class AddPhographActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText photo_name;
    private Spinner spinner_artist,spinner_category;
    private ImageView imageView;
    private DBHelper db;
    private Button add_btn_details;
    private String art_name;
    private String cat_name;
    private String []artist_num;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int READ_REQUEST_CODE = 42;
    private boolean isExceptionFound=false;
    private Bitmap image = null;
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

        add_btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_all_details();
            }
        });
        db=new DBHelper(this);
        
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_image();
            }
        });


        spinner_category.setOnItemSelectedListener(AddPhographActivity.this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, categoryDetails);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_category.setAdapter(adapter);

        spinner_artist.setOnItemSelectedListener(AddPhographActivity.this);

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
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,artist_names);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_artist.setAdapter(adapter1);
    }

    private void add_all_details() {
        byte[] photoImage = getImageByteArray();
        if(photo_name.getText().toString().isEmpty()){
            Toast.makeText(AddPhographActivity.this,"photo name is empty",Toast.LENGTH_SHORT).show();
        }
        else if( isExceptionFound){
            Toast.makeText(AddPhographActivity.this,"select photo",Toast.LENGTH_SHORT).show();
        }
        else{
            boolean isAdded=db.addPhotograph(photo_name.getText().toString(),art_name,cat_name,photoImage);

            if(isAdded){
                photo_name.setText("");
                Toast.makeText(AddPhographActivity.this,"sucessfully added",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(AddPhographActivity.this,"cannot added",Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)  {

        if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                Uri uri = data.getData();
                try {
                    loadBitmap(uri);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    private void loadBitmap(Uri uri)  throws  IOException{
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();

        imageView.setImageBitmap(image);
    }

    private void load_image() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId()==R.id.txt_artist_name){
            art_name=artist_num[i];
            Toast.makeText(AddPhographActivity.this,art_name,Toast.LENGTH_SHORT).show();
        }
        else if(adapterView.getId()==R.id.txt_photo_category){
            cat_name=adapterView.getItemAtPosition(i).toString();
            Toast.makeText(AddPhographActivity.this,cat_name,Toast.LENGTH_SHORT).show();

        }


    }
    private byte[]  getImageByteArray(){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            isExceptionFound=false;
            return outputStream.toByteArray();
        }
        catch (Exception e){
            isExceptionFound=true;
            return null;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
