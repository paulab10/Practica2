package com.paulabetancur.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.w3c.dom.Text;

import static android.R.attr.data;
import static com.paulabetancur.practica2.R.id.profileImg;

public class PerfilActivity extends DrawerActivity {

    //private String correo, contrasena;
    private String correoR, contrasenaR, personEmail, personId, personName;
    private TextView tNombre, tContrasena;
    private ImageView imgProfile;
    private int optlog;


    //Intent intent;

    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs;


    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setTitle("Mi Perfil"); //Set Title of activity
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();
        optlog = prefs.getInt("optlog", 0);


        tNombre = (TextView) findViewById(R.id.tNombre);
        tContrasena = (TextView) findViewById(R.id.tContrasena);
        imgProfile = (ImageView) findViewById(R.id.profileImg);

        // Getting profile info from shared preferences
        tNombre.setText(prefs.getString(LoginActivity.TAG_NAME, ""));   // User name
        tContrasena.setText(prefs.getString(LoginActivity.TAG_EMAIL, ""));  // User email


        // Fetch image
        // Create image from fb URL
        FetchImage fetchImage = new FetchImage(this, new FetchImage.AsyncResponse() {
            @Override
            public void processFinish(Bitmap bitmap) {
                if (bitmap != null) {
                    Resources res = getResources();
                    RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                            .create(res, bitmap);
                    roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                    imgProfile.setImageDrawable(roundBitmap);
                }
            }
        });
        fetchImage.execute(prefs.getString(LoginActivity.TAG_URLIMG, ""));




        if (optlog == 1){ //Facebook


        } else if (optlog == 2){ //Google
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .requestProfile()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(getApplicationContext(), "Error en login", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        } else if (optlog ==3 ){ //Registro
            // Getting profile info from shared preferences
            tNombre.setText(prefs.getString("correo", ""));   // User name
            tContrasena.setText(prefs.getString("contrasena", ""));  // User email
        }
        SubMenu subMenu = navigationView.getMenu().getItem(3).getSubMenu();
        MenuItem menuItem = subMenu.getItem(0);
        menuItem.setChecked(true);
    }



}

