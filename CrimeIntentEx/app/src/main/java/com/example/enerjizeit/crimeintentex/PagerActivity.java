package com.example.enerjizeit.crimeintentex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class PagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Crime> crimeList;
    private static final String EXTRA_CRIME_ID = "com.example.enerjizeit.crimeintentex.crime_id";
    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, PagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        viewPager = (ViewPager)findViewById(R.id.activity_pager);
        crimeList = CrimeLab.getCrimeLab(this).getCrimes();
        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimeList.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }
            @Override
            public int getCount() {
                return crimeList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return crimeList.get(position).getTitle();
            }
        });
         /* найдите индекс отображаемого преступления; для этого переберите и проверьте
         идентификаторы всех преступлений. Когда вы найдете экземпляр Crime, у которого
          поле mId совпадает с crimeId в дополнении интента, измените текущий элемент по
           индексу найденного объекта Crime. */
        for(int i=0; i<crimeList.size(); i++){
            if(crimeList.get(i).getId().equals(id)){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
