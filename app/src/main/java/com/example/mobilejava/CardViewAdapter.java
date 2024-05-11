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

public class CardViewAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<MyModel> modelArrayList ;

    public CardViewAdapter(Context context, ArrayList<MyModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item,container,false);
        ImageView imagetop=view.findViewById(R.id.imagetop);
        TextView title=view.findViewById(R.id.title);
        TextView description=view.findViewById(R.id.description);
        MyModel model=modelArrayList.get(position);
        String titletv=model.getTitle();
        String des=model.getDescription();
        int imagee=model.getImage();
        imagetop.setImageResource(imagee);
        title.setText(titletv);
        description.setText(des);

        container.addView(view,position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
