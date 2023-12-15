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
    public void access0(Player currentPlayer, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard) {
        int count = 0;
        boolean alreadyReceived = false;
        for (Card food : foodDeck) {
            if (food.getName().equals(foodDiscard.get(0).getName()) && !alreadyReceived) {
                Utility.moveCards(foodDeck, count, currentPlayer.getHand());
                alreadyReceived = true;
            }
            count++;
        }
    }

    public abstract void accessAbility(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder);

    public abstract String accessDescription();

    public abstract String accessInstruction();

    public abstract String accessExample();

    public abstract int accessCost();

}