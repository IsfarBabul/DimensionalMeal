import java.util.ArrayList;
import java.util.Scanner;

public class GrainsFoodCard extends FoodCard{
    public GrainsFoodCard(String name, int level, Scanner scan) {
        super(name, level, scan);
    }
    public void accessAbility(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        switch (super.getLevel()) {
            case 1 -> accessAbility1(currentPlayer, dimensionDiscard, foodDiscard, turnOrder);
            case 2 -> accessAbility2(currentPlayer, dimensionDiscard, foodDiscard, turnOrder);
            case 3 -> accessAbility3(currentPlayer, dimensionDeck, foodDeck, turnOrder);
            case 4 -> accessAbility4(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
            default -> access0(currentPlayer, foodDeck, foodDiscard);
        }
    }
    public String accessDescription() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "You can intimidate someone.";
            case 2 -> string = "You establish dominance over the crowd.";
            case 3 -> string = "Your power influences the deck itself.";
            case 4 -> string = "Your influence permeates within all the decks.";
        }
        return string;
    }
    public String accessInstruction() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "1. Choose an opponent\n2. They must discard a random card";
            case 2 -> string = "1. All other players give up a random card\n2. Of those discarded this way, you may gain one of those cards";
            case 3 -> string = "1. All other players give up a random card\n2. Separate them into Food and Dimension cards\n3. Put the cards in their respective decks in any order you choose";
            case 4 -> string = "1. All other players give up a random card\n2. Choose either the Food or Dimension discard\n3. Take the top 10 cards(or as many as you can) and put them \n   on top of the respective deck in any order you choose";
        }
        return string;
    }
    public String accessExample() {
        String string = "";
        switch (super.getLevel()) {
            case 1 -> string = "Pick one player besides yourself.\nThey must pick a random card and put it in the appropriate discard pile.";
            case 2 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, you may choose one to add to your hand.";
            case 3 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, split them into Food and Dimension cards.\nPlace them on the appropriate decks in any order you wish.";
            case 4 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, choose either the Food or Dimension discard pile.\nTake the top 10 cards from the appropriate discard pile and place them on the appropriate deck in any order you wish.";
        }
        return string;
    }
    public int accessCost() {
        return super.getLevel();
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        int cardIndex = (int) (Math.random() * (targetPlayer.getHand().size() + 1));
        if(targetPlayer.getHand().get(cardIndex) instanceof DimensionCard) {
            Utility.moveCards(targetPlayer.getHand(), cardIndex, dimensionDiscard);
        } else {
            Utility.moveCards(targetPlayer.getHand(), cardIndex, foodDiscard);
        }
    }

    private void accessAbility2(Player currentPlayer, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        ArrayList<Card> tempArray = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int dimension = (int) (Math.random() * (player.getHand().size() + 1));
                Utility.moveCards(player.getHand(), dimension, tempArray);
            }
        }
        StringBuilder cardOptions = new StringBuilder("[");
        for (Card item : tempArray) {
            cardOptions.append(item.getName()).append(", ");
            if (!item.getName().equals(tempArray.get(tempArray.size() - 1).getName())) {
                cardOptions.append(", ");
            }
        }
        cardOptions.append("]");
        System.out.print("Among this list: " + cardOptions + ", type an index number corresponding to the item from left to right starting from 0: ");
        int index = scan.nextInt();
        if (index < tempArray.size()) {
            Utility.moveCards(tempArray, index, currentPlayer.getHand());
            Utility.moveCards(tempArray, index, foodDiscard);
            Utility.moveCards(tempArray, index, dimensionDiscard);
        }
        for (int i = 0; i < tempArray.size(); i++) {
            if (tempArray.get(i) instanceof FoodCard) {
                Utility.moveCards(tempArray, i, foodDiscard);
            } else {
                Utility.moveCards(tempArray, i, dimensionDiscard);
            }
        }
    }

    private void accessAbility3(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> foodDeck, Player[] turnOrder) {
        ArrayList<Card> tempArray = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
                Utility.moveCards(player.getHand(), dimension, tempArray);
            }
        }
        ArrayList<Card> tempArray1 = new ArrayList<>(0);
        ArrayList<Card> tempArray2 = new ArrayList<>(0);
        for (Card card : tempArray) {
            if (card instanceof FoodCard) {
                tempArray1.add(card);
            } else {
                tempArray2.add(card);
            }
        }
        StringBuilder cardOptions = new StringBuilder("[");
        for (Card food : tempArray) {
            cardOptions.append(food.getName());
            if (!food.getName().equals(tempArray1.get(tempArray1.size() - 1).getName())) {
                cardOptions.append(", ");
            }
            cardOptions.append("]");
        }
        System.out.println("These are the Food cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<Card> tempArray3 = new ArrayList<>(tempArray1.size());
        for (Card foodCard : tempArray1) {
            System.out.print("Position of " + foodCard + ": ");
            int index = scan.nextInt();
            tempArray3.add(index, foodCard);
        }
        for (int i = 0; i < tempArray3.size(); i++) {
            Utility.moveCards(tempArray3, i, foodDeck, i);
        }
        cardOptions = new StringBuilder("[");
        for (Card dimension : tempArray2) {
            cardOptions.append(dimension.getName());
            if (!dimension.getName().equals(tempArray2.get(tempArray2.size() - 1).getName())) {
                cardOptions.append(", ");
            }
            cardOptions = new StringBuilder("]");
        }
        System.out.println("These are the Dimension cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<Card> tempArray4 = new ArrayList<>(tempArray2.size());
        for (Card dimensionCard : tempArray2) {
            System.out.print("Position of " + dimensionCard + ": ");
            int index = scan.nextInt();
            tempArray4.add(index, dimensionCard);
        }
        for (int i = 0; i < tempArray4.size(); i++) {
            Utility.moveCards(tempArray4, i, dimensionDeck, i);
        }
    }

    private void accessAbility4(Player currentPlayer, ArrayList<Card> dimensionDeck, ArrayList<Card> dimensionDiscard, ArrayList<Card> foodDeck, ArrayList<Card> foodDiscard, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int cardIndex = (int) (Math.random() * (dimensionDeck.size() + 1));
                if(player.getHand().get(cardIndex) instanceof DimensionCard) {
                    Utility.moveCards(player.getHand(), cardIndex, dimensionDiscard);
                } else {
                    Utility.moveCards(player.getHand(), cardIndex, foodDiscard);
                }
            }
        }
        System.out.println("Choose to take from the Dimension or Food deck");
        String choice = scan.nextLine();
        choice = choice.toLowerCase();

        Card[] selectedCards = new Card[10];
        int nullCount = 0;
        for (int i = 0; i < 10; i++) {
            if(selectedCards[i] != null) {
                if(choice.equals("dimension")) {
                    selectedCards[i] = dimensionDiscard.get(i);
                } else {
                    selectedCards[i] = foodDiscard.get(i);
                }
            } else {
                nullCount++;
            }
        }
        System.out.print("These are the Dimension cards obtained: ");
        Utility.printElementNames(selectedCards);
        System.out.println("Choose what order to place them on the deck from top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<Card> tempArray = new ArrayList<>(selectedCards.length - nullCount);
        for (Card card : selectedCards) {
            if (card != null) {
                System.out.print("Position of " + card + ": ");
                int index = scan.nextInt();
                tempArray.add(index, card);
            }
        }
        for (int i = 0; i < tempArray.size(); i++) {
            if (choice.equals("dimension")) {
                Utility.moveCards(tempArray, i, dimensionDeck, i);
            } else {
                Utility.moveCards(tempArray, i, foodDeck, i);
            }
        }
    }
}
