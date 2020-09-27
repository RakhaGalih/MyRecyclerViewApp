package com.example.myrecyclerviewapp;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
private RecyclerView rvHeroes;
private ArrayList<Hero> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View _mw = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(_mw);
        setTitle("Mode List");

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerList();
    }

    private void showSelectedHero(Hero hero) {
        Toast.makeText(this, "Kamu memilih " + hero.getName(), Toast.LENGTH_SHORT).show();
    }


    private void showRecyclerList() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallback(new GridHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showRecyclerCardView(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHeroes.setAdapter(cardViewHeroAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.action_list:
                setTitle("Mode List");
                showRecyclerList();
                break;
            case R.id.action_grid:
                setTitle("Mode Grid");
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                setTitle("Mode CardView");
                showRecyclerCardView();
                break;
        }
    }

}