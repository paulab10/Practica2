package com.paulabetancur.practica2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.paulabetancur.practica2.Fragments.BlankFragment;
import com.paulabetancur.practica2.Fragments.MapsFragment;
import com.paulabetancur.practica2.Fragments.PerfilFragment;
import com.paulabetancur.practica2.Fragments.Tab2Fragment;
import com.paulabetancur.practica2.Fragments.TabFragment;

public class DrawerActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    protected int home_menu;
    protected DrawerLayout fullLayout;
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected Bundle extras;
    private int optlog;
    private ImageView imgProfile;

    SharedPreferences prefs;


    SharedPreferences.Editor editor;

    //private GoogleSignHandle googleSignHandle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tab2Fragment fragment  = new Tab2Fragment();
        BlankFragment discos = new BlankFragment();
        //getSupportFragmentManager().beginTransaction()
                //.replace(R.id.containerView,fragment).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,discos).commit();
        getSupportActionBar().setTitle("Inicio");
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        String sname=null,semail=null,sid=null;
        Uri photoUrl=null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            sname = user.getDisplayName();
            semail = user.getEmail();
            sid = user.getUid();
            photoUrl = user.getPhotoUrl();
        }else {
            logOut();
        }

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

        // Set user
        username.setText(sname);
        // Set email
        email.setText(semail);
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
        fetchImage.execute(photoUrl.toString());
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
                        switch (item.getItemId()) {
                            case R.id.home:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    getSupportActionBar().setTitle("Inicio");
                                    //Tab2Fragment fragment  = new Tab2Fragment();
                                    BlankFragment discos = new BlankFragment();
                                    //getSupportFragmentManager().beginTransaction().replace(R.id.containerView,fragment).commit();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView,discos).commit();
                                    item.setChecked(true);      // Start activity after some delay
                                }
                                break;
                            case R.id.maps:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    getSupportActionBar().setTitle("Mapas");
                                    MapsFragment fragment = new MapsFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView, fragment).commit();
                                    item.setChecked(true);      // Start activity after some delay
                                }
                                break;
                            case R.id.filter:
                                fullLayout.closeDrawer(GravityCompat.START);
                                if (!item.isChecked()) {
                                    getSupportActionBar().setTitle("Encuentra tu discoteca");
                                    TabFragment fragment = new TabFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView, fragment).commit();
                                    item.setChecked(true);
                                }
                                break;
                            case R.id.profile:
                                fullLayout.closeDrawer(GravityCompat.START);
                                Log.d("Item check",String.valueOf(item.isChecked()));
                                if (!item.isChecked()) {
                                    getSupportActionBar().setTitle("Perfil");
                                    PerfilFragment fragment = new PerfilFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.containerView, fragment).commit();
                                    item.setChecked(true);
                                }
                                break;
                            case R.id.logout:
                                fullLayout.closeDrawer(GravityCompat.START);
                                LoginManager.getInstance().logOut();
                                FirebaseAuth.getInstance().signOut();
                                logOut();
                                break;
                        }
                        return true;
                    }
                });
    }
    protected Intent intent;

    private void logOut() {
        Intent intent = new Intent(DrawerActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }



}
