/*
//package com.example.pc.beautifulphotoeffect.Util;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.RandomAccessFile;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.nio.channels.FileChannel.MapMode;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//
//import javax.microedition.khronos.opengles.GL10;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.content.res.AssetManager;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.Paint.Style;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Typeface;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.opengl.GLES10;
//import android.os.Environment;
//import android.text.Layout.Alignment;
//import android.text.StaticLayout;
//import android.text.TextPaint;
//import android.util.Log;
//import android.view.View;
//
//import com.decopuchi.camera.Frame;
//import com.decopuchi.shop.Item_product;
//import com.decopuchi.text.Item_text;
//import com.gateweb.android.decopuchi.R;
//
//public class Util {
//	public static boolean isBackground = false;
//	// public static String uriBackground_root = "";
//	public static String uriBackground = "";
//	// public static String uri_effect;
//	public static boolean isPost = false;
//	public static boolean isReview = false;
//	public static int scale_type_background_curent = 1;
//	public static int scale_type_background_init = 1;
//	public static int width_screen;
//	public static int heigh_screen;
//	public static String date_bonus = "";
//	public static boolean RECEIVEDATA = false;
//	// public static boolean isLayout;
//	public static int restore_layout = 0;
//
//	public static float pxFromDp(final Context context, final float dp) {
//		return dp * context.getResources().getDisplayMetrics().density;
//	}
//
//	public boolean checkExistInArray(String[] arr, String element) {
//		for (int i = 0; i < arr.length; i++) {
//			if (element.equalsIgnoreCase(arr[i])) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static String getDatabyHttpUrlConn(String urlString) {
//		String str_return = "0";
//		int connectTimeout = 8000;
//		int socketTimeout = 5000;
//		URL url;
//		HttpURLConnection urlConnection = null;
//		InputStream inputStream = null;
//		try {
//			url = new URL(urlString);
//
//			urlConnection = (HttpURLConnection) url.openConnection();
//			urlConnection.setConnectTimeout(connectTimeout);
//			urlConnection.setReadTimeout(socketTimeout);
//			urlConnection.setRequestMethod("GET");
//			inputStream = new BufferedInputStream(
//					urlConnection.getInputStream());
//			str_return = convertInputStreamToString(inputStream);
//			Log.e("str_return by httpurl: ", " " + str_return);
//		} catch (Exception e) {
//		} finally {
//			urlConnection.disconnect();
//		}
//
//		return str_return;
//	}
//
//	public static String convertInputStreamToString(InputStream inputStream)
//			throws IOException {
//		BufferedReader bufferedReader = new BufferedReader(
//				new InputStreamReader(inputStream));
//		String line = "";
//		String result = "";
//		while ((line = bufferedReader.readLine()) != null)
//			result += line;
//
//		inputStream.close();
//		return result;
//
//	}
//
//	public static String getSizeName(Context context) {
//		int screenLayout = context.getResources().getConfiguration().screenLayout;
//		screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
//
//		switch (screenLayout) {
//		case Configuration.SCREENLAYOUT_SIZE_SMALL:
//			return "small";
//		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
//			return "normal";
//		case Configuration.SCREENLAYOUT_SIZE_LARGE:
//			return "large";
//		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
//			return "xlarge";
//		default:
//			return "undefined";
//		}
//	}
//
//	public static void DeleteRecursive(File fileOrDirectory) {
//		if (fileOrDirectory.isDirectory())
//			for (File child : fileOrDirectory.listFiles())
//				DeleteRecursive(child);
//
//		fileOrDirectory.delete();
//	}
//
//	public static Bitmap RemovePixelsTransparent(Bitmap mBitmap) {
//		float x_min = Frame.getXleft(mBitmap);
//		float x_max = Frame.getXright(mBitmap);
//		float y_min = Frame.getYTop(mBitmap);
//		float y_max = Frame.getYbottom(mBitmap);
//
//		Bitmap zBitmap = Bitmap.createBitmap(mBitmap, (int) x_min, (int) y_min,
//				(int) (x_max - x_min), (int) (y_max - y_min));
//
//		return zBitmap;
//	}
//
//	public static boolean IsTransparent(Bitmap mBitmap) {
//		mBitmap = Frame.getResizedBitmap(mBitmap, 10.0f);
//		for (int i = 0; i < mBitmap.getWidth(); i++) {
//			for (int j = 0; j < mBitmap.getHeight(); j++) {
//				if (mBitmap.getPixel(i, j) != Color.TRANSPARENT) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
//		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
//				bitmap.getHeight(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(output);
//
//		final int color = 0xff424242;
//		final Paint paint = new Paint();
//		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//		final RectF rectF = new RectF(rect);
//		final float roundPx = pixels;
//
//		paint.setAntiAlias(true);
//		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(color);
//		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, rect, rect, paint);
//
//		return output;
//	}
//
//	// public static String getDataEffect(String URL) {
//	// String str_return = "0";
//	// HttpParams httpParameters = new BasicHttpParams();
//	// // Set the timeout in milliseconds until a connection is established.
//	// // The default value is zero, that means the timeout is not used.
//	// int timeoutConnection = 3000;
//	// HttpConnectionParams.setConnectionTimeout(httpParameters,
//	// timeoutConnection);
//	// // Set the default socket timeout (SO_TIMEOUT)
//	// // in milliseconds which is the timeout for waiting for data.
//	// int timeoutSocket = 5000;
//	// HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//	//
//	// HttpClient httpclient = new DefaultHttpClient(httpParameters);
//	// HttpGet request = new HttpGet(URL);
//	//
//	// Log.e("url terop: ", " " + URL);
//	//
//	// try {
//	// HttpResponse response = httpclient.execute(request);
//	// HttpEntity resEntity = response.getEntity();
//	// String _response = EntityUtils.toString(resEntity, "UTF-8");
//	// str_return = _response;
//	// } catch (Exception e1) {
//	// e1.printStackTrace();
//	// }
//	// httpclient.getConnectionManager().shutdown();
//	// return str_return;
//	// }
//
//	// public static String getData(String URL) {
//	// String str_return = "0";
//	// HttpParams httpParameters = new BasicHttpParams();
//	// // Set the timeout in milliseconds until a connection is established.
//	// // The default value is zero, that means the timeout is not used.
//	// int timeoutConnection = 3000;
//	// HttpConnectionParams.setConnectionTimeout(httpParameters,
//	// timeoutConnection);
//	// // Set the default socket timeout (SO_TIMEOUT)
//	// // in milliseconds which is the timeout for waiting for data.
//	// int timeoutSocket = 5000;
//	// HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//	//
//	// HttpClient httpclient = new DefaultHttpClient(httpParameters);
//	// HttpGet request = new HttpGet(URL);
//	//
//	// try {
//	// HttpResponse response = httpclient.execute(request);
//	// HttpEntity resEntity = response.getEntity();
//	// String _response = EntityUtils.toString(resEntity, "UTF-8");
//	// str_return = _response;
//	// } catch (Exception e1) {
//	// e1.printStackTrace();
//	// }
//	// httpclient.getConnectionManager().shutdown();
//	// return str_return;
//	// }
//
//	// public static void RankingLogProduct(String URL) {
//	// HttpClient httpclient = new DefaultHttpClient();
//	// httpclient.getParams().setParameter(
//	// CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//	// httpclient.getConnectionManager().shutdown();
//	// // return str_return;
//	// }
//
//	public static String getLanguageLocal() {
//		String language = Locale.getDefault().getLanguage();
//		String localeess = Locale.getDefault().toString();
//		// Log.e("sasasas", "" + localeess);
//		// String language = Locale.getDefault().toString();
//
//		if (language.equals("ja")) {
//			language = "ja";
//		} else {
//			language = "en";
//		}
//
//		Log.e("Language", "" + language);
//
//		return language;
//	}
//
//	public static String getStringFromResource(Context mcontext, int resource) {
//		return mcontext.getResources().getString(resource);
//	}
//
//	@SuppressWarnings("deprecation")
//	public static Bitmap decodeScaledBitmapFromSdCard(String filePath) {
//
//		// First decode with inJustDecodeBounds=true to check dimensions
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(filePath, options);
//
//		// Calculate inSampleSize
//		options.inSampleSize = calculateInSampleSize(options, 2000, 2000);
//		options.inScaled = false;
//		// Decode bitmap with inSampleSize set
//		options.inJustDecodeBounds = false;
//		return BitmapFactory.decodeFile(filePath, options);
//	}
//	@SuppressWarnings("deprecation")
//	public static Bitmap decodeScaledBitmapFromSdCardScale(String filePath) {
//
//		// First decode with inJustDecodeBounds=true to check dimensions
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(filePath, options);
//
//		// Calculate inSampleSize
//		options.inSampleSize = calculateInSampleSize(options, 1500, 1500);
//		options.inScaled = false;
//		// Decode bitmap with inSampleSize set
//		options.inJustDecodeBounds = false;
//		return BitmapFactory.decodeFile(filePath, options);
//	}
//	public static Bitmap decodeSampledBitmapFromResource(String filePath,
//			int reqWidth, int reqHeight) {
//
//		// First decode with inJustDecodeBounds=true to check dimensions
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(filePath, options);
//
//		// Calculate inSampleSize
//		options.inSampleSize = calculateInSampleSize(options, reqWidth,
//				reqHeight);
//		options.inScaled = false;
//		// Decode bitmap with inSampleSize set
//		options.inJustDecodeBounds = false;
//		return BitmapFactory.decodeFile(filePath, options);
//	}
//
//	@SuppressWarnings("deprecation")
//	public static Bitmap decodeScaledBitmapFromSdCardAdapter(String filePath) {
//
//		// First decode with inJustDecodeBounds=true to check dimensions
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeFile(filePath, options);
//
//		// Calculate inSampleSize
//		options.inSampleSize = calculateInSampleSize(options, 150, 150);
//		options.inScaled = false;
//		// Decode bitmap with inSampleSize set
//		options.inJustDecodeBounds = false;
//		return BitmapFactory.decodeFile(filePath, options);
//	}
//
//	public static int calculateInSampleSize(BitmapFactory.Options options,
//			int reqWidth, int reqHeight) {
//		// Raw height and width of image
//		final int height = options.outHeight;
//		final int width = options.outWidth;
//		int inSampleSize = 1;
//
//		if (height > reqHeight || width > reqWidth) {
//
//			final int halfHeight = height / 2;
//			final int halfWidth = width / 2;
//
//			// Calculate the largest inSampleSize value that is a power of 2 and
//			// keeps both
//			// height and width larger than the requested height and width.
//			while ((halfHeight / inSampleSize) > reqHeight
//					&& (halfWidth / inSampleSize) > reqWidth) {
//				inSampleSize *= 2;
//			}
//		}
//
//		return inSampleSize;
//	}
//
//	public static Bitmap ScaleBitmap(Bitmap bm, float scalingFactor) {
//		int scaleHeight = (int) (bm.getHeight() * scalingFactor);
//		int scaleWidth = (int) (bm.getWidth() * scalingFactor);
//		return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, true);
//	}
//
//	public static Bitmap ScaleBitmapWithSize(Bitmap bm) {
//		int scalingFactor = calculateInScaleFactor(bm, 150, 150);
//		int scaleHeight = (int) (bm.getHeight() / scalingFactor);
//		int scaleWidth = (int) (bm.getWidth() / scalingFactor);
//
//		return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, true);
//	}
//
//	public static Bitmap ScaleBitmapWithSize(Bitmap bm, int reqWidth,
//			int reqHeight) {
//		int scalingFactor = calculateInScaleFactor(bm, reqWidth, reqHeight);
//		int scaleHeight = (int) (bm.getHeight() / scalingFactor);
//		int scaleWidth = (int) (bm.getWidth() / scalingFactor);
//
//		return Bitmap.createScaledBitmap(bm, scaleWidth, scaleHeight, true);
//	}
//
//	public static int calculateInScaleFactor(Bitmap bm, int reqWidth,
//			int reqHeight) {
//		// Raw height and width of image
//		final int height = bm.getHeight();
//		final int width = bm.getWidth();
//		int inSampleSize = 1;
//
//		if (height > reqHeight || width > reqWidth) {
//			// Calculate the largest inSampleSize value that is a power of 2 and
//			// keeps both
//			// height and width larger than the requested height and width.
//			while ((height / inSampleSize) > reqHeight
//					&& (width / inSampleSize) > reqWidth) {
//				inSampleSize *= 2;
//			}
//		}
//
//		return inSampleSize;
//	}
//
//	public static Bitmap getBitmapFromView(View view) {
//		// Define a bitmap with the same size as the view
//		Bitmap mBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
//				Config.ARGB_8888);
//		Canvas canvas = new Canvas(mBitmap);
//		// Get the view's background
//		Drawable bgDrawable = view.getBackground();
//		if (bgDrawable != null)
//			bgDrawable.draw(canvas);
//		else
//			canvas.drawColor(Color.WHITE);
//		view.draw(canvas);
//		return mBitmap;
//	}
//
//	public static String getStringDownloadCompleted(int type, Context mcontext) {
//		String s = "";
//		switch (type) {
//		case 0:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_stamp);
//			break;
//		case 1:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_TextStamp);
//			break;
//		case 2:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_Frame);
//			break;
//		case 3:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_CropShape);
//			break;
//		case 4:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_background);
//			break;
//		case 5:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_effect);
//			break;
//		case 6:
//			s = getStringFromResource(mcontext,
//					R.string.notice_download_completed_Font);
//			break;
//
//		default:
//			break;
//		}
//		return s;
//	}
//
//	public static Item_product getProInfoFromService(String json) {
//		Item_product item = new Item_product();
//		JSONObject _obj;
//		try {
//			_obj = new JSONObject(json);
//			if (_obj != null) {
//
//				item.setProduct_id(_obj.getString("product_id"));
//				item.setProduct_explain(_obj.getString("product_explain"));
//				item.setProduct_data(_obj.getString("product_data"));
//				item.setProduct_aboutsize(_obj.getString("product_aboutsize"));
//				item.setProduct_size(_obj.getString("product_size"));
//				item.setProduct_version(_obj.getString("product_version"));
//				ArrayList<String> list_sample = new ArrayList<String>();
//				ArrayList<String> list_thumbar = new ArrayList<String>();
//				JSONArray _arr_sample = _obj.getJSONArray("product_sample");
//
//				if (_arr_sample != null) {
//					int n = _arr_sample.length();
//					for (int i = 0; i < n; i++) {
//						list_sample.add(_arr_sample.getString(i));
//					}
//				}
//				item.setProduct_sample(list_sample);
//				JSONArray _arr_thumbar = _obj.getJSONArray("product_thumbnail");
//				if (_arr_thumbar != null) {
//					int m = _arr_thumbar.length();
//					for (int j = 0; j < m; j++) {
//						list_thumbar.add(_arr_thumbar.getString(j));
//					}
//				}
//				item.setProduct_thumbnail(list_thumbar);
//
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			// Toast.makeText(getApplicationContext(), "heheh", 1000).show();
//		}
//
//		return item;
//	}
//
//	@SuppressLint("SimpleDateFormat")
//	public static String getDateTime() {
//		String time = "";
//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//		Date date = new Date();
//		time = format.format(date);
//		// Log.e("NOW", "" + time);
//		return time;
//	}
//
//	@SuppressLint("SimpleDateFormat")
//	public static String getDateTimeFormat() {
//		String time = "";
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		Date date = new Date();
//		time = format.format(date);
//		// Log.e("NOW1", "" + time);
//		return time;
//	}
//
//	@SuppressLint("SimpleDateFormat")
//	public static String getDateTimeFormat1() {
//		String time = "";
//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		time = format.format(date);
//		// Log.e("NOW1", "" + time);
//		return time;
//	}
//
//	public static String storeImage(Context context, Bitmap image,
//			String imgname) {
//		String uri = "";
//		File pictureFile = getOutputMediaFile(context, imgname);
//		if (pictureFile == null) {
//			Log.d("error",
//					"Error creating media file, check storage permissions: ");// e.getMessage());
//			return uri;
//		}
//		uri = pictureFile.getAbsolutePath();
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.PNG, 100, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d("error", "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d("error", "Error accessing file: " + e.getMessage());
//		}
//		return uri;
//	}
//
//	public static String storeImage_Social(Context context, Bitmap image,
//			String imgname) {
//		String uri = "";
//		File pictureFile = getOutputMediaFile_Social(context, imgname);
//		if (pictureFile == null) {
//			Log.d("error",
//					"Error creating media file, check storage permissions: ");// e.getMessage());
//			return uri;
//		}
//		uri = pictureFile.getAbsolutePath();
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d("error", "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d("error", "Error accessing file: " + e.getMessage());
//		}
//		return uri;
//	}
//
//	public static String storeImage_External(Context context, Bitmap image,
//			String imgname) {
//		String uri = "";
//		File pictureFile = getOutputMediaFile_External(context, imgname);
//		if (pictureFile == null) {
//			Log.d("error",
//					"Error creating media file, check storage permissions: ");// e.getMessage());
//			return uri;
//		}
//		uri = pictureFile.getAbsolutePath();
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.PNG, 100, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d("error", "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d("error", "Error accessing file: " + e.getMessage());
//		}
//		return uri;
//	}
//
//	public static String storeImage_OnlyShow(Context context, Bitmap image,
//			String imgname) {
//		String uri = "";
//		File pictureFile = getOutputMediaFile_External(context, imgname);
//		if (pictureFile == null) {
//			Log.d("error",
//					"Error creating media file, check storage permissions: ");// e.getMessage());
//			return uri;
//		}
//		uri = pictureFile.getAbsolutePath();
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.PNG, 70, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d("error", "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d("error", "Error accessing file: " + e.getMessage());
//		}
//		return uri;
//	}
//
//	public static String storeImageCamera(Context context, Bitmap image,
//			String imgname) {
//		String uri = "";
//		File pictureFile = getOutputMediaFileCamera(context, imgname);
//		if (pictureFile == null) {
//			Log.d("error",
//					"Error creating media file, check storage permissions: ");// e.getMessage());
//			return uri;
//		}
//		uri = pictureFile.getAbsolutePath();
//		try {
//			FileOutputStream fos = new FileOutputStream(pictureFile);
//			image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Log.d("error", "File not found: " + e.getMessage());
//		} catch (IOException e) {
//			Log.d("error", "Error accessing file: " + e.getMessage());
//		}
//		return uri;
//	}
//
//	*/
/** Create a File for saving an image or video *//*

//	public static File getOutputMediaFile(Context context, String imgname) {
//		// To be safe, you should check that the SDCard is mounted
//		// using Environment.getExternalStorageState() before doing this.
//		// File mediaStorageDir = new File(
//		// Environment.getExternalStorageDirectory() + "/Android/data/"
//		// + context.getPackageName() + "/Files");
//		File mediaStorageDir = new File(context.getFilesDir()
//				+ "/Android/data/" + context.getPackageName() + "/Files");
//
//		// This location works best if you want the created images to be shared
//		// between applications and persist after your app has been uninstalled.
//
//		// Create the storage directory if it does not exist
//		if (!mediaStorageDir.exists()) {
//			if (!mediaStorageDir.mkdirs()) {
//				return null;
//			}
//		}
//		// // Create a media file name
//		// String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm")
//		// .format(new Date());
//		File mediaFile;
//		String mImageName = imgname + ".png";
//		mediaFile = new File(mediaStorageDir.getPath() + File.separator
//				+ mImageName);
//		return mediaFile;
//	}
//
//	*/
/** Create a File for saving an image or video *//*

//	public static File getOutputMediaFileCamera(Context context, String imgname) {
//		// To be safe, you should check that the SDCard is mounted
//		// using Environment.getExternalStorageState() before doing this.
//		// File mediaStorageDir = new File(
//		// Environment.getExternalStorageDirectory() + "/Android/data/"
//		// + context.getPackageName() + "/Files");
//		File mediaStorageDir = new File(context.getFilesDir()
//				+ "/Android/data/" + context.getPackageName() + "/Files");
//
//		// This location works best if you want the created images to be shared
//		// between applications and persist after your app has been uninstalled.
//
//		// Create the storage directory if it does not exist
//		if (!mediaStorageDir.exists()) {
//			if (!mediaStorageDir.mkdirs()) {
//				return null;
//			}
//		}
//		// // Create a media file name
//		// String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm")
//		// .format(new Date());
//		File mediaFile;
//		String mImageName = imgname + ".jpg";
//		mediaFile = new File(mediaStorageDir.getPath() + File.separator
//				+ mImageName);
//		return mediaFile;
//	}
//
//	private static File getOutputMediaFile_External(Context context,
//			String imgname) {
//		// To be safe, you should check that the SDCard is mounted
//		// using Environment.getExternalStorageState() before doing this.
//		File mediaStorageDir = new File(
//				Environment.getExternalStorageDirectory() + "/Android/data/"
//						+ context.getPackageName() + "/Files");
//
//		// This location works best if you want the created images to be shared
//		// between applications and persist after your app has been uninstalled.
//
//		// Create the storage directory if it does not exist
//		if (!mediaStorageDir.exists()) {
//			if (!mediaStorageDir.mkdirs()) {
//				return null;
//			}
//		}
//		// // Create a media file name
//		// String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm")
//		// .format(new Date());
//		File mediaFile;
//		String mImageName = imgname + ".png";
//		mediaFile = new File(mediaStorageDir.getPath() + File.separator
//				+ mImageName);
//		return mediaFile;
//	}
//
//	private static File getOutputMediaFile_Social(Context context,
//			String imgname) {
//		// To be safe, you should check that the SDCard is mounted
//		// using Environment.getExternalStorageState() before doing this.
//		File mediaStorageDir = new File(
//				Environment.getExternalStorageDirectory() + "/Android/data/"
//						+ context.getPackageName() + "/Files");
//
//		// This location works best if you want the created images to be shared
//		// between applications and persist after your app has been uninstalled.
//
//		// Create the storage directory if it does not exist
//		if (!mediaStorageDir.exists()) {
//			if (!mediaStorageDir.mkdirs()) {
//				return null;
//			}
//		}
//		// // Create a media file name
//		// String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm")
//		// .format(new Date());
//		File mediaFile;
//		String mImageName = imgname + ".jpg";
//		mediaFile = new File(mediaStorageDir.getPath() + File.separator
//				+ mImageName);
//		return mediaFile;
//	}
//
//	public static Bitmap textAsBitmap(Item_text item, int width, int height,
//			float scale) {
//
//		Bitmap image = Bitmap.createBitmap(width, height,
//				Config.ARGB_8888);
//		image.eraseColor(item.getBg_color());
//
//		Bitmap image_bg = Bitmap.createBitmap(width, height,
//				Config.ARGB_8888);
//
//		Canvas canvas = new Canvas(image_bg);
//
//		Paint paint = new Paint();
//		paint.setFilterBitmap(true);
//		paint.setAntiAlias(true);
//		paint.setAlpha(item.getAlpha());
//
//		canvas.drawBitmap(image, new Matrix(), paint);
//
//		drawMultiLineEllipsizedText(canvas, 0, image_bg.getHeight() / 2,
//				image_bg.getWidth(), image_bg.getHeight(), item, scale);
//		image_bg = Util.getRoundedCornerBitmap(image_bg, item.getCorner());
//		return image_bg;
//
//	}
//
//	public static void drawMultiLineEllipsizedText(Canvas _canvas, float _left,
//			float _top, float _right, float _bottom, Item_text item, float scale) {
//		if (!item.getText().equals("")) {
//			item.setPreview(item.getText());
//		} else {
//			item.setPreview("Tap to enter text");
//		}
//
//		float height = _bottom - _top;
//
//		TextPaint textPaint = new TextPaint();
//		textPaint.setStyle(Style.FILL);
//		textPaint.setColor(item.getColor());
//		textPaint.setAntiAlias(true);
//		textPaint.setTextSize((int) (item.getTextsize() * scale) + 1);
//
//		textPaint.setAntiAlias(true);
//
//		if (!item.getFont().equals("")) {
//			File f = new File(item.getFont());
//			if (f.isDirectory() == true) {
//				File[] files = new File(item.getFont()).listFiles();
//
//				Typeface face = Typeface.createFromFile(files[0]);
//				textPaint.setTypeface(face);
//			} else {
//				Typeface face = Typeface.createFromFile(f);
//				textPaint.setTypeface(face);
//			}
//		} else {
//			// textPaint.setTypeface(Typeface.create(Typeface.DEFAULT,
//			// Typeface.BOLD));
//		}
//
//		StaticLayout measuringTextLayout = new StaticLayout(item.getPreview(),
//				textPaint, (int) Math.abs(_right - _left - item.getMargin()),
//				Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
//
//		int line = 0;
//
//		int totalLineCount = measuringTextLayout.getLineCount();
//
//		for (line = 0; line < totalLineCount; line++) {
//			int lineBottom = measuringTextLayout.getLineBottom(line);
//
//			if (lineBottom > height * 2) {
//				break;
//			}
//
//		}
//
//		line--;
//
//		if (line < 0) {
//			return;
//		}
//
//		int lineEnd;
//		try {
//			lineEnd = measuringTextLayout.getLineEnd(line);
//		} catch (Throwable t) {
//			lineEnd = item.getPreview().length();
//		}
//		String truncatedText = item.getPreview().substring(0,
//				Math.max(0, lineEnd));
//
//		if (truncatedText.length() < 3) {
//			return;
//		}
//
//		if (truncatedText.length() < item.getPreview().length()) {
//			truncatedText = truncatedText.substring(0,
//					Math.max(0, truncatedText.length() - 3));
//			truncatedText += "...";
//		}
//		Alignment alignment = Alignment.ALIGN_NORMAL;
//		switch (item.getAlign()) {
//		case 0:
//			alignment = Alignment.ALIGN_CENTER;
//			break;
//		case 1:
//			alignment = Alignment.ALIGN_NORMAL;
//			break;
//		case 2:
//			alignment = Alignment.ALIGN_OPPOSITE;
//			break;
//		default:
//			break;
//		}
//
//		StaticLayout drawingTextLayout = new StaticLayout(truncatedText,
//				textPaint, (int) Math.abs(_right - _left - item.getMargin()),
//				alignment, 1.0f, 0.0f, false);
//
//		switch (item.getAlign()) {
//		case 0:
//			_canvas.translate(_left, _top - drawingTextLayout.getHeight() / 2);
//			break;
//		case 1:
//			// trai
//			_canvas.translate(_left + item.getMargin(), _top
//					- drawingTextLayout.getHeight() / 2);
//			break;
//		case 2:
//			// _canvas.translate((_left - item.getMargin() > points[2].x) ?
//			// _left
//			// - item.getMargin() : _left,
//			// _top - drawingTextLayout.getHeight() / 2);
//
//			break;
//		default:
//			break;
//		}
//		_canvas.save();
//		drawingTextLayout.draw(_canvas);
//		_canvas.restore();
//
//	}
//
//	public static Bitmap CutBitmapWidthMask(Bitmap mBitmap, Bitmap mBitmap_mask) {
//		Bitmap bm_result = Bitmap.createBitmap(mBitmap_mask.getWidth(),
//				mBitmap_mask.getHeight(), Config.ARGB_8888);
//		Canvas tempCanvas = new Canvas(bm_result);
//		Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		paint.setDither(true);
//		paint.setStrokeCap(Paint.Cap.ROUND);
//		paint.setStrokeJoin(Paint.Join.ROUND);
//		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
//		tempCanvas.drawBitmap(mBitmap, 0, 0, null);
//		tempCanvas.drawBitmap(mBitmap_mask, 0, 0, paint);
//		paint.setXfermode(null);
//		return bm_result;
//	}
//
//	public static Bitmap ContraBitmapWidthMask(Bitmap mBitmap,
//			Bitmap mBitmap_mask) {
//		Bitmap bm_result = Bitmap.createBitmap(mBitmap_mask.getWidth(),
//				mBitmap_mask.getHeight(), Config.ARGB_8888);
//		Canvas tempCanvas = new Canvas(bm_result);
//		Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		paint.setDither(true);
//		paint.setStrokeCap(Paint.Cap.ROUND);
//		paint.setStrokeJoin(Paint.Join.ROUND);
//		paint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
//		tempCanvas.drawBitmap(mBitmap, 0, 0, null);
//		tempCanvas.drawBitmap(mBitmap_mask, 0, 0, paint);
//		paint.setXfermode(null);
//		return bm_result;
//
//	}
//
//	*/
/**
//	 * Converts a immutable bitmap to a mutable bitmap. This operation doesn't
//	 * allocates more memory that there is already allocated.
//	 *
//	 * @param imgIn
//	 *            - Source image. It will be released, and should not be used
//	 *            more
//	 * @return a copy of imgIn, but muttable.
//	 *//*

//	public static Bitmap convertToMutable(Bitmap imgIn) {
//		if (imgIn == null) {
//			return null;
//		}
//		try {
//			// this is the file going to use temporally to save the bytes.
//			// This file will not be a image, it will store the raw image data.
//			File file = new File(Environment.getExternalStorageDirectory()
//					+ File.separator + "temp.tmp");
//
//			// Open an RandomAccessFile
//			// Make sure you have added uses-permission
//			// android:name="android.permission.WRITE_EXTERNAL_STORAGE"
//			// into AndroidManifest.xml file
//			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//
//			// get the width and height of the source bitmap.
//			int width = imgIn.getWidth();
//			int height = imgIn.getHeight();
//			Config type = Config.ARGB_8888;
//
//			// Copy the byte to the file
//			// Assume source bitmap loaded using options.inPreferredConfig =
//			// Config.ARGB_8888;
//			FileChannel channel = randomAccessFile.getChannel();
//			MappedByteBuffer map = channel.map(MapMode.READ_WRITE, 0,
//					imgIn.getRowBytes() * height);
//			imgIn.copyPixelsToBuffer(map);
//			// recycle the source bitmap, this will be no longer used.
//			imgIn.recycle();
//			System.gc();// try to force the bytes from the imgIn to be released
//
//			// Create a new bitmap to load the bitmap again. Probably the memory
//			// will be available.
//			imgIn = Bitmap.createBitmap(width, height, type);
//			map.position(0);
//			// load it back from temporary
//			imgIn.copyPixelsFromBuffer(map);
//			// close the temporary file and channel , then delete that also
//			channel.close();
//			randomAccessFile.close();
//
//			// delete the temp file
//			file.delete();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return imgIn;
//	}
//
//	public static Bitmap getBitmapFromAsset(Context context, String filePath) {
//		AssetManager assetManager = context.getAssets();
//
//		InputStream istr;
//		Bitmap bitmap = null;
//		try {
//			istr = assetManager.open(filePath);
//			bitmap = BitmapFactory.decodeStream(istr);
//		} catch (IOException e) {
//			// handle exception
//			Log.e("eMess: ", " " + e.getMessage());
//		}
//		return bitmap;
//	}
//
//	public static Bitmap getBitmapFromAssetAdapter(Context context,
//			String filePath) {
//		AssetManager assetManager = context.getAssets();
//
//		InputStream istr;
//		Bitmap bitmap = null;
//		try {
//			istr = assetManager.open(filePath);
//			bitmap = BitmapFactory.decodeStream(istr);
//		} catch (IOException e) {
//			// handle exception
//			Log.e("eMess: ", " " + e.getMessage());
//		}
//		return ScaleBitmapWithSize(bitmap);
//	}
//
//	public static float getScalefromMatrix(Matrix mMatrix) {
//		float[] matrix = new float[9];
//		mMatrix.getValues(matrix);
//		float scale_x = matrix[Matrix.MSCALE_X];
//		float scale_y = matrix[Matrix.MSKEW_Y];
//		return (float) Math.sqrt(scale_x * scale_x + scale_y * scale_y);
//	}
//
//	public static float getDXfromMatrix(Matrix mMatrix) {
//		float[] matrix = new float[9];
//		mMatrix.getValues(matrix);
//		return matrix[Matrix.MTRANS_X];
//	}
//
//	public static float getDYfromMatrix(Matrix mMatrix) {
//		float[] matrix = new float[9];
//		mMatrix.getValues(matrix);
//		return matrix[Matrix.MTRANS_Y];
//	}
//
//	public static float getAnglefromMatrix(Matrix mMatrix) {
//		float[] matrix = new float[9];
//		mMatrix.getValues(matrix);
//		return (float) (-1.0f)
//				* Math.round((float) Math.atan2((float) matrix[Matrix.MSKEW_X],
//						(float) matrix[Matrix.MSCALE_X]) * (180.0f / Math.PI));
//	}
//
//	public static Bitmap getPreviewBitmap(Bitmap bm_root, int type_root,
//			int type_out) {
//		int[] maxSize = new int[1];
//		GLES10.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxSize, 0);
//		Log.e("MAX", "" + maxSize[0]);
//		Bitmap bm_scale = null, bm_result = null;
//		int x = 0, y = 0;
//		float scale_ = bm_root.getWidth() / (float) bm_root.getHeight();
//		// dinh dang 1 rect vertical
//		int height_output_type1 = 2800;
//		int width_output_type1 = (int) (2800.0f / ((Util.heigh_screen * 3 / 4.0f) / (float) (Util.width_screen)));
//		// dinh dang 2 rect horizantal
//		int height_output_type2 = 2000;
//		int width_output_type2 = (int) (2000 * (Util.width_screen / (Util.width_screen * 3 / 4.0f)));
//		// dinh dang 3 rect square
//		int width_output_type3 = 2000;
//		int height_output_type3 = 2000;
//		// dinh dang 4 phone
//		int height_output_type4 = 2000;
//		float scale_screen = Util.width_screen / (float) Util.heigh_screen;
//		int width_output_type4 = (int) (2000 * scale_screen);
//
//		int width_output_type5, height_output_type5;
//		if (scale_ <= 1) {
//			height_output_type5 = 2000;
//			width_output_type5 = (int) (height_output_type5 * scale_);
//		} else {
//			width_output_type5 = 2000;
//			height_output_type5 = (int) (width_output_type5 / scale_);
//		}
//		int width_temp, height_temp;
//		switch (type_out) {
//		case 1:
//			switch (type_root) {
//			case 1:
//				bm_result = Bitmap.createScaledBitmap(bm_root,
//						width_output_type1, height_output_type1, false);
//				break;
//			case 2:
//				height_temp = height_output_type1;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type1) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type1, height_output_type1);
//				break;
//			case 3:
//				height_temp = height_output_type1;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type1) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type1, height_output_type1);
//				break;
//			case 4:
//				width_temp = width_output_type1;
//				height_temp = (int) (width_temp * (bm_root.getHeight() / (float) bm_root
//						.getWidth()));
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type1) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type1, height_output_type1);
//				break;
//			case 5:
//				if (scale_ >= 1) {
//					height_temp = height_output_type1;
//					width_temp = (int) (height_temp * bm_root.getWidth() / (float) bm_root
//							.getHeight());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = (int) ((width_temp - width_output_type1) / 2.0f);
//					y = 0;
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type1, height_output_type1);
//				} else {
//					width_temp = width_output_type1;
//					height_temp = (int) (width_output_type1
//							/ (float) bm_root.getWidth() * bm_root.getHeight());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = 0;
//					y = (int) ((height_temp - height_output_type1) / 2.0f);
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type1, height_output_type1);
//				}
//				break;
//			default:
//				break;
//			}
//			break;
//		case 2:
//			switch (type_root) {
//			case 1:
//				width_temp = width_output_type2;
//				height_temp = (int) (width_temp * bm_root.getHeight() / (float) bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type2) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type2, height_output_type2);
//				break;
//			case 2:
//				bm_result = Bitmap.createScaledBitmap(bm_root,
//						width_output_type2, height_output_type2, false);
//				break;
//			case 3:
//				width_temp = width_output_type2;
//				height_temp = (int) (width_temp / (float) bm_root.getWidth() * bm_root
//						.getHeight());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type2) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type2, height_output_type2);
//				break;
//			case 4:
//				width_temp = width_output_type2;
//				height_temp = (int) (width_temp / (float) bm_root.getWidth() * bm_root
//						.getHeight());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type2) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type2, height_output_type2);
//				break;
//			case 5:
//				if (scale_ <= 1) {
//					width_temp = width_output_type2;
//					height_temp = (int) (width_temp
//							/ (float) bm_root.getWidth() * bm_root.getHeight());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = 0;
//					y = (int) ((height_temp - height_output_type2) / 2.0f);
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type2, height_output_type2);
//				} else {
//					height_temp = height_output_type2;
//					width_temp = (int) (height_temp
//							/ (float) bm_root.getHeight() * bm_root.getWidth());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = (int) ((width_temp - width_output_type2) / 2.0f);
//					y = 0;
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type2, height_output_type2);
//				}
//				break;
//			default:
//				break;
//			}
//			break;
//		case 3:
//			switch (type_root) {
//			case 1:
//				width_temp = width_output_type3;
//				height_temp = (int) (width_temp / (float) bm_root.getWidth() * bm_root
//						.getHeight());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type3) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type3, height_output_type3);
//				break;
//			case 2:
//				height_temp = height_output_type3;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type3) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type3, height_output_type3);
//				break;
//			case 3:
//				bm_result = Bitmap.createScaledBitmap(bm_root,
//						width_output_type3, height_output_type3, false);
//				break;
//			case 4:
//				width_temp = width_output_type3;
//				height_temp = (int) (width_temp / (float) bm_root.getWidth() * bm_root
//						.getHeight());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type3) / 2.0f);
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type3, height_output_type3);
//				break;
//			case 5:
//				if (scale_ <= 1) {
//					width_temp = width_output_type3;
//					height_temp = (int) (width_temp
//							/ (float) bm_root.getWidth() * bm_root.getHeight());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = 0;
//					y = (int) ((height_temp - height_output_type3) / 2.0f);
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type3, height_output_type3);
//				} else {
//					height_temp = height_output_type3;
//					width_temp = (int) (height_temp
//							/ (float) bm_root.getHeight() * bm_root.getWidth());
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//					x = (int) ((width_temp - width_output_type3) / 2.0f);
//					y = 0;
//					bm_result = Bitmap.createBitmap(bm_scale, x, y,
//							width_output_type3, height_output_type3);
//				}
//				break;
//			default:
//				break;
//			}
//			break;
//		case 4:
//			switch (type_root) {
//			case 1:
//				height_temp = height_output_type4;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type4) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type4, height_output_type4);
//				break;
//			case 2:
//				height_temp = height_output_type4;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type4) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type4, height_output_type4);
//				break;
//			case 3:
//				height_temp = height_output_type4;
//				width_temp = (int) (height_temp / (float) bm_root.getHeight() * bm_root
//						.getWidth());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = (int) ((width_temp - width_output_type4) / 2.0f);
//				y = 0;
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type4, height_output_type4);
//				break;
//			case 4:
//				bm_result = Bitmap.createScaledBitmap(bm_root,
//						width_output_type4, height_output_type4, false);
//				break;
//			case 5:
//				width_temp = width_output_type4;
//				height_temp = (int) (width_temp / (float) bm_root.getWidth() * bm_root
//						.getHeight());
//				bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//						height_temp, false);
//				x = 0;
//				y = (int) ((height_temp - height_output_type4) / 2.0f);
//				if (y < 0) {
//					height_temp = height_output_type4;
//					width_temp = (int) (height_temp
//							/ (float) bm_root.getHeight() * bm_root.getWidth());
//					x = (int) ((width_temp - width_output_type4) / 2.0f);
//					y = 0;
//					bm_scale = Bitmap.createScaledBitmap(bm_root, width_temp,
//							height_temp, false);
//				}
//				bm_result = Bitmap.createBitmap(bm_scale, x, y,
//						width_output_type4, height_output_type4);
//				break;
//			default:
//				break;
//			}
//			break;
//		case 5:
//			bm_result = Bitmap.createScaledBitmap(bm_root, width_output_type5,
//					height_output_type5, false);
//			break;
//		default:
//			break;
//		}
//		if (bm_scale != null) {
//			bm_scale.recycle();
//		}
//		return bm_result;
//	}
//
//	public static Drawable UriToDrawable(String file_path, Context context) {
//		Drawable drawable = null;
//		try {
//			Uri uri = Uri.fromFile((new File(file_path)));
//			InputStream inputStream = context.getContentResolver()
//					.openInputStream(uri);
//			drawable = Drawable.createFromStream(inputStream, uri.toString());
//		} catch (FileNotFoundException e) {
//
//		}
//		return drawable;
//	}
//
//	*/
/**
//	 * @author Flight
//	 * @param mContext
//	 * @param input_value
//	 * @param key
//	 *//*

//
//	public static void SaveSharedPreferences_Int(Context mContext,
//			int input_value, String key) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", Context.MODE_PRIVATE);
//		Editor editor = sharedPreferences.edit();
//		editor.putInt(key, input_value).commit();
//	}
//
//	*/
/**
//	 * @param mContext
//	 * @param key
//	 * @return value save in share preferences
//	 *//*

//	public static int GetSharedPreferences_Int(Context mContext, String key) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", Context.MODE_PRIVATE);
//		return sharedPreferences.getInt(key, 0);
//	}
//
//	*/
/**
//	 * @author Flight
//	 * @param mContext
//	 * @param input_value
//	 * @param key
//	 *//*

//	@SuppressLint("CommitPrefEdits")
//	public static void SaveSharedPreferences_String(Context mContext,
//			String input_value, String key) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		sharedPreferences.edit().putString(key, input_value).commit();
//	}
//
//	*/
/**
//	 * @author Flight
//	 * @param mContext
//	 * @param key
//	 * @return string value save in sharepreferences
//	 *//*

//
//	public static String GetSharedPreferences_String(Context mContext,
//			String key) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		return sharedPreferences.getString(key, "");
//	}
//	public static boolean getPostPre(Context mContext) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		return sharedPreferences.getBoolean("isInstroduce", false);
//	}
//	public static void setPostPre(Context mContext, boolean value) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		sharedPreferences.edit().putBoolean("isInstroduce", value)
//				.commit();
//	}
//
//	public static void SaveSharedPreferences_isBackground(Context mContext,
//			Boolean input_value) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		sharedPreferences.edit().putBoolean("IS_BACKGROUND", input_value)
//				.commit();
//	}
//
//	public static boolean GetSharedPreferences_isBackground(Context mContext) {
//		SharedPreferences sharedPreferences = mContext.getSharedPreferences(
//				"MyPrefs", mContext.MODE_PRIVATE);
//		return sharedPreferences.getBoolean("IS_BACKGROUND", false);
//	}
//
//	public static File getWritableFolder(Context context) {
//		File folder = context.getFilesDir();
//		if (externalStorageAvailable()) {
//			try {
//				folder = Environment
//						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//				if (!folder.exists() || !folder.canWrite()) {
//					folder = Environment
//							.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//				}
//				if (!folder.exists() || !folder.canWrite()) {
//					folder = Environment.getExternalStorageDirectory();
//				}
//			} catch (Exception e) {
//				folder = Environment.getExternalStorageDirectory();
//			} catch (Error e) {
//				folder = Environment.getExternalStorageDirectory();
//			}
//			if (!folder.exists() || !folder.canWrite()) {
//				folder = context.getFilesDir();
//			}
//		}
//		Log.e("Save folder: ", " " + folder);
//		return folder;
//	}
//
//	private static boolean externalStorageAvailable() {
//		boolean mExternalStorageAvailable;
//		boolean mExternalStorageWriteable;
//		String state = Environment.getExternalStorageState();
//
//		if (state.equals(Environment.MEDIA_MOUNTED)) {
//			// We can read and write the media
//			mExternalStorageAvailable = mExternalStorageWriteable = true;
//		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
//			// We can only read the media
//			mExternalStorageAvailable = true;
//			mExternalStorageWriteable = false;
//		} else {
//			// Something else is wrong. It may be one of many other states, but
//			// all we need
//			// to know is we can neither read nor write
//			mExternalStorageAvailable = mExternalStorageWriteable = false;
//		}
//		return mExternalStorageAvailable && mExternalStorageWriteable;
//	}
//}
*/
