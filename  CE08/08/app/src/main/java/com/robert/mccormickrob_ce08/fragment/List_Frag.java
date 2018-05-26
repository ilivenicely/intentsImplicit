package com.robert.mccormickrob_ce08.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.activity.DetailsActivity;
import com.robert.mccormickrob_ce08.activity.FormActivity;
import com.robert.mccormickrob_ce08.adapter.List_Adapter;
import com.robert.mccormickrob_ce08.database.SQLiteHelper;
import com.robert.mccormickrob_ce08.model_class.Model_Monster;

public class List_Frag extends Fragment {

    FloatingActionButton floatingActionButton;
    ListView listView;
    LinearLayout llNoData;
    SQLiteHelper helper;
    List<Model_Monster> modelMonsters = new ArrayList<>();
    List_Adapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_list, container, false);
        helper=new SQLiteHelper(getActivity());
        llNoData = view.findViewById(R.id.llNoData);

        floatingActionButton = view.findViewById(R.id.floating_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), FormActivity.class), 101);
            }
        });

        listView = view.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("Data",modelMonsters.get(position));
                startActivityForResult(intent,102);
            }
        });
        modelMonsters.addAll(helper.getAllMonster());
        listAdapter = new List_Adapter(getActivity(), modelMonsters);
        listView.setAdapter(listAdapter);

        if (modelMonsters.isEmpty()) {
            listView.setVisibility(View.GONE);
        } else {
            listView.setVisibility(View.VISIBLE);
            llNoData.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode==101)
            {
                Model_Monster modelMonster = (Model_Monster) data.getSerializableExtra("data");
                modelMonsters.add(modelMonster);
                listAdapter.notifyDataSetChanged();
                if (llNoData.getVisibility() == View.VISIBLE) {
                    llNoData.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
            } else if (requestCode == 102) {
                modelMonsters.clear();
                modelMonsters.addAll(helper.getAllMonster());
                listAdapter.notifyDataSetChanged();

                if (modelMonsters.isEmpty()) {
                    llNoData.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    llNoData.setVisibility(View.GONE);
                }
            }

        }
    }
}
