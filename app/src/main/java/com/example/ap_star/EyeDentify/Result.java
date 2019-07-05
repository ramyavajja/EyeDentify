package com.example.ap_star.EyeDentify;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageHelper;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.Caption;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.VisionServiceClient;
import edmt.dev.edmtdevcognitivevision.VisionServiceRestClient;


public class Result extends AppCompatActivity {


    private final String API_Key = "8f05b5781187466b91abae49f7ef6b66";
    private final String API_Link = "https://eastus.api.cognitive.microsoft.com/vision/v2.0";

    //Declare VisionClient
    VisionServiceClient visionServiceClient = new VisionServiceRestClient(API_Key, API_Link);

    private Bitmap bitmap;
    private Uri myUri;
    private TextView description;
    String decription;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final Uri myUri = Uri.parse(getIntent().getStringExtra("imageUri"));
        description=(TextView)findViewById(R.id.txtDescription);
        final ImageView resultImage=(ImageView)findViewById(R.id.resultImage);
        Button detectButton=(Button)findViewById(R.id.btnProcess);


       //resultImage.setImageURI(myUri);

       try {
           bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), myUri);
           Picasso.with(getApplicationContext()).load(myUri).into(resultImage);
           //resultImage.setImageBitmap(bitmap);

       }catch(FileNotFoundException e){
           e.printStackTrace();
       }catch (IOException e){
           e.printStackTrace();
       }

        /*
        //---Associate this Uri value with a name
        Bundle extra=new Bundle();
        extra.putString("IMAGE_FILENAME",myUri.toString());
        */

        final ByteArrayOutputStream outputStream=new ByteArrayOutputStream();;
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


        //button click
        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {


                    //Check whether there is a internet connection
                    Boolean connection = isNetworkAvaliable(getApplicationContext());
                    if (connection == false)
                        Toast.makeText(Result.this, "You require Interent connection to use this service.!!", Toast.LENGTH_LONG).show();
                    else {

                        AsyncTask<InputStream, String, String> visionTask = new AsyncTask<InputStream, String, String>() {
                            ProgressDialog progressDialog = new ProgressDialog(Result.this);


                            @Override
                            protected void onPreExecute() {
                                progressDialog.show();
                            }

                            @Override
                            protected String doInBackground(InputStream... inputStreams) {

                                try {
                                    publishProgress("Recognizing");
                                    String[] features = {"Description"};
                                    String[] details = {};

                                    ByteArrayOutputStream output = new ByteArrayOutputStream();

                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                                    ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

                                    AnalysisResult visionResult = visionServiceClient.analyzeImage(inputStream, features, details);

                                    // AnalysisResult visionResult = visionServiceClient.analyzeImage(inputStream, features, details);

                                    String jsonResult = new Gson().toJson(visionResult);
                                    return jsonResult;

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (VisionServiceException e) {
                                    e.printStackTrace();
                                }

                                return null;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                progressDialog.dismiss();

                                AnalysisResult result = new Gson().fromJson(s, AnalysisResult.class);
                                StringBuilder result_Text = new StringBuilder();
                                for (Caption caption : result.description.captions)
                                    result_Text.append(caption.text);
                                if (result_Text.toString() != null) {
                                    description.setText(result_Text.toString());
                                    Toast.makeText(Result.this, result_Text.toString(), Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(Result.this, "Image or Object in your Image is not clear!!!", Toast.LENGTH_LONG).show();

                                }
                                //decription=result_Text.toString();
                            }

                            @Override
                            protected void onProgressUpdate(String... values) {
                                progressDialog.setMessage(values[0]);
                            }
                        };

                        visionTask.execute(inputStream);

                    }
                }

            }
        });

    }



    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }






}
