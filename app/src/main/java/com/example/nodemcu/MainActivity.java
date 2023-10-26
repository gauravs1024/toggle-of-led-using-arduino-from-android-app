package com.example.nodemcu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button btnled1,btnled2;
    TextView message;
    EditText ip;
    String mes,btn1cmd1="/onled1",btn1cmd2="/offled1",btn2cmd1="/onled2",btn2cmd2="/offled2";
    int led_flag1=0,led_flag2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnled1 = findViewById(R.id.led1);
        ip=findViewById(R.id.ip);
        message=findViewById(R.id.message);
        btnled2 = findViewById(R.id.led2);



        btnled1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(led_flag1==0){
            String nodemcu_url="http://"+mes+btn1cmd1;
            new sendurltask().execute(nodemcu_url);
                mes=ip.getText().toString();
                Toast.makeText(MainActivity.this, "http://"+mes+btn1cmd1, Toast.LENGTH_SHORT).show();
                message.setText("http://"+mes+btn1cmd1);
                led_flag1=1;
                btnled1.setText("OFFLED1");
                }
                else if(led_flag1==1){
                    String nodemcu_url="http://"+mes+btn1cmd2;
                    new sendurltask().execute(nodemcu_url);
                    mes=ip.getText().toString();
                    Toast.makeText(MainActivity.this, "http://"+mes+btn1cmd2, Toast.LENGTH_SHORT).show();
                    message.setText("http://"+mes+btn1cmd2);
                    led_flag1=0;
                    btnled1.setText("ONLED1");
                }

            }
        });
        btnled2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (led_flag2== 0) {
                    String nodemcu_url = "http://" + mes + btn2cmd1;
                    new sendurltask().execute(nodemcu_url);
                    mes = ip.getText().toString();
                    Toast.makeText(MainActivity.this, "http://" + mes + btn2cmd1, Toast.LENGTH_SHORT).show();
                    message.setText("http://" + mes + btn2cmd1);
                    led_flag2 = 1;
                    btnled2.setText("OFFLED1");
                } else if (led_flag2 == 1) {
                    String nodemcu_url = "http://" + mes + btn2cmd2;
                    new sendurltask().execute(nodemcu_url);
                    mes = ip.getText().toString();
                    Toast.makeText(MainActivity.this, "http://" + mes + btn2cmd2, Toast.LENGTH_SHORT).show();
                    message.setText("http://" + mes + btn2cmd2);
                    led_flag2 = 0;
                    btnled2.setText("ONLED1");
                }
            }


        });

    }






private class sendurltask extends AsyncTask<String,Void,String>{
        @Override
    protected String doInBackground(String...urls){
            try{
                URL url=new URL(urls[0]);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responsecode=connection.getResponseCode();
                if(responsecode== HttpsURLConnection.HTTP_OK){
                    return "URL sent succesfully";
                }
                else{
                    return "Failed to connect to node mcu Response code: "+responsecode;
                }


            }

            catch(IOException e){
                e.printStackTrace();
                return "Error:" +e.getMessage();
            }
        }
}

}