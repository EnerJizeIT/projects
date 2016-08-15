package it.enerjize.workout;

public class Workout {
    private String name, descrition;

    public static final Workout[] workouts = {
            new Workout("The Limb Loosener", "5 Handstand push-ups\n10 1-legged squats \n15 Pull-ups"),
            new Workout("Core Agony", "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special", "5 Pull-ups\n10 Pull-ups\n15 Squats"),
            new Workout("Strength and Length", "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    };

    private Workout(String name, String descrition){
        this.name = name;
        this.descrition = descrition;
    }

    public String getDescrition(){
        return descrition;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return  this.name;
    }
}
