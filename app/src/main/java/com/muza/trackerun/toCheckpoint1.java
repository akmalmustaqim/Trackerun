package com.muza.trackerun;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class toCheckpoint1 extends AppCompatActivity {

    VideoView videoView, videoView2;
    Button btnScan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_checkpoint1);

        VideoView videoView = (VideoView) findViewById(R.id.video1);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cp1));
        videoView.start();

        VideoView videoView2 = (VideoView) findViewById(R.id.video2);
        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alert_cp1));
        videoView2.start();

        btnScan1 = (Button) findViewById(R.id.btnScan1);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                btnScan1.setEnabled(true);
            }
        });
    }

    public void toQr1(View v)
    {
        Intent intent = new Intent(getApplicationContext(), qr1.class);
        startActivity(intent);
    }
}
