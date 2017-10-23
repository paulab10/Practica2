package com.paulabetancur.practica2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;


import com.paulabetancur.practica2.CustomList;
import com.paulabetancur.practica2.CustomListAdapter;
import com.paulabetancur.practica2.DrawerActivity;
import com.paulabetancur.practica2.R;

import java.util.ArrayList;


public class FilterListFragment extends ListFragment implements AdapterView.OnItemClickListener{
    protected int posit = 1;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        posit = getArguments().getInt("position");
        ((DrawerActivity) getActivity()).getSupportActionBar().setTitle("Filtro");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<CustomList> list = new ArrayList<>();
        switch(posit){
            case 0:
                list = CustomList.addItem(R.drawable.ic_music_note_black_24px, R.drawable.ic_menu_send, "Crossover","");
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

    protected Intent intent;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        /*Handler handler = new Handler();
        intent = new Intent(getContext(), SeeRoutes_Show.class);
        TextView routeTitle = (TextView) view.findViewById(R.id.list_title);
        TextView routeNumber = (TextView) view.findViewById(R.id.list_detail);

        intent.putExtra(Tags.TAG_ROUTE_NAME, routeTitle.getText().toString());
        intent.putExtra(Tags.TAG_ROUTE_NUMBER, routeNumber.getText().toString());
        intent.putExtra(FindRoute_TripActivity.TAG_OPTION, "show");
        handler.postDelayed(delay, 150);*/
    }
    protected Runnable delay = new Runnable() {
        @Override
        public void run() {
            startActivity(intent);
        }
    };
}
