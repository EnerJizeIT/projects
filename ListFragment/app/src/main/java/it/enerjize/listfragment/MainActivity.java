package it.enerjize.listfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class MainActivity extends FragmentActivity {
    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsFragment = (DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
    }
    public DetailsFragment getDetailsFragment(){
        return detailsFragment;
    }
}
