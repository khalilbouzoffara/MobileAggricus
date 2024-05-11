package com.example.mobilejava;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MostViewedHelperClass {
    int image;
    String title,price,desc;

    public MostViewedHelperClass(int image, String title,String price,String desc) {
        this.image = image;
        this.title = title;
        this.desc=desc;
        this.price =price;

    }

    public int getImage() {
        return this.image;
    }

    public String getTitle() {
   return  this.title ;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }
}
