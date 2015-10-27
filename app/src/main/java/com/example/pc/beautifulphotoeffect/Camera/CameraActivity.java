package com.example.pc.beautifulphotoeffect.Camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.PictureCallback;

import com.example.pc.beautifulphotoeffect.PhotoEffect.PhotoCroppingActivity;
import com.example.pc.beautifulphotoeffect.PhotoEffect.PhotoCroppingActivity_;
import com.example.pc.beautifulphotoeffect.R;
//import com.example.pc.beautifulphotoeffect.Util.Const;
import com.example.pc.beautifulphotoeffect.Util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by pc on 10/23/2015.
 */
@EActivity (R.layout.camera_activity)
public class CameraActivity extends Activity{
    @ViewById
    ImageView img_back, img_save, img_take_photo_button;
    @ViewById
    RelativeLayout rl_camera_preview;
    @ViewById
    TextView tv_title;
    Dialog dialog;
    private CameraPreview mPreview;
    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseContext();

    }

    @AfterViews
    public void init(){
        tv_title.setText(getResources().getString(R.string.camera_activity_title));
        img_save.setImageResource(R.drawable.flash_button);
        dialog = new Dialog(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        createCameraPreview();
    }

    private void createCameraPreview() {
        mPreview = new CameraPreview(this, 0, CameraPreview.LayoutMode.FitToParent);
        LayoutParams previewLayoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        previewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        rl_camera_preview.addView(mPreview, 0, previewLayoutParams);
    }

    @Click ({R.id.img_back, R.id.img_save, R.id.img_take_photo_button})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_take_photo_button:
                takeaPhoto();
                break;

        }
    }

    private void takeaPhoto() {
        try {

            dialog.show();
            // progressDialog.show();
//            mDialog_camera.setVisibility(View.VISIBLE);
            img_take_photo_button.setEnabled(false);

//            int count = 0;
//            while (count < numImage) {
//                orientationListener.rememberOrientation();
//                if (isOffSound) {
//                    mPreview.takePreviewRawData();
//                } else {
                    mPreview.mCamera.takePicture(mShutterCallback, null,
                            mPicture);
//                }
//                count++;
//            }
        } catch (Exception e) {
            // TODO: handle exception
//            new DisplayErrorDialog(Util.getStringFromResource(
//                    getApplicationContext(), R.string.text_Error),
//                    Util.getStringFromResource(this, R.string.CantTakeImage),
//                    this).start();
//            btnTakePhoto.setEnabled(true);
//            btnCaptureMultiple.setEnabled(true);
//            btnFlash.setEnabled(true);
//            btnSwitchCamera.setEnabled(true);
//            img_left_ln.setEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_leave);
    }
    ShutterCallback mShutterCallback = new ShutterCallback() {
        public void onShutter() {
            // do stuff like playing shutter sound here
        }
    };
    private PictureCallback mPicture = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            // createCameraPreview();
            mPreview.startCameraPreview();

            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            int rotation = (mPreview.displayOrientation
//                    + orientationListener.getRememberedOrientation() + mPreview.layoutOrientation) % 360;
//
//            if (rotation != 0) {
//                if (mCameraId == 1) {
//                    Matrix matrix = new Matrix();
//                    matrix.setScale(-1.0f, 1.0f);
//                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                            bitmap.getWidth(), bitmap.getHeight(), matrix,
//                            false);
//                    matrix.reset();
//                    matrix.postRotate(rotation);
//                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                            bitmap.getWidth(), bitmap.getHeight(), matrix,
//                            false);
//                } else {
//                    Matrix matrix = new Matrix();
//                    matrix.postRotate(rotation);
//                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                            bitmap.getWidth(), bitmap.getHeight(), matrix,
//                            false);
//                }
//            } else {
//                if (mCameraId == 1) {
//                    Matrix matrix = new Matrix();
//                    matrix.setScale(-1.0f, 1.0f);
//                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                            bitmap.getWidth(), bitmap.getHeight(), matrix,
//                            false);
//                }
//            }
            String uriOnePic = Util.storeImageCamera(mContext, bitmap,
                    "imageEffect");
            dialog.dismiss();

//            bitmaps.add(uriOnePic);
//            if (bitmaps.size() != 0) {
                Intent intent = new Intent(CameraActivity.this,
                        PhotoCroppingActivity_.class);
//                btnTakePhoto.setEnabled(true);
////                btnTakePhoto.setImageResource(R.drawable.btn_camera_capture);
////                btnCaptureMultiple.setEnabled(true);
////                btnFlash.setEnabled(true);
////                btnSwitchCamera.setEnabled(true);
//                ImageSession.getInstance().setBitmap(bitmaps.get(0));
//                intent.putExtra("CheckCamera", getIntent().getExtras()
//                        .getBoolean("CheckCamera"));
//                intent.putExtra(Const.URI_IN_CAMERA, uriOnePic );
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_enter,
                        R.anim.slide_right_leave);
            }

    };
}
