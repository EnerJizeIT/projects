package it.enerjize.listfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import android.support.v4.app.ListFragment;


public class MasterFragment extends ListFragment {
    private ArrayList<ContactItem> list;
    private CustomAdapter adapter;


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContacts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        adapter = new CustomAdapter(getActivity(), list);
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        ContactItem item = adapter.getItem(position);

        ((MainActivity)getActivity()).getDetailsFragment().refreshContactDetails(item);
    }

    private void initContacts() {
        list = new ArrayList<ContactItem>();
        // Заполняем список контактов
        list.add(new ContactItem(
                "Jacob Anderson", "412412411", "mail", R.drawable.a1));
        list.add(new ContactItem(
                "Emily Duncan", "161863187", "mail", R.drawable.a2));
        list.add(new ContactItem(
                "Michael Fuller", "896443658", "mail", R.drawable.a3));
        list.add(new ContactItem(
                "Emma Greenman", "964990543", "mail", R.drawable.a4));
        list.add(new ContactItem(
                "Joshua Harrison", "759285086", "mail", R.drawable.a5));
        list.add(new ContactItem(
                "Madison Johnson", "950285777", "mail", R.drawable.a6));
        list.add(new ContactItem(
                "Matthew Cotman", "687699999", "mail", R.drawable.a7));
        list.add(new ContactItem(
                "Olivia Lawson", "161863187", "mail", R.drawable.a8));
        list.add(new ContactItem(
                "Andrew Chapman", "546599645", "mail", R.drawable.a9));
        list.add(new ContactItem(
                "Daniel Honeyman", "876545644", "mail", R.drawable.a10));
        list.add(new ContactItem(
                "Isabella Jackson", "907868756", "mail", R.drawable.a11));
        list.add(new ContactItem(
                "William Patterson", "687699693", "mail", R.drawable.a12));
        list.add(new ContactItem(
                "Joseph Godwin", "965467575", "mail", R.drawable.a13));
        list.add(new ContactItem(
                "Samantha Bush", "907865645", "mail", R.drawable.a14));
        list.add(new ContactItem(
                "Christopher Gateman", "896874556", "mail", R.drawable.a15));
    }

}
