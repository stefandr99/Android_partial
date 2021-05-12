package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainMorseActivity extends AppCompatActivity {
    EditText plainText;
    TextView morseCode;
    Translator translator;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_morse);

        plainText = (EditText) findViewById(R.id.edit_plain_text);
        morseCode = (TextView) findViewById(R.id.morse_code_resulted);
        translator = new Translator();
        sb = new StringBuilder();

        plainText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sb.setLength(0);
                for(int i = 0; i < s.length(); i++) {
                    sb.append(translator.encoder.get(s.charAt(i)));
                    sb.append(" ");
                }
                morseCode.setText(sb.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}