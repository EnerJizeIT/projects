package it.enerjize.beeradvisor;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends Activity
{
    private BeerExpert beerExpert = new BeerExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }
    public void onClickFindBeer(View view)
    {
        // получить ссылку на TextView
        TextView brands = (TextView) findViewById(R.id.brands);
        // получить ссылку на Spinner
        Spinner color = (Spinner) findViewById(R.id.color);
        // получить вариант, выбранный в Spinner
        String beerType = String.valueOf(color.getSelectedItem());
        // вывести выбраный вариант
        brands.setText(beerType);
        // получить рекомедации от класса BeerExpert
        List<String> brandsList = beerExpert.getBrands(beerType); // получить контейнер List с сортами пива.
        StringBuilder stringBuilder = new StringBuilder(); // построить String по данным из List

        for( String brand : brandsList)
        {
            stringBuilder.append(brand).append('\n');
        }
        // вывести результаты в надписи
        brands.setText(stringBuilder);
    }
}
