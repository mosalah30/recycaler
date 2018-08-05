package com.example.mosalah.recycaler;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Main1Activity extends AppCompatActivity {
     public  static FragmentManager fragmentManager;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        if (findViewById(R.id.Active)!=null){
            if(savedInstanceState!=null){
                return;
            }
             fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.Active, new BlankFragment2()).commit();

        }


    }


}


