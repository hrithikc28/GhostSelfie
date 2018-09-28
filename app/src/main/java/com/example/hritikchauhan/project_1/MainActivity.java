package com.example.hritikchauhan.project_1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Intent serviceIntent;
    URI imageUri;
    Button btn;
    Uri file;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String word_reverse = "";

        serviceIntent = new Intent(getApplicationContext(), MyService.class);
        startService(serviceIntent);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isCameraUsebyAnotherApp())
                {
                    Toast.makeText(MainActivity.this,"Camera is busy with another operation", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    takePicture();
                }

                /*
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT , Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
                */

                //startService(serviceIntent);

               /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        takePicture();
                    }
                }, 3000);*/

                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        while (true)

                        {
                            if (!isMyServiceRunning(MyService.class)) {
                                takePicture();
                                break;
                            }
                        }
                    }
                }, 100);*/



                /*EditText text = (EditText) findViewById(R.id.word_field);
                String word = text.getText().toString();

                // converting String to character array
                // by using toCharArray
                char[] word_1 = word.toCharArray();

                String word_reverse = "";

                for (int i = word_1.length - 1; i >= 0; i--) {
                    word_reverse += word_1[i];

                    Intent numbersIntent = new Intent(MainActivity.this, Reverse_Activity.class);
                    numbersIntent.putExtra("KEY_StringName", word_reverse);

                    // Start the new activity
                    startActivity(numbersIntent);

            }
            */

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btn.setEnabled(true);
            }
        }
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }*/
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    /*private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }*/

    public boolean isCameraUsebyAnotherApp() {
        Camera camerastatus = null;
        try {
            camerastatus = Camera.open();
        } catch (RuntimeException e) {
            return true;
        } finally {
            if (camerastatus != null) camerastatus.release();
        }
        return false;
    }

}
