import java.util.ArrayList;

public class MealCard {
   private final int level;
   public ArrayList<String> foodType;
   private ArrayList<Integer> multipliers;
   public MealCard(int level) {
       this.level = level;
       foodType = new ArrayList<String>(0);
       multipliers = new ArrayList<Integer>(0);
   }

   public void makeMealCard() {
        String[] foods = new String[]{"\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11", "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C", "\uD83E\uDD69", "\uD83C\uDF64", "\uD83C\uDF3E", "\uD83C\uDF5E", "\uD83E\uDDC0", "\uD83C\uDF76"};


        for (int i = level + 3; i > 0; i--) {
            int randNum = (int) (Math.random() * 11);
            String food = foods[randNum];
            int multiplier = 1;
            for (String type : foodType) { // for each type in the string array foodType
                if (food.equals(type)) {    // dupes clause
                    if (multiplier < 3) {
                        multiplier++;
                        multipliers.add(multiplier);
                    } else {
                        i++;
                    }
                } else {
                    foodType.add(food);
                    multipliers.add(multiplier);
                }
            }
       }
   }
}
