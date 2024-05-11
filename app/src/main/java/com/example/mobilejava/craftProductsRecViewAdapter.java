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

public class craftProductsRecViewAdapter extends RecyclerView.Adapter<craftProductsRecViewAdapter.ViewHolder> {
    private static final String TAG3 = "craftProductsRecViewAdapter";
    private ArrayList<MostViewedHelperClass> items3 = new ArrayList<>();
    private Context context3;
    private RecycleViewOnClick recycleViewOnClick ;

    public craftProductsRecViewAdapter(Context context3, RecycleViewOnClick recycleViewOnClick) {
        this.context3 = context3;
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
        Log.d(TAG3,"onBindViewHolder : Called");
        MostViewedHelperClass helperClass = items3.get(position);
        holder.imagetop.setImageResource(helperClass.getImage());
        holder.title.setText(items3.get(position).getTitle());
        holder.description.setText(helperClass.getDesc());
        holder.valeur.setText(helperClass.getPrice());
    }

    @Override
    public int getItemCount() {
        return items3.size();
    }

    public void setItems3(ArrayList<MostViewedHelperClass> items3) {
        this.items3 = items3;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imagetop;
        private TextView title,description, valeur;
        RecycleViewOnClick recycleViewOnClick;

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
