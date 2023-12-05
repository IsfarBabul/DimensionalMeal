import java.util.ArrayList;

public class Player {
    private final String playerName;
    private ArrayList<FoodCard> foodHand;
    private ArrayList<DimensionCard> dimensionHand;
    private int dimensionLevel;
    private int actionsLeft;
    private ArrayList<MealCard> mealCompletion;
    public Player(String name) {
        playerName = name;
    }
    public Player() {
        String[] names = {"Abby", "Nicolas", "Gabriel", "Macy", "Miller", "David", "Rick", "Thomas", "Edward", "Duolingo", "Toaster", "Amanda"};
        int randString = (int) (Math.random() * 12);
        String name = names[randString];
        int randNum = (int) (Math.random() * Integer.MAX_VALUE);
        playerName = name + randNum;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void increaseDimensionLevel(int incrementLevel) {
        dimensionLevel += incrementLevel;
    }
    public void decreaseActions(int decrement) {
        actionsLeft -= decrement;
    }
    public void increaseActions(int increment) {
        actionsLeft += increment;
    }
}
