package com.example.przemcio.zadanie7;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private MediaRecorder myAudioRecorder;
    String mFileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button start = (Button) findViewById(R.id.button3);
        final Button stop = (Button) findViewById(R.id.button4);
        final Button play = (Button) findViewById(R.id.button5);



        stop.setEnabled(false);
        play.setEnabled(false);

        myAudioRecorder=null;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAudioRecorder == null) {
                    myAudioRecorder = new MediaRecorder();


                    mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
                    mFileName += "/audiorecordtest.3gp";
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    myAudioRecorder.setOutputFile(mFileName);
                }
                try {
                    myAudioRecorder.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                myAudioRecorder.start();
                start.setEnabled(false);
                stop.setEnabled(true);
                play.setEnabled(false);

            }

        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                stop.setEnabled(false);
                play.setEnabled(true);
                start.setEnabled(true);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer m;
                m = new MediaPlayer();
                try {
                    m.setDataSource(mFileName);
                    m.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                m.start();


            }

        });
    }
}
