package com.example.pc.beautifulphotoeffect.PhotoEffect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.beautifulphotoeffect.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TAM on 24-Oct-15.
 */
@EActivity (R.layout.photo_cropping_activity)
public class PhotoCroppingActivity extends Activity {

    ImageLoader imageLoader;
    DisplayImageOptions displayOptions;
    String picturePath;
    @ViewById
    TextView tv_title;
    @ViewById
    ImageView img_back, img_save, gallery_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picturePath = getIntent().getStringExtra("picturePath");
    }
    @AfterViews
    public void init(){

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        displayOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisk(false)
                .considerExifParams(true).cacheInMemory(true).build();
        imageLoader.displayImage("file://" + picturePath,
                gallery_image,displayOptions);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_enter, R.anim.no_anim);
    }

    @Click ({R.id.img_back, R.id.img_save})
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                onBackPressed();
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_leave);
                break;
            case R.id.img_save:
                saveImage();
                break;
        }
    }

    private void saveImage() {

    }
}
