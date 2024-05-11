package com.example.mobilejava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HandyCraftsRecViewAdapter extends RecyclerView.Adapter<HandyCraftsRecViewAdapter.ViewHolder> {
    private static final String TAG2 = "HandyCraftsRecViewAdapter";
    private ArrayList<MostViewedHelperClass> items2 = new ArrayList<>();
    private Context context2;
    private RecycleViewOnClick recycleViewOnClick ;

    public HandyCraftsRecViewAdapter(Context context2,RecycleViewOnClick recycleViewOnClick) {
        this.context2 = context2;
        this.recycleViewOnClick=recycleViewOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new ViewHolder(view,recycleViewOnClick);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG2,"onBindHolder : Called");
        MostViewedHelperClass helperClass = items2.get(position);
        holder.imagetop.setImageResource(helperClass.getImage());
        holder.title.setText(items2.get(position).getTitle());
        holder.description.setText(helperClass.getDesc());
        holder.valeur.setText(helperClass.getPrice());

    }

    public void setItems2(ArrayList<MostViewedHelperClass> items2) {
        this.items2 = items2;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items2.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imagetop;
        RecycleViewOnClick recycleViewOnClick;
        private TextView title,description, valeur;
        public ViewHolder(@NonNull View itemView, RecycleViewOnClick recycleViewOnClick) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imagetop = itemView.findViewById(R.id.imagetop);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            valeur = itemView.findViewById(R.id.valeur);
            this.recycleViewOnClick=recycleViewOnClick;
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleViewOnClick.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
