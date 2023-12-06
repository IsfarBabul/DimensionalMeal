import java.util.ArrayList;
import java.util.Scanner;
public class DimensionalMeal {
    private Scanner scan;
    private ArrayList<FoodCard> foodDeck;
    private ArrayList<FoodCard> foodDiscard;
    private ArrayList<DimensionCard> dimensionDeck;
    private ArrayList<DimensionCard> dimensionDiscard;
    private ArrayList<MealCard> mealDeck;
    private ArrayList<MealCard> mealDiscard;
    private Player[] turnOrder;
    private int orderIndex;
    private int completeTurns;
    private Player currentPlayer;
    private boolean win;
    public DimensionalMeal() {
        foodDeck = new ArrayList<FoodCard>();
        foodDiscard = new ArrayList<FoodCard>();
        dimensionDeck = new ArrayList<DimensionCard>();
        dimensionDiscard = new ArrayList<DimensionCard>();
        mealDeck = new ArrayList<MealCard>();
        mealDiscard = new ArrayList<MealCard>();
        orderIndex = 0;
        win = false;
    }
    public void start(Scanner scan) {
        this.scan = scan;
        setup();
        while (!win) {
            currentPlayer.increaseActions(4);
            while (currentPlayer.getActionsLeft() > 0) {
                loadGUI(turnOrder[0]);
                int option = scan.nextInt();
                optionOutcome(option);
            }
            orderIndex++;
            if(orderIndex == turnOrder.length) {
                completeTurns++;
                orderIndex = 0;
            }
            currentPlayer = turnOrder[orderIndex];
        }
    }
    private void setup() {
        createPlayers();
        createFoodDeck();
        createDimensionDeck();
        for (Player player : turnOrder) {
            giveMealCard(player);
        }
        currentPlayer = turnOrder[0];
    }
    public void loadGUI(Player currentPlayer) {
        System.out.println("\uD83C\uDFB4(65) - Dimension Deck              \uD83D\uDDC2️(3) - Dimension Discard");
        System.out.println();
        System.out.println("\uD83E\uDDFA(69) - Food Deck(it's a basket)    \uD83D\uDDD1\uFE0F(7) - Food Discard");
        System.out.println();
        System.out.println("\uD83C\uDF7D\uFE0F(14) - Meal Deck                   ♻️(12) - Meal Discard");
        System.out.println("_____________");
        System.out.println("|Meal Card: |");
        System.out.println("|           |");
        System.out.println("|\uD83E\uDD69x2       |");
        System.out.println("|\uD83E\uDD6Cx1       |");
        System.out.println("|\uD83C\uDF64x1       |");
        System.out.println("|\uD83E\uDED0x1       |");
        System.out.println("|           |");
        System.out.println("| \uD83C\uDFC6WIN!!!  |");
        System.out.println("L___________J");
        System.out.println();
        System.out.println("Current Dimension Level: 4");
        System.out.println("Actions Left: 3");
        System.out.println("[Player name]'s Hand:");
        System.out.println("|\uD83E\uDD694|\uD83E\uDDC00|\uD83C\uDF113|\uD83C\uDF3E1|\uD83C\uDF3E2|\uD83E\uDD6C2|\uD83C\uDF690|┃|┃|⛊|☀| ");
        System.out.println("[0] Play a Food Card in your hand (Cost: Whatever the card costs of food)");
        System.out.println("[1] Buy a Food Card (Cost: 2 actions)");
        System.out.println("[2] Check the Stats of a Food Card you have");
        System.out.println("[3] Buy a Dimension Card(Cost: 1 action)");
        System.out.println("[4] Fuse two Dimension Cards of the same type (Cost: Level of dimension you want to get to)");
        System.out.println("[5] Enhanced Fusion (Cost: Level of dimension you want to get to + 2 actions)");
        System.out.println("[6] Check Food Discard");
        System.out.println("[7] Check Dimension Discard");
        System.out.println("[8] Check Meal Discard");
        System.out.println("[9] Upgrade a Food Card(Cost: 1 action)");
        System.out.println();
        System.out.println("Enter an Option:");
    }
    //-----------------------------!!!!!!!!!!!!!!-------------------PRIVATE METHODS-------------------!!!!!!!!!!!!!!!!!------------------------------------any code below this threshold must be private--------------------
    private void shuffleMealDeck() {
        int lengthOfArray = mealDeck.size();
        ArrayList<MealCard> newDeck = new ArrayList<>(lengthOfArray);
        while(!mealDeck.isEmpty()) {
            int randNum = (int) (Math.random() * (lengthOfArray - 1));
            newDeck.add(mealDeck.get(randNum));
            mealDeck.remove(randNum);
            lengthOfArray = mealDeck.size();
        }
        mealDeck = newDeck;
    }
    private void shuffleDimensionDeck() {
        int lengthOfArray = dimensionDeck.size();
        ArrayList<DimensionCard> newDeck = new ArrayList<>(lengthOfArray);
        while(!dimensionDeck.isEmpty()) {
            int randNum = (int) (Math.random() * (lengthOfArray - 1));
            newDeck.add(dimensionDeck.get(randNum));
            dimensionDeck.remove(randNum);
            lengthOfArray = dimensionDeck.size();
        }
        dimensionDeck = newDeck;
    }
    private void shuffleFoodDeck() {
        int lengthOfArray = foodDeck.size();
        ArrayList<FoodCard> newDeck = new ArrayList<>(lengthOfArray);
        while(!foodDeck.isEmpty()) {
            int randNum = (int) (Math.random() * (lengthOfArray - 1));
            newDeck.add(foodDeck.get(randNum));
            foodDeck.remove(randNum);
            lengthOfArray = foodDeck.size();
        }
        foodDeck = newDeck;
    }
    private void createPlayers() {
        System.out.println("How many players would like to play? ");
        int numPlayers = scan.nextInt();
        turnOrder = new Player[numPlayers];
        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("What is Player " + i + "'s name?");
            Player player = new Player();
            turnOrder[i - 1] = player;
            System.out.println(player.getPlayerName());   //testing purposes
        }
    }
    private void createFoodDeck() {
        String[] foods = new String[]{"\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11", "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C", "\uD83E\uDD69", "\uD83C\uDF64", "\uD83C\uDF3E", "\uD83C\uDF5E", "\uD83E\uDDC0", "\uD83C\uDF76"};
        for (String food : foods) {
            for (int i = 0; i < 10; i++) {
                FoodCard foodCard = new FoodCard(food, scan);
                foodDeck.add(foodCard);
            }
        }
        for (int i = 0; i < 8; i++) {
            FoodCard foodCard = new FoodCard("\uD83C\uDF69", scan);
            foodDeck.add(foodCard);
        }
        shuffleFoodDeck();
    }
    public void createDimensionDeck() {
        String[] dimensions = new String[]{"⚫", "┃", "⬛", "⛊", "☀"};
        for (int i = 0; i < 60; i++) {
            DimensionCard dimensionCard = new DimensionCard(dimensions[0]);
        }
        for (int i = 0; i < 20; i++) {
            DimensionCard dimensionCard = new DimensionCard(dimensions[1]);
        }
        shuffleDimensionDeck();
    }
    public void giveMealCard(Player player) {
        MealCard mealCard = new MealCard(player.getDimensionLevel());
        player.setMealCard(mealCard);
    }
    public void optionOutcome(int outcome) {

    }
}

