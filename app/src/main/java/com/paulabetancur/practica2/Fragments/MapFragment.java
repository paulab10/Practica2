package com.paulabetancur.practica2.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paulabetancur.practica2.InfoActivity;
import com.paulabetancur.practica2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {


    public MapFragment() {
        // Required empty public constructor
    }

    MapView mapView;
    private GoogleMap mMap;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Discotecas");


        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.266953, -75.569111);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    if(postSnapshot.child("Lat").exists() && postSnapshot.child("Lng").exists() && postSnapshot.child("direccion").exists() && postSnapshot.child("telefono").exists() && postSnapshot.child("presupuesto").exists() && postSnapshot.child("musica").exists()) {
                        LatLng marker = new LatLng(Double.parseDouble(postSnapshot.child("Lat").getValue().toString()), Double.parseDouble(postSnapshot.child("Lng").getValue().toString()));
                        MarkerOptions mark = new MarkerOptions().position(marker).title(postSnapshot.getKey()).
                                snippet("Nombre: " + postSnapshot.getKey()+
                                        "\nDirección: " + postSnapshot.child("direccion").getValue().toString() +
                                        "\nTeléfono: " + postSnapshot.child("telefono").getValue().toString() +
                                        "\nMúsica: " + postSnapshot.child("musica").getValue().toString() +
                                        "\nPresupuesto: " + postSnapshot.child("presupuesto").getValue().toString());
                        mMap.addMarker(mark);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Intent intent = new Intent(getActivity(), InfoActivity.class);
                getActivity().startActivity(intent);
                //Toast.makeText(getContext(),marker.getSnippet(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
