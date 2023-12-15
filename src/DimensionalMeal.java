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
        MealCard mealCard = currentPlayer.getMealCard();
        System.out.println("\uD83C\uDFB4(" + dimensionDeck.size() + ") - Dimension Deck              \uD83D\uDDC2️(" + dimensionDiscard.size() + ") - Dimension Discard");
        System.out.println();
        System.out.println("\uD83E\uDDFA(" + foodDeck.size() + ") - Food Deck(it's a basket)    \uD83D\uDDD1️(" + foodDiscard.size() + ") - Food Discard");
        System.out.println();
        System.out.println("\uD83C\uDF7D️(" + mealDeck.size() + ") - Meal Deck                   ♻️(" + mealDiscard.size() + ") - Meal Discard");
        System.out.println("_____________");
        System.out.println("|Meal Card: |");
        System.out.println("|           |");
        for (int j = 0; j < mealCard.getFoodItem().size(); j++) {
            System.out.println("|" + mealCard.getFoodItem().get(j) + " x" + mealCard.getMultipliers().get(j) + "       |");
        }
        System.out.println("|           |");
        if (mealCard.getLevel() == 4) {
            System.out.println("| \uD83C\uDFC6WIN!!!  |");
        } else {
            System.out.println("|           |");
        }
        System.out.println("L___________J");
        System.out.println();
        System.out.println();
        System.out.println("Current Dimension Level: " + currentPlayer.getDimensionLevel());
        System.out.println("Actions Left: " + currentPlayer.getActionsLeft());
        System.out.println("[Player name]'s Hand:");
        Utility.printElementNames(currentPlayer.getHand());
        System.out.println();
        System.out.println("[0] Play a Food Card in your hand (Cost: Whatever the card costs of food)");
        System.out.println("[1] Buy a Food Card (Cost: 2 actions)");
        System.out.println("[2] Check the Stats of a Food Card you have");
        System.out.println("[3] Buy a Dimension Card(Cost: 1 action)");
        System.out.println("[4] Fuse two Dimension Cards of the same type (Cost: Level of dimension you want to get to)");
        System.out.println("[5] Enhanced Fusion (Cost: Double the Level of dimension you want to get to)");
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
            loadGUI(turnOrder[0]);
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
            case 2 -> checkHandCardStats();   // 2/5
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
            foodCard.accessAbility(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
            currentPlayer.decreaseActions(foodCard.accessCost());
            Utility.moveCards(currentPlayer.getHand(), foodIndex.get(choice), foodDiscard);
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

        int levelOfCurrentFuse = -1;
        if (fusePotential[0].getLevel() == fusePotential[1].getLevel()) {
            levelOfCurrentFuse = fusePotential[0].getLevel();
        }
        if (levelOfCurrentFuse < 4 && currentPlayer.getActionsLeft() >= levelOfCurrentFuse + 1 && levelOfCurrentFuse != -1) {
            String[] dimensions = new String[]{"┃", "⬛", "⛊", "☀"};
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index1), dimensionDiscard);
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index2), dimensionDiscard);
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
        Card[] fusePotential = new DimensionCard[4];
        Utility.printElementNames(dimensionCards);
        System.out.print("Pick Dimension Card 1: ");
        int index1 = scan.nextInt();
        fusePotential[0] = dimensionCards.get(index1);
        System.out.print("Pick Dimension Card 2: ");
        int index2 = scan.nextInt();
        fusePotential[1] = dimensionCards.get(index2);
        System.out.print("Pick Dimension Card 3: ");
        int index3 = scan.nextInt();
        fusePotential[0] = dimensionCards.get(index1);
        System.out.print("Pick Dimension Card 4: ");
        int index4 = scan.nextInt();
        fusePotential[1] = dimensionCards.get(index2);
        int levelOfCurrentFuse = -1;
        int fuseAttemptLevel = fusePotential[0].getLevel();
        int count = 0;
        for (Card card : fusePotential) {
            if (card.getLevel() == fuseAttemptLevel) {
                count++;
            }
        }
        if (count == 4) {
            levelOfCurrentFuse = fuseAttemptLevel;
        }
        if (levelOfCurrentFuse < 3 && currentPlayer.getActionsLeft() >= (levelOfCurrentFuse + 1) * 2 && levelOfCurrentFuse != -1) {
            String[] dimensions = new String[]{"┃", "⬛", "⛊", "☀"};
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index1), dimensionDiscard);
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index2), dimensionDiscard);
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index3), dimensionDiscard);
            Utility.moveCards(currentPlayer.getHand(), dimensionIndex.get(index4), dimensionDiscard);
            currentPlayer.decreaseActions((levelOfCurrentFuse + 1) * 2 );
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

