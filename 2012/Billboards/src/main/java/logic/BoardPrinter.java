package logic;

import dp.StateTreeBuilder;
import model.Board;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.min;

public class BoardPrinter {

    private static Logger logger = Logger.getLogger(BoardPrinter.class.getName());

    private static Map<String, Long> cache = new TreeMap<String, Long>();

    public static long getTextSize(Board b) {

        String cacheKey = b.getText() + " @ " + b.getLinesOccupied();

        if (cache.containsKey(cacheKey)) {
            logger.debug("Cache hit: " + cacheKey + " : " + cache.get(cacheKey));
            return cache.get(cacheKey);
        }

        long max = min(
                b.getHeight() / (1 + b.getLinesOccupied()),
                b.getWidth() / b.getText().length()
        );

        for (Board newBoard : StateTreeBuilder.getLevelOfStates(b)) {

            long diffLength = b.getText().length() - newBoard.getText().length() - 1;

            max = Math.max(
                    max,
                    Math.min(b.getWidth() / diffLength, getTextSize(newBoard))
            );
        }

        cache.put(cacheKey, max);

        return max;
    }

    public static void clearCache() {
        cache = new TreeMap<String, Long>();
    }


}
