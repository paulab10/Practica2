package com.paulabetancur.practica2.Fragments;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paulabetancur.practica2.Discotecas;
import com.paulabetancur.practica2.FetchImage;
import com.paulabetancur.practica2.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private RatingBar discoOneRatingBar, discoTwoRatingBar, discoThreeRatingBar, discoFourRatingBar;
    private ArrayList<Discotecas> list = new ArrayList<>();
    private TextView[] tNombre = new TextView[4];
    private RatingBar[] discoRatingBar = new RatingBar[4];
    private ImageView iprimera, isegunda, itercera,icuarta;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);
        discoRatingBar[0] = (RatingBar) view.findViewById(R.id.rating_bar_0);
        discoRatingBar[1] = (RatingBar) view.findViewById(R.id.rating_bar_1);
        discoRatingBar[2] = (RatingBar) view.findViewById(R.id.rating_bar_2);
        discoRatingBar[3] = (RatingBar) view.findViewById(R.id.rating_bar_3);
        tNombre[0] = (TextView) view.findViewById(R.id.tSemana1);
        tNombre[1] = (TextView) view.findViewById(R.id.tSemana2);
        tNombre[2] = (TextView) view.findViewById(R.id.tSemana3);
        tNombre[3] = (TextView) view.findViewById(R.id.tSemana4);
        iprimera = (ImageView) view.findViewById(R.id.iPrimera);
        isegunda = (ImageView) view.findViewById(R.id.iSegunda);
        itercera = (ImageView) view.findViewById(R.id.iTercera);
        icuarta = (ImageView) view.findViewById(R.id.iCuarta);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Discotecas");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    String temp = postSnapshot.getKey().toString();
                    if ("Babylon".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                        (float)5)
                                );
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    iprimera.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                    if ("oye bonita".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)3.5));
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    isegunda.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                    if ("Sabana bar".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)4));
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    itercera.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                    if ("La ruana de juana".compareToIgnoreCase(postSnapshot.getKey().toString()) == 0){
                        list.add(new Discotecas(postSnapshot.child("URL").getValue().toString(),
                                postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                postSnapshot.child("telefono").getValue().toString(),
                                postSnapshot.child("musica").getValue().toString(),
                                postSnapshot.child("presupuesto").getValue().toString(),
                                (float)3));
                        FetchImage fetchImage = new FetchImage(getApplicationContext(), new FetchImage.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap bitmap) {
                                if (bitmap != null) {
                                    Resources res = getApplicationContext().getResources();
                                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                            .create(res, bitmap);

                                    icuarta.setImageDrawable(roundBitmap);
                                }
                            }
                        });
                        fetchImage.execute(postSnapshot.child("URL").getValue().toString());


                    }
                }
                Collections.sort(list, new Comparator<Discotecas>() {
                    @Override
                    public int compare(Discotecas o1, Discotecas o2) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return o1.getRating() > o2.getRating() ? -1 : (o1.getRating() < o2.getRating()) ? 1 : 0;
                    }
                });
                for (int i = 0; i < 4; i++){
                    if (list.get(i) != null){
                        tNombre[i].setText(list.get(i).getName());
                        discoRatingBar[i].setRating(list.get(i).getRating());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
