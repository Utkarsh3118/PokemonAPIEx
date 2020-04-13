package com.example.pokemonapiexample.models;

import java.util.ArrayList;

public class PokemonRequestor {

    private ArrayList<Pokemon>  results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
