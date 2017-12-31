package com.muza.trackerun;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class toCheckpoint2 extends AppCompatActivity {

    VideoView videoView3, videoView4;
    Button btnScan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_checkpoint2);

        VideoView videoView3 = (VideoView) findViewById(R.id.video3);
        videoView3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cp2));
        videoView3.start();

        VideoView videoView4 = (VideoView) findViewById(R.id.video4);
        videoView4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alert_cp2));
        videoView4.start();

        btnScan2 = (Button) findViewById(R.id.btnScan2);

        videoView3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                btnScan2.setEnabled(true);
            }
        });
    }

    public void toQr2(View v)
    {
        Intent intent = new Intent(getApplicationContext(), qr2.class);
        startActivity(intent);
    }
}
