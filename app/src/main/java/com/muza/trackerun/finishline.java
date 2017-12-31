package com.muza.trackerun;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class finishline extends AppCompatActivity {

    VideoView videoView5, videoView6;
    Button btnScan3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishline);

        VideoView videoView5 = (VideoView) findViewById(R.id.video5);
        videoView5.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cp3));
        videoView5.start();

        VideoView videoView6 = (VideoView) findViewById(R.id.video6);
        videoView6.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alert_cp3));
        videoView6.start();

        btnScan3 = (Button) findViewById(R.id.btnScan3);

        videoView5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                btnScan3.setEnabled(true);
            }
        });
    }

    public void toQr3(View v)
    {
        Intent intent = new Intent(getApplicationContext(), qr3.class);
        startActivity(intent);
    }
}
