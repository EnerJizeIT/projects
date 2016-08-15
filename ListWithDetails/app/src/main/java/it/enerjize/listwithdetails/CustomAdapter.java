package it.enerjize.listwithdetails;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<InfoLab> list;
    private LayoutInflater inflater;
    private Context context;


    public CustomAdapter(Context context, ArrayList<InfoLab> list) {
        this.list = list;
        this.context = context;
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
        /* Если строка создавалась ранее (convertView != null), то ее прорисовывать не надо */
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_row, null);
        }

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView name = (TextView) view.findViewById(R.id.name);

        /* Читаем контакт из списка контактов */
        final InfoLab item = list.get(position);
        /* Заполняем строку данными */
        image.setImageResource(item.getPhotoID());
        name.setText(item.getName());

        ImageView edit = (ImageView) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog(item);
            }
        });

        return view;
    }
    private void openEditDialog(final InfoLab item) {
        LayoutInflater dlgInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = dlgInflater.inflate(R.layout.edit_row, null);

        final EditText name = (EditText)root.findViewById(R.id.editName);
        final EditText phone = (EditText)root.findViewById(R.id.editPhone);
        final EditText email = (EditText)root.findViewById(R.id.editEmail);
        final EditText about = (EditText)root.findViewById(R.id.editAbout);

        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());
        about.setText(item.getAbout());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(root);
        builder.setMessage("Edit Contact");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setName(name.getText().toString());
                item.setPhone(phone.getText().toString());
                item.setEmail(email.getText().toString());
                item.setAbout(about.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setCancelable(true);
        builder.create();
        builder.show();
    }

}
