package com.robert.mccormickrob_ce08.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.database.SQLiteHelper;
import com.robert.mccormickrob_ce08.model_class.Model_Monster;

public class Form_frag extends Fragment {

    EditText monster_name, monster_leg;
    Switch aSwitch;

    String name;
    int leg, fur = 0;

    SQLiteHelper helper;
    SQLiteDatabase sqLiteDatabase;

    boolean isField_Empty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_form, container, false);

        initViews(v);

        return v;
    }

    public void initViews(View v) {
        helper = new SQLiteHelper(getActivity());
        monster_name = v.findViewById(R.id.ed_name);
        monster_leg = v.findViewById(R.id.ed_leg);
        aSwitch = v.findViewById(R.id.switch_on_off);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fur = 1;
                } else {
                    fur = 0;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            addMonsterData();
        }
        return super.onOptionsItemSelected(item);
    }


    public void addMonsterData() {
        name = monster_name.getText().toString();
        String strLeg = monster_leg.getText().toString();
        isEmptyOrNot(name, strLeg);
        if (isField_Empty == true) {
            leg = Integer.parseInt(strLeg);
            int id =helper.addMonster(name, fur, leg);
            Toast.makeText(getActivity(), "Successfully inserted", Toast.LENGTH_LONG).show();
            clear_Field(id);
        } else {
            Toast.makeText(getActivity(), "Please fill all field", Toast.LENGTH_LONG).show();
        }
    }

    public void isEmptyOrNot(String name, String leg) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(leg)) {
            isField_Empty = false;
        } else {
            isField_Empty = true;
        }
    }

    public void clear_Field(int id) {
        monster_leg.getText().clear();
        monster_name.getText().clear();
        aSwitch.setChecked(false);
        Intent intent = new Intent();
        Model_Monster modelMonster = new Model_Monster(id,name, fur, leg);
        intent.putExtra("data", modelMonster);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}
