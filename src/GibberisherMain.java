import java.util.Scanner;

/**
 * Like the dictionary class, my professor wrote most of this
 * class, I made some tweaks to make it more accessible and
 * handle user input to decide what dataset to generate
 * nonsense words from.
 */
public class GibberisherMain {

    public static String[] runGibberisherTest(int segmentLength, int wordTests, String selection) {
        Gibberisher g = new Gibberisher(segmentLength);
        long start = System.currentTimeMillis();
        g.train(Dictionary.getWords(selection));
        long stop = System.currentTimeMillis();
        System.out.println("Loaded " + g.getSampleCount() + " samples in " + (stop - start) + "ms.");

        String[] words = new String[wordTests];
        start = System.currentTimeMillis();
        for (int i = 0; i < words.length; i++) {
            words[i] = g.generate();
        }
        stop = System.currentTimeMillis();
        System.out.println("Generated " + words.length + " words in Loaded in " + (stop - start) + "ms.");
        return words;
    }

    public static void printSome(String[] words, int length) {
        System.out.println("Here's a few:");
        for (int i = 0; i < length; i++) {
            System.out.println(" * "+words[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // chose training data set
        System.out.println("Chose a training set: 'words', 'russian names', 'russian baby boy names', 'pokemon', 'russian pokemon', 'cities', 'animals', 'girl names', or 'boy names'");
        System.out.print(">> ");
        String selection = scanner.nextLine();

        // chose segment size
        System.out.println("Chose a segment size:");
        System.out.println("(small numbers will produce more irregular results while large numbers may generate strings from the dataset; 3 is usually a good size)");
        System.out.print(">> ");
        int segSize = scanner.nextInt();

        // chose how many nonsense strings to generate and print
        System.out.println("Chose how many results to print:");
        System.out.print(">> ");
        int numToPrint = scanner.nextInt();

        printSome(runGibberisherTest(segSize, numToPrint, selection), numToPrint);

    }
}
