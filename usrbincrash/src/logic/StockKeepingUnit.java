package logic;

public class StockKeepingUnit {

    private String label;
    private long weight;
    private long cost;

    public StockKeepingUnit(String label, long weight, long cost) {
        this.label = label;
        this.weight = weight;
        this.cost = cost;
    }

    public String getLabel() {
        return label;
    }

    public long getWeight() {
        return weight;
    }

    public long getCost() {
        return cost;
    }
}
