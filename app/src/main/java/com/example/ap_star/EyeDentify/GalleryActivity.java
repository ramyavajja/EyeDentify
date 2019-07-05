package com.example.ap_star.EyeDentify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {

    private static final int PICK_IMAGE=100;
    public static final int REQUEST_PERMISSION = 200;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION );
        }else {

            openGallery();
        }
    }

    private void openGallery(){

        Intent int2=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(int2,PICK_IMAGE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
                Toast.makeText(this, "Thanks for granting Permission", Toast.LENGTH_SHORT).show();
            }else{
                this.finish();
                Toast.makeText(this, "Permission needed to access the Gallery!!", Toast.LENGTH_SHORT).show();

            }
        }
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

        } else if (resultCode == RESULT_CANCELED) {
            //sendToPrevActivity();
            this.finish();
            Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
        }
    }


}
