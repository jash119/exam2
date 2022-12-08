package com.example.exam2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adaptor1 extends BaseAdapter {

    MainActivity mainActivity;

    public adaptor1(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    @Override
    public int getCount() {
        return config.category_name.length;
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
    public View getView(int i, View converView, ViewGroup parent) {
        converView = LayoutInflater.from(mainActivity).inflate(R.layout.item_file_1,parent,false);

        ImageView imageView = converView.findViewById(R.id.item_image);
        imageView.setImageResource(config.category_image[i]);

        TextView textView = converView.findViewById(R.id.item_text);
        textView.setText(config.category_name[i]);

        return converView;
    }
}
