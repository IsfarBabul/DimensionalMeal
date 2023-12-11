import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    private Utility() { };
    public static void moveDimensionCards(ArrayList<DimensionCard> giftingDeck, int giftingIndex, ArrayList<DimensionCard> receivingDeck, int receivingIndex) {
        receivingDeck.add(receivingIndex, giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static void moveDimensionCards(ArrayList<DimensionCard> giftingDeck, int giftingIndex, ArrayList<DimensionCard> receivingDeck) {
        receivingDeck.add(giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static void moveFoodCards(ArrayList<FoodCard> giftingDeck, int giftingIndex, ArrayList<FoodCard> receivingDeck, int receivingIndex) {
        receivingDeck.add(receivingIndex, giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static void moveFoodCards(ArrayList<FoodCard> giftingDeck, int giftingIndex, ArrayList<FoodCard> receivingDeck) {
        receivingDeck.add(giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static Player chooseAnOpponent(Scanner scan, Player currentPlayer, Player[] turnOrder) {
        StringBuilder opponents = new StringBuilder();
        Player targetPlayer = currentPlayer;
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer) && !player.equals(turnOrder[turnOrder.length - 1])) {
                opponents.append(player.getPlayerName()).append(", ");
            }
            opponents.append("and ").append(turnOrder[turnOrder.length - 1]);
        }
        System.out.print("Choose a player(" + opponents + ")(type the name completely): ");
        String targetPlayerString = scan.nextLine();
        for (Player player : turnOrder) {
            if (targetPlayerString.equals(player.getPlayerName())) {
                targetPlayer = player;
            }
        }
        return targetPlayer;
    }
    public static boolean dimensionOrFood() {
        return (int) (Math.random() * 2) == 0;
    }
    public static Player[] identifyNextTurnOrder(Player[] turnOrder) {
        Player[] nextOrder = new Player[turnOrder.length];
        int count = 1;
        for (Player player : turnOrder) {
            if (count == turnOrder.length) {
                count = 0;
            }
            turnOrder[count] = player;
        }
        return nextOrder;
    }
    /*public static ArrayList<FoodCard> collectFoodCards(Player currentPlayer, ArrayList<FoodCard> foodDeck, Player[] turnOrder) {
        ArrayList<FoodCard> tempArray = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int food = (int) (Math.random() * (foodDeck.size() + 1));
                Utility.moveFoodCards(player.getFoodHand(), food, tempArray);
            }
        }
        return tempArray;
    }
    public static ArrayList<DimensionCard> collectDimensionCards(Player currentPlayer, ArrayList<DimensionCard> foodDeck, Player[] turnOrder) {
        ArrayList<DimensionCard> tempArray = new ArrayList<>(0);
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                int food = (int) (Math.random() * (foodDeck.size() + 1));
                Utility.moveDimensionCards(player.getDimensionHand(), food, tempArray);
            }
        }
        return tempArray;
    }*/
}
