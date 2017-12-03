package com.paulabetancur.practica2.Fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.paulabetancur.practica2.FetchImage;
import com.paulabetancur.practica2.LoginActivity;
import com.paulabetancur.practica2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    public PerfilFragment() {
        // Required empty public constructor
    }

    private TextView tNombre, tContrasena;
    private ImageView imgProfile;
    String sname=null,semail=null,sid=null;
    Uri photoUrl=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        tNombre = (TextView) view.findViewById(R.id.tNombre);
        tContrasena = (TextView) view.findViewById(R.id.tContrasena);
        imgProfile = (ImageView) view.findViewById(R.id.profileImg);
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
        tNombre.setText(sname);   // User name
        tContrasena.setText(semail);  // User email

        FetchImage fetchImage = new FetchImage(getActivity(), new FetchImage.AsyncResponse() {
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
        fetchImage.execute(photoUrl.toString());


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void logOut() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
