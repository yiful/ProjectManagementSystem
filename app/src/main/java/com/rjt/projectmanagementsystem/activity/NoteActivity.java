package com.rjt.projectmanagementsystem.activity;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import android.Manifest;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;

public class NoteActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST = 101;
    private static final int TakePhotoRequest = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabInAttachments);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                int permissionCheckStorage = ContextCompat.checkSelfPermission(NoteActivity.this,
                        Manifest.permission.CAMERA);
                if (permissionCheckStorage == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(NoteActivity.this, new String[]{
                            Manifest.permission.CAMERA},
                    //        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                     //       Manifest.permission.READ_EXTERNAL_STORAGE},
                            CAMERA_PERMISSION_REQUEST);
                }else{
                    Intent intent = new Intent(NoteActivity.this, CameraActivity.class);
                    startActivity(intent);
                    //startActivityForResult(intent,TakePhotoRequest);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "camera access granted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,TakePhotoRequest);
            }else{
                Toast.makeText(this, "camera access denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TakePhotoRequest){
            /*Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);*/
        }
    }
}
