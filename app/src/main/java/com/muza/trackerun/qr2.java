package com.muza.trackerun;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class qr2 extends AppCompatActivity {

    SurfaceView cameraPreview2;
    TextView txtResult2;
    BarcodeDetector barcodeDetector2;
    CameraSource cameraSource2;
    final int RequestCameraPermissionID2 = 1001;
    Button btnProceed2;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID2: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource2.start(cameraPreview2.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr2);

        cameraPreview2 = (SurfaceView) findViewById(R.id.cameraPreview2);
        txtResult2 = (TextView) findViewById(R.id.txtResult2);
        btnProceed2 = (Button) findViewById(R.id.btnProceed2);

        barcodeDetector2 = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource2 = new CameraSource
                .Builder(this, barcodeDetector2)
                .setRequestedPreviewSize(640, 480)
                .build();

        //Add Event
        cameraPreview2.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(qr2.this,
                            new String[]{android.Manifest.permission.CAMERA},RequestCameraPermissionID2);
                    return;
                }
                try {
                    cameraSource2.start(cameraPreview2.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                cameraSource2.stop();
            }
        });

        barcodeDetector2.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() !=0)
                {
                    txtResult2.post(new Runnable() {
                        @Override
                        public void run() {
                            //Create vibrate
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            txtResult2.setText(qrcodes.valueAt(0).displayValue);
                            //button reenabled
                            if (txtResult2.getText().equals("Checkpoint 2 Validated"))
                            {
                                btnProceed2.setEnabled(true);
                            }
                        }
                    });
                }
            }
        });


    }

    public void tofinishline(View v)
    {
        Intent intent = new Intent(getApplicationContext(), finishline.class);
        startActivity(intent);
    }
}