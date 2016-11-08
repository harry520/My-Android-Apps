package com.example.harryjiang.intentstest;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void process(View view) {
        Intent intent, chooser;

        if (view.getId() == R.id.launchMap) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076, 72.8777"));
            chooser = Intent.createChooser(intent, "Launch Maps");
            startActivity(chooser);
        }
        if (view.getId() == R.id.launchMarket) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.google.android.googlequicksearchbox"));
            chooser = Intent.createChooser(intent, "Launch Market");
            startActivity(chooser);
        }
        if (view.getId() == R.id.sendEmail) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"slidenerd@gmail.com", "dolphindevelopers@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hi, This Was Sent from My App");
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, What's Up? How are you doing? This is my first email massage.");
            intent.setType("message/rfc822");
            chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);
        }
        if (view.getId() == R.id.sendImage) {
            Uri imageUri = Uri.parse("android.resource://com.example.harryjiang.intentstest/drawable/" + R.mipmap.ic_launcher);
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, I have attached this image.");
            chooser = Intent.createChooser(intent, "Send Image");
            startActivity(chooser);
        }
        if (view.getId() == R.id.sendImages) {
            File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String[] listOfPictures = pictures.list();
            ArrayList<Uri> arrayList = new ArrayList<>();
            Uri uri;
            for (String picture : listOfPictures) {
                uri = Uri.parse("file://" + pictures.toString() + "/" + picture);
                arrayList.add(uri);
            }
            intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.putExtra(Intent.EXTRA_STREAM, arrayList);
            chooser = Intent.createChooser(intent, "Send Images");
            startActivity(chooser);
        }
    }
}
