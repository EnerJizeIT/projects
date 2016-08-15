package it.enerjize.expandablelistview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseExpandableListAdapter{

    private LayoutInflater inflater;
    /* Набор данных тебе типа ContactGroup, а не InfoLab */
    private ArrayList<ContactGroup> data;
    private Context context;

    public CustomAdapter(Context context, ArrayList<ContactGroup> data) {
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override /* возвращает количество групп     */
    public int getGroupCount() {
        return data.size();
    }

    @Override /* возвращает группу для заданной позиции */
    public ContactGroup getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override /* возвращает идентификатор строки группы для заданной позиции */
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override /* создает строку для группы, заполняет ее данными и добавляет ее в список */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.row_group, null);
        }
        ContactGroup group = data.get(groupPosition);

        TextView name = (TextView)view.findViewById(R.id.detailsName);
        name.setText(group.getName());

        return view;
    }

    @Override /* возвращает количество дочерних элементов в группе с заданной позицией */
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getContactsList().size();
    }

    @Override /* возвращает дочерний элемент для заданной позиции в группе и заданной
    позиции в списке дочерних элементов этой группы */
    public InfoLab getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getInfoLab(childPosition);
    }

    @Override /* возвращает идентификатор строки дочернего элемента для заданной
     позиции группы и заданной позиции дочернего элемента */
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override /* создает строку для дочернего элемента группы, заполняет ее данными и добавляет ее в список */
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.row_child, null);
        }
        InfoLab item = data.get(groupPosition).getInfoLab(childPosition);

        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView name = (TextView)view.findViewById(R.id.name);

        image.setImageResource(item.getPhotoID());
        name.setText(item.getName());

        return view;
    }

    @Override /* указывает, можно ли выбрать данный пункт списка */
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
