package it.enerjize.starbuzz;

public class Drink {
    // Каждый объект Drink состоит из поле имени, описания и идентификатора ресурса изображения.
    private String name;
    private String description;
    private int imageResourceId;

    //drinks - Массив с элементами Drink
    public static final Drink[] drinks =
            {
            new Drink("Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte),
                    //R.drawable адресс изображения
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter)

            };

    //Конструктор Drink
    private Drink(String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription()
            // Get методы для приватных переменных
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public String toString()
    {
        // В качестве строкового представления Drink используется название напитка
        return this.name;
    }
}