package com.example.pokemonapiexample;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PokemonDetail extends Fragment {

    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if(instance==null)
            instance = new PokemonDetail();
        return instance;
    }


    public PokemonDetail() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
    }

}
