package com.paulabetancur.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscoProfileActivity extends AppCompatActivity {

    TextView tInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_profile);

        tInfo = (TextView) findViewById(R.id.tInfod);

        final String[] textFields = new String[5];
        final ImageView image = (ImageView) findViewById(R.id.imbar);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("marker_data");
        //String []fields = data.split("\n");

        tInfo.setText(data);


    }
}
