import logic.WaysCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class DoubleSquares {


    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());
        String[] data = new String[linesNumber];
        int i = 0;

        while ((strLine = in.readLine()) != null) {
            System.out.println(WaysCounter.countWays(Integer.parseInt(strLine)));
        }

    }
}
