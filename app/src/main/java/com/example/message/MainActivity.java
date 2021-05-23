package com.example.message;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText txt_pNumber,txt_message;
    Button btn;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txt_message = findViewById(R.id.txt_message);
        txt_pNumber = findViewById(R.id.txt_phone_number);
        btn = findViewById(R.id.Send);

       huhdha();

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   huhdha();
                   btn_send();
                   hideSoftKeyboard(MainActivity.this);
                   txt_message.setText("");
                   txt_pNumber.setText("");

               } catch (Exception e){
                   Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
               }

           }
       });
        
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    private void huhdha(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void btn_send() {
        String phoneNumber = txt_pNumber.getText().toString().trim();
        String Meessage = txt_message.getText().toString().trim();

        if (txt_pNumber.getText().toString().equals("") || !txt_message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Meessage, null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Enter Number or Message", Toast.LENGTH_SHORT).show();
        }
    }




}

