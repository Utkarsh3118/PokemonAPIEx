package com.example.pokemonapiexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.pokemonapiexample.models.Pokemon;
import com.example.pokemonapiexample.models.PokemonRequestor;
import com.example.pokemonapiexample.pokeapi.PokeApiService;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;
    private static final String TAG = " PokemonExample ";
    private int offset;
    private boolean flag;
    Toolbar toolbar;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Pokemon's List");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        listPokemonAdapter =new ListPokemonAdapter(this);
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0)
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                        if(flag)
                        {
                            if((visibleItemCount + pastVisibleItems)>=totalItemCount)
                            {
                                Log.i(TAG,"final");
                                flag = false;
                                offset+= 20;
                                obtainerData(offset);
                            }
                        }
                    }
                }
        });
        retrofit =  new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        flag =true;
        offset = 0;
        obtainerData(offset);
    }

    private void obtainerData(int offset) {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonRequestor> pokemonRequestorCall = service.obtaienerListenPokemon(20,offset);

        pokemonRequestorCall.enqueue(new Callback<PokemonRequestor>()
        {
            @Override
            public void onResponse(Call<PokemonRequestor> call, Response<PokemonRequestor> response)
            {
                flag = true;
                if(response.isSuccessful())
                {
                    PokemonRequestor pokemonRequestor = response.body();
                    ArrayList<Pokemon> ListPokemon = pokemonRequestor.getResults();
                    listPokemonAdapter.additionlistPokemon(ListPokemon);
                }
                else
                {
                    Log.e(TAG," onResponse: = "+response.errorBody());
                }
            }



            @Override
            public void onFailure(Call<PokemonRequestor> call, Throwable t)
            {
                    flag = true;
                    Log.e(TAG," onFailure: = "+t.getMessage());
            }
        });

    }
}
