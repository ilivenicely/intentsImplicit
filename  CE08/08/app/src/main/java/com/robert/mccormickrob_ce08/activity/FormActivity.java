package com.robert.mccormickrob_ce08.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.fragment.Form_frag;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        load_form_frag();
    }

    public void load_form_frag(){
        Fragment fragment=new Form_frag();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_form,fragment);
        fragmentTransaction.commit();
    }
}
