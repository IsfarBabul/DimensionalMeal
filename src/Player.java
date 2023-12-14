import java.util.ArrayList;

public class Player {
    private final String playerName;
    private ArrayList<Card> hand;
    private MealCard mealCard;
    private int dimensionLevel;
    private int actionsLeft;
    private int nextTurnActions;
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

    public ArrayList<Card> getHand() {
        return hand;
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

    public int getNextTurnActions() {
        return nextTurnActions;
    }

    public void setNextTurnActions(int nextTurnActions) {
        this.nextTurnActions = nextTurnActions;
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
    public void increaseNextTurnActions(int increment) {
        nextTurnActions += increment;
    }
    public void decreaseNextTurnActions(int decrement) {
        nextTurnActions -= decrement;
    }
    private void initializeInstances() {
        hand = new ArrayList<>(0);
        dimensionLevel = 0;
        actionsLeft = 0;
    }
}
