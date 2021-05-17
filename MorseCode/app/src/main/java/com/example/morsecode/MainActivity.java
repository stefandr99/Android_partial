package com.example.morsecode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button encryptMorse;
    Button decryptMorse;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encryptMorse = (Button) findViewById(R.id.encryptMorseButton);
        decryptMorse = (Button) findViewById(R.id.decryptMorseButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        encryptMorse.setOnClickListener(v -> openMainMorseActivity());

        decryptMorse.setOnClickListener(v -> openDecryptMorseActivity());

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

    public void openDecryptMorseActivity() {
        Intent intent = new Intent(this, DecryptMorseActivity.class);
        startActivity(intent);
    }
}