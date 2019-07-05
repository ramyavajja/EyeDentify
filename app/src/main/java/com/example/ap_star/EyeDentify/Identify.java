package com.example.ap_star.EyeDentify;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Identify extends AppCompatActivity {

    private static final int PICK_IMAGE=100;
    Uri imageUri;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        Button bt_cam=(Button)findViewById(R.id.btn_camera);
        Button bt_gal=(Button)findViewById(R.id.btn_gallery);
        //imgView =(ImageView)findViewById(R.id.imageView2);

        bt_cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent int1=new Intent(Identify.this , CameraActivity.class);
                startActivity(int1);
            }
        });

        bt_gal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //Intent int2=new Intent(Identify.this ,Result.class);
               // startActivity(int2);
                Intent int2= new Intent(Identify.this,GalleryActivity.class);
                startActivity(int2);
               // openGallery();

            }


        });

    }

    /*
    private void openGallery(){

        Intent int2=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(int2,PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri=data.getData();
            //imgView.setImageURI(imageUri);

            Intent next =new Intent(this,Result.class);
            next.putExtra("imageUri",imageUri.toString());
            startActivity(next);
            this.finish();

        }
    }
    */

}
