public class DimensionCard {
    private final String dimensionName;
    private int dimensionLevel;
    private final String dimensionDescription;
    private final String dimensionInstruction;
    public DimensionCard(String name) {
        dimensionName  = name;
        switch (name) {
            case "\uD83E\uDED0", "\uD83C\uDF5A", "\uD83C\uDF11" -> dimensionLevel = 0;
            case "\uD83E\uDD66", "\uD83C\uDF46", "\uD83E\uDD6C" -> dimensionLevel = 1;
            case "\uD83E\uDD69", "\uD83C\uDF64" -> dimensionLevel = 2;
            case "\uD83C\uDF3E", "\uD83C\uDF5E" -> dimensionLevel = 3;
            case "\uD83E\uDDC0", "\uD83C\uDF76" -> dimensionLevel = 4;
        }
        dimensionDescription = descriptor(dimensionLevel);
        dimensionInstruction = instructor(dimensionLevel);
    }
    public String getDimensionName() {
        return dimensionName;
    }
    public int getDimensionLevel() {
        return dimensionLevel;
    }
    public String getDimensionDescription() {
        return dimensionDescription;
    }
    public String getDimensionInstruction() {
        return dimensionInstruction;
    }
    private String descriptor(int level) {
        String[] descriptor = {"A singular and lonely point in space.", "A line that stretches out infinitely to both sides.", "A plane that comprises the basics of the world.", "A space that forms our reality.", "A spacetime that cannot simply be comprehended."};
        return descriptor[level];
    }
    private String instructor(int level) {
        String[] instructor = {"1. Fuse two points to make a line\n2. Enhance fuse four points to make a plane", "1. Fuse two lines to make a plane\n2. Enhance fuse four lines to make a space", "1. Fuse two planes to make a space\n2. Enhance fuse four points to make a spacetime", "1. Fuse two spaces to make a spacetime\n2. Enhance fuse four spaces to make a sprinkle", "1. Fuse two points to make a sprinkle\n2. Enhance fuse four spacetimes to make a ketchup bottle"};
        return instructor[level];
    }
}
