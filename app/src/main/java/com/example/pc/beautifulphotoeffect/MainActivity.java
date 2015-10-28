package com.example.pc.beautifulphotoeffect;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.beautifulphotoeffect.PhotoEffect.PhotoCroppingActivity_;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOAD_IMAGE = 1;
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
//        Intent i = new Intent(MainActivity.this, CameraActivity_.class);
//        startActivity(i);
//        overridePendingTransition(R.anim.slide_left_enter, R.anim.no_anim);
//        imageLoader.displayImage("file://" + photo.getUriBitmap(),
//                holder.imageItem,disPlayoption);
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQUEST_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_LOAD_IMAGE){
            Uri imageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Intent intent = new Intent(MainActivity.this, PhotoCroppingActivity_.class);
            intent.putExtra("picturePath", picturePath);
            startActivity(intent);
        }
    }
}
