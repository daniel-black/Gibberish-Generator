import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class was given out my professor. I made a few modification
 * so that it would work with different training data.
 */
public class Dictionary {

    public static String[] getWords(String selection) {
        Scanner scan = null;
        try {

            if(selection.equals("pokemon")){
                scan = new Scanner(new FileInputStream("pokemon.txt"));
            } else if(selection.equals("cities")){
                scan = new Scanner(new FileInputStream("cities.txt"));
            } else if(selection.equals("boy names")){
                scan = new Scanner(new FileInputStream("boyBabyNames.txt"));
            } else if(selection.equals("animals")){
                scan = new Scanner(new FileInputStream("animals.txt"));
            } else if(selection.equals("words")){
                scan = new Scanner(new FileInputStream("words.txt"));
            } else if(selection.equals("girl names")){
                scan = new Scanner(new FileInputStream("girlBabyNames.txt"));
            } else if(selection.equals("russian names")){
                scan = new Scanner(new FileInputStream("russianNames.txt"));
            } else if(selection.equals("russian baby boy names")){
                scan = new Scanner(new FileInputStream("russianBabyBoyNames.txt"));
            } else if(selection.equals("russian pokemon")){
                scan = new Scanner(new FileInputStream("russianPokemon.txt"));
            } else {
                // fail condition
                scan = new Scanner(new FileInputStream("fail-on-purpose"));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
            // If you are getting this error the words file isn't where it's expected to be.
        }

        List<String> words = new ArrayList<>();
        while (scan.hasNext()) {
            words.add(scan.next());
        }

        // yeah this one is a bit confusing to me too. But that's how java says to call the function, and who am I to argue
        return words.toArray(new String[0]);

    }

//    public static void main(String[] args) {
//        // This serves as the dictionary file's test. It might seem weird to have a test for the dictionary file, but, if this doesn't work a lot of other things will fail.
//        // Therefore, I figured we should have a way to confirm correctness.
//        String[] words = getWords();
//        System.out.println(words.length);
//        for (int i = 0; i < words.length; i += words.length / 15) {
//            System.out.println("\"" + words[i] + "\"");
//        }
//    }
}