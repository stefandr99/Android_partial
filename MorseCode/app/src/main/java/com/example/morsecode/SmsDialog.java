package com.example.morsecode;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsDialog extends AppCompatDialogFragment {
    private EditText phoneNumber;
    private String number;
    private String code;
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        code = bundle.getString("code");

        if(!checkPermission(Manifest.permission.SEND_SMS)) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.send_code_layout, null);
        phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);

        builder.setView(view)
                .setTitle("Send SMS")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        number = phoneNumber.getText().toString();

                        if(checkPermission(Manifest.permission.SEND_SMS)) {
                            if (!TextUtils.isEmpty(number)) {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(number, null, code, null, null);
                            } else {
                                Toast.makeText(getActivity(), "Enter phone number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(getActivity(), permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
