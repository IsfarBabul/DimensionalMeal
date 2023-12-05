public class FoodCard {
    private final String foodName;
    private final String foodType;
    private String abilityDescription;
    private String abilityInstruction;
    private int abilityCost;
    private int foodLevel;
    public FoodCard(String name, String type, int level) {
        foodName = name;
        foodType = type;
        foodLevel = level;
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
            case "grain" -> numType = 3;
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
            case "grain" -> numType = 3;
            case "dairy" -> numType = 4;
        }
        String[] fruit = {"Gain 1 action", "Gain 2 actions", "Gain 3 actions", "1. Gain 5 actions\n2. The next player in turn order gains 2 actions\n3. All other remaining players gain 1 action"};
        String[] vegetable = {"Gain 1 Dimension card", "Gain 2 Dimension cards", "Gain 4 Dimension cards", "1. Gain 6 Dimension cards\n2. All other players gain 2 Dimension cards"};
        String[] protein = {"1. Choose an opponent\n2. They start their next turn with 1 fewer action", "On your next turn, start with 2 more actions", "1. On your next turn, start with 3 more actions\n2. Choose an opponent\n3. They start their next turn with 1 fewer action", "1. On your next turn, start with 5 more actions\n2. All other players start their next turn with 1 fewer action"};
        String[] grains = {"1. Choose an opponent\n2. They must discard a random card", "1. All other players must discard a random card\n2. Of those discard this way, you may gain one of those cards", "1. All other players give up a random card\n2. Separate them into Food and Dimension cards\n3. Put the cards in their respective decks in any order you choose", "1. All other players give up a random card\n2. Choose either the Food or Dimension discard\n3. Take the top 10 cards(or as many as you can) and put them \n   on top of the respective deck in any order you choose"};
        String[] dairy = {"1. Choose two people(one can be you)\n2. A random card from each player will be given to the other player", "1. One random card is chosen per player(including you) \n2. This card is given to the next person in turn order", "1. Choose one food card you have(required)\n2. Choose an opponent\n3. They must discard all Food cards of that type", "1. Choose a Dimension level\n2. Choose an opponent\n3. They must discard all Dimension cards of that level or lower\n4. If they discard all Dimension cards in this way, they must discard their Meal card and gain a new one"};
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
            case "grain" -> numType = 3;
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
}