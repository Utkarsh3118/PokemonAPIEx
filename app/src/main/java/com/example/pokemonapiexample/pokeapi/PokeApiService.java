package com.example.pokemonapiexample.pokeapi;

import com.example.pokemonapiexample.models.PokemonRequestor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService
{
    @GET("pokemon")
  Call<PokemonRequestor> obtaienerListenPokemon(@Query("limit") int limit,@Query("offset") int offset);


}
