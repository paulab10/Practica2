package com.paulabetancur.practica2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private String correoR, contrasenaR;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private EditText eCorreo, eContrasena;
    private String Correo, Contrasena, personName, personGivenName, personFamilyName, personEmail, personId;
    private Uri personPhoto;
    private int RC_SIGN_IN = 5678;
    private int optlog;

    public final static String TAG_NAME = "name";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASS = "pass";
    public final static String TAG_URLIMG = "imgUrl";
    public final static String LOGIN_OPTION = "optlog";

    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eContrasena = (EditText) findViewById(R.id.eContrasena);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);



        //Login con google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Error en login", Toast.LENGTH_SHORT).show();
                    }
                })

                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        //Login con facebook
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                optlog = 1;
                // Obtain user info from Facebook
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), fbRequest);
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,gender,birthday,email,picture"); // El cumpleaños puede servir para mi app final
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error en login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    GraphRequest.GraphJSONObjectCallback fbRequest = new GraphRequest.GraphJSONObjectCallback() {
        @Override
        public void onCompleted(JSONObject object, GraphResponse response) {
            try {
                // get profile information
                String name = "";
                String email = "";
                String uriPicture = "";
                if (object.getString("name") != null) {
                    name = object.getString("name");
                }
                if (object.getString("email") != null) {
                    email = object.getString("email");
                }
                if (object.getString("picture") != null) {
                    JSONObject imagen = new JSONObject(object.getString("picture"));
                    JSONObject imagen2 = new JSONObject(imagen.getString("data"));
                    uriPicture = imagen2.getString("url");
                }
                // Save profile information to shared preferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(TAG_NAME, name);
                editor.putString(TAG_EMAIL, email);
                editor.putString(TAG_URLIMG, uriPicture);
                //editor.putBoolean(getString(R.string.is_guest), false);
                editor.putInt(LOGIN_OPTION, 1).apply();
                editor.apply();

                // After fetching data from fb
                // open Main Activity
                goMainActivity();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void goMainActivity() {

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();

        //almacenar el valor de optlog
        editor.putInt("optlog", optlog).apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void iniciar(View view) {

        correoR = prefs.getString("correo", "");
        contrasenaR = prefs.getString("contrasena", "");

        Correo = eCorreo.getText().toString();
        Contrasena = eContrasena.getText().toString();
        optlog = 3;

        if(Correo.equals(correoR) && Contrasena.equals(contrasenaR)){
            goMainActivity();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1234 && resultCode == RESULT_OK) { //Registro
            Toast.makeText(this, "REGISTRO ÉXITOSO", Toast.LENGTH_SHORT).show();

        } else if (requestCode == RC_SIGN_IN) { //google

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInAccount acct = result.getSignInAccount();
            try{
                //acct.get
                prefs.edit().putString(TAG_NAME, acct.getGivenName()).apply();
                prefs.edit().putString(TAG_EMAIL, acct.getEmail()).apply();
                prefs.edit().putString(TAG_URLIMG, acct.getPhotoUrl().toString()).apply();
            }catch (Exception e){
                Log.e("Error URL", "Error obteniendo url");
            }

            handleSignInResult(result);

        } else { //Login con facebook
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("google", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            optlog=2;
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d("nombre de usuario: ", acct.getDisplayName());
            Toast.makeText(getApplicationContext(), "Login exitoso", Toast.LENGTH_SHORT).show();
            goMainActivity();
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this, "Error en login", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrese(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivityForResult(intent, 1234);
    }
}
