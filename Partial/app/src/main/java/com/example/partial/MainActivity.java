package com.example.partial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText numar1 = (EditText) findViewById(R.id.numar1EditView);
        EditText numar2 = (EditText) findViewById(R.id.numar2EditView);
        Button calculeaza = (Button) findViewById(R.id.calculeazaButton);

        String nr1 = numar1.getText().toString();
        String nr2 = numar2.getText().toString();

        calculeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nr2.equals("0")) {
                    showToast(calculeaza);
                }
                else {
                    showResult(calculeaza);
                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operatii, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

    }

    public void showResult(android.view.View btn)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rezultat");
        builder.setMessage("");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("SHARE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showToast(View btn)
    {
        Toast toast = Toast.makeText(this, "Nu se poate imparti la 0!", Toast.LENGTH_SHORT );
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String operatie = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}