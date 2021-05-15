package com.example.morsecode;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainMorseActivity extends AppCompatActivity {
    EditText plainText;
    TextView morseCode;
    Translator translator;
    StringBuilder sb;
    Button flashButton;
    Button smsButton;
    CameraManager cameraManager;
    private final static int unit = 100;
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_morse);

        plainText = (EditText) findViewById(R.id.edit_plain_text);
        morseCode = (TextView) findViewById(R.id.morse_code_resulted);
        flashButton = (Button) findViewById(R.id.flash_code);
        smsButton = (Button) findViewById(R.id.send_sms_code);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);


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

        flashButton.setOnClickListener(v -> flashTheCode());
        smsButton.setOnClickListener(v -> sendMorseSms());

    }

    //o unitate are 200 milisecunde
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void flashTheCode() {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(MainMorseActivity.this, "You cannot flash the code!", Toast.LENGTH_SHORT).show();
        }
        else {
            String myCode = morseCode.getText().toString();
            try {
                for(int i = 0; i < myCode.length(); i++) {
                    char next = '*', current = myCode.charAt(i);
                    if(i + 1 < myCode.length())
                        next = myCode.charAt(i + 1);

                    if(current == '.') {
                        cameraManager.setTorchMode("0", true);
                        TimeUnit.MILLISECONDS.sleep(unit);
                        cameraManager.setTorchMode("0", false);
                        if(next != ' ')
                            TimeUnit.MILLISECONDS.sleep(unit);
                    }
                    else if(current == '-') {
                        cameraManager.setTorchMode("0", true);
                        TimeUnit.MILLISECONDS.sleep(unit * 3);
                        cameraManager.setTorchMode("0", false);
                        if(next != ' ')
                            TimeUnit.MILLISECONDS.sleep(unit);
                    }
                    else if(current == ' ' || next != '|') {
                        TimeUnit.MILLISECONDS.sleep(unit);
                    }
                    else if(current == '|') {
                        TimeUnit.MILLISECONDS.sleep(unit * 7);
                        i++;
                    }


                }
            } catch (CameraAccessException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMorseSms() {
        SmsDialog smsDialog = new SmsDialog();
        String myCode = morseCode.getText().toString();
        Bundle args = new Bundle();
        args.putString("code", myCode);

        smsDialog.setArguments(args);
        smsDialog.show(getSupportFragmentManager(), "Send sms");
    }
}