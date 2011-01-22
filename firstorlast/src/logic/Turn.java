package logic;


public class Turn {

    private long badOvertakeProbability;
    private long badPassProbability;

    public Turn(long badOvertakeProbability, long badPassProbability) {
        this.badOvertakeProbability = badOvertakeProbability;
        this.badPassProbability = badPassProbability;
    }

    public long getBadOvertakeProbability() {
        return badOvertakeProbability;
    }

    public long getBadPassProbability() {
        return badPassProbability;
    }

    @Override
    public String toString() {
        return "t:" + badOvertakeProbability + ":" + badPassProbability + "!";
    }
}

