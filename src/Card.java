public abstract class Card {
    private final String name;
    private int level;

    public Card(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void incrementLevel() {
        level++;
    }

    public abstract String accessDescription(int level);

    public abstract String accessInstruction(int level);

    public abstract String accessExample(int level);
}
