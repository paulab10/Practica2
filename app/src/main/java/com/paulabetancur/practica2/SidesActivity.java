package com.paulabetancur.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SidesActivity extends AppCompatActivity {

    String[] music = {
            "crossover",
            "bachata"
    };

    ArrayList<SideItems> list = new ArrayList<>();
    ArrayList<String> musicCategory = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String ndb,nfilter;
    int var;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sides);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Discotecas");
        var = getIntent().getIntExtra("filter",-1);
        if( var == 3){
            nfilter = getIntent().getStringExtra("text");
        }else {
            pos = getIntent().getIntExtra("position",-1);
            switch (var){
                case 0:
                    switch (pos){
                        case 0:
                            nfilter = "crossover";
                            break;
                        case 1:
                            nfilter = "vallenato";
                            break;
                        case 2:
                            nfilter = "salsa";
                            break;
                        case 3:
                            nfilter = "merengue";
                            break;
                        case 4:
                            nfilter = "bachata";
                            break;
                        case 5:
                            nfilter = "reggaeton";
                            break;
                        case 6:
                            nfilter = "reggae";
                            break;
                        case 7:
                            nfilter = "rock";
                            break;
                    }
                    break;
                case 1:
                    switch (pos){
                        case 0:
                            nfilter = "70";
                            break;
                        case 1:
                            nfilter = "33";
                            break;
                        case 2:
                            nfilter = "poblado";
                            break;
                    }
                    break;
                case 2:
                    switch (pos){
                        case 0:
                            nfilter = "$";
                            break;
                        case 1:
                            nfilter = "$$";
                            break;
                        case 2:
                            nfilter = "$$$";
                            break;
                        case 3:
                            nfilter = "$$$";
                            break;
                        case 4:
                            nfilter = "$$$$";
                            break;
                    }
                    break;
            }

        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    ndb = postSnapshot.getKey().toLowerCase();
                    Log.d("Name: ",ndb);
                    if(postSnapshot.child("URL").exists()&&postSnapshot.child("direccion").exists()&&postSnapshot.child("telefono").exists()&&postSnapshot.child("musica").exists()&&postSnapshot.child("presupuesto").exists()){
                        if(var == 3){
                            if(ndb.contains(nfilter)){
                                list.add(new SideItems(postSnapshot.child("URL").getValue().toString(),postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                        postSnapshot.child("telefono").getValue().toString(),postSnapshot.child("musica").getValue().toString(),postSnapshot.child("presupuesto").getValue().toString()));
                            }
                        }
                        else if(var == 0){
                            ndb = postSnapshot.child("musica").getValue().toString().toLowerCase();
                            if(ndb.contains(nfilter)){
                                list.add(new SideItems(postSnapshot.child("URL").getValue().toString(),postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                        postSnapshot.child("telefono").getValue().toString(),postSnapshot.child("musica").getValue().toString(),postSnapshot.child("presupuesto").getValue().toString()));
                            }

                        } else if(var == 1){
                            ndb = postSnapshot.child("lugar").getValue().toString().toLowerCase();
                            if(ndb.contains(nfilter)) {
                                list.add(new SideItems(postSnapshot.child("URL").getValue().toString(), postSnapshot.getKey(), postSnapshot.child("direccion").getValue().toString(),
                                        postSnapshot.child("telefono").getValue().toString(), postSnapshot.child("musica").getValue().toString(), postSnapshot.child("presupuesto").getValue().toString()));
                            }
                        }else if (var == 2){
                            ndb = postSnapshot.child("presupuesto").getValue().toString().toLowerCase();
                            if(ndb.equals(nfilter)){
                                list.add(new SideItems(postSnapshot.child("URL").getValue().toString(),postSnapshot.getKey(),postSnapshot.child("direccion").getValue().toString(),
                                        postSnapshot.child("telefono").getValue().toString(),postSnapshot.child("musica").getValue().toString(),postSnapshot.child("presupuesto").getValue().toString()));
                            }

                        }
                    }

                }
                recyclerView = (RecyclerView)findViewById(R.id.recycler);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(SidesActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                TextView terr = (TextView)findViewById(R.id.tErr);
                if(list.isEmpty()){
                    terr.setVisibility(View.VISIBLE);
                }else{
                    terr.setVisibility(View.GONE);
                }
                adapter = new SideItemsAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
