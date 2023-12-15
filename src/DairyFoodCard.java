import java.util.ArrayList;
import java.util.Scanner;

public class DairyFoodCard extends FoodCard{
    public DairyFoodCard(String name, int level, Scanner scan) {
        super(name, level, scan);
    }
    public void accessAbility(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        switch (super.getLevel()) {
            case 1 -> accessAbility1(currentPlayer, turnOrder);
            case 2 -> accessAbility2(turnOrder);
            case 3 -> accessAbility3(currentPlayer, turnOrder, foodDiscard);
            case 4 -> accessAbility4(currentPlayer, scan, turnOrder, dimensionDiscard);
            default -> access0(currentPlayer, foodDeck, foodDiscard);
        }
    }
    public String accessDescription() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "You seem to be a little prankster.";
            case 2 -> string = "Your pranks affect everyone.";
            case 3 -> string = "You trample over the farms of your foes.";
            case 4 -> string = "Your sucking away their entire existence?";
        }
        return string;
    }
    public String accessInstruction() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "1. Choose two opponents(or you and your opponent for 1v1s)\n2. A random card from each player will be given to the other player";
            case 2 -> string = "1. One random card is chosen per player(including you) \n2. This card is given to the next person in turn order";
            case 3 -> string = "1. Look at your Dimension level\n2. Choose one Food card you have(required to activate ability otherwise it does nothing and you wasted a card)\n3. Choose an opponent\n4. They must discard a number Food cards of that type up to your one more than your level";
            case 4 -> string = "1. Look at your Dimension level\n2. Choose an opponent\n3. They must discard all Dimension cards below that level\n4. If they discard all dimension cards this way they must discard their current meal card and draw a new one";
        }
        return string;
    }
    public String accessExample() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "Pick two players besides yourself.\nEach player picks a random card and must give it to the other player.\nIn 2-player matches, you swap with your opponent.";
            case 2 -> string = "EVERY player chooses a random card.\nEach card is given to the next player that would get their turn after the player giving the card would end their turn.";
            case 3 -> string = "Check your Dimension level and if you have have a Food card.\nIf so then choose a player besides yourself.\nThey must move all Food cards of the same name in their hand to the Food discard pile.";
            case 4 -> string = "Check your Dimension level and choose a player besides yourself.\nThey must discard all Dimension cards up to one less than your level\nIf all Dimension cards are discard this way, that player must discard their meal card and draw a new one.";
        }
        return string;
    }
    public int accessCost() {
        return super.getLevel();
    }

    //------------PRIVATE METHODS-----------//
    public void accessAbility1(Player currentPlayer, Player[] turnOrder) {
        Player opponent1 = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder); // select opponent
        Player opponent2 = opponent1;
        while (opponent2 == opponent1 && turnOrder.length != 2) { // failsafe if both opponents r same
            opponent2 = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        }
        if (turnOrder.length == 2) {   //failsafe if only 2 players
            opponent2 = currentPlayer;
        }
        int cardIndex1 = (int) (Math.random() * (opponent1.getHand().size() + 1));
        int cardIndex2 = (int) (Math.random() * (opponent2.getHand().size() + 1));
        Utility.moveCards(opponent1.getHand(), cardIndex1, opponent2.getHand());
        Utility.moveCards(opponent2.getHand(), cardIndex2, opponent1.getHand());
    }

    public void accessAbility2(Player[] turnOrder) {
        int[] cardIndexChosen = new int[turnOrder.length];
        for (int i = 0; i < turnOrder.length; i++) {
            cardIndexChosen[i] = (int) (Math.random() * (turnOrder[i].getHand().size() + 1));
        }
        for (int i = 0; i < turnOrder.length - 1; i++) {
            Utility.moveCards(turnOrder[i].getHand(), cardIndexChosen[i], turnOrder[i + 1].getHand());
        }
        Utility.moveCards(turnOrder[turnOrder.length - 1].getHand(), cardIndexChosen[cardIndexChosen.length - 1], turnOrder[0].getHand());
    }

    public void accessAbility3(Player currentPlayer, Player[] turnOrder, ArrayList<Card> foodDiscard) {
        boolean hasFoodCard = false;
        ArrayList<Card> foodCards = new ArrayList<>(0);
        for (Card card : currentPlayer.getHand()) {
            if (card instanceof FoodCard) {
                hasFoodCard = true;
                foodCards.add(card);
            }
        }
        if (hasFoodCard) {
            System.out.println("Choose a Food card in your hand (from index 0 onward): ");
            Utility.printElementNames(foodCards);
            int index = scan.nextInt();
            Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
            int count = 0;
            for (Card card : targetPlayer.getHand()) {
                if (card.getName().equals(currentPlayer.getHand().get(index).getName())) {
                    Utility.moveCards(targetPlayer.getHand(), count, foodDiscard);
                }
                count++;
            }
        }
    }

    public void accessAbility4(Player currentPlayer, Scanner scan, Player[] turnOrder, ArrayList<Card> dimensionDiscard) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        for (int i = 0; i < targetPlayer.getHand().size(); i++) { // for each dimension card in that players hand
            if (targetPlayer.getDimensionLevel() >= targetPlayer.getHand().get(i).getLevel()) {
                Utility.moveCards(targetPlayer.getHand(), i, dimensionDiscard, 0);
            }
        }
    }
}
