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
            turnOrder[i].getMealCard().setName(turnOrder[i].getPlayerName());
            Utility.moveCards(mealDeck, 0, mealCardSuspension, i);
        }
        currentPlayer = turnOrder[0];
    }
    private void loadGUI() {
        MealCard mealCard = currentPlayer.getMealCard();
        System.out.println("\uD83C\uDFB4(" + dimensionDeck.size() + ") - Dimension Deck              \uD83D\uDDC2️(" + dimensionDiscard.size() + ") - Dimension Discard");
        System.out.println();
        System.out.println("\uD83E\uDDFA(" + foodDeck.size() + ") - Food Deck(it's a basket)    \uD83D\uDDD1️(" + foodDiscard.size() + ") - Food Discard");
        System.out.println();
        System.out.println("\uD83C\uDF7D️(" + mealDeck.size() + ") - Meal Deck                   ♻️(" + mealDiscard.size() + ") - Meal Discard");
        System.out.println();
        System.out.println(mealCard.getName());
        System.out.println("____________");
        System.out.println("|          |");
        for (int j = 0; j < mealCard.getFoodItem().size(); j++) {
            System.out.println("|" + mealCard.getFoodItem().get(j) + " x" + mealCard.getMultipliers().get(j) + "     |");
        }
        System.out.println("|          |");
        if (mealCard.getLevel() == 4) {
            System.out.println("|" + Utility.returnFunText("[0] \uD83C\uDFC6WIN!", 33) + "|");
        } else {
            System.out.println("|" + Utility.returnFunText("[0] " + currentPlayer.getDimensionLevel() + "-->" + (currentPlayer.getDimensionLevel() + 1),  33) + " |");
        }
        System.out.println("L__________J");
        System.out.println();
        System.out.println("Number of completed turns: " + completeTurns);
        System.out.println();
        System.out.println("Current Dimension Level: " + currentPlayer.getDimensionLevel());
        System.out.println("Actions Left: " + currentPlayer.getActionsLeft());
        System.out.println(currentPlayer.getPlayerName() + "'s Hand:");
        Utility.printElementNames(currentPlayer.getHand());
        System.out.println();
        Utility.funText("[1] Buy a Dimension Card(Cost: 1 action)", 32);
        Utility.funText("[2] Buy a Food Card (Cost: 2 actions)", 36);
        Utility.funText("[3] Upgrade a Food Card(Cost: 1 action)", 36);
        Utility.funText("[4] Play a Food Card in your hand (Cost: Whatever the card costs of food)", 36);
        Utility.funText("[5] Fuse two Dimension Cards of the same type (Cost: Level of dimension you want to get to)", 34);
        Utility.funText("[6] Enhanced Fusion (Cost: Double the Level of dimension you want to get to)", 34);
        Utility.funText("[7] Check the Stats of a card you have", 35);
        Utility.funText("[8] Check Dimension Discard", 35);
        Utility.funText("[9] Check Food Discard", 35);
        Utility.funText("[10] Check Meal Discard", 35);
        Utility.funText("[11] Check other players' stats", 35);
        Utility.funText("[12] Instructions to how each other option works", 31);
        System.out.println();
        System.out.println("Enter an Option(invalid options do nothing):");
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
                loadGUI();
                int option = scan.nextInt();
                optionOutcome(option);
            }
            loadGUI();
            System.out.println("Confirm end of turn");
            scan.nextLine();
            scan.nextLine();
            orderIndex++;
            if(orderIndex == turnOrder.length) {
                completeTurns++;
                orderIndex = 0;
            }
            currentPlayer = turnOrder[orderIndex];
            pauseSwap(currentPlayer, turnOrder);
        }
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
        System.out.println("Allow time for " + prevPlayer.getPlayerName() + " to swap places with " + currentPlayer.getPlayerName() + " to prevent players from looking at each others' data.");
        scan.nextLine();
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
    private void shuffleFoodDeck()  {
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
            mealCard.obtainCurrentRequirements();
            mealDeck.add(mealCard);
        }
    }
    private void optionOutcome(int outcome) {
        if(!currentPlayer.getHand().isEmpty()) {
            switch (outcome) {
                case 0 -> upgradeLevel();
                case 3 -> upgradeFoodCard();
                case 4 -> playFoodCard();
                case 5 -> fuse();
                case 6 -> enhanceFuse();
            }
        }
        switch (outcome) {
            case 1 -> buyDimensionCard();
            case 2 -> buyFoodCard();
            case 7 -> checkHandCardStats();
            case 8 -> checkDimensionDiscard();
            case 9 -> checkFoodDiscard();
            case 10 -> checkMealDiscard();
            case 11 -> checkOpponentStats();
            case 12 -> optionInstructions();
            case 69 -> System.out.println("Nice! Never Gonna Give You Up!");
            case 420 -> System.out.println("You needed option 12 to get here didn't you...or your that unoriginal...");
        }
        scan.nextLine();
    }
    private void upgradeLevel() {
        int levelToUpgradeTo = currentPlayer.getDimensionLevel() + 1;
        ArrayList<String> requiredFoodItems = currentPlayer.getMealCard().getFoodItem();
        ArrayList<Integer> requiredMultipliers = currentPlayer.getMealCard().getMultipliers();
        ArrayList<String> playerFoodItems = new ArrayList<>(requiredFoodItems.size());
        ArrayList<Integer> playerMultipiers = new ArrayList<>(requiredMultipliers.size());
        for (int i = 0; i < requiredFoodItems.size(); i++) {
            for (Card food : currentPlayer.getHand()) {
                if (food.getName().equals(requiredFoodItems.get(i)) && food.getLevel() == currentPlayer.getDimensionLevel()) {
                    if (food.equals(item)) {
                        if (!foodItem.contains(food)) {
                            foodItem.add(food);
                        }
                        count++;
                    }
                }
            }
        }
    }
    private void checkOpponentStats() {

    }
    private void optionInstructions() {
        Utility.clearWindow();
        Utility.funText("Options", 4);
        System.out.println("[0] Indicates an pointing from your current level to the level you can get to or show \"\uD83C\uDFC6WIN!\" if you can win.");
        System.out.println("[1] Spend 1 action to buy a Dimension card from the Dimension deck.");
        System.out.println("[2] Spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[3] Spend 1 action to upgrade 1 Food card to the next level(and not any level higher) by selecting that Food card and 1 Dimension card matching the level to upgrade to.");
        System.out.println("[4] Play one Food card from your hand and put it in the Food discard. Spend the necessary amount of actions.");
        System.out.println("[5] Fuse two Dimension cards into one of the next level by discarding two of the same Dimension cards and then drawing from a separate Fuse pile.");
        System.out.println("[6] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[7] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[8] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[9] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[10] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[11] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[12] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[69] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[420] Allows you to spend 2 actions to buy a Food card from the Food deck.");
        System.out.println("[Any other Choice] Any choice that is not one of the above or can't be done due to certain restrictions will simply do nothing and take no actions from you.");
    }
    private void playFoodCard() {
        ArrayList<Card> foodCards = new ArrayList<>();
        ArrayList<Integer> foodIndex = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i) instanceof FoodCard) {
                foodCards.add(currentPlayer.getHand().get(i));
                foodIndex.add(i);
            }
        }
        System.out.print("What food card would you like to play from the given options(starting from index 0: ");
        Utility.printElementNames(foodCards);
        System.out.println();
        int choice = scan.nextInt();
        FoodCard foodCard = (FoodCard) currentPlayer.getHand().get(foodIndex.get(choice));
        if (currentPlayer.getActionsLeft() >= foodCard.accessCost()) {
            Utility.moveCards(currentPlayer.getHand(), foodIndex.get(choice), foodDiscard);
            foodCard.accessAbility(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
            currentPlayer.decreaseActions(foodCard.accessCost());
        } else {
            System.out.println("Purchase Unsuccessful");
        }
        scan.nextLine();
        Utility.clearWindow();
    }
    private void buyFoodCard() {
        if (currentPlayer.getActionsLeft() >= 2) {
            int level = dimensionDeck.get(0).getLevel();
            Utility.moveCards(dimensionDeck, 0, dimensionDiscard);
            foodDeck.get(0).setLevel(level);
            Utility.moveCards(foodDeck, 0, currentPlayer.getHand());
            currentPlayer.decreaseActions(2);
        } else {
            System.out.println("Purchase Unsuccessful");
        }
        scan.nextLine();
        Utility.clearWindow();
    }
    private void checkHandCardStats() {
        System.out.print("Choose a card in your hand to look at (type option from 0 onward): ");
        Utility.printElementNames(currentPlayer.getHand());
        System.out.println();
        int choice = scan.nextInt();
        Card infoCard = currentPlayer.getHand().get(choice);
        Utility.clearWindow();
        System.out.println("Name: " + infoCard.getName());
        System.out.println();
        System.out.println("Description:");
        System.out.println(infoCard.accessDescription());
        System.out.println();
        System.out.println("Instruction:");
        System.out.println(infoCard.accessInstruction());
        System.out.println();
        System.out.println("Example:");
        System.out.println(infoCard.accessExample());
        System.out.println();
        if (infoCard instanceof FoodCard) {
            System.out.println("Cost: " + ((FoodCard) infoCard).accessCost());
        } else {
            System.out.println("Cost: Depends on what you do with the card.");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Click to exit.");
        scan.nextLine();
        scan.nextLine();
        Utility.clearWindow();
    }
    private void buyDimensionCard() {
        if (currentPlayer.getActionsLeft() >= 1) {
            Utility.moveCards(dimensionDeck, 0, currentPlayer.getHand());
            currentPlayer.decreaseActions(1);

        } else {
            System.out.println("Purchase Unsuccessful");
        }
        scan.nextLine();
        Utility.clearWindow();
    }
    private void fuse() {
        ArrayList<Card> dimensionCards = new ArrayList<>();
        ArrayList<Integer> dimensionIndex = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i) instanceof DimensionCard) {
                dimensionCards.add(currentPlayer.getHand().get(i));
                dimensionIndex.add(i);
            }
        }
        Card[] fusePotential = new DimensionCard[2];
        Utility.printElementNames(dimensionCards);
        System.out.print("Pick Dimension Card 1: ");
        int index1 = scan.nextInt();
        fusePotential[0] = dimensionCards.get(index1);
        System.out.print("Pick Dimension Card 2: ");
        int index2 = scan.nextInt();
        fusePotential[1] = dimensionCards.get(index2);
        int lowerIndex = Math.min(index1, index2);
        int higherIndex = Math.max(index1, index2);
        int levelOfCurrentFuse = -1;
        if (fusePotential[0].getLevel() == fusePotential[1].getLevel()) {
            levelOfCurrentFuse = fusePotential[0].getLevel();
        }
        if (levelOfCurrentFuse < 4 && currentPlayer.getActionsLeft() >= levelOfCurrentFuse + 1 && levelOfCurrentFuse != -1) {
            String[] dimensions = new String[]{"┃", "⬛", "⛊", "☀"};
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(higherIndex), dimensionDiscard);
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(lowerIndex), dimensionDiscard);
            currentPlayer.decreaseActions(levelOfCurrentFuse + 1);
            DimensionCard dimensionCard = new DimensionCard(dimensions[levelOfCurrentFuse], levelOfCurrentFuse + 1);
            currentPlayer.getHand().add(dimensionCard);
        } else {
            System.out.println("Fuse failed.");
        }
        scan.nextLine();
        Utility.clearWindow();
    }
    private void enhanceFuse() {
        ArrayList<Card> dimensionCards = new ArrayList<>();
        ArrayList<Integer> dimensionIndex = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i) instanceof DimensionCard) {
                dimensionCards.add(currentPlayer.getHand().get(i));
                dimensionIndex.add(i);
            }
        }
        int[] fusePotential = new int[4];
        Utility.printElementNames(dimensionCards);
        for (int i = 0; i < fusePotential.length; i++) {
            System.out.println("Pick Dimension Card " + (i + 1) + ": ");
            fusePotential[i] = scan.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            int higherIndex = Math.max(fusePotential[i], fusePotential[i + 1]);
            int lowerIndex = Math.min(fusePotential[i], fusePotential[i + 1]);
            fusePotential[i] = higherIndex;
            fusePotential[i + 1] = lowerIndex;
        }
        int levelOfCurrentFuse = -1;
        int fuseAttemptLevel = dimensionCards.get(fusePotential[0]).getLevel();
        int count = 0;
        for (int i = 0; i < fusePotential.length; i++) {
            if (dimensionCards.get(fusePotential[i]).getLevel() == fuseAttemptLevel) {
                count++;
            }
        }
        if (count == 4) {
            levelOfCurrentFuse = fuseAttemptLevel;
        }
        if (levelOfCurrentFuse < 3 && currentPlayer.getActionsLeft() >= (levelOfCurrentFuse + 1) * 2 && levelOfCurrentFuse != -1) {
            String[] dimensions = new String[]{"┃", "⬛", "⛊", "☀"};
            for (int i = 0; i < fusePotential.length; i++) {
                Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(i), dimensionDiscard);
            }
            currentPlayer.decreaseActions((levelOfCurrentFuse + 1) * 2);
            DimensionCard dimensionCard1 = new DimensionCard(dimensions[levelOfCurrentFuse], levelOfCurrentFuse + 2);
            DimensionCard dimensionCard2 = new DimensionCard(dimensions[levelOfCurrentFuse], levelOfCurrentFuse + 2);
            currentPlayer.getHand().add(dimensionCard1);
            currentPlayer.getHand().add(dimensionCard2);
        } else {
            System.out.println("Enhanced fuse failed.");
        }
        scan.nextLine();
        Utility.clearWindow();
    }
    private void checkFoodDiscard() {
        System.out.println("Food Discard: ");
        Utility.printElementNames(foodDiscard);
        System.out.println();
        System.out.println("Click to exit.");
        scan.nextLine();
        scan.nextLine();
        Utility.clearWindow();
    }
    private void checkDimensionDiscard() {
        System.out.println("Dimension Discard: ");
        Utility.printElementNames(dimensionDiscard);
        System.out.println();
        System.out.println("Click to exit.");
        scan.nextLine();
        scan.nextLine();
        Utility.clearWindow();
    }
    private void checkMealDiscard() {
        System.out.print("Choose a card in the meal discard to look at (type option from 0 onward): ");
        Utility.printElementNames(mealDiscard);
        System.out.println();
        int choice = scan.nextInt();
        MealCard infoCard = (MealCard) mealDiscard.get(choice);
        int origLevel = infoCard.getLevel();
        for (int i = 0; i < 4; i++) {
            infoCard.setLevel(i);
            System.out.println("_____________");
            System.out.println("|Meal Card: |");
            System.out.println("|           |");
            for (int j = 0; j <= infoCard.getFoodItem().size(); j++) {
                System.out.println("|" + infoCard.getFoodItem().get(j) + " x" + infoCard.getMultipliers().get(j) + "       |");
            }
            System.out.println("|           |");
            System.out.println("|           |");
            System.out.println("L___________J");
            System.out.println();
        }
        infoCard.setLevel(origLevel);
        System.out.println();
        System.out.println();
        System.out.println("Click to exit.");
        scan.nextLine();
        scan.nextLine();
        Utility.clearWindow();
    }
    private void upgradeFoodCard() {
        System.out.println("Choose 1 Food and 1 Dimension card(from index 0 onward): ");
        Utility.printElementNames(currentPlayer.getHand());
        System.out.println();
        System.out.println("Food card: ");
        int foodCardIndex = scan.nextInt();
        System.out.println("Dimension card: ");
        int dimensionCardIndex = scan.nextInt();
        int dimensionCardLevel = currentPlayer.getHand().get(dimensionCardIndex).getLevel();
        if (dimensionCardLevel - 1 == currentPlayer.getHand().get(foodCardIndex).getLevel()) {
            currentPlayer.getHand().get(foodCardIndex).setLevel(dimensionCardLevel);
            Utility.moveCards(currentPlayer.getHand(), dimensionCardIndex, dimensionDiscard);
        } else {
            System.out.println("Upgrade failed.");
        }
    }
}

