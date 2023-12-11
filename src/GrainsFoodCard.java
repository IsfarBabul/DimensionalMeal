import java.util.ArrayList;
import java.util.Scanner;

public class GrainsFoodCard extends FoodCard{
    public GrainsFoodCard(String name, Scanner scan) {
        super(name, scan);
    }
    public void accessAbility(int level, Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        switch (level) {
            case 1 -> accessAbility1(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
            case 2 -> accessAbility2(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
            case 3 -> accessAbility3(currentPlayer, dimensionDeck, foodDeck, turnOrder);
            case 4 -> accessAbility4(currentPlayer, dimensionDeck, dimensionDiscard, foodDeck, foodDiscard, turnOrder);
        }
    }
    public String accessDescription(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "You can intimidate someone.";
            case 2 -> string = "You establish dominance over the crowd.";
            case 3 -> string = "Your power influences the deck itself.";
            case 4 -> string = "Your influence permeates within all the decks.";
        }
        return string;
    }
    public String accessInstruction(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "1. Choose an opponent\n2. They must discard a random card";
            case 2 -> string = "1. All other players give up a random card\n2. Of those discarded this way, you may gain one of those cards";
            case 3 -> string = "1. All other players give up a random card\n2. Separate them into Food and Dimension cards\n3. Put the cards in their respective decks in any order you choose";
            case 4 -> string = "1. All other players give up a random card\n2. Choose either the Food or Dimension discard\n3. Take the top 10 cards(or as many as you can) and put them \n   on top of the respective deck in any order you choose";
        }
        return string;
    }
    public String accessExample(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Pick one player besides yourself.\nThey must pick a random card and put it in the appropriate discard pile.";
            case 2 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, you may choose one to add to your hand.";
            case 3 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, split them into Food and Dimension cards.\nPlace them on the appropriate decks in any order you wish.";
            case 4 -> string = "All players besides yourself pick a random card\nThey must discard that card.\nFrom the cards discarded this way, choose either the Food or Dimension discard pile.\nTake the top 10 cards from the appropriate discard pile and place them on the appropriate deck in any order you wish.";
        }
        return string;
    }
    public int accessCost(int level) {
        return level;
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        if(Utility.dimensionOrFood()) {
            int dimension = (int) (Math.random() * (dimensionDeck.size() + 1));
            Utility.moveDimensionCards(targetPlayer.getDimensionHand(), dimension, dimensionDiscard);
        } else {
            int food = (int) (Math.random() * (foodDeck.size() + 1));
            Utility.moveFoodCards(targetPlayer.getFoodHand(), food, foodDiscard);
        }
    }

    private void accessAbility2(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
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
            cardOptions.append(food.getFoodName()).append(", ");
        }
        for (DimensionCard dimension : tempArray2) {
            cardOptions.append(dimension.getDimensionName());
            if (!dimension.getDimensionName().equals(tempArray2.get(tempArray2.size() - 1).getDimensionName())) {
                cardOptions.append(", ");
            }
        }
        cardOptions.append("]");
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
    }

    private void accessAbility3(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<FoodCard> foodDeck, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray1 = new ArrayList<>(0);
        ArrayList<DimensionCard> tempArray2 = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
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
            cardOptions.append("]");
        }
        System.out.println("These are the Food cards obtained: " + cardOptions);
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
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
        System.out.println("Choose what order to place them on the deck from the top(which is 0) to bottom(the # of cards there are minus 1)");
        ArrayList<DimensionCard> tempArray4 = new ArrayList<>(tempArray2.size());
        for (DimensionCard dimensionCard : tempArray2) {
            System.out.print("Position of " + dimensionCard + ": ");
            int index = scan.nextInt();
            tempArray4.add(index, dimensionCard);
        }
        for (int i = 0; i < tempArray4.size(); i++) {
            Utility.moveDimensionCards(tempArray4, i, dimensionDeck, i);
        }
    }

    private void accessAbility4(Player currentPlayer, ArrayList<DimensionCard> dimensionDeck, ArrayList<DimensionCard> dimensionDiscard, ArrayList<FoodCard> foodDeck, ArrayList<FoodCard> foodDiscard, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                if (Utility.dimensionOrFood()) {
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
                cardOptions.append("]");
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
                cardOptions.append("]");
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
    }
}
