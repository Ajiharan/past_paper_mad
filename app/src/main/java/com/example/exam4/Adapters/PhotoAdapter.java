package com.example.exam4.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exam4.R;
import com.example.exam4.model.Photograph;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter<Photograph> {
    private Activity context;
    private ArrayList<Photograph> mylist;
    public PhotoAdapter(@NonNull Activity context, ArrayList<Photograph> mylist) {
        super(context,0,mylist);
        this.context=context;
        this.mylist=mylist;
    }

    private class PhotoHolder{
        ImageView imageView;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhotoAdapter.PhotoHolder holder=null;

        if(convertView == null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.photo_list_views,parent,false);
            holder=new PhotoAdapter.PhotoHolder();
            holder.imageView=convertView.findViewById(R.id.my_images);

            convertView.setTag(holder);
        }
        else{

            holder= (PhotoHolder) convertView.getTag();

        }
        Photograph photograph=mylist.get(position);
        holder.imageView.setImageBitmap(photograph.getBitmap());


        return convertView;
    }
}
