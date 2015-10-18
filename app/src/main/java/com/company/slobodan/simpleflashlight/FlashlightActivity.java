package com.company.slobodan.simpleflashlight;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class FlashlightActivity extends AppCompatActivity {

    public static Camera camera = null;
    private boolean FLASHLIGHT_ON = false;
    private Camera.Parameters param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        ImageButton startBtn = (ImageButton) findViewById(R.id.imageButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flashlight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null)
            camera.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        int d = Log.d("OnPause", "OnPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume", "OnResume");
    }

    public void onClickStartBtn(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(FlashlightActivity.this, "Device do not have flash", Toast.LENGTH_LONG).show();
            return;
        }
        if (FLASHLIGHT_ON) {
            stopFlash();
            FLASHLIGHT_ON = false;
        } else {
            startFlash();
            FLASHLIGHT_ON = true;
        }
    }

    private void startFlash() {
        if (camera == null)
            camera = Camera.open();
        if (camera.getParameters() == null)
            Log.d("camParameters", "camParameters eq null");
        param = camera.getParameters();
        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(param);
        Log.i("Flash on ", "Flash is on");
    }

    private void stopFlash() {
        if (camera != null) {
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
            Log.i("Flash off ", "Flash is off");
        }
    }
}
