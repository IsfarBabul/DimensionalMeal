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
        boolean alreadyReceived = false;
        int targetCardIndex = -1;
        for (int i = 0; i < foodDeck.size(); i++) {
            if (foodDeck.get(i).getName().equals(foodDiscard.get(0).getName()) && !alreadyReceived) {
                targetCardIndex = i;
                alreadyReceived = true;
            }
        }
        if (targetCardIndex != -1) {
            Utility.moveCards(foodDeck, targetCardIndex, currentPlayer.getHand());
        }
    }

    public abstract void accessAbility(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder);

    public abstract String accessDescription();

    public abstract String accessInstruction();

    public abstract String accessExample();

    public abstract int accessCost();

}