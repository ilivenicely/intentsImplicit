package com.robert.mccormickrob_ce08.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.database.SQLiteHelper;

public class Details_frag extends Fragment {

    TextView txt_name, txt_fur, txt_leg;
    String name;
    int id;
    int fur;
    int leg;
    SQLiteHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_delete, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            helper.deleteMonster(id);
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }else if (item.getItemId()==R.id.share){
            shareData();
        }
        return super.onOptionsItemSelected(item);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_details, container, false);

        helper=new SQLiteHelper(getActivity());

        txt_name = view.findViewById(R.id.tv_name);
        txt_fur = view.findViewById(R.id.tv_fur);
        txt_leg = view.findViewById(R.id.tv_legs);

        Bundle bundle = getArguments();
        id=bundle.getInt("Id");
        name = bundle.getString("Name");
        fur = bundle.getInt("Fur");
        leg = bundle.getInt("Leg");


        txt_name.setText(name);
        if (fur == 1) {
            txt_fur.setText("Yes");
        } else {
            txt_fur.setText("No");
        }
        txt_leg.setText(leg + "");

        return view;
    }

    private void shareData()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Name:"+name+" Fur:"+fur+" Leg"+leg);
        startActivity(Intent.createChooser(intent, "Share"));
    }

}
