import java.util.ArrayList;
import java.util.Scanner;

public class DairyFoodCard extends FoodCard{
    public DairyFoodCard(String name, Scanner scan) {
        super(name, scan);
    }
    public void accessAbility(int level, Player currentPlayer, Player[] turnOrder) {
        switch (level) {
            case 1 -> accessAbility1(currentPlayer, turnOrder);
            case 2 -> accessAbility2(currentPlayer, turnOrder);
            case 3 -> accessAbility3(currentPlayer, turnOrder);
            case 4 -> accessAbility4(currentPlayer, turnOrder);
        }
    }
    public String accessDescription(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "You seem to be a little prankster.";
            case 2 -> string = "Your pranks affect everyone.";
            case 3 -> string = "You trample over the farms of your foes.";
            case 4 -> string = "Your sucking away their entire existence?";
        }
        return string;
    }
    public String accessInstruction(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "1. Choose two opponents(or you and your opponent for 1v1s)\n2. A random card from each player will be given to the other player";
            case 2 -> string = "1. One random card is chosen per player(including you) \n2. This card is given to the next person in turn order";
            case 3 -> string = "1. Look at your Dimension level\n2. Choose one Food card you have(required)\n3. Choose an opponent\n4. They must discard a number Food cards of that type up to your one more than your level";
            case 4 -> string = "1. Look at your Dimension level\n2. Choose an opponent\n3. They must discard all Dimension cards below that level\n4. If they discard all dimension cards this way they must discard their current meal card and draw a new one";
        }
        return string;
    }
    public String accessExample(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Pick two players besides yourself.\nEach player picks a random card and must give it to the other player.\nIn 2-player matches, you swap with your opponent.";
            case 2 -> string = "EVERY player chooses a random card.\nEach card is given to the next player that would get their turn after the player giving the card would end their turn.";
            case 3 -> string = "Check your Dimension level and if you have have a Food card.\nIf so then choose a player besides yourself.\nThey must move all Food cards in their hand to the Food discard pile.";
            case 4 -> string = "Check your Dimension level and choose a player besides yourself.\nThey must discard all Dimension cards up to one less than your level\nIf all Dimension cards are discard this way, that player must discard their meal card and draw a new one.";
        }
        return string;
    }
    public int accessCost(int level) {
        return level;
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer, Player[] turnOrder) {
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

    private void accessAbility2(Player currentPlayer, Player[] turnOrder) {

    }

    private void accessAbility3(Player currentPlayer, Player[] turnOrder, ArrayList<FoodCard> foodDiscard) {

    }

    private void accessAbility4(Player currentPlayer, Scanner scan, Player[] turnOrder, ArrayList<DimensionCard> dimensionDiscard) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        for (int i = 0; i < targetPlayer.getDimensionHand().size(); i++) { // for each dimension card in that players hand
            if (targetPlayer.getDimensionLevel() >= targetPlayer.getDimensionHand().get(i).getDimensionLevel()) {
                Utility.moveDimensionCards(targetPlayer.getDimensionHand(), i, dimensionDiscard, 0);
            }
        }
    }
}
