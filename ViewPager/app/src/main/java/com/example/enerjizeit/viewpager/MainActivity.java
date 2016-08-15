package com.example.enerjizeit.viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private PageAdapter adapter;
    private ArrayList<ItemLab> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContacts();

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter = new PageAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);



    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(MainActivity.this, String.format("Selected page %d", position+1), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initContacts() {
        list = new ArrayList<>();

        list.add(new ItemLab(
                "Jacob Anderson", "412412411", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Emily Duncan", "161863187", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Michael Fuller", "896443658", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Emma Greenman", "964990543", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Joshua Harrison", "759285086", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Madison Johnson", "950285777", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Matthew Cotman", "687699999", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Olivia Lawson", "161863187", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Andrew Chapman",  "546599645", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Daniel Honeyman", "876545644", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Isabella Jackson", "907868756", about, R.drawable.kiss));
        list.add(new ItemLab(
                "William Patterson", "687699693", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Joseph Godwin", "965467575", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Samantha Bush", "907865645", about, R.drawable.kiss));
        list.add(new ItemLab(
                "Christopher Gateman", "896874556", about, R.drawable.kiss));

    }
    private String about = "about";
}
