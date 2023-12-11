import java.util.ArrayList;
import java.util.Scanner;

public class ProteinFoodCard extends FoodCard{
    public ProteinFoodCard(String name, Scanner scan) {
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
            case 1 -> string = "You seem to go faster.";
            case 2 -> string = "Your picking up the pace.";
            case 3 -> string = "You know how to run!";
            case 4 -> string = "Your Usain Bolt! Your speed shocks the crowd!";
        }
        return string;
    }
    public String accessInstruction(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "1. Choose an opponent\n2. They start their next turn with 1 fewer action";
            case 2 -> string = "1. On your next turn, start with 2 more actions\n2.Choose an opponent\n3. They start their next turn with 1 fewer action";
            case 3 -> string = "1. On your next turn, start with 3 more actions\n2. Choose an opponent\n3. They start their next turn with 1 fewer action";
            case 4 -> string = "1. On your next turn, start with 5 more actions\n2. All other players start their next turn with 1 fewer action";
        }
        return string;
    }
    public String accessExample(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Pick one player besides yourself.\nTheir action count is decreased by 1.";
            case 2 -> string = "Your action count is increased by 2 on your next turn.\nAlso, Pick one player besides yourself\nTheir action count is decreased by 1.";
            case 3 -> string = "Your action count is increased by 3 on your next turn.\nAlso, Pick one player besides yourself\nTheir action count is decreased by 1.";
            case 4 -> string = "Your action count is increased by 5 on your next turn.\nAlso, Pick all other players' action counts are decreased by 1.";
        }
        return string;
    }
    public int accessCost(int level) {
        return level;
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
    }

    private void accessAbility2(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
        currentPlayer.increaseNextTurnActions(2);
    }

    private void accessAbility3(Player currentPlayer, Player[] turnOrder) {
        Utility.chooseAnOpponent(scan, currentPlayer, turnOrder).decreaseNextTurnActions(1);
        currentPlayer.increaseNextTurnActions(3);
    }

    private void accessAbility4(Player currentPlayer, Player[] turnOrder) {
        for (Player player : turnOrder) {
            if (!player.equals(currentPlayer)) {
                player.decreaseNextTurnActions(1);
            }
        }
        currentPlayer.increaseNextTurnActions(5);
    }
}
