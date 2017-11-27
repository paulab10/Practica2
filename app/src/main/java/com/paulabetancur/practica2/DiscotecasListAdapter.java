package com.paulabetancur.practica2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Paula on 26/11/2017.
 */

public class DiscotecasListAdapter extends ArrayAdapter<Discotecas> {

    public DiscotecasListAdapter(@NonNull Context context, ArrayList<Discotecas> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        // Get the data item for this position
        Discotecas itemDiscotecas = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.disco_details, parent, false);
        }

        // Lookup view for data population

        final ImageView image = (ImageView) v.findViewById(R.id.imbar);
        TextView name = (TextView) v.findViewById(R.id.dname);
        TextView dir = (TextView) v.findViewById(R.id.tdir);
        TextView tel = (TextView) v.findViewById(R.id.ttel);
        TextView music = (TextView) v.findViewById(R.id.tmusic);
        TextView price = (TextView) v.findViewById(R.id.tprice);

        // Populate the data into the template view using the data object
        name.setText(itemDiscotecas.getName());
        dir.setText(itemDiscotecas.getDir());
        tel.setText(itemDiscotecas.getTel());
        music.setText(itemDiscotecas.getMusic());
        price.setText(itemDiscotecas.getPrice());
        /*FetchImage fetchImage = new FetchImage(getContext(), new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    Resources res = getContext().getResources();
                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                            .create(res, bitmap);
                    roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                    image.setImageDrawable(roundBitmap);
                }
            }
        });
        fetchImage.execute(itemDiscotecas.getImage());*/
        return v;
    }
}
