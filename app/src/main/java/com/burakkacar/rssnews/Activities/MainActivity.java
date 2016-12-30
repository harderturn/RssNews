package com.burakkacar.rssnews.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.burakkacar.rssnews.Fragments.FragmentList;
import com.burakkacar.rssnews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.acivity_container);
        if (fragment==null)
        {
            fragment = new FragmentList();
            fragmentManager.beginTransaction()
                    .add(R.id.acivity_container,fragment,null)
                    .commit();
        }
    }
}
