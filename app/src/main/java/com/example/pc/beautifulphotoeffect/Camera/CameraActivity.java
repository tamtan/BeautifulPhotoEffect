package com.example.pc.beautifulphotoeffect.Camera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.example.pc.beautifulphotoeffect.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by pc on 10/23/2015.
 */
@EActivity (R.layout.camera_activity)
public class CameraActivity extends Activity{
    @ViewById
    ImageView img_back, img_save;
    @ViewById
    RelativeLayout rl_camera_preview;

    private CameraPreview mPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Click ({R.id.img_back, R.id.img_save})
    public void onClick(View v){
        if(v.getId() == R.id.img_back){
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_enter, R.anim.no_anim);
    }
}
