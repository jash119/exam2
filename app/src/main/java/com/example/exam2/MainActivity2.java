package com.example.exam2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity2 extends AppCompatActivity {

    GridView gridView;
    ActionBar actionBar;
    int pos;
    int[] img;
    String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        actionBar=getSupportActionBar();

        gridView=findViewById(R.id.grid);

        pos=getIntent().getIntExtra("pos",0);

        if(pos==0){
            actionBar.setTitle(config.category_name[pos]);
            name=config.nature_name;
            img=config.nature_image;
        }
        if(pos==1){
            actionBar.setTitle(config.category_name[pos]);
            name=config.animal_name;
            img=config.animal_image;
        }
        if(pos==2){
            actionBar.setTitle(config.category_name[pos]);
            name=config.river_name;
            img=config.river_image;
        }
        if(pos==3){
            actionBar.setTitle(config.category_name[pos]);
            name=config.flower_name;
            img=config.flower_image;
        }

        adaptor2 adaptor2 = new adaptor2(this,name,img);
        gridView.setAdapter(adaptor2);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("pos",i);
                intent.putExtra("name",name);
                intent.putExtra("img",img);
                startActivity(intent);
            }
        });
    }
}