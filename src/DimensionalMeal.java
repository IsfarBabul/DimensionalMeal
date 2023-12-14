import java.util.ArrayList;
import java.util.Scanner;
public class DimensionalMeal {
    private Scanner scan;
    private ArrayList<Card> foodDeck;
    private ArrayList<Card> foodDiscard;
    private ArrayList<Card> dimensionDeck;
    private ArrayList<Card> dimensionDiscard;
    private ArrayList<Card> mealDeck;
    private ArrayList<Card> mealDiscard;
    private ArrayList<Card> mealCardSuspension;
    private Player[] turnOrder;
    private int orderIndex;
    private int completeTurns;
    private Player currentPlayer;
    private boolean win;
    public DimensionalMeal() {
        foodDeck = new ArrayList<>();
        foodDiscard = new ArrayList<>();
        dimensionDeck = new ArrayList<>();
        dimensionDiscard = new ArrayList<>();
        mealDeck = new ArrayList<>();
        mealDiscard = new ArrayList<>();
        mealCardSuspension = new ArrayList<>();
        orderIndex = 0;
        win = false;
    }
    public void run(Scanner scan) {
        this.scan = scan;
        start();
        game();
    }
    //-----------------------------!!!!!!!!!!!!!!-------------------PRIVATE METHODS-------------------!!!!!!!!!!!!!!!!!------------------------------------any code below this threshold must be private--------------------
    private void start() {
        createPlayers();
        createFoodDeck();
        createDimensionDeck();
        createMealDeck();
        for (int i = 0; i < turnOrder.length; i++) {
            turnOrder[i].setMealCard((MealCard) mealDeck.get(0));
            turnOrder[i].getMealCard().setName(turnOrder[i].getPlayerName() + "'s Meal Card");
            Utility.moveCards(mealDeck, 0, mealCardSuspension, i);
        }
        currentPlayer = turnOrder[0];
    }
    private void loadGUI(Player currentPlayer) {
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
    private void game() {
        while (!win) {
            currentPlayer.increaseActions(4);
            currentPlayer.increaseActions(currentPlayer.getNextTurnActions());
            currentPlayer.setNextTurnActions(0);
            if (currentPlayer.getActionsLeft() < 0) {
                System.out.println("You still have less than 0 actions. You lose your turn.");
            }
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
        pauseSwap(currentPlayer, turnOrder);
    }
    private void pauseSwap(Player currentPlayer, Player[] turnOrder) {
        Utility.clearWindow();
        Player prevPlayer = null;
        for(int i = 1; i < turnOrder.length; i++) {
            if (turnOrder[i].equals(currentPlayer)) {
                prevPlayer = turnOrder[i - 1];
            }
        }
        if (prevPlayer == null) {
            prevPlayer = turnOrder[turnOrder.length - 1];
        }
        System.out.println("Allow time for " + prevPlayer.getPlayerName() + " to swap places with " + currentPlayer + " to prevent players from looking at each others' data.");
        Utility.clearWindow();
    }
    private void shuffleMealDeck() {
        int lengthOfArray = mealDeck.size();
        ArrayList<Card> newDeck = new ArrayList<>(lengthOfArray);
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
        ArrayList<Card> newDeck = new ArrayList<>(lengthOfArray);
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
        ArrayList<Card> newDeck = new ArrayList<>(lengthOfArray);
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
                FoodCard foodCard = null;
                switch (food) {
                    case "\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11" -> foodCard = new FruitFoodCard(food, 0, scan);
                    case "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C" -> foodCard = new VegetableFoodCard(food, 0, scan);
                    case "\uD83E\uDD69", "\uD83C\uDF64" -> foodCard = new ProteinFoodCard(food, 0, scan);
                    case "\uD83C\uDF3E", "\uD83C\uDF5E" -> foodCard = new GrainsFoodCard(food, 0, scan);
                    case "\uD83E\uDDC0", "\uD83C\uDF76" -> foodCard = new DairyFoodCard(food, 0, scan);
                }
                foodDeck.add(foodCard);
            }
        }
        for (int i = 0; i < 8; i++) {
            FoodCard foodCard = new WildFoodCard("\uD83C\uDF69", 0, scan);
            foodDeck.add(foodCard);
        }
        shuffleFoodDeck();
    }
    private void createDimensionDeck() {
        String[] dimensions = new String[]{"⚫", "┃", "⬛", "⛊", "☀"};
        for (int i = 0; i < 60; i++) {
            DimensionCard dimensionCard = new DimensionCard(dimensions[0], 0);
            dimensionDeck.add(dimensionCard);
        }
        for (int i = 0; i < 20; i++) {
            DimensionCard dimensionCard = new DimensionCard(dimensions[1],  1);
            dimensionDeck.add(dimensionCard);
        }
        shuffleDimensionDeck();
    }
    private void createMealDeck() {
        for (int i = 0; i < 20; i++) {
            MealCard mealCard = new MealCard(null, 0);
            mealDeck.add(mealCard);
        }
    }
    private void optionOutcome(int outcome) {
        switch (outcome) {
            case 0 -> playFoodCard();  // 3/5
            case 1 -> buyFoodCard();       // 1/5
            case 2 -> checkFoodCardStats();   // 2/5
            case 3 -> buyDimensionCard();   // 1/5
            case 4 -> fuse();               // 3/5
            case 5 -> enhanceFuse();         // 3/5
            case 6 -> checkFoodDiscard();            // 2/5
            case 7 -> checkDimensionDiscard();        // 2/5
            case 8 -> checkMealDiscard();             // 4/5
            case 9 -> upgradeFoodCard();           // 3/5
        }
    }
    private void playFoodCard() {

    }
    private void buyFoodCard() {

    }
    private void checkFoodCardStats() {

    }
    private void buyDimensionCard() {

    }
    private void fuse() {

    }
    private void enhanceFuse() {

    }
    private void checkFoodDiscard() {

    }
    private void checkDimensionDiscard() {

    }
    private void checkMealDiscard() {

    }
    private void upgradeFoodCard() {

    }
}

