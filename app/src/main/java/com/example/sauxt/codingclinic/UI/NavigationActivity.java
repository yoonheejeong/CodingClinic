package com.example.sauxt.codingclinic.UI;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.sauxt.codingclinic.R;


public class NavigationActivity extends AppCompatActivity{

    FragmentManager fm;
    FragmentTransaction fTran;
    FragmentOne frag1;
    FragmentTwo frag2;
    FragmentThree frag3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemListener);

        Intent intent = getIntent();

        frag1 = new FragmentOne();
        frag2 = new FragmentTwo();
        frag3 = new FragmentThree();

        passInfo(intent);
        setFrag(0);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemListener

            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        public boolean onNavigationItemSelected(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.action_one:
                    //setTitle("Fragment Title One");
                    setFrag(0);
                    return true;

                case R.id.action_two:
                    //setTitle("Fragment Title Two");
                    setFrag(1);
                    return true;

                case R.id.action_three:
                    //setTitle("Fragment Title Three");
                    setFrag(2);
                    return true;

            }
            return false;

        }

    };

    public void setFrag(int n){
        fm = getSupportFragmentManager();
        fTran = fm.beginTransaction();

        switch (n){
            case 0:
                fTran.replace(R.id.frame, frag1);
                fTran.commit();
                break;

            case 1:
                fTran.replace(R.id.frame, frag2);
                fTran.commit();
                break;

            case 2:
                fTran.replace(R.id.frame, frag3);
                fTran.commit();
                break;
        }
    }




    void passInfo(Intent intent) {
        String month = intent.getStringExtra("month");
        String date = intent.getStringExtra("date");
        String birthday = month + "월 " + date + "일";

        Bundle bundle = new Bundle();
        bundle.putString("birth_string", birthday);
        frag1.setArguments(bundle);

    }

}
