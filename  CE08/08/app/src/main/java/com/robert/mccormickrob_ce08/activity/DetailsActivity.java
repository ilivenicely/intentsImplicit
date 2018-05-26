package com.robert.mccormickrob_ce08.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.fragment.Details_frag;
import com.robert.mccormickrob_ce08.model_class.Model_Monster;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        load_Details_Frag();
    }

    public void load_Details_Frag(){

        Model_Monster modelMonster= (Model_Monster) getIntent().getSerializableExtra("Data");

        Fragment fragment=new Details_frag();

        Bundle bundle=new Bundle();
        bundle.putInt("Id",modelMonster.getId());
        bundle.putString("Name",modelMonster.getMonster_name());
        bundle.putInt("Fur",modelMonster.getMonster_fur());
        bundle.putInt("Leg",modelMonster.getMonster_leg());

        fragment.setArguments(bundle);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_details,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
