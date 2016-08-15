package com.example.enerjizeit.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<InfoClass> list;

    public CustomAdapter(ArrayList<InfoClass> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public InfoClass getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.row, null);
        }
        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView text = (TextView)view.findViewById(R.id.title);

        final InfoClass item = list.get(position);
        text.setText(item.getTitle());
        image.setImageResource(item.getImage());

        return view;
    }
}
