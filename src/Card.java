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

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract String accessDescription();

    public abstract String accessInstruction();

    public abstract String accessExample();

}
