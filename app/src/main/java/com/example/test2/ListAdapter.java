package com.example.test2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<students> studentslist;


    public ListAdapter(Activity mContext, List<students> studentslist) {
       super(mContext,R.layout.list_item,studentslist);
       this.mContext=mContext;
       this.studentslist=studentslist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=mContext.getLayoutInflater();
        View listitemview=inflater.inflate(R.layout.list_item,null,true);


        TextView ID=listitemview.findViewById(R.id.t1);
        TextView name=listitemview.findViewById(R.id.t2);
        TextView habbit=listitemview.findViewById(R.id.t3);

        students students=studentslist.get(position);


        ID.setText((students.getId()));
        name.setText(students.getName());
        habbit.setText(students.getAge());

        return  listitemview;
    }
}
