package com.example.exam2;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exam2.R;
import com.example.exam2.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    ImageView pre,next,share,photo;

    String[] arr;
//    String[] imga;
    int detailpos;
    int[] img;
//    Bitmap bitmap;
    ActionBar actionBar;
    ImageView download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        download = findViewById(R.id.download);
        photo = findViewById(R.id.photo);
        pre = findViewById(R.id.pre);
        next = findViewById(R.id.next);
        share = findViewById(R.id.share);

        detailpos = getIntent().getIntExtra("pos",0);
        arr =  getIntent().getStringArrayExtra("name");
        img =  getIntent().getIntArrayExtra("img");

        actionBar=getSupportActionBar();
        actionBar.setTitle(arr[detailpos]);
        photo.setImageResource(img[detailpos]);

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(detailpos>0){
                    detailpos--;
                    photo.setImageResource(img[detailpos]);
                    actionBar.setTitle(arr[detailpos]);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detailpos< arr.length-1){
                    detailpos++;
                    actionBar.setTitle(arr[detailpos]);
                    photo.setImageResource(img[detailpos]);
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap icon = getBitmapFromView(photo);

                System.out.println("bitmap==>"+icon);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                int num = new  Random().nextInt(2000);

                File f = new File(config.file.getAbsolutePath()+ "/Flower"+num+".jpg");
                try
                {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

//                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(f.getAbsolutePath()));
                try {
                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(), "img", "Identified image")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(Intent.createChooser(share, "Share Image"));
            }
        });

        download.setOnClickListener(v -> {

            Bitmap icon = getBitmapFromView(photo);

            System.out.println("bitmap==>"+icon);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            int num = new Random().nextInt(2000);

            File f = new File(config.file.getAbsolutePath()+ "/Flower"+num+".jpg");
            try
            {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                Toast.makeText(this,"File Downloded",Toast.LENGTH_SHORT).show();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

}