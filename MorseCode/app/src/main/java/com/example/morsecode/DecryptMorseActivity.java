package com.example.morsecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class DecryptMorseActivity extends AppCompatActivity {
    EditText morseCode;
    TextView plainText;
    Translator translator;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_morse);

        morseCode = (EditText) findViewById(R.id.edit_morse_code_text);
        plainText = (TextView) findViewById(R.id.plain_text_resulted);
        translator = new Translator();
        sb = new StringBuilder();

        morseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sb.setLength(0);
                String[] letters = s.toString().split("\\s+");
                for(int i = 0; i < letters.length; i++) {
                    if(translator.decoder.containsKey(letters[i]))
                        sb.append(translator.decoder.get(letters[i]));
                    else {
                        sb.setLength(0);
                        sb.append("Please enter a valid code!");
                        i = letters.length;
                    }
                }
                plainText.setText(sb.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}