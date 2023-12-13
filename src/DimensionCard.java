public class DimensionCard extends Card{
    public DimensionCard(String name, int level) {
        super(name, level);
    }


    public String accessDescription(int level) {
        String[] descriptor = {"A singular and lonely point in space.", "A line that stretches out infinitely to both sides.", "A plane that comprises the basics of the world.", "A space that forms our reality.", "A spacetime that cannot simply be comprehended."};
        return descriptor[level];
    }


    public String accessInstruction(int level) {
        String[] instructor = {"1. Fuse two points to make a line\n2. Enhance fuse four points to make a plane", "1. Fuse two lines to make a plane\n2. Enhance fuse four lines to make a space", "1. Fuse two planes to make a space\n2. Enhance fuse four points to make a spacetime", "1. Fuse two spaces to make a spacetime\n2. No enhanced fusions available", "1. Too Unstable to fuse\n2. Enhanced fusion is catastrophic"};
        return instructor[level];
    }


    public String accessExample(int level) {
        String[] instructor = {"1. Fuse two points to make a line\n2. Enhance fuse four points to make a plane", "1. Fuse two lines to make a plane\n2. Enhance fuse four lines to make a space", "1. Fuse two planes to make a space\n2. Enhance fuse four points to make a spacetime", "1. Fuse two spaces to make a spacetime\n2. No enhanced fusions available", "1. Too Unstable to fuse\n2. Enhanced fusion is catastrophic"};
        return instructor[level];
    }
}
