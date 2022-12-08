package com.example.exam2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adaptor2 extends BaseAdapter {

    MainActivity2 mainActivity2;
    String[] name;
    int[] img;



    public adaptor2(MainActivity2 mainActivity2, String[] name, int[] img) {
        this.mainActivity2=mainActivity2;
        this.img=img;
        this.name=name;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup parent) {

        convertview = LayoutInflater.from(mainActivity2).inflate(R.layout.item_file2,parent,false);

        ImageView imageView = convertview.findViewById(R.id.item_img2);
        imageView.setImageResource(img[i]);

        TextView textView = convertview.findViewById(R.id.item_text2);
        textView.setText(name[i]);

        return convertview;
    }
}
