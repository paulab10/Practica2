package com.paulabetancur.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.w3c.dom.Text;

import static android.R.attr.data;

public class PerfilActivity extends AppCompatActivity {

    //private String correo, contrasena;
    private String correoR, contrasenaR, personEmail, personId, personName;
    private TextView tNombre, tContrasena;
    private int optlog;

    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        Intent intent;

        switch (id){
            case R.id.mPerfil:

                break;
            case R.id.mCerrarp:


                editor.putInt("optlog", 0);
                editor.commit();

                if (optlog == 1){ //Facebook
                    LoginManager.getInstance().logOut();
                    intent = new Intent(PerfilActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if(optlog == 2){ //Google
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    // ...
                                }
                            });

                    intent = new Intent(PerfilActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if(optlog == 3){ //Cuenta usuario
                    intent = new Intent(PerfilActivity.this, LoginActivity.class);
                    intent.putExtra("correo", correoR);
                    intent.putExtra("contrasena", contrasenaR);
                    startActivity(intent);
                    finish();
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menup) {
        getMenuInflater().inflate(R.menu.menu, menup);
        return super.onCreateOptionsMenu(menup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        final int optlog = prefs.getInt("optlog", 0);

        tNombre = (TextView) findViewById(R.id.tNombre);
        tContrasena = (TextView) findViewById(R.id.tContrasena);



        if (optlog == 1){ //Facebook

        } else if (optlog == 2){ //Google

            tNombre.setText(prefs.getString(LoginActivity.TAG_NAME, ""));
            tContrasena.setText(prefs.getString(LoginActivity.TAG_EMAIL, ""));


        } else if (optlog ==3 ){ //Registro

            Bundle extras = getIntent().getExtras();
            correoR = extras.getString("correo");
            contrasenaR = extras.getString("contrasena");

            tNombre.setText(correoR);
            tContrasena.setText(contrasenaR);
        }

    }
/*    public void cargarpp(MenuItem item) {

        if (optlog == 1){
            //intent = new Intent(MainActivity.this, PerfilActivity.class);
            //startActivity(intent);
        }else if(optlog == 2){

            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            //intent.putExtra("optlog", optlog);
            startActivity(intent);

        }else if(optlog == 3){
            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            intent.putExtra("correo", correoR);
            intent.putExtra("contrasena", contrasenaR);
            intent.putExtra("optlog", optlog);
            startActivity(intent);
        }

    }*/

}

