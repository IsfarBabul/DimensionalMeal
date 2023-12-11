import java.util.ArrayList;
import java.util.Scanner;

public class FoodCard extends Card{
    Scanner scan;
    private final String foodName;
    private String foodType;
    private String abilityDescription;
    private String abilityInstruction;
    private int abilityCost;
    private int foodLevel;
    public FoodCard(String name, Scanner scan) {
        super(name, scan);
        this.scan = scan;
        foodName = name;
        foodName = name;
        switch (name) {
            case "\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11" -> foodType = "fruit";
            case "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C" -> foodType = "vegetable";
            case "\uD83E\uDD69", "\uD83C\uDF64" -> foodType = "protein";
            case "\uD83C\uDF3E", "\uD83C\uDF5E" -> foodType = "grains";
            case "\uD83E\uDDC0", "\uD83C\uDF76" -> foodType = "dairy";
        }
        foodLevel = 0;
        abilityDescription = setAbilityDescription(foodType, foodLevel);
        abilityInstruction = setAbilityInstruction(foodType, foodLevel);
        abilityCost = setAbilityCost(foodType, foodLevel);
    }
    public String getFoodName() {
        return foodName;
    }
    public String getFoodType() {
        return foodType;
    }
    public int getFoodLevel() {
        return foodLevel;
    }
    public String getAbilityDescription() {
        return abilityDescription;
    }
    public String getAbilityInstruction() {
        return abilityInstruction;
    }
    public int getAbilityCost() {
        return abilityCost;
    }
    public void incrementFoodLevel() {
        foodLevel++;
        abilityDescription = setAbilityDescription(foodType, foodLevel);
        abilityInstruction = setAbilityInstruction(foodType, foodLevel);
        abilityCost = setAbilityCost(foodType, foodLevel);
    }
    public String setAbilityDescription(String type, int level) {
        int numType = 0;
        if(level == 0) {
            return "Nothing special here...just a plain ol' colored dot.";
        } else if(type.equals("wild")) {
            return "The greatest delicacy in existence...except perhaps pizza.";
        }
        level--;
        switch (type) {
            case "fruit" -> {
            }
            case "vegetable" -> numType = 1;
            case "protein" -> numType = 2;
            case "grains" -> numType = 3;
            case "dairy" -> numType = 4;
        }
        String[] fruit = {"Your mood seems to lighten. I wonder why?", "You seem to heal faster.", "You feel like you can do anything!", "The fruit empowers you...and your opponents!"};
        String[] vegetable = {"You see weird flashes.", "Your seeing things.", "You spot random objects.", "You can identify items across space and time...your opponents can see \"things\" too."};
        String[] protein = {"You seem to go faster.", "Your picking up the pace.", "You know how to run!", "Your Usain Bolt! Your speed shocks the crowd!"};
        String[] grains = {"You can intimidate someone.", "You establish dominance over the crowd.", "Your power influences the deck itself.", "Your influence permeates within all the decks."};
        String[] dairy = {"You seem to be a little prankster.", "Your pranks affect everyone.", "You trample over the farms of your foes.", "Your sucking away their entire existence?"};
        String[][] descriptor = {fruit, vegetable, protein, grains, dairy};
        return descriptor[numType][level];
    }
    public String setAbilityInstruction(String type, int level) {
        int numType = 0;
        if(level == 0) {
            return "1. Search the deck for the same card";
        } else if(type.equals("wild")) {
            return "1. Identify your dimension level\n2. Look at the different food you need for your meal\n3. Obtain one of each different food up to your dimension level\n4. If no present food of a type, choose a different one or disregard";
        }
        level--;
        switch (type) {
            case "fruit" -> {
            }
            case "vegetable" -> numType = 1;
            case "protein" -> numType = 2;
            case "grains" -> numType = 3;
            case "dairy" -> numType = 4;
        }
        String[] fruit = {"Gain 1 action",
                          "Gain 2 actions",
                          "Gain 3 actions",
                          "1. Gain 5 actions\n2. The next player in turn gains 2 actions\n3. All other remaining players gain 1 action"};
        String[] vegetable = {"Gain 1 Dimension card",
                              "Gain 2 Dimension cards",
                              "Gain 4 Dimension cards",
                              "1. Gain 6 Dimension cards\n2. All other players gain 2 Dimension cards"};
        String[] protein = {"1. Choose an opponent\n2. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 2 more actions\n2. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 3 more actions\n2. Choose an opponent\n3. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 5 more actions\n2. All other players start their next turn with 1 fewer action"};
        String[] grains = {"1. Choose an opponent\n2. They must discard a random card",
                           "1. All other players give up a random card\n2. Of those discarded this way, you may gain one of those cards",
                           "1. All other players give up a random card\n2. Separate them into Food and Dimension cards\n3. Put the cards in their respective decks in any order you choose",
                           "1. All other players give up a random card\n2. Choose either the Food or Dimension discard\n3. Take the top 10 cards(or as many as you can) and put them \n   on top of the respective deck in any order you choose"};
        String[] dairy = {"1. Choose two opponents(or you and your opponent for 1v1s)\n2. A random card from each player will be given to the other player",
                          "1. One random card is chosen per player(including you) \n2. This card is given to the next person in turn order",
                          "1. Choose one food card you have(required)\n2. Choose an opponent\n3. They must discard all Food cards of that type",
                          "1. Look at your Dimension level\n2. Choose an opponent\n3. They must discard all Dimension cards of that level or lower\n4. If they discard all dimension cards this way they must discard their current meal card and draw a new one"};
        String[][] instructor = {fruit, vegetable, protein, grains, dairy};
        return instructor[numType][level];
    }
    public int setAbilityCost(String type, int level) {
        int numType = 0;
        if(level == 0 || type.equals("wild")) {
            return 1;
        }
        level--;
        switch (type) {
            case "fruit" -> {
            }
            case "vegetable" -> numType = 1;
            case "protein" -> numType = 2;
            case "grains" -> numType = 3;
            case "dairy" -> numType = 4;
        }
        int[] fruit = {0, 0, 0, 0};
        int[] vegetable = {0, 1, 2, 3};
        int[] protein = {1, 1, 2, 4};
        int[] grains = {2, 3, 3, 4};
        int[] dairy = {1, 2, 3, 4};
        int[][] descriptor = {fruit, vegetable, protein, grains, dairy};
        return descriptor[numType][level];
    }
    public void accessAbility(String type, int level, Player currentPlayer, Player[] turnOrder, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard) {
        int numType = 0;
        if(type.equals("wild")) {
            accessWild();
        } else if (level == 0) {
            access0(currentPlayer, foodDeck, foodDiscard);
        }
        level--;
        switch (type) {
            case "fruit" -> {
                switch (level) {
                    case 1 -> accessFruit1(currentPlayer);
                    case 2 -> accessFruit2(currentPlayer);
                    case 3 -> accessFruit3(currentPlayer);
                    default -> accessFruit4(currentPlayer, turnOrder);
                }
            }
            case "vegetable" -> {
                switch (level) {
                    case 1 -> accessVegetable1(currentPlayer, dimensionDeck);
                    case 2 -> accessVegetable2(currentPlayer, dimensionDeck);
                    case 3 -> accessVegetable3(currentPlayer, dimensionDeck);
                    default -> accessVegetable4(currentPlayer, dimensionDeck, turnOrder);
                }
            }
            case "protein" -> {
                switch (level) {
                    case 1 -> accessProtein1(currentPlayer, turnOrder);
                    case 2 -> accessProtein2(currentPlayer, turnOrder);
                    case 3 -> accessProtein3(currentPlayer, turnOrder);
                    default -> accessProtein4(currentPlayer, turnOrder);
                }
            }
            case "grains" -> {
                switch (level) {
                    case 1 -> accessGrains1(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
                    case 2 -> accessGrains2(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
                    case 3 -> accessGrains3(currentPlayer, dimensionDeck, foodDiscard, turnOrder);
                    default -> accessGrains4(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
                }
            }
            case "dairy" -> {
                switch (level) {
                    case 1 -> accessDairy1(currentPlayer);
                    case 2 -> accessDairy2(currentPlayer);
                    case 3 -> accessDairy3(currentPlayer);
                    default -> accessDairy4(currentPlayer, turnOrder);
                }
            }
        }
    }
    //------------PRIVATE METHODS-----------//

    //----------------ABILITIES--------------//
    /**------------------------FRUIT-----------------------**/
    private void accessFruit1(Player currentPlayer) {
        currentPlayer.increaseActions(1);
    }

    private void accessFruit2(Player currentPlayer) {
        currentPlayer.increaseActions(2);
    }

    private void accessFruit3(Player currentPlayer) {
        currentPlayer.increaseActions(3);
    }

    private void accessFruit4(Player currentPlayer, Player[] turnOrder) {
        Player nextPlayer = null;
        boolean next = false;
        if (currentPlayer.equals(turnOrder[turnOrder.length - 1])) {
            nextPlayer = turnOrder[0];
        }
        for (Player player : turnOrder) {
            player.increaseActions(1);
            if (currentPlayer.equals(player)) {
                next = true;
            }
            if (next) {
                nextPlayer = player;
                next = false;
            }
        }
        assert nextPlayer != null;
        nextPlayer.increaseActions(1);
        currentPlayer.increaseActions(4);
    }

    /**------------------------VEGETABLE-----------------------**/
    private void accessVegetable1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
    }

    private void accessVegetable2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 2; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
    }

    private void accessVegetable3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
    }

