import java.util.HashMap;

public class PegCase {

    private PegField field;
    private int goalPosition;

    public int optimalEntryPoint = -1;
    public float optimalProbability = 0f;

    public PegCase(PegField field, int goalPosition) {
        System.out.println("Working on field of: " + field.getRows() + " rows; Goal: " + goalPosition);
        this.goalPosition = goalPosition;
        this.field = field;
    }

    public void findOptimalEntryPoint() {

        for (int c = 0; c < field.getCols(); c++) {
            System.out.println("("+field.getRows()+"): position " + c);
            int desiredExitPoint;
            float optimalOutOfProbability;

            if (field.getRows() > 1) {
                PegCase subCase = new PegCase(field.cloneWithoutFirstRow(), goalPosition);
                subCase.findOptimalEntryPoint();

                desiredExitPoint = subCase.optimalEntryPoint;
                optimalOutOfProbability = subCase.optimalProbability;
            } else {
                desiredExitPoint = goalPosition;
                optimalOutOfProbability = 1;
            }

            if (optimalOutOfProbability == 0) {
                return;
            }

            float currentProbability = getProbabilityForPosition(field, 0,  c,  desiredExitPoint);

            if (currentProbability == 0) {
                return;
            }

            System.out.println("("+field.getRows()+") Sub probability: " + optimalOutOfProbability + " for EP: " + desiredExitPoint);
            System.out.println("("+field.getRows()+") Current probability: " + c + " -> " + desiredExitPoint + " : " + currentProbability);

            if (currentProbability * optimalOutOfProbability > optimalProbability) {
                optimalProbability = currentProbability * optimalOutOfProbability;
                optimalEntryPoint = c;
            }
        }
    }

    private float getProbabilityForPosition(PegField field, int r, int c, int goalPosition) {

        if (field.isHole(r, c) && goalPosition == c) {
            //System.out.println("a");
            // This is a hole above the goal
            return 1f;
        }

        if (
                field.isPeg(r, c)
                        &&
                (
                    (goalPosition == c+1 && !field.isWall(r, c+1))
                            ||
                    (goalPosition == c-1 && !field.isWall(r, c-1))
                )
           ) {
            //System.out.println("b");
            return 0.5f;
        }

        if (field.isWall(r, c) &&
                (c==0 && goalPosition == c+1 && !field.isPeg(r, c+1)) || // Left wall and goal is nearest right spot
                (c==field.getCols()-1 && goalPosition == c-1 && !field.isPeg(r, c-1)) // Right wall and goal is nearest left spot
            ) {
            //System.out.println("c");
            return 1f;
        }

        return 0;
    }
}
