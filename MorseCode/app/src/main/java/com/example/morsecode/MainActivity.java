package com.example.morsecode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startMorse;
    Button infoMorse;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMorse = (Button) findViewById(R.id.startMorseButton);
        infoMorse = (Button) findViewById(R.id.infoMorseButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        startMorse.setOnClickListener(v -> openMainMorseActivity());

        infoMorse.setOnClickListener(v -> openInfoMorseActivity());

        exitButton.setOnClickListener(v -> exitApplication());
    }

    public void openMainMorseActivity() {
        Intent intent = new Intent(this, MainMorseActivity.class);
        startActivity(intent);
    }

    public void exitApplication() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Sunteți sigur că doriți să ieșiți?")
                .setCancelable(false)
                .setNegativeButton("Nu", (dialog, which) -> dialog.cancel())
                .setPositiveButton("Da", (dialog, which) -> finish());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void openInfoMorseActivity() {
        Intent intent = new Intent(this, InfoMorseActivity.class);
        startActivity(intent);
    }
}