    private void accessVegetable4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, Player[] turnOrder) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
        for (int i = 0; i <= 2; i++) {
            for (Player player :turnOrder) {
                Utility.moveDimensionCards(dimensionDeck, 0, player.getDimensionHand(), 0);
            }
        }
    }

    /**------------------------PROTEIN-----------------------**/
    private void accessProtein1(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
    }

    private void accessProtein2(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
        currentPlayer.increaseNextTurnActions(2);
    }

    private void accessProtein3(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
        currentPlayer.increaseNextTurnActions(3);
    }

    private void accessProtein4(Player currentPlayer, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                player.decreaseNextTurnActions(1);
            }
        }
        currentPlayer.increaseNextTurnActions(5);
    }

    /**------------------------GRAINS-----------------------**/
    private void accessGrains1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        if(Utility.dimensionOrFood()) {
            int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
            Utility.moveDimensionCards(targetPlayer.getDimensionHand(), dimension, dimensionDiscard);
        } else {
            int food = (int) (Math.random() * (foodDeck.size() + 1));
            Utility.moveFoodCards(targetPlayer.getFoodHand(), food, foodDiscard);
        }
    }

    private void accessGrains2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
                    int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
                    Utility.moveDimensionCards(player.getDimensionHand(), dimension, tempArray2);
                } else {
                    int food = (int) (Math.random() * (foodDeck.size() + 1));
                    Utility.moveFoodCards(player.getFoodHand(), food, tempArray1);
                }
            }
        }
        StringBuilder cardOptions = new StringBuilder("[");
        for (FoodCard food : tempArray1) {
            cardOptions.append(food.getFoodName()).append(", ");
        }
        for (DimensionCard dimension : tempArray2) {
            cardOptions.append(dimension.getDimensionName());
            if (!dimension.getDimensionName().equals(tempArray2.get(tempArray2.size() - 1).getDimensionName())) {
                cardOptions.append(", ");
            }
        }
        cardOptions.append("]");
        System.out.print("Among this list: " + cardOptions + ", type an index number corresponding to the item from left to right starting from 0: ");
        int index = scan.nextInt();
        if (index < tempArray1.size()) {
            Utility.moveFoodCards(tempArray1, index, currentPlayer.getFoodHand());
        } else {
            index -= tempArray1.size();
            Utility.moveDimensionCards(tempArray2, index, currentPlayer.getDimensionHand());
        }
        Utility.moveFoodCards(tempArray1, index, foodDiscard);
        Utility.moveDimensionCards(tempArray2, index, dimensionDiscard);
    }

    private void accessGrains3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<FoodCard> foodDeck, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
                    int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
                    Utility.moveDimensionCards(player.getDimensionHand(), dimension, tempArray2);
                } else {
                    int food = (int) (Math.random() * (foodDeck.size() + 1));
                    Utility.moveFoodCards(player.getFoodHand(), food, tempArray1);
                }
            }
        }
        StringBuilder cardOptions = new StringBuilder("[");
        for (FoodCard food : tempArray1) {
            cardOptions.append(food.getFoodName());
            if (!food.getFoodName().equals(tempArray1.get(tempArray1.size() - 1).getFoodName())) {
                cardOptions.append(", ");
            }
            cardOptions.append("]");
        }
        System.out.println("These are the Food cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<FoodCard> tempArray3 = new ArrayList<>(tempArray1.size());
        for (FoodCard foodCard : tempArray1) {
            System.out.print("Position of " + foodCard + ": ");
            int index = scan.nextInt();
            tempArray3.add(index, foodCard);
        }
        for (int i = 0; i < tempArray3.size(); i++) {
            Utility.moveFoodCards(tempArray3, i, foodDeck, i);
        }
        cardOptions = new StringBuilder("[");
        for (DimensionCard dimension : tempArray2) {
            cardOptions.append(dimension.getDimensionName());
            if (!dimension.getDimensionName().equals(tempArray2.get(tempArray2.size() - 1).getDimensionName())) {
                cardOptions.append(", ");
            }
            cardOptions = new StringBuilder("]");
        }
        System.out.println("These are the Dimension cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<DimensionCard> tempArray4 = new ArrayList<>(tempArray2.size());
        for (DimensionCard dimensionCard : tempArray2) {
            System.out.print("Position of " + dimensionCard + ": ");
            int index = scan.nextInt();
            tempArray4.add(index, dimensionCard);
        }
        for (int i = 0; i < tempArray4.size(); i++) {
            Utility.moveDimensionCards(tempArray4, i, dimensionDeck, i);
        }
    }

    private void accessGrains4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
                    int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
                    Utility.moveDimensionCards(player.getDimensionHand(), dimension, dimensionDiscard);
                } else {
                    int food = (int) (Math.random() * (foodDeck.size() + 1));
                    Utility.moveFoodCards(player.getFoodHand(), food, foodDiscard);
                }
            }
        }

        System.out.println("Choose to take from the Dimension or Food deck");
        String choice = scan.nextLine();
        choice = choice.toLowerCase();

        if (choice.equals("dimension")) {
            ArrayList<DimensionCard> tempArray1 = new ArrayList<>(10);

            for (int i = 10; i > 0; i--) {
                Utility.moveDimensionCards(dimensionDiscard, i, tempArray1, i);
            }

            StringBuilder cardOptions = new StringBuilder("[");

            for (DimensionCard dimension : tempArray1) {
                cardOptions.append(dimension.getDimensionName());
                if (!dimension.getDimensionName().equals(tempArray1.get(tempArray1.size() - 1).getDimensionName())) {
                    cardOptions.append(", ");
                }
                cardOptions.append("]");
            }

            System.out.println("These are the Dimension cards obtained: " + cardOptions);
            System.out.println("Choose what order to place them on the deck from top(which is 0) to bottom(the # of cards there are minus 1)");
            ArrayList<DimensionCard> tempArray4 = new ArrayList<>(tempArray1.size());

            for (DimensionCard dimensionCard : tempArray1) {
                System.out.print("Position of " + dimensionCard + ": ");
                int index = scan.nextInt();
                tempArray4.add(index, dimensionCard);
            }

            for (int i = 0; i < tempArray4.size(); i++) {
                Utility.moveDimensionCards(tempArray4, i, dimensionDeck, i);
            }

        } else {
            ArrayList<FoodCard> tempArray2 = new ArrayList<>(10);

            for (int i = 10; i > 0; i--) {
                Utility.moveFoodCards(foodDiscard, i, tempArray2, i);
            }

            StringBuilder cardOptions = new StringBuilder("[");
            for (FoodCard food : tempArray2) {
                cardOptions.append(food.getFoodName());
                if (!food.getFoodName().equals(tempArray2.get(tempArray2.size() - 1).getFoodName())) {
                    cardOptions.append(", ");
                }
                cardOptions.append("]");
            }

            System.out.println("These are the Food cards obtained: " + cardOptions);
            System.out.println("Choose what order to place them on the deck from top(which is 0) to bottom(the # of cards there are minus 1)");
            ArrayList<FoodCard> tempArray3 = new ArrayList<>(tempArray2.size());

            for (FoodCard foodCard : tempArray2) {
                System.out.print("Position of " + foodCard + ": ");
                int index = scan.nextInt();
                tempArray3.add(index, foodCard);
            }

            for (int i = 0; i < tempArray3.size(); i++) {
                Utility.moveFoodCards(tempArray3, i, foodDeck, i);
            }
        }
    }

    /**------------------------DAIRY-----------------------**/
    private void accessDairy1(Player currentPlayer, Player[] turnOrder) {
        Player opponent1 = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder); // select opponent
        Player opponent2 = opponent1;
        while (opponent2 == opponent1 && turnOrder.length != 2) { // failsafe if both opponents r same
            opponent2 = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        }
        if (turnOrder.length == 2) {   //failsafe if only 2 players
            opponent2 = currentPlayer;
        }
        Player[] specifiedOrder = new Player[2];
        String[] orderMarker = {"dimension", "dimension"};
        specifiedOrder[0] = opponent1;
        specifiedOrder[1] = opponent2;
        ArrayList<DimensionCard> tempArray1 = new ArrayList<>(0);
        ArrayList<FoodCard> tempArray2 = new ArrayList<>(0);
        for (int i = 0; i <= 1; i++) {
            if(Utility.dimensionOrFood() && !specifiedOrder[0].getDimensionHand().isEmpty()) {
                int dimension = (int) (Math.random() * (specifiedOrder[0].getDimensionHand().size() - 1));
                Utility.moveDimensionCards(opponent1.getDimensionHand(), dimension, tempArray1);
            } else {
                int food = (int) (Math.random() * (specifiedOrder[0].getFoodHand().size() - 1));
                Utility.moveFoodCards(opponent1.getFoodHand(), food, tempArray2);
                orderMarker[i] = "food";
            }
            specifiedOrder = Utility.identifyNextTurnOrder(specifiedOrder);
        }
        Player[] nextOrder = Utility.identifyNextTurnOrder(specifiedOrder);
        if (orderMarker[0].equals(orderMarker[1])) {
            for (int i = 0; i <= 1; i++) {
                if (orderMarker[0].equals("dimension")) {
                    Utility.moveDimensionCards(tempArray1, i, nextOrder[i].getDimensionHand());
                } else {
                    Utility.moveFoodCards(tempArray2, i, nextOrder[i].getFoodHand());
                }
            }
        } else {
            if (orderMarker[0].equals("dimension")) {
                Utility.moveDimensionCards(tempArray1, 0, nextOrder[0].getDimensionHand());
                Utility.moveFoodCards(tempArray2, 0, nextOrder[1].getFoodHand());
            } else {
                Utility.moveDimensionCards(tempArray1, 0, nextOrder[1].getDimensionHand());
                Utility.moveFoodCards(tempArray2, 0, nextOrder[0].getFoodHand());
            }
        }
    }

    private void accessDairy2() {

    }

    private void accessDairy3(Player currentPlayer, Player[] turnOrder, ArrayList<FoodCard> foodDiscard) {

    }

    private void accessDairy4(Player currentPlayer, Scanner scan, Player[] turnOrder, ArrayList<DimensionCard> dimensionDiscard) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        for (int i = 0; i < targetPlayer.getDimensionHand().size(); i++) { // for each dimension card in that players hand
            if (targetPlayer.getDimensionLevel() >= targetPlayer.getDimensionHand().get(i).getDimensionLevel()) {
                Utility.moveDimensionCards(targetPlayer.getDimensionHand(), i, dimensionDiscard, 0);
            }
        }
    }

    /**------------------------Zero-----------------------**/
    private void access0(Player currentPlayer, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard) {
        int count = 0;
        boolean alreadyReceived = false;
        for (FoodCard food : foodDeck) {
            if (food.getFoodName().equals(foodDiscard.get(0).getFoodName()) && !alreadyReceived) {
                Utility.moveFoodCards(foodDeck, count, currentPlayer.getFoodHand());
                alreadyReceived = true;
            }
            count++;
        }
    }
    /**------------------------Wild-----------------------**/
    private void accessWild(Player curentPlayer) {

    }
}