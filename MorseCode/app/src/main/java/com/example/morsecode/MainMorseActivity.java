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
import android.os.Handler;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Handler mainHandler = new Handler();

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void flashTheCode() {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(MainMorseActivity.this, "You cannot flash the code!", Toast.LENGTH_SHORT).show();
        }
        else {
            String myCode = morseCode.getText().toString();
            //try {
                String[] words = myCode.split(" ");

                for(int i = 0; i < words.length; i++) {
                    String word = words[i];

                    //CharacterCodeThread runnable = new CharacterCodeThread(word);
                    TorchThread runnable = new TorchThread(word);
                    new Thread(runnable).start();showCharacterCode(word);

                    /*if(words[i].compareTo("|") == 0) {
                        TimeUnit.MILLISECONDS.sleep(7 * unit);
                    }

                    for(int j = 0; j < word.length(); j++) {
                        if(word.charAt(j) == '.') {
                            cameraManager.setTorchMode("0", true);
                            TimeUnit.MILLISECONDS.sleep(unit);
                            cameraManager.setTorchMode("0", false);
                        }
                        else if(word.charAt(j) == '-') {
                            cameraManager.setTorchMode("0", true);
                            TimeUnit.MILLISECONDS.sleep(unit * 3);
                            cameraManager.setTorchMode("0", false);
                        }
                        TimeUnit.MILLISECONDS.sleep(unit);
                    }

                    if(i + 1 < words.length && words[i + 1].compareTo("|") != 0)
                        TimeUnit.MILLISECONDS.sleep(unit);

                     */
                }

            /*} catch (CameraAccessException | InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void showCharacterCode(String code) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.character_code_toast, (ViewGroup) findViewById(R.id.toast_code_root));

        TextView codeTextView = layout.findViewById(R.id.toast_code_text);
        codeTextView.setText(code);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    public void sendMorseSms() {
        SmsDialog smsDialog = new SmsDialog();
        String myCode = morseCode.getText().toString();
        Bundle args = new Bundle();
        args.putString("code", myCode);

        smsDialog.setArguments(args);
        smsDialog.show(getSupportFragmentManager(), "Send sms");
    }

    class TorchThread implements Runnable {
        String letter;

        TorchThread(String letter) {
            this.letter = letter;
        }

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    if(letter.compareTo("|") == 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(7 * unit);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    for(int j = 0; j < letter.length(); j++) {
                        if(letter.charAt(j) == '.') {
                            try {
                                cameraManager.setTorchMode("0", true);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                TimeUnit.MILLISECONDS.sleep(unit);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                cameraManager.setTorchMode("0", false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(letter.charAt(j) == '-') {
                            try {
                                cameraManager.setTorchMode("0", true);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                TimeUnit.MILLISECONDS.sleep(unit * 3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                cameraManager.setTorchMode("0", false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(unit);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }

    class CharacterCodeThread implements Runnable {
        String code;

        CharacterCodeThread(String code) {
            this.code = code;
        }

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.character_code_toast, (ViewGroup) findViewById(R.id.toast_code_root));

                    TextView codeTextView = layout.findViewById(R.id.toast_code_text);
                    codeTextView.setText(code);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);

                    toast.show();
                }
            });

        }
    }
}