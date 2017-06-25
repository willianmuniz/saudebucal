package br.com.willmo.saudebucal.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import br.com.willmo.saudebucal.R;

public class ShowVideoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);

//        Uri path = Uri.parse("android.resource://br.com.willmo.saudebocal/" + R.raw.sample);
//        VideoView
//                videoView = (VideoView)findViewById(R.id.videoView2);
//        videoView.setVideoURI(path);
//        videoView.start();

//        Button b = (Button) findViewById(R.id.button2);
//        b.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                action();
//            }
//        });
    }
}
