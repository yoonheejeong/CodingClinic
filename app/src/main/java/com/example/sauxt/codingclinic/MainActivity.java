package com.example.sauxt.codingclinic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean allSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.myButton);
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){

                allSet = false;

                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                putData(intent);

                if(allSet) {
                    startActivity(intent);
                }

            }
        });



    }

    void putData(Intent intent){
        EditText mInput = (EditText)findViewById(R.id.input_month);
        EditText dInput = (EditText)findViewById(R.id.input_date);

        String month =  mInput.getText().toString();
        String date = dInput.getText().toString();

        if(month.length() == 0 || date.length() == 0){
            Toast emptyErr = Toast.makeText(getApplicationContext(),"생일을 모두 입력해주세요", Toast.LENGTH_SHORT);
            emptyErr.show();
        }else{
            allSet = true;
            intent.putExtra("month", month);
            intent.putExtra("date", date);
        }

    }









}
