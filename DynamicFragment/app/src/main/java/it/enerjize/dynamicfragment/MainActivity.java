package it.enerjize.dynamicfragment;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import it.enerjize.dynamicfragment.fragment.OneFragment;
import it.enerjize.dynamicfragment.fragment.TwoFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener, View.OnLongClickListener {

    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Button btn1, btn2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        manager = getSupportFragmentManager();

        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();

        btn1 = (Button)findViewById(R.id.btnAdd);
        btn2 = (Button)findViewById(R.id.btnRemove);
        btn1.setOnLongClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnLongClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        if(v.getId() == R.id.btnAdd){
            if(manager.findFragmentByTag(OneFragment.TAG) == null ) {
                transaction.add(R.id.container, mOneFragment, OneFragment.TAG);
                transaction.commit();
            } else {
                Toast.makeText(MainActivity.this, "Сначала удали", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.btnRemove){
            if(manager.findFragmentByTag(OneFragment.TAG) != null ) {
            Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
            transaction.remove(mOneFragment);
        } else {
            Toast.makeText(MainActivity.this, "ШНЯГА", Toast.LENGTH_LONG).show();
        }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        transaction = manager.beginTransaction();
        if(manager.findFragmentByTag(TwoFragment.TAG) == null ) {
            if (v.getId() == R.id.btnAdd) {
                transaction.add(R.id.container, mTwoFragment, mTwoFragment.TAG);
                transaction.commit();
            } else {
                Toast.makeText(MainActivity.this, "Сначала удали", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.btnRemove){
            if(manager.findFragmentByTag(TwoFragment.TAG) != null) {
                Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                transaction.remove(mTwoFragment);
            } else {
                Toast.makeText(MainActivity.this, "ШНЯГА", Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
}
