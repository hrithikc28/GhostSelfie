package com.example.hritikchauhan.project_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Reverse_Activity extends AppCompatActivity {

    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_);

        EditText text = (EditText)findViewById(R.id.reverse_field);
        text.setText(getIntent().getStringExtra("KEY_StringName"));

        image = (ImageView)findViewById(R.id.reverse_image);
      //  Bitmap bmp = BitmapFactory.decodeByteArray(buffer, start, a);
       // image.setImageBitmap(bmp);
    }
}
