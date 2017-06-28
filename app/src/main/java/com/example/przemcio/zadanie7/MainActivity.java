package com.example.przemcio.zadanie7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnNewAlertDialogButton;
    private MediaPlayer mediaPlayer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNewAlertDialogButton = (Button) findViewById(R.id.btnNewAlertDialogButton);
        Button lista = (Button) findViewById(R.id.button);
        Button zapis = (Button) findViewById (R.id.button6);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLista();
            }
        });


        btnNewAlertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialogWithButtons();
            }
        });

        Button nagrywanie = (Button) findViewById(R.id.button2);

        nagrywanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,Main2Activity.class);
                startActivity(intent);
            }
        });

        zapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                context = getApplicationContext();
                Intent intent = new Intent(context,Main3Activity.class);
                startActivity(intent);
            }
        });
    }

    public void play(View v)
    {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this,R.raw.piosenka1);
        mediaPlayer.start();

    }

    public void playSound(int x) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        switch (x)
        {
            case 0:
                mediaPlayer=MediaPlayer.create(this, R.raw.piosenka1);
                mediaPlayer.start();
                File sdcard = Environment.getExternalStorageDirectory();
                File dir = new File(sdcard.getAbsolutePath() + "/MojePliki/");
                File file = new File(dir, "piosenka1.txt");
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                FileInputStream in;
                try {
                    in = new FileInputStream(file);
                    in.read(bytes);
                    in.close();
                } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String contents = new String(bytes);
                TextView tv = (TextView) findViewById(R.id.textView);
                tv.setText(contents);
                break;

            case 1:
                mediaPlayer=MediaPlayer.create(this, R.raw.piosenka2);
                mediaPlayer.start();
                File sdcard2 = Environment.getExternalStorageDirectory();
                File dir2 = new File(sdcard2.getAbsolutePath() + "/MojePliki/");
                File file2 = new File(dir2, "piosenka2.txt");
                int length2 = (int) file2.length();
                byte[] bytes2 = new byte[length2];
                FileInputStream in2;
                try {
                    in = new FileInputStream(file2);
                    in.read(bytes2);
                    in.close();
                } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String contents2 = new String(bytes2);
                TextView tv2 = (TextView) findViewById(R.id.textView);
                tv2.setText(contents2);
                break;
        }
        // mediaPlayer = MediaPlayer.create(this,raw.piosenka1);
        //mediaPlayer.start();
    }

    public void stopSound(View view) {

        if (mediaPlayer == null) {
            mediaPlayer.release();
        }
        else mediaPlayer.stop();
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    private void createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                mediaPlayer.stop();
                showToast("Wychodzę");
                finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowaleś wyjście");

            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
    }

    private Dialog createLista() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Pierwsza piosenka", "Druga piosenka"};
        dialogBuilder.setTitle("Lista piosenek");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                showToast("Wybrałeś: " + options[position]);
                playSound(position);

            }
        });
        dialogBuilder.create();
        dialogBuilder.show();
        return dialogBuilder.create();
    }
};


