package com.paulabetancur.practica2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paulabetancur.practica2.CustomList;
import com.paulabetancur.practica2.CustomListAdapter;
import com.paulabetancur.practica2.DrawerActivity;
import com.paulabetancur.practica2.LoginActivity;
import com.paulabetancur.practica2.R;
import com.paulabetancur.practica2.SidesActivity;

import java.util.ArrayList;


public class FilterListFragment extends ListFragment implements AdapterView.OnItemClickListener{
    protected int posit = 1;
    SearchView search;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        posit = getArguments().getInt("position");
        search = (SearchView) view.findViewById(R.id.nbar);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), SidesActivity.class);
                intent.putExtra("filter",3);
                intent.putExtra("text",query.toLowerCase());
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<CustomList> list = new ArrayList<>();
        switch(posit){
            case 0:
                list = CustomList.addItem(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Crossover","");
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Vallenato", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Salsa", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Merengue", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Bachata", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Reggaeton", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Reggae", ""));
                list.add(new CustomList(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send,
                        "Rock", ""));
                break;
            case 1:
                list = CustomList.addItem(R.drawable.ic_place_black_24px, R.drawable.ic_menu_send, "La 70","");
                list.add(new CustomList(R.drawable.ic_place_black_24px, R.drawable.ic_menu_send,
                        "La 33", ""));
                list.add(new CustomList(R.drawable.ic_place_black_24px, R.drawable.ic_menu_send,
                        "El Poblado", ""));
                break;
            case 2:
                list = CustomList.addItem(R.drawable.ic_attach_money_black_24px, R.drawable.ic_menu_send, "$","");
                list.add(new CustomList(R.drawable.ic_attach_money_black_24px, R.drawable.ic_menu_send,
                        "$$", ""));
                list.add(new CustomList(R.drawable.ic_attach_money_black_24px, R.drawable.ic_menu_send,
                        "$$$", ""));
                list.add(new CustomList(R.drawable.ic_attach_money_black_24px, R.drawable.ic_menu_send,
                        "$$$$", ""));
                list.add(new CustomList(R.drawable.ic_attach_money_black_24px, R.drawable.ic_menu_send,
                        "$$$$$", ""));
                break;
        }

        CustomListAdapter adapter  = new CustomListAdapter((getActivity()).getApplicationContext(), list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Intent intent = new Intent(getActivity(), SidesActivity.class);
        intent.putExtra("filter",posit);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
