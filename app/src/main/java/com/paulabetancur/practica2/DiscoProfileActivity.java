package com.paulabetancur.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DiscoProfileActivity extends AppCompatActivity {

    TextView tInfo;
    RatingBar rEstrellas;
    ImageView iFoto;
    TextView tDiscoDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disco_profile);

        tInfo = (TextView) findViewById(R.id.tInfod);
        rEstrellas= (RatingBar) findViewById(R.id.rating_bar_profile);
        iFoto = (ImageView) findViewById(R.id.iFotodisco);
        tDiscoDescripcion = (TextView) findViewById(R.id.tDescripcion);



        final String[] textFields = new String[5];
        final ImageView image = (ImageView) findViewById(R.id.imbar);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("marker_data");
        String []fields = data.split("\n");


        tInfo.setText(fields[0]+"\n"+fields[4]);
        rEstrellas.setRating((float) 4);

        tDiscoDescripcion.setText("Discoteca ubicada en la "+ fields[1] +". \nTipo de música: " + fields[3] +".\nContacto: "+fields[2]+".");


    }
}
