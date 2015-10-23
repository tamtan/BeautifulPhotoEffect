package com.example.pc.beautifulphotoeffect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.beautifulphotoeffect.Camera.CameraActivity;
import com.example.pc.beautifulphotoeffect.Camera.CameraActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends AppCompatActivity {
    @ViewById
    ImageView img_gallery, img_camera ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
//    @AfterViews
//    public void init(){
//        img_save.setVisibility(View.INVISIBLE);
//        img_back.setVisibility(View.INVISIBLE);
//    }

    @Click ({R.id.img_camera,R.id.img_gallery})
    public void onClick(View v){
//        Toast.makeText(MainActivity.this, "hello",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, CameraActivity_.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_left_enter, R.anim.no_anim);
    }
}
