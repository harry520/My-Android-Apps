package com.example.harryjiang.runtimepermission;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AbsRuntimePermission {

    private static final int REQUEST_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_CONTACTS}, R.string.msg, REQUEST_PERMISSION);
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        // Do anything when permissions are granted.
        Toast.makeText(getApplicationContext(), "Permissions are granted.", Toast.LENGTH_LONG).show();
    }
}
