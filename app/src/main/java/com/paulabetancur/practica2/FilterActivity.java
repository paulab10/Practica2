package com.paulabetancur.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulabetancur.practica2.Fragments.FilterListFragment;
import com.paulabetancur.practica2.Fragments.TabFragment;

public class FilterActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        if (findViewById(R.id.containerView) != null){

            TabFragment fragment = new TabFragment();

            getSupportFragmentManager().beginTransaction().
                    add(R.id.containerView, fragment).commit();
        }
    }
}
