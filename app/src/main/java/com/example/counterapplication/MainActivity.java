package com.example.counterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private int counterInt;
    private TextView counter;
    private int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = (TextView) findViewById(R.id.counterNum);
        counterInt = 0;
        counter.setText(String.valueOf(counterInt));
    }

    public void initCounter(View view){
        counterInt = 0;
        counter.setText(String.valueOf(counterInt));
    }

    public void plusCounter(View  view){
        counter.setText(String.valueOf(++counterInt));
    }

    public void minCounter(View view){
        counter.setText(String.valueOf(--counterInt));
    }

    // this function is triggered when
    // the Select Image Button is clicked
    public void image(View view){
        // create an instance of the
        // intent of the type image
        Intent i =  new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }
    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                   Intent share = new Intent(Intent.ACTION_SEND);
                   share.setType("image/jpeg");
                   share.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
//                   share.setPackage("com.whatsapp");
                    startActivity(Intent.createChooser(share, "Share Image"));
                }
            }
        }
    }
    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, ("De counter staat nu op " + counterInt));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "My first android share");
        startActivity(shareIntent);

    }
}