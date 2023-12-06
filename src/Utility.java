import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    private Utility() { };
    public static ArrayList<DimensionCard> moveDimensionCards(ArrayList<DimensionCard> giftingDeck, int giftingIndex, ArrayList<DimensionCard> receivingDeck, int receivingIndex) {
        receivingDeck.add(receivingIndex, giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
        return receivingDeck;
    }
    public static ArrayList<DimensionCard> moveDimensionCards(ArrayList<DimensionCard> giftingDeck, int giftingIndex, ArrayList<DimensionCard> receivingDeck) {
        receivingDeck.add(giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
        return receivingDeck;
    }
    public static void moveFoodCards(ArrayList<FoodCard> giftingDeck, int giftingIndex, ArrayList<FoodCard> receivingDeck, int receivingIndex) {
        receivingDeck.add(receivingIndex, giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static ArrayList<FoodCard> moveFoodCards(ArrayList<FoodCard> giftingDeck, int giftingIndex, ArrayList<FoodCard> receivingDeck) {
        receivingDeck.add(giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
        return receivingDeck;
    }
    public static Player chooseAnOpponent(Scanner scan, Player currentPlayer, Player[] turnOrder) {
        String opponents = "";
        Player targetPlayer = currentPlayer;
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer) && !player.equals(turnOrder[turnOrder.length - 1])) {
                opponents += player.getPlayerName() + ", ";
            }
            opponents += "and " + turnOrder[turnOrder.length - 1];
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
}
