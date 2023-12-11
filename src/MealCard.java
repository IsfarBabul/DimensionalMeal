import java.util.ArrayList;

public class MealCard {
   private int level;
   public ArrayList<String> foodType;
   private final ArrayList<Integer> multipliers;
   private ArrayList<ArrayList<String>> mealRequirements = new ArrayList<ArrayList<String>>(0);
   public MealCard(int level) {
       this.level = level;
       foodType = new ArrayList<String>(0);
       multipliers = new ArrayList<Integer>(0);
       mealRequirements = makeMealRequirements(mealRequirements);
   }

   public void obtainCurrentRequirements(int level) {
       String[] foods = new String[]{"\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11", "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C", "\uD83E\uDD69", "\uD83C\uDF64", "\uD83C\uDF3E", "\uD83C\uDF5E", "\uD83E\uDDC0", "\uD83C\uDF76"};
       ArrayList<String> currentMealRequirements = mealRequirements.get(level + 2);
       for (String food : foods) {
           int count = 0;
           for (String item : currentMealRequirements) {
               if (food.equals(item)) {
                   if (!foodType.contains(food)) {
                       foodType.add(food);
                   }
                   count++;
               }
           }
           if (count != 0) {
               multipliers.add(count);
           }
       }
   }
   public ArrayList<ArrayList<String>> makeMealRequirements(ArrayList<ArrayList<String>> mealRequirements) {
       String[] foods = new String[]{"\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11", "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C", "\uD83E\uDD69", "\uD83C\uDF64", "\uD83C\uDF3E", "\uD83C\uDF5E", "\uD83E\uDDC0", "\uD83C\uDF76"};
       int prevMealRequirementIndex = mealRequirements.size() - 1;
       ArrayList<String> newMealRequirement = new ArrayList<>(0);
       if(!mealRequirements.get(prevMealRequirementIndex).isEmpty()) {
           newMealRequirement = mealRequirements.get(prevMealRequirementIndex);
       }
       int randNum = (int) (Math.random() * 11);
       String food = foods[randNum];
       newMealRequirement.add(food);
       mealRequirements.add(newMealRequirement);
       if (mealRequirements.size() < 6) {
           makeMealRequirements(mealRequirements);
       }
       return mealRequirements;
   }
    public void setLevel(int level) {
        this.level = level;
    }
}
