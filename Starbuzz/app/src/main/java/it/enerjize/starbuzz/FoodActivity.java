package it.enerjize.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends Activity{

    public static final String FOODCONS = "it.enerjize.starbuzz.FoodActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodId = getIntent().getIntExtra(FOODCONS, 1);
        Food food = Food.foods[foodId];

        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(food.getImageId());
        photo.setContentDescription(food.getDescription());

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(food.getName());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(food.getDescription());
    }
}
