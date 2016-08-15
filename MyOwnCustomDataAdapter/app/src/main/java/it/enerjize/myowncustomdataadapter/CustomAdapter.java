package it.enerjize.myowncustomdataadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<InfoLab> list;
    private LayoutInflater inflater;
    public CustomAdapter(Context context, ArrayList<InfoLab> list) {
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override /* возвращает количество элементов списка */
    public int getCount() {
        return list.size();
    }
    @Override /* возвращает элемент списка, находящийся в заданной позиции */
    public InfoLab getItem(int position) {
        return list.get(position);
    }
    @Override /* возвращает идентификатор строки списка */
    public long getItemId(int position) {
        return 0;
    }
    @Override /* создает строку списка, заполняет ее данными и добавляет ее в список. */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(convertView == null){/* Если строка создавалась ранее (convertView != null), то ее прорисовывать не надо */
            view = inflater.inflate(R.layout.list_row, null);
        }
        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView phone = (TextView)view.findViewById(R.id.phone);
        TextView email = (TextView)view.findViewById(R.id.email);
        /* Читаем контакт из списка контактов */
        InfoLab item = list.get(position);
        /* Заполняем строку данными */
        image.setImageResource(item.getPhotoID());
        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());

        return view;
    }
}
