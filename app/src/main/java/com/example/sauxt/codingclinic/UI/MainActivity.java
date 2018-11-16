package com.example.sauxt.codingclinic.UI;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sauxt.codingclinic.Data.Entity.Feed;
import com.example.sauxt.codingclinic.Data.Network.FeedListAdapter;
import com.example.sauxt.codingclinic.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager mLayoutManager;
    private Toolbar myToolbar;
    private RecyclerView mRecyclerView;
    private FeedListAdapter feedAdapter;
    String[] REQUIRED_PERMISSIONS  = { Manifest.permission.READ_EXTERNAL_STORAGE };
    public static final int NEW_FEED_ACTIVITY_REQUEST_CODE = 1;
    public static final int PERMISSIONS_REQUEST_CODE = 100;
    private FeedViewModel mFeedViewModel;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        getPermissions();

        toolbarSetting();

        createAdapter();

        recyclerviewSetting();

        viewModelSetting();

    }

    private void findView() {
        mLayout = findViewById(R.id.layout_main);
    }

    public void viewModelSetting(){
        // Model Provider
        mFeedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        // Observe : Model의 LiveData를 관찰
        mFeedViewModel.getAllFeeds().observe(this, new Observer<List<Feed>>() {
            @Override
            public void onChanged(@Nullable List<Feed> feeds) {
                feedAdapter.setFeeds(feeds);
            }
        });

    }


    public void getPermissions(){
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(MainActivity.this,
                REQUIRED_PERMISSIONS[0]);


        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {

            // 사용자가 퍼미션 거부를 한 적이 있는 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    REQUIRED_PERMISSIONS[0])){

                // 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해준다.
                Snackbar.make(mLayout, R.string.askPermission,
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions( MainActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);

                    }
                }).show();


            } else {
                // 사용자가 퍼미션 거부를 한 적이 없는 경우, 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {

                    // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])){

                        // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                        Snackbar.make(mLayout, R.string.reAskPermission,
                                Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();

                    } else {

                        // “다시 묻지 않음”을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                        Snackbar.make(mLayout, R.string.pickedDoNotAsk,
                                Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }


                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
        mRecyclerView.setAdapter(feedAdapter);

    }


    public void createAdapter(){
        feedAdapter = new FeedListAdapter(this, new ArrayList<Feed>(0), new FeedListAdapter.PostFeedListener() {

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
                startActivityForResult(intent, NEW_FEED_ACTIVITY_REQUEST_CODE);
                return true;

             case android.R.id.home:
                 finish();
                 return true;


            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_FEED_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            // Insert new Feed to Room Database
            Feed feed = new Feed(data.getStringExtra("comment"), data.getStringExtra("uri"));
            mFeedViewModel.insert(feed);

        }

    }


}


