import java.io.BufferedReader;
import java.io.FileReader;

public class Puzzle {

    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;

        int linesCount = Integer.parseInt(in.readLine());

        while ((strLine = in.readLine()) != null) {
            String[] data = strLine.split(" ");

            // ...

        }

    }
}
