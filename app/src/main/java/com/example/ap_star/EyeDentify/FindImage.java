package com.example.ap_star.EyeDentify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FindImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_image);
    }
}


            /*
                        try {
                            if (result_Text.toString() != "") {
                                AddImageTag(myUri,"TAG TEXT");
                            } else
                                Toast.makeText(Result.this, "Picture is not clear to detect objects!", Toast.LENGTH_LONG).show();

                        }catch(IOException e){
                            e.printStackTrace();
                        }


 private void AddImageTag(Uri imageUri,String decr) throws IOException{

        description=(TextView)findViewById(R.id.txtDescription);
        try {

           String path= getImageRealPath(getContentResolver(), imageUri, null);
            ExifInterface exif2 = new ExifInterface(path);
            //exif2.setAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION, "TAG SAMPLE");
           // exif2.saveAttributes();
            String tag = exif2.getAttribute(ExifInterface.TAG_DATETIME);
            //Toast.makeText(this, tag, Toast.LENGTH_LONG).show();
            description.setText(tag);
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    private String getImageRealPath(ContentResolver contentResolver, Uri uri, String whereClause)
    {
        String ret = "";

        // Query the uri with condition.
        Cursor cursor = contentResolver.query(uri, null, whereClause, null, null);

        if(cursor!=null)
        {
            boolean moveToFirst = cursor.moveToFirst();
            if(moveToFirst)
            {

                // Get columns name by uri type.
                String columnName = MediaStore.Images.Media.DATA;

                if( uri==MediaStore.Images.Media.EXTERNAL_CONTENT_URI )
                {
                    columnName = MediaStore.Images.Media.DATA;
                }else if( uri==MediaStore.Audio.Media.EXTERNAL_CONTENT_URI )
                {
                    columnName = MediaStore.Audio.Media.DATA;
                }else if( uri==MediaStore.Video.Media.EXTERNAL_CONTENT_URI )
                {
                    columnName = MediaStore.Video.Media.DATA;
                }

                // Get column index.
                int imageColumnIndex = cursor.getColumnIndex(columnName);

                // Get column value which is the uri related file local path.
                ret = cursor.getString(imageColumnIndex);
            }
        }
        return ret;
    }


 */