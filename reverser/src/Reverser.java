import java.io.BufferedReader;
import java.io.FileReader;

public class Reverser {


    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());

        while ((strLine = in.readLine()) != null) {

            int i = 1;
            for (String word : strLine.split(" ")) {
                System.out.print(reverse(word, i) + " ");
                i++;
            }

            System.out.println();

        }

    }

    private static String reverse(String word, int number) {
        if (word.length() <= number) {
            return word;
        } else {
            return reverse(right(word), number) + reverse(left(word), number);
        }
    }

    private static String right(String word) {
        return word.substring(word.length() / 2);
    }

    private static String left(String word) {
        return word.substring(0, word.length() / 2);
    }
}
