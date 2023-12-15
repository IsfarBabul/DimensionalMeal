import java.util.ArrayList;
import java.util.Scanner;

public class WildFoodCard extends FoodCard{

    public WildFoodCard(String name, int level, Scanner scan) {
        super(name, level, scan);
    }
    @Override
    public void accessAbility(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        System.out.println("Would you like to access the Zero or Wild ability? ");
        String choice = scan.nextLine();
        choice = choice.toLowerCase();
        if (choice.equals("zero")) {
            access0(currentPlayer, foodDeck, foodDiscard);
        } else {
            ArrayList<String> foodItems = currentPlayer.getMealCard().getFoodItem();
            int itemsObtained = 0;
            boolean itemFound = false;
            do {
                System.out.println("Choose from the available options by typing a number starting from index 0: " + foodItems);
                int index = scan.nextInt();
                int count = 0;
                for (Card food : foodDeck) {
                    if (food.getName().equals(foodItems.get(index)) && !itemFound) {
                        Utility.moveCards(foodDeck, count, currentPlayer.getHand());
                        count++;
                        itemFound = true;
                    }
                }
                System.out.println("Your chosen option wasn't found...");
            } while (itemsObtained < currentPlayer.getDimensionLevel());
            System.out.println("You have reached your limit of items obtainable.");
        }
    }
    public String accessDescription() {
        return "The greatest delicacy in existence...except perhaps pizza.";
    }
    public String accessInstruction() {
        return "1. Identify your dimension level\\n2. Look at the different food you need for your meal\\n3. Obtain one of each different food up to your dimension level\\n4. If no present food of a type, choose a different one or disregard\"";
    }
    public String accessExample() {
        return "Check your dimension level as well as the specific food items on your meal card.\nSearch the deck for a number of those specific food items equal to your level.\nChoose as many food items as there are in the deck and you forfeit the rest.";
    }
    public int accessCost() {
        return super.getLevel();
    }
}
