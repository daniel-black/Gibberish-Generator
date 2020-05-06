// Daniel Black

/**
 * This class represents a part of a word and the letter that follows it.
 */
public class LetterSample {
    private String segment;
    private char nextLetter;
    public static final char STOP = '.';

    /**
     * The constructor initializes the string segment and the
     * next letter following the segment
     * @param segment
     * @param nextLetter
     */
    public LetterSample(String segment, char nextLetter){
        this.segment = segment;
        this.nextLetter = nextLetter;
    }

    /**
     * getter for the string segment
     * @return
     */
    public String getSegment(){ return segment; }

    /**
     * getter for the next letter
     * @return
     */
    public char getNextLetter(){ return nextLetter; }

    /**
     * Generates a bunch of letter sample objects (stored in
     * a LetterSample array) from an input string given a
     * specified segment size
     * @param input
     * @param segmentSize
     * @return
     */
    public static LetterSample[] toSamples(String input, int segmentSize){
        String seg = "";
        char let = ' ';
        input = sanitize(input) + STOP;

        LetterSample[] output = new LetterSample[input.length()];
        for(int i = 0; i < input.length(); i++){
            let = input.charAt(i);
            if(i < segmentSize){
                seg = input.substring(0, i);
            } else {
                seg = input.substring(i - segmentSize, i);
            }
            output[i] = new LetterSample(seg, let);
        }
        return output;
    }

    /**
     * Helper function that builds a new string with lowercase
     * alphabetic characters and no non-alphabetic characters
     * @param str
     * @return
     */
    private static String sanitize(String str){
        str = str.toLowerCase();
        String clean = "";
        for(int i = 0; i < str.length(); i++){
            if(Character.isAlphabetic(str.charAt(i))){
                clean += str.charAt(i);
            }
        }
        return clean;
    }

    /**
     * formats the data like: "segment" -> nextLetter
     * @return
     */
    @Override
    public String toString() {
        return "\"" + segment + "\" -> " + nextLetter;
    }
}
