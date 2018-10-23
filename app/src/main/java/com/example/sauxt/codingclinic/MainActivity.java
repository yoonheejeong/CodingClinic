package com.example.sauxt.codingclinic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager mLayoutManager;
    private Toolbar myToolbar;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = ApiUtils.getGitHubService();

        toolbarSetting();

        createAdapter();

        recyclerviewSetting();

        loadUsers();

    }

    public void loadUsers(){

        service.getGitHubUsers().enqueue(new Callback<List<GitUsers>>() {

            @Override
            public void onResponse(Call<List<GitUsers>> call, Response<List<GitUsers>> response) {
                if(response.isSuccessful()){
                    myAdapter.updateLists(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<GitUsers>> call, Throwable t) {

            }
        });


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
        mRecyclerView.setAdapter(myAdapter);

    }


    public void createAdapter(){
        myAdapter = new MyAdapter(this, new ArrayList<GitUsers>(0), new MyAdapter.PostUserListener() {
            @Override
            public void onPostClick(Integer id) {
                Toast.makeText(MainActivity.this, "Post id is " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_menu:
                Intent intent = new Intent(MainActivity.this , UploadActivity.class);
                startActivity(intent);
                return true;

             case android.R.id.home:
                 finish();
                 return true;


            default:

                return super.onOptionsItemSelected(item);

        }
    }

}


