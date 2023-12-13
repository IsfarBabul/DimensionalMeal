import java.util.ArrayList;
import java.util.Scanner;

public class VegetableFoodCard extends FoodCard{

    public VegetableFoodCard(String name, int level, Scanner scan) {
        super(name, level, scan);
    }
    public void accessAbility(int level, Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        switch (level) {
            case 1 -> accessAbility1(currentPlayer, dimensionDeck);
            case 2 -> accessAbility2(currentPlayer, dimensionDeck);
            case 3 -> accessAbility3(currentPlayer, dimensionDeck);
            case 4 -> accessAbility4(currentPlayer, dimensionDeck, turnOrder);
        }
    }
    public String accessDescription(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "You see weird flashes.";
            case 2 -> string = "Your seeing things.";
            case 3 -> string = "You spot random objects.";
            case 4 -> string = "You can identify items across space and time...your opponents can see \"things\" too.";
        }
        return string;
    }
    public String accessInstruction(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Gain 1 Dimension card";
            case 2 -> string = "Gain 2 Dimension cards";
            case 3 -> string = "Gain 4 Dimension cards";
            case 4 -> string = "1. Gain 6 Dimension cards\n2. All other players gain 2 Dimension cards";
        }
        return string;
    }
    public String accessExample(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Draw 1 Dimension card from the Dimension deck.";
            case 2 -> string = "Draw 2 Dimension card from the Dimension deck.";
            case 3 -> string = "Draw 4 Dimension card from the Dimension deck.";
            case 4 -> string = "Draw 6 Dimension card from the Dimension deck.\nEveryone else draws 2 Dimension cards from the Dimension deck.";
        }
        return string;
    }
    public int accessCost(int level) {
        return level - 1;
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
    }

    private void accessAbility2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 2; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
    }

    private void accessAbility3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
    }

    private void accessAbility4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, Player[] turnOrder) {
        for (int i = 0; i <= 4; i++) {
            Utility.moveDimensionCards(dimensionDeck, 0, currentPlayer.getDimensionHand(),0);
        }
        for (int i = 0; i <= 2; i++) {
            for (Player player :turnOrder) {
                Utility.moveDimensionCards(dimensionDeck, 0, player.getDimensionHand(), 0);
            }
        }
    }
}
