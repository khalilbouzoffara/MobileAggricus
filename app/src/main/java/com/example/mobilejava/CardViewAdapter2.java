package com.example.mobilejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class CardViewAdapter2 extends PagerAdapter {
    private Context context;
    private ArrayList<MostViewedHelperClass> modelArrayList2 ;

    public CardViewAdapter2(Context context, ArrayList<MostViewedHelperClass> modelArrayList) {
        this.context = context;
        this.modelArrayList2 = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList2.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_2,container,false);
        ImageView imagetop=view.findViewById(R.id.imagetop);
        TextView title=view.findViewById(R.id.title);
        TextView description=view.findViewById(R.id.description);
        TextView prize=view.findViewById(R.id.valeur);

        MostViewedHelperClass model=modelArrayList2.get(position);

        String price = model.getPrice();
        String titletv=model.getTitle();
        String des=model.getDesc();
        int imagee=model.getImage();
        imagetop.setImageResource(imagee);
        title.setText(titletv);
        prize.setText(price);
        description.setText(des);
        container.addView(view,position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

