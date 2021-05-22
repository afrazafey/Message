package com.example.message;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText txt_pNumber,txt_message;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txt_message = (EditText)findViewById(R.id.txt_message);
        txt_pNumber = (EditText)findViewById(R.id.txt_phone_number);
        
        
        
    }

    public void btn_send(View view) {
        
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck== PackageManager.PERMISSION_GRANTED);{
            
            MyMessage();
            
        }

        else{

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }


    }

    private void MyMessage() {

        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Meessage = txt_message.getText().toString().trim();

        if (txt_pNumber.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            SmsManager.sendTextMessage(phoneNumber, null, Meessage, null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Enter Number os Message", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode){

            case 0:

                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                    MyMessage();

                }

                else{
                    Toast.makeText(this, "You dont have Required Permission to make this action ", Toast.LENGTH_SHORT).show();
                }



                break;



        }


    }
}

