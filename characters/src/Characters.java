import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Characters {


    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(args[0]));

        String strLine;
        int linesNumber = Integer.parseInt(in.readLine());

        while ((strLine = in.readLine()) != null) {
            System.out.println(countCharacters(strLine));
        }

    }

    public static int countCharacters(String sentence) {
        Set<Character> charactersNumberSet = new HashSet<Character>();
        for (char c : sentence.toLowerCase().toCharArray()) {
            if (!charactersNumberSet.contains(c)) {
                charactersNumberSet.add(c);
            }
        }

        return charactersNumberSet.size();

    }
}
