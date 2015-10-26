package com.example.pc.beautifulphotoeffect.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
    public static String storeImageCamera(Context context, Bitmap image,
                                          String imgname) {
        String uri = "";
        File pictureFile = getOutputMediaFileCamera(context, imgname);
//        if (pictureFile == null) {
//            Log.d("error",
//                    "Error creating media file, check storage permissions: ");// e.getMessage());
//            return uri;
//        }
        uri = pictureFile.getAbsolutePath();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("error", "Error accessing file: " + e.getMessage());
        }
        return uri;
    }

    public static File getOutputMediaFileCamera(Context context, String imgname) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        // File mediaStorageDir = new File(
        // Environment.getExternalStorageDirectory() + "/Android/data/"
        // + context.getPackageName() + "/Files");
        File mediaStorageDir = new File(context.getFilesDir()
                + "/Android/data/" + context.getPackageName() + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // // Create a media file name
        // String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm")
        // .format(new Date());
        File mediaFile;
        String mImageName = imgname + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + mImageName);
        return mediaFile;
    }
}