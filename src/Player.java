import java.util.ArrayList;

public class Player {
    private final String playerName;
    private ArrayList<FoodCard> foodHand;
    private ArrayList<DimensionCard> dimensionHand;
    private MealCard mealCard;
    private int dimensionLevel;
    private int actionsLeft;
    private ArrayList<MealCard> mealCompletion;
    public Player(String name) {
        playerName = name;
        initializeInstances();
    }
    public Player() {
        String[] names = {"Abby", "Nicolas", "Gabriel", "Macy", "Miller", "David", "Rick", "Thomas", "Edward", "Duolingo", "Toaster", "Amanda"};
        int randString = (int) (Math.random() * 12);
        String name = names[randString];
        int randNum = (int) (Math.random() * Integer.MAX_VALUE);
        playerName = name + randNum;
        initializeInstances();
    }
    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<FoodCard> getFoodHand() {
        return foodHand;
    }

    public ArrayList<DimensionCard> getDimensionHand() {
        return dimensionHand;
    }

    public int getDimensionLevel() {
        return dimensionLevel;
    }

    public MealCard getMealCard() {
        return mealCard;
    }

    public void setMealCard(MealCard mealCard) {
        this.mealCard = mealCard;
    }

    public ArrayList<MealCard> getMealCompletion() {
        return mealCompletion;
    }

    public int getActionsLeft() {
        return actionsLeft;
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
    private void initializeInstances() {
        foodHand = new ArrayList<>(0);
        dimensionHand = new ArrayList<>(0);
        dimensionLevel = 0;
        actionsLeft = 0;
    }
}
