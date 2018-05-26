package com.robert.mccormickrob_ce08.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.robert.mccormickrob_ce08.R;
import com.robert.mccormickrob_ce08.model_class.Model_Monster;

public class List_Adapter extends BaseAdapter {

    Context context;
    List<Model_Monster> monsterList;

    public List_Adapter(Context listActivity, List<Model_Monster> monsterList) {

        this.context=listActivity;
        this.monsterList=monsterList;
    }

    @Override
    public int getCount() {
        return monsterList.size();
    }

    @Override
    public Model_Monster getItem(int position) {
        return monsterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(getItem(position).getMonster_name());

        return convertView;
    }

    class ViewHolder
    {
        TextView tvName;

        ViewHolder(View view) {
            tvName = view.findViewById(R.id.tv_rowlist);
        }
    }
}
