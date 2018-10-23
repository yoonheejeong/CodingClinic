package com.example.sauxt.codingclinic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UploadActivity extends AppCompatActivity {

    private EditText editText;
    private Button uploadBtn;
    private ImageView imgView;
    private String comment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editText = findViewById(R.id.comment);
        uploadBtn = findViewById(R.id.uploadBtn);
        imgView = findViewById(R.id.imgSelector);


        uploadBtn.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View view) {

            }
        });

        // Save user comment
        comment = editText.getText().toString();

    }


}
