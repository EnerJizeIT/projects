package it.enerjize.starbuzz;

public class Food {
    private String name, description;
    private int imageId;

    private Food(String mName, String mDescription, int mImageId){
        mName = name;
        mDescription = description;
        mImageId = imageId;
    }
    public final static Food [] foods = {
            new Food("Burger", "Taste pork with bread", R.drawable.bur),
            new Food("IceCream","Icecream with some taste, for fat child", R.drawable.ice),
            new Food("Eggs", "With bread, for breakfast", R.drawable.eg)};

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public String toString(){
        return name;
    }
}
