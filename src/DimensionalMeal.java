import java.util.Scanner;
public class DimensionalMeal {
    private Scanner scan;
    private FoodCard[] foodDeck;
    private FoodCard[] foodDiscard;
    private DimensionCard[] dimensionDeck;
    private DimensionCard[] dimensionDiscard;
    private MealCard[] mealDeck;
    private MealCard[] mealDiscard;
    private Player[] turnOrder;
    public DimensionalMeal() { }
    public void start(Scanner scan) {
        this.scan = scan;
        setup();
        Player player = new Player();
        System.out.println(player.getPlayerName());
        loadGUI(player);
    }
    private void setup() {
        System.out.println("How many players would like to play? ");
        int numPlayers = scan.nextInt();
    }
    public void loadGUI(Player currentPlayer) {
        System.out.println("\uD83C\uDFB4(65) - Dimension Deck              \uD83D\uDDC2️(3) - Dimension Discard");
        System.out.println();
        System.out.println("\uD83E\uDDFA(69) - Food Deck(it's a basket)    \uD83D\uDDD1\uFE0F(7) - Food Discard");
        System.out.println();
        System.out.println("\uD83C\uDF7D\uFE0F(14) - Meal Deck                   ♻️(12) - Meal Discard");
        System.out.println("_____________");
        System.out.println("|Meal Card: |");
        System.out.println("|           |");
        System.out.println("|\uD83E\uDD69x2       |");
        System.out.println("|\uD83E\uDD6Cx1       |");
        System.out.println("|\uD83C\uDF64x1       |");
        System.out.println("|\uD83E\uDED0x1       |");
        System.out.println("|           |");
        System.out.println("| \uD83C\uDFC6WIN!!!  |");
        System.out.println("L___________J");
        System.out.println();
        System.out.println("Current Dimension Level: 4");
        System.out.println("Actions Left: 3");
        System.out.println("[Player name]'s Hand:");
        System.out.println("|\uD83E\uDD694|\uD83E\uDDC00|\uD83C\uDF113|\uD83C\uDF3E1|\uD83C\uDF3E2|\uD83E\uDD6C2|\uD83C\uDF690|┃|┃|⛊|☀| ");
        System.out.println("[0] Play a Food Card in your hand(Cost: Whatever the card costs of food)");
        System.out.println("[1] Buy a Food Card(Cost: 2 actions)");
        System.out.println("[2] Check the Stats of a Food Card you have");
        System.out.println("[3] Buy a Dimension Card(Cost: 1 action)");
        System.out.println("[4] Fuse two Dimension Cards(Cost: Level of dimension you want to get to)");
        System.out.println("[5] Enhanced Fusion(Cost: Level of dimension you want to get to + 2 actions)");
        System.out.println("[6] Check Food Discard");
        System.out.println("[7] Check Dimension Discard");
        System.out.println("[8] Check Meal Discard");
        System.out.println("[9] Upgrade a Food Card(Cost: 1 action)");
        System.out.println();
        System.out.println("Enter an Option:");
    }
}
