import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    private Utility() { }
    public static void moveCards(ArrayList<Card> giftingDeck, int giftingIndex, ArrayList<Card> receivingDeck, int receivingIndex) {
        receivingDeck.add(receivingIndex, giftingDeck.get(giftingIndex));
        giftingDeck.remove(giftingDeck.get(giftingIndex));
    }
    public static void moveCards(ArrayList<Card> giftingDeck, int giftingIndex, ArrayList<Card> receivingDeck) {
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
    /*public static boolean determineRandomCard(ArrayList<Card> deck) {
        Player targetPlayer = Utility.chooseAnOpponent(scan, currentPlayer, turnOrder);
        int cardIndex = (int) (Math.random() * (targetPlayer.getHand().size() + 1));
        if(targetPlayer.getHand().get(cardIndex) instanceof DimensionCard) {
            Utility.moveCards(targetPlayer.getHand(), cardIndex, dimensionDiscard);
        } else {
            Utility.moveCards(targetPlayer.getHand(), cardIndex, foodDiscard);
        }
    }*/
    public static void printElementNames(ArrayList<Card> deck) {
        System.out.print("|");
        for (Card card : deck) {
            System.out.print(card.getName() + card.getLevel() + "|");
        }
    }
    public static void printElementNames(Card[] deck) {
        System.out.print("|");
        for (int i = 0; i < deck.length - 1; i++) {
            System.out.print(deck[i].getName() + "|");
        }
    }
    public static void enforceQuestionAnswer() {

    }
    public static void funText(String text, int textNum) {
        System.out.println((char)27 + "[" + textNum + "m" + text + ((char)27 + "[0m"));
    }
    public static String returnFunText(String text, int textNum) {
        return (char)27 + "[" + textNum + "m" + text + ((char)27 + "[0m");
    }
    public static void clearWindow() {
        try {
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Issue found with clearing the window: " + e.getMessage());
        }
    }
}
