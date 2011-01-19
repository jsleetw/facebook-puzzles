import logic.WaysCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Almost {


    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());

        while ((strLine = in.readLine()) != null) {
            String[] data = strLine.split(" ");
            long a = Long.valueOf(data[0]);
            long c = Long.valueOf(data[1]);

            long b = c / a;

            if (Math.abs(a*b - c) > Math.abs(a*(b+1) - c)) {
                b++;
            }

            System.out.println(b);

        }

    }
}
