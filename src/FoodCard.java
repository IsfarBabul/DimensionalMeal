import java.util.ArrayList;
import java.util.Scanner;

public class FoodCard {
    Scanner scan;
    private final String foodName;
    private String foodType;
    private String abilityDescription;
    private String abilityInstruction;
    private int abilityCost;
    private int foodLevel;
    public FoodCard(String name, Scanner scan) {
        this.scan = scan;
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
                          "1. Gain 5 actions\n2. The next player in turn order gains 2 actions\n3. All other remaining players gain 1 action"};
        String[] vegetable = {"Gain 1 Dimension card",
                              "Gain 2 Dimension cards",
                              "Gain 4 Dimension cards",
                              "1. Gain 6 Dimension cards\n2. All other players gain 2 Dimension cards"};
        String[] protein = {"1. Choose an opponent\n2. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 2 more actions\n2. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 3 more actions\n2. Choose an opponent\n3. They start their next turn with 1 fewer action",
                            "1. On your next turn, start with 5 more actions\n2. All other players start their next turn with 1 fewer action"};
        String[] grains = {"1. Choose an opponent\n2. They must discard a random card",
                           "1. All other players give up a random card\n2. Of those discard this way, you may gain one of those cards",
                           "1. All other players give up a random card\n2. Separate them into Food and Dimension cards\n3. Put the cards in their respective decks in any order you choose",
                           "1. All other players give up a random card\n2. Choose either the Food or Dimension discard\n3. Take the top 10 cards(or as many as you can) and put them \n   on top of the respective deck in any order you choose"};
        String[] dairy = {"1. Choose two opponents(or you and your opponent for 1v1s)\n2. A random card from each player will be given to the other player",
                          "1. One random card is chosen per player(including you) \n2. This card is given to the next person in turn order",
                          "1. Choose one food card you have(required)\n2. Choose an opponent\n3. They must discard all Food cards of that type",
                          "1. Look at your Dimension level\n2. Choose an opponent\n3. They must discard all Dimension cards of that level or lower"};
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
    public int accessAbility(String type, int level, Player currentPlayer, Player[] turnOrder, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard) {
        int numType = 0;
        if(type.equals("wild")) {
            return accessWild();
        } else if (level == 0) {
            return access0();
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
        int[] fruit = {accessFruit1(currentPlayer), accessFruit2(currentPlayer), accessFruit3(currentPlayer), accessFruit4(currentPlayer, turnOrder)};
        int[] vegetable = {accessVegetable1(currentPlayer, dimensionDeck), accessVegetable2(currentPlayer, dimensionDeck), accessVegetable3(currentPlayer, dimensionDeck), accessVegetable4(currentPlayer, dimensionDeck, turnOrder)};
        int[] protein = {accessProtein1(), accessProtein2(), accessProtein3(), accessProtein4()};
        int[] grains = {accessGrains1(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder), accessGrains2(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder), accessGrains3(currentPlayer, dimensionDeck, foodDeck, turnOrder), accessGrains4(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder)};
        int[] dairy = {accessDairy1(), accessDairy2(), accessDairy3(), accessDairy4()};
        int[][] descriptor = {fruit, vegetable, protein, grains, dairy};
        return descriptor[numType][level];
    }
    //------------PRIVATE METHODS-----------//

    //----------------ABILITIES--------------//
    public int accessFruit1(Player currentPlayer) {
        currentPlayer.increaseActions(1);
        return 1;
    }

    public int accessFruit2(Player currentPlayer) {
        currentPlayer.increaseActions(2);
        return 1;
    }

    public int accessFruit3(Player currentPlayer) {
        currentPlayer.increaseActions(3);
        return 1;
    }

    public int accessFruit4(Player currentPlayer, Player[] turnOrder) {
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
        return 1;
    }

    public int accessVegetable1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        return 1;
    }

    public int accessVegetable2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 2; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
        return 1;
    }

    public int accessVegetable3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
        return 1;
    }

    public int accessVegetable4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, Player[] turnOrder) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
        for (int i = 0; i <= 2; i++) {
            for (Player player :turnOrder) {
                Utility.moveDimensionCards(dimensionDeck, 0, player.getDimensionHand(), 0);
            }
        }
        return 1;
    }

    public int accessProtein1(Player currentPlayer, Player ) {
        for (:
             ) {
            
        }
        return 1;
    }
    public int accessProtein2() {
        return 1;
    }
    public int accessProtein3() {
        return 1;
    }
    public int accessProtein4() {
        return 1;
    }
    public int accessGrains1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        int dimensionOrFood = (int) (Math.random() * 2);
        if(dimensionOrFood == 0) {
            int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
            Utility.moveDimensionCards(targetPlayer.getDimensionHand(), dimension, dimensionDiscard);
        } else {
            int food = (int) (Math.random() * (foodDeck.size() + 1));
            Utility.moveFoodCards(targetPlayer.getFoodHand(), food, foodDiscard);
        }
        return 1;
    }
    public int accessGrains2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int dimensionOrFood = (int) (Math.random() * 2);
                if (dimensionOrFood == 0) {
                    int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
                    Utility.moveDimensionCards(player.getDimensionHand(), dimension, tempArray2);
                } else {
                    int food = (int) (Math.random() * (foodDeck.size() + 1));
                    Utility.moveFoodCards(player.getFoodHand(), food, tempArray1);
                }
            }
        }
        String cardOptions = "[";
        for (FoodCard food : tempArray1) {
            cardOptions += food.getFoodName() + ", ";
        }
        for (DimensionCard dimension : tempArray2) {
            cardOptions += dimension.getDimensionName();
            if (!dimension.getDimensionName().equals(tempArray2.get(tempArray2.size() - 1).getDimensionName())) {
                cardOptions += ", ";
            }
        }
        cardOptions = "]";
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
        return 1;
    }
    public int accessGrains3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int dimensionOrFood = (int) (Math.random() * 2);
                if (dimensionOrFood == 0) {
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
            cardOptions = new StringBuilder("]");
        }
        System.out.println("These are the Food cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from top(which is 0) to bottom(the # of cards there are minus 1)");
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
        System.out.println("Choose what order to place them on the deck from top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<DimensionCard> tempArray4 = new ArrayList<>(tempArray2.size());
        for (DimensionCard dimensionCard : tempArray2) {
            System.out.print("Position of " + dimensionCard + ": ");
            int index = scan.nextInt();
            tempArray4.add(index, dimensionCard);
        }
        for (int i = 0; i < tempArray4.size(); i++) {
            Utility.moveDimensionCards(tempArray4, i, dimensionDeck, i);
        }
        return 1;
    }
    public int accessGrains4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int dimensionOrFood = (int) (Math.random() * 2);
                if (dimensionOrFood == 0) {
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
                cardOptions = new StringBuilder("]");
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
                cardOptions = new StringBuilder("]");
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

        return 1;
    }
    public int accessDairy1() {
        return 1;
    }
    public int accessDairy2() {
        return 1;
    }

    public int accessDairy3(Player currentPlayer, Player[] turnOrder, ArrayList<FoodCard> foodDiscard) {

        return 1;
    }
    public int accessDairy4() {
        return 1;
    }

    /**------------------------Zero-----------------------**/
    public int access0(Player currentPlayer, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard) {
        for (FoodCard food : foodDeck) {
            if (food.getFoodName().equals(foodDiscard.get(0))) {

            }
        }
        return 1;
    }
    /**------------------------Wild-----------------------**/
    public int accessWild() {

    }
}