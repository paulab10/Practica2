package com.paulabetancur.practica2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Discotecas
{
    private String image;
    private String name;
    private String dir;
    private String price;
    private String music;
    private String tel;

    public Discotecas(String image, String name, String dir, String tel, String music, String price)
    {
        this.name = name;
        this.image = image;
        this.music = music;
        this.dir = dir;
        this.price = price;
        this.tel = tel;

    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public String getPrice() {
        return price;
    }

    public String getMusic() {
        return music;
    }

    public String getTel() {
        return tel;
    }
}

