package com.example.enerjizeit.crimeintentex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class CrimeListFragment extends Fragment {
    public CrimeListFragment() {}
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private boolean subTitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            subTitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        MenuItem subTitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (subTitleVisible) {
            subTitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subTitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_new_crime){
            Crime crime = new Crime();
            CrimeLab.getCrimeLab(getActivity()).addCrime(crime);
            Intent intent = PagerActivity.newIntent(getActivity(), crime.getId());
            startActivity(intent);
            return  true;
            /* После того как команда меню будет обработана, верните true; тем самым вы сообщаете, что дальнейшая обработка не
нужна. Секция else вызывает реализацию суперкласса, если идентификатор
команды не известен в вашей реализации. */
        } if (item.getItemId() == R.id.menu_item_show_subtitle){
            subTitleVisible = !subTitleVisible;
            getActivity().invalidateOptionsMenu();
            updateSubtitle();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subTitleVisible);
    }

    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, String.valueOf(crimeCount));
        if (!subTitleVisible){
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    public void updateUI() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimeList = crimeLab.getCrimes();
        if (adapter == null) {
            adapter = new RecyclerAdapter(crimeList, getActivity());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setCrimes(crimeList);
            adapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }
}
