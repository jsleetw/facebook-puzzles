public class PegEntryPoint {

    private int hole;
    private float probability;

    public PegEntryPoint(int holeNumber, float probability) throws Exception {

        assert probability > 1;

        this.hole = holeNumber;
        this.probability = probability;
    }

    public int getHole() {
        return hole;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return hole + " " + probability;
    }
}
