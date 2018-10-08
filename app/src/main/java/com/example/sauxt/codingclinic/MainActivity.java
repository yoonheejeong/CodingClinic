package com.example.sauxt.codingclinic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ImoticonInfo> imoticonInfoArrayList = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarSetting();

        recyclerviewSetting();

        addInfoToArrayList();

        createAdapter();



        mRecyclerView.setAdapter(myAdapter);


    }

    public void toolbarSetting(){

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

    }

    public void recyclerviewSetting(){
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    public void addInfoToArrayList(){

        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_game_over, "So sad"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_ddr, "So exciting"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_tv, "So funny"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_game_over, "So sad"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_ddr, "So exciting"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_tv, "So funny"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_game_over, "So sad"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_ddr, "So exciting"));
        imoticonInfoArrayList.add(new ImoticonInfo(R.drawable.img_tv, "So funny"));

    }

    public void createAdapter(){
        myAdapter = new MyAdapter(imoticonInfoArrayList);
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_menu:
                return true;

             case android.R.id.home: {
                 finish();
                 return true;
             }

            default:

                return super.onOptionsItemSelected(item);

        }
    }

}

