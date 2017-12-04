package com.paulabetancur.practica2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView VerMas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disco_details);

        final TextView [] textFields = new TextView[5];
        final ImageView image = (ImageView) findViewById(R.id.imbar);

        textFields[0] = (TextView) findViewById(R.id.dname);
        textFields[1] = (TextView) findViewById(R.id.tdir);
        textFields[2] = (TextView) findViewById(R.id.ttel);
        textFields[3] = (TextView) findViewById(R.id.tmusic);
        textFields[4] = (TextView) findViewById(R.id.tprice);

        VerMas = (TextView) findViewById(R.id.tvermas);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("marker_data");
        String []fields = data.split("\n");
        for (int i = 0; i < 5; i++){
            textFields[i].setText(fields[i]);
        }

        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    Resources res = getApplicationContext().getResources();
                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                            .create(res, bitmap);
                    roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                    image.setImageDrawable(roundBitmap);
                }
            }
        });
        fetchImage.execute(fields[5]);

        VerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, DiscoProfileActivity.class);
                intent.putExtra("marker_data", data);
                startActivity(intent);
            }
        });
    }
}
