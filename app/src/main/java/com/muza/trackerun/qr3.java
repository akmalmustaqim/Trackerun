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

public class qr3 extends AppCompatActivity {

    SurfaceView cameraPreview3;
    TextView txtResult3;
    BarcodeDetector barcodeDetector3;
    CameraSource cameraSource3;
    final int RequestCameraPermissionID3 = 1001;
    Button btnProceed3;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID3: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource3.start(cameraPreview3.getHolder());
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
        setContentView(R.layout.activity_qr3);

        cameraPreview3 = (SurfaceView) findViewById(R.id.cameraPreview3);
        txtResult3 = (TextView) findViewById(R.id.txtResult3);
        btnProceed3 = (Button) findViewById(R.id.btnProceed3);

        barcodeDetector3 = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource3 = new CameraSource
                .Builder(this, barcodeDetector3)
                .setRequestedPreviewSize(640, 480)
                .build();

        //Add Event
        cameraPreview3.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(qr3.this,
                            new String[]{android.Manifest.permission.CAMERA},RequestCameraPermissionID3);
                    return;
                }
                try {
                    cameraSource3.start(cameraPreview3.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                cameraSource3.stop();
            }
        });

        barcodeDetector3.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if(qrcodes.size() !=0)
                {
                    txtResult3.post(new Runnable() {
                        @Override
                        public void run() {
                            //Create vibrate
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            txtResult3.setText(qrcodes.valueAt(0).displayValue);
                            //button reenabled
                            if (txtResult3.getText().equals("Finish"))
                            {
                                btnProceed3.setEnabled(true);
                            }
                        }
                    });
                }
            }
        });


    }

    public void toend(View v)
    {
        Intent intent = new Intent(getApplicationContext(), end.class);
        startActivity(intent);
    }
}