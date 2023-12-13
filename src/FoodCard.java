import java.util.ArrayList;
import java.util.Scanner;

public abstract class FoodCard extends Card {
    Scanner scan;

    public FoodCard(String name, int level, Scanner scan) {
        super(name, level);
        this.scan = scan;
    }

    //------------PRIVATE METHODS-----------//

    /**
     * ------------------------Zero-----------------------
     **/
    private void access0(Player currentPlayer, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard) {
        int count = 0;
        boolean alreadyReceived = false;
        for (FoodCard food : foodDeck) {
            if (food.getName().equals(foodDiscard.get(0).getName()) && !alreadyReceived) {
                Utility.moveFoodCards(foodDeck, count, currentPlayer.getFoodHand());
                alreadyReceived = true;
            }
            count++;
        }
    }

    public abstract void accessAbility(int level, Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder);

    @Override
    public abstract String accessDescription(int level);

    @Override
    public abstract String accessInstruction(int level);

    @Override
    public abstract String accessExample(int level);
    public abstract int accessCost(int level);

}