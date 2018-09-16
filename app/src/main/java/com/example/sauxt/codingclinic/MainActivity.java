package com.example.sauxt.codingclinic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    boolean toggle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button)findViewById(R.id.myButton);
        final TextView mainText = (TextView) findViewById(R.id.mainText);

        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                if(toggle){
                    mainText.setText("What is next?");
                }else{
                    mainText.setText("First Step");
                }

                toggle = !toggle;

            }
        });

    }
}
