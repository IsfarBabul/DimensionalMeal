import java.util.ArrayList;

public class MealCard extends Card{
   private String name;
   private int level;
   public ArrayList<String> foodItem;
   private final ArrayList<Integer> multipliers;
   private ArrayList<ArrayList<String>> mealRequirements = new ArrayList<>(0);
   public MealCard(String name, int level) {
       super(name, level);
       foodItem = new ArrayList<>(0);
       multipliers = new ArrayList<>(0);
       mealRequirements = makeMealRequirements(mealRequirements);
   }

    public ArrayList<String> getFoodItem() {
        return foodItem;
    }

    public ArrayList<Integer> getMultipliers() {
        return multipliers;
    }

    public void obtainCurrentRequirements(int level) {
       String[] foods = new String[]{"\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11", "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C", "\uD83E\uDD69", "\uD83C\uDF64", "\uD83C\uDF3E", "\uD83C\uDF5E", "\uD83E\uDDC0", "\uD83C\uDF76"};
       ArrayList<String> currentMealRequirements = mealRequirements.get(level + 2);
       for (String food : foods) {
           int count = 0;
           for (String item : currentMealRequirements) {
               if (food.equals(item)) {
                   if (!foodItem.contains(food)) {
                       foodItem.add(food);
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
       ArrayList<String> newMealRequirement;
       if (mealRequirements.isEmpty()) {
           newMealRequirement = new ArrayList<>();
       } else {
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
        obtainCurrentRequirements(level);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String accessDescription(int level) {
        return null;
    }

    public String accessInstruction(int level) {
        return null;
    }

    public String accessExample(int level) {
        return null;
    }
}
