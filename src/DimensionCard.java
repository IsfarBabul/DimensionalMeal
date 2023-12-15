public class DimensionCard extends Card{
    public DimensionCard(String name, int level) {
        super(name, level);
    }


    public String accessDescription() {
        String[] descriptor = {"A singular and lonely point in space.", "A line that stretches out infinitely to both sides.", "A plane that comprises the basics of the world.", "A space that forms our reality.", "A spacetime that cannot simply be comprehended."};
        return descriptor[super.getLevel()];
    }


    public String accessInstruction() {
        String[] instructor = {"1. Fuse two points to make a line\n2. Enhance fuse four points to make two planes", "1. Fuse two lines to make a plane\n2. Enhance fuse four lines to make two spaces", "1. Fuse two planes to make a space\n2. Enhance fuse four points to make two spacetimes", "1. Fuse two spaces to make a spacetime\n2. No enhanced fusions available", "1. Too Unstable to fuse\n2. Enhanced fusion is catastrophic"};
        return instructor[super.getLevel()];
    }


    public String accessExample() {
        String[] instructor = {"1. You have two Dimension Point cards, spend 1 action to fuse them, and gain a Dimension Line card\n2. You have four Dimension Point cards, spend 3 actions to fuse them, and gain a Dimension Plane card",
                               "1. You have two Dimension Line cards, spend 2 action to fuse them, and gain a Dimension Line card\n2. You have four Dimension Point cards, spend 4 actions to fuse them, and gain a Dimension Plane card",
                               "1. You have two Dimension Plane cards, spend 3 action to fuse them, and gain a Dimension Line card\n2. You have four Dimension Point cards, spend 5 actions to fuse them, and gain a Dimension Plane card",
                               "1. You have two Dimension Space cards, spend 4 action to fuse them, and gain a Dimension Line card\n2. This would cause a minor chaos implosion",
                               "1. Fusing these powerful things causes a minor chaos explosion\n2. CATASTROPHIC CHAOS BANG - theorized to have caused the Big Bang of the universe"};
        return instructor[super.getLevel()];
    }
}
