package com.paulabetancur.practica2.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paulabetancur.practica2.Discotecas;
import com.paulabetancur.practica2.DrawerActivity;
import com.paulabetancur.practica2.R;

import java.util.ArrayList;

public class ListaActivity extends DrawerActivity  {

    private ListView listView;
    private ArrayList<Discotecas> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



 /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,nombres);*/

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        listView = (ListView) findViewById(R.id.list);
        users = new ArrayList<Discotecas>();

        final Adapter adapter = new Adapter(this, users);

        listView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    users.add(userSnapshot.getValue(Discotecas.class));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    class Adapter extends ArrayAdapter<Discotecas> {

        public Adapter(ListaActivity context, ArrayList<Discotecas> user) {
            super(context, R.layout.list_item, Discotecas);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list_item, null);

            Discotecas discotecas = getItem(position);


            TextView tDireccion = item.findViewById(R.id.tDireccion);
            tDireccion.setText(discotecas.getDireccion());

            TextView tTelefono = item.findViewById(R.id.tTelefono);
            tTelefono.setText(discotecas.getTelefono();

            TextView tNombre = item.findViewById(R.id.tNombre);
            tNombre.setText(discotecas.getNombre());

            return item;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){

            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}

