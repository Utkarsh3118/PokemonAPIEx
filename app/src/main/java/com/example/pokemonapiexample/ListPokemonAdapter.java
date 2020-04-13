package com.example.pokemonapiexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemonapiexample.Interface.IItemClickListner;
import com.example.pokemonapiexample.models.Pokemon;

import java.util.ArrayList;

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public ListPokemonAdapter(Context context) {
        this.context=context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListPokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPokemonAdapter.ViewHolder holder, int position) {

        final Pokemon p = dataset.get(position);
        holder.numberTextview.setText(p.getNumber()+". "+p.getName());

        holder.setiItemClickListner(new IItemClickListner() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, "Clicked at pokemon: "+p.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(context)
                .load("https://pokeres.bastionbot.org/images/pokemon/"+p.getNumber()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.PhotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void additionlistPokemon(ArrayList<Pokemon> listPokemon) {

        dataset.addAll(listPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView PhotoImageView;
        private TextView numberTextview;

        IItemClickListner iItemClickListner;
        public void setiItemClickListner(IItemClickListner iItemClickListner) {
            this.iItemClickListner = iItemClickListner;
        }


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            PhotoImageView = itemView.findViewById(R.id.PhotoImageView);
            numberTextview = itemView.findViewById(R.id.numberTextView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            iItemClickListner.onClick(view,getAdapterPosition());
        }
    }
}
