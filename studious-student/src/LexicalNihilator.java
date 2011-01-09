import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class LexicalNihilator {

    public String makeLowest(ArrayList<String> input) throws Exception{

        if (input.size() < 2) {
            throw new Exception("Input length is lower then 2!");
        }

        if (input.size() == 2) {
            String lowestValue = getLowest(input);
            if (input.get(0) == lowestValue) {
                return lowestValue + input.get(1);
            }

            return lowestValue + input.get(0);
        }

        ArrayList<String> availableOptions = new ArrayList<String>();

        for(int i=0; i< input.size(); i++) {

            ArrayList<String> reducedInput = new ArrayList<String>(input);
            reducedInput.remove(i);

            availableOptions.add(input.get(i) + makeLowest(reducedInput));
        }

        return getLowest(availableOptions);
    }

    private String getLowest(ArrayList<String> options) {
        Collections.sort(options);
        return options.get(0);
    }
}
