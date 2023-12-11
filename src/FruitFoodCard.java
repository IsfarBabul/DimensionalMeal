import java.util.Scanner;

public class FruitFoodCard extends FoodCard{
    public FruitFoodCard(String name, Scanner scan) {
        super(name, scan);
    }
    public void accessAbility(int level, Player currentPlayer, Player[] turnOrder) {
        switch (level) {
            case 1 -> accessAbility1(currentPlayer);
            case 2 -> accessAbility2(currentPlayer);
            case 3 -> accessAbility3(currentPlayer);
            case 4 -> accessAbility4(currentPlayer, turnOrder);
        }
    }
    public String accessDescription(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Your mood seems to lighten. I wonder why?";
            case 2 -> string = "You seem to heal faster.";
            case 3 -> string = "You feel like you can do anything!";
            case 4 -> string = "The fruit empowers you...and your opponents!";
        }
        return string;
    }
    public String accessInstruction(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Gain 1 action";
            case 2 -> string = "Gain 2 actions";
            case 3 -> string = "Gain 3 actions";
            case 4 -> string = "1. Gain 5 actions\n2. The next player in turn gains 2 actions\n3. All other remaining players gain 1 action";
        }
        return string;
    }
    public String accessExample(int level) {
        String string = "";
        switch (level) {
            case 1 -> string = "Your action count is increased by 1.";
            case 2 -> string = "Your action count is increased by 2.";
            case 3 -> string = "Your action count is increased by 3.";
            case 4 -> string = "Your action count is increased by 5.\nThe next player, the person going next after you, increases their action count by 2.\nEvery other player's action counter is increased by 1.\nOpponents' gained action points are available as soon as their next turn starts.";
        }
        return string;
    }
    public int accessCost(int level) {
        return 0;
    }

    //------------PRIVATE METHODS-----------//
    private void accessAbility1(Player currentPlayer) {
        currentPlayer.increaseActions(1);
    }

    private void accessAbility2(Player currentPlayer) {
        currentPlayer.increaseActions(2);
    }

    private void accessAbility3(Player currentPlayer) {
        currentPlayer.increaseActions(3);
    }

    private void accessAbility4(Player currentPlayer, Player[] turnOrder) {
        Player nextPlayer = null;
        boolean next = false;
        if (currentPlayer.equals(turnOrder[turnOrder.length - 1])) {
            nextPlayer = turnOrder[0];
        }
        for (Player player : turnOrder) {
            player.increaseActions(1);
            if (currentPlayer.equals(player)) {
                next = true;
            }
            if (next) {
                nextPlayer = player;
                next = false;
            }
        }
        assert nextPlayer != null;
        nextPlayer.increaseActions(1);
        currentPlayer.increaseActions(4);
    }
}
