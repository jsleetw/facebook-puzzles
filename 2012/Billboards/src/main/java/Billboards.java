import logic.BoardPrinter;
import model.Board;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Billboards {


    private static Logger logger = Logger.getLogger(Billboards.class.getName());


    public static void main(String... args) throws Exception {

        List<String> lines = FileUtils.readLines(new File(args[0]), "UTF-8");
        List<String> results = new ArrayList<String>();

        Integer numberOfCases = new Integer(lines.get(0));

        logger.info("Number of cases: " + numberOfCases);

        for (int i = 1; i<= numberOfCases; i++) {

            BoardPrinter.clearCache();

            logger.info("On case" + i);

            String line = lines.get(i);
            String[] values = line.split(" ");

            Board board = new Board(new Long(values[0]), new Long(values[1]), line.substring(values[0].length() + values[1].length() + 2));

            results.add("Case #" + i + ": " + BoardPrinter.getTextSize(board));
        }
        
        FileUtils.writeLines(new File(args[1]), results);



    }
}
