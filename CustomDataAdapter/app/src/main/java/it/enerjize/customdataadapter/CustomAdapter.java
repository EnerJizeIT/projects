package it.enerjize.customdataadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ContactItem> data;

    public CustomAdapter(Context context, ArrayList<ContactItem> contactItemList) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data = contactItemList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ContactItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.row, null/* ViewGroup root. Optional view to be the parent of the generated hierarchy. */);
        }
        ContactItem item = data.get(position);

        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView phone = (TextView)view.findViewById(R.id.phone);

        image.setImageResource(item.getPhotoID());
        name.setText(item.getName());
        phone.setText(item.getPhone());

        return view;
    }
}