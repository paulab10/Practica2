package com.paulabetancur.practica2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.paulabetancur.practica2.Fragments.Blank2Fragment;
import com.paulabetancur.practica2.Fragments.BlankFragment;
import com.paulabetancur.practica2.Fragments.FilterListFragment;
import com.paulabetancur.practica2.Fragments.ListaFragment;
import com.paulabetancur.practica2.Fragments.MapaFragment;
import com.paulabetancur.practica2.Fragments.RecomendadasFragment;
import com.paulabetancur.practica2.Fragments.TabFragment;

public class MapsActivity extends DrawerActivity {

    private TextView mTextMessage;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Para que me salga el fragmento apenas salga
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        MapaFragment fragment = new MapaFragment();
        ft.add(R.id.content, fragment).commit();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fm = getSupportFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    ft = fm.beginTransaction();
                    MapaFragment fragment = new MapaFragment();
                    ft.replace(R.id.content, fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    ft = fm.beginTransaction();
                    ListaFragment fragment1 = new ListaFragment();
                    ft.replace(R.id.content, fragment1).commit();
                    return true;
                case R.id.navigation_notifications:
                    ft = fm.beginTransaction();
                    RecomendadasFragment fragment2 = new RecomendadasFragment();
                    ft.replace(R.id.content, fragment2).commit();
                    return true;
            }
            return false;
        }

    };


}
