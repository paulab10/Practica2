package com.paulabetancur.practica2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
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

public class DrawerActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    protected int home_menu;
    protected DrawerLayout fullLayout;
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected Bundle extras;
    private int optlog;
    private ImageView imgProfile;

    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs;


    SharedPreferences.Editor editor;

    //private GoogleSignHandle googleSignHandle;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();
        optlog = prefs.getInt("optlog", 0);



        /**
         * This is going to be our actual root layout.
         */
        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer, null);
        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        /**
         * Note that we don't pass the child's layoutId to the parent,
         * instead we pass our inflated layout.
         */
        super.setContentView(fullLayout);

        /** Create a toolbar and check whether child set Toolbar
         * option or not.
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (useToolbar()){
            //toolbar.setTitle(toolbarTitle());
            setSupportActionBar(toolbar);
        }else
            toolbar.setVisibility(View.GONE);

        /** Set menu depending on child **/
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setMenu();
        if (navigationView != null) {
            setupNavigationDrawerContent();
        }


        /** Set the Drawer toggle **/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, fullLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        fullLayout.addDrawerListener(toggle);

        toggle.syncState();





        /**
         * Instance email & username of
         * navigation drawer
         */
        TextView email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email_drawer);
        TextView username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_drawer);
        final ImageView profileImg = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.image_drawer);
        //imgProfile = (ImageView) findViewById(R.id.profileImg);

        // Load preferences
        preferences = this.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        /**
         * Set username and email depending on
         * whether the user is a guest or logged in
         *  with Facebook or Google+ or as a Guest
         */


        /**
         * Set username and email depending on
         * whether the user is a guest or logged in
         *  with Facebook or Google+ or as a Guest
         */
        if (preferences.getInt("optlog", 0) == 1 ){
            email.setText("");
            //username.setText(getString(R.string.guest));
        }else {
            /* Read data from shared preferences */
            // Set name
            username.setText(preferences.getString(LoginActivity.TAG_NAME, ""));
            // Set email
            email.setText(preferences.getString(LoginActivity.TAG_EMAIL, ""));
            // Create image from fb URL
            FetchImage fetchImage = new FetchImage(this, new FetchImage.AsyncResponse() {
                @Override
                public void processFinish(Bitmap bitmap) {
                    if (bitmap != null) {
                        Resources res = getResources();
                        RoundedBitmapDrawable roundBitmap = RoundedBitmapDrawableFactory
                                .create(res, bitmap);
                        roundBitmap.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                        profileImg.setImageDrawable(roundBitmap);
                    }
                }
            });
            fetchImage.execute(prefs.getString(LoginActivity.TAG_URLIMG, ""));

        }
    }



    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void setMenu(){

        navigationView.inflateMenu(R.menu.main_menu);

    }

    /**
     * Helper method that can be used by child classes to
     * specify that they don't want a {@link Toolbar}
     * @return true
     */
    protected boolean useToolbar()
    {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (fullLayout.isDrawerOpen(GravityCompat.START)) {
            fullLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    protected void setupNavigationDrawerContent() {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        // Handle navigation view item clicks here.
                        //Intent intent;
                        Handler handler = new Handler();
                        switch (item.getItemId()) {
                            case R.id.home:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    intent = new Intent(DrawerActivity.this,MainActivity.class);
                                    handler.postDelayed(delay, 150);
                                    item.setChecked(true);      // Start activity after some delay
                                }
                                break;
                            case R.id.maps:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    intent = new Intent(DrawerActivity.this,MapsActivity.class);
                                    handler.postDelayed(delay, 150);
                                    item.setChecked(true);      // Start activity after some delay

                                }
                                break;
                            case R.id.filter:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    intent = new Intent(DrawerActivity.this, FilterActivity.class);
                                    handler.postDelayed(delay, 150);
                                    item.setChecked(true);
                                }
                                break;
                            case R.id.profile:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    intent = new Intent(DrawerActivity.this, PerfilActivity.class);
                                    handler.postDelayed(delay, 150);
                                    item.setChecked(true);
                                }
                                break;
                            case R.id.logout:
                                // Update preferences, in this case
                                // changing logging status
                                fullLayout.closeDrawer(GravityCompat.START);
                                preferences.edit().putInt(LoginActivity.LOGIN_OPTION, 0).apply();

                                Toast.makeText(DrawerActivity.this, Integer.toString(optlog), Toast.LENGTH_SHORT).show();


                                // Logout from Google
                                /*if (preferences.getInt(WelcomeScreenActivity.LOGIN_OPTION, 0) == WelcomeScreenActivity.GOOGLE_LOGIN)
                                    googleSignHandle.signOutAndRevoke();
                                else {
                                    // Return to Welcome Activity
                                    intent = new Intent(DrawerActivity.this, WelcomeScreenActivity.class);
                                    intent.putExtra("activity", "home");
                                    handler.postDelayed(delay, 150);
                                }*/
                                // Return to Welcome Activity
                                //intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                //intent.putExtra("activity", "home");
                                //handler.postDelayed(delay, 150);


                                editor.putString(LoginActivity.TAG_NAME, "");
                                editor.putString(LoginActivity.TAG_EMAIL, "");
                                editor.putString(LoginActivity.TAG_URLIMG, "");
                                editor.apply();

                                //intent.putExtra("activity", "home");


                                //prefs.edit().clear().apply();
                                if (optlog == 1){ //Facebook
                                    LoginManager.getInstance().logOut();
                                    optlog = 0;
                                    editor.putInt("optlog", 0).commit();

                                    intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else if(optlog == 2){ //Google
                                    optlog = 0;
                                    editor.putInt("optlog", 0).commit();



                                    LoginManager.getInstance().logOut();

                                    Toast.makeText(DrawerActivity.this, "Cerró sesión", Toast.LENGTH_SHORT).show();

                                  /*  Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                            new ResultCallback<Status>() {
                                                @Override
                                                public void onResult(Status status) {
                                                    // ...
                                                }
                                            });*/

                                    intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                    //Toast.makeText(this, "Cerró sesión", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();

                                } else if(optlog == 3){ //Cuenta usuario
                                    optlog = 0;
                                    editor.putInt("optlog", 0).commit();
                                    intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                break;
                        }
                        return true;
                    }
                });
    }
    protected Intent intent;

    protected Runnable delay = new Runnable() {
        @Override
        public void run() {
            startActivity(intent);
            finish();
        }
    };

    protected Intent putExtras(Class className){
        Intent intent = new Intent(this, className);
        intent.putExtra("username", extras.getString("username"));
        intent.putExtra("email", extras.getString("email"));
        return intent;
    }

    /*
    @Override
    public void signOutRevokeAccess() {
        intent = new Intent(DrawerActivity.this,WelcomeScreenActivity.class);
        intent.putExtra("activity", "home");
        startActivity(intent);
        finish();
    }*/
}
