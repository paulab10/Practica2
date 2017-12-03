package com.paulabetancur.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.paulabetancur.practica2.Fragments.BlankFragment;
import com.paulabetancur.practica2.Fragments.Tab2Fragment;
import com.paulabetancur.practica2.Fragments.TabFragment;

public class MainActivity extends DrawerActivity {


    private String correoR, contrasenaR;
    private String correo, contrasena, personEmail, personId, personName;
    private int optlog;



    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inicio");

        if (findViewById(R.id.containerView) != null){
            //Fragment fragment = new Tab2Fragment();
            Fragment fragment = new BlankFragment();

            getSupportFragmentManager().beginTransaction().
                    add(R.id.containerView, fragment).commit();
        }

        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
    }

}

