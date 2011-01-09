import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class StudiousStudent {

    private static LexicalNihilator lexicalNihilator = new LexicalNihilator();

    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());
        String[] data = new String[linesNumber];
        int i = 0;

        while ((strLine = in.readLine()) != null) {
            System.out.println(
                    lexicalNihilator.makeLowest(
                            new ArrayList<String>(
                                    Arrays.asList(strLine.substring(2).split(" "))
                            )
                    )
            );
        }

    }
}
