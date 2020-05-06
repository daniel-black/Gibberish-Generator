// Daniel Black

/**
 * The Gibberisher class pulls all of the components together
 * to create a rich dataset from which gibberish words can
 * be generated.
 */
public class Gibberisher {
    private Trie<CharBag> model;        // stores associated letter counts for each possible word segment
    private int segmentLength;
    private int letterSamplesProcessed;

    /**
     * The constructor takes in the segmentLength size and
     * sets the variable tracking how many samples have been
     * processed to zero. It also initializes the Trie of
     * CharBags, model.
     * @param segmentLength
     */
    public Gibberisher(int segmentLength){
        this.segmentLength = segmentLength;
        this.letterSamplesProcessed = 0;
        model = new Trie<>();
    }

    /**
     * The train function is the core utility of the Gibberisher
     * class. The base form of the function takes a LetterSample
     * object and adds the letter following the segment to the
     * appropriate CharBag located on the last node of the trie
     * representation of the string segment. If the string segment
     * is new to the model, it creates the branch and makes a new,
     * empty CharBag on the last node in the string representation.
     * @param ls
     */
    public void train(LetterSample ls){
        // if this is the first sample in a char bag for a given word segment...
        if(model.get(ls.getSegment()) == null){
            model.put(ls.getSegment(), new CharBag());      // make the CharBag for the new word segment branch
        }

        model.get(ls.getSegment()).add(ls.getNextLetter());
        letterSamplesProcessed++;
    }

    /**
     * This second, polymorphic train function takes a String as an
     * input and uses the static toSamples() method from LetterSample
     * to chunk the string into segments according to the specified
     * segment length. Then it simply loops over all the segments and
     * calls the base form train function on the individual LetterSample
     * objects that were created from the string.
     * @param word
     */
    public void train(String word){
        LetterSample[] chunked = LetterSample.toSamples(word, segmentLength);
        for(int k = 0; k < chunked.length; k++){
            train(chunked[k]);
        }
    }

    /**
     * The third variation of the train function takes an array of strings
     * as input. It loops over each element and calls the train function
     * that takes a string as an input which in term, after breaking up
     * the string into LetterSample objects, calls the first train function.
     * @param samples
     */
    public void train(String[] samples){
        for(int i = 0; i < samples.length; i++){
            train(samples[i]);
        }
    }

    /**
     * returns the number of samples processed.
     * @return
     */
    public int getSampleCount(){ return letterSamplesProcessed; }

    /**
     * Builds up a nonsense word starting from an empty string. The next
     * character is chosen based on the previous letters. Until the string
     * is longer than the segment length, the sample will be the entire,
     * under-progress output string. Once the output string is larger than
     * the segment size, the sample is taken as the last however many
     * characters in the output string depending on how big the segment
     * length is. The process stops when a STOP character is generated
     * as the last character in the output string. The stop character is
     * then removed and the output string is returned.
     * @return
     */
    public String generate(){
        int i = 0;
        String sample = "";
        String output = "";
        while(!output.endsWith(".")){
            if(output.length() <= segmentLength){
                sample = output;
            } else {
                sample = output.substring(i - segmentLength);
            }

            output += model.get(sample).getRandomChar();
            i++;
        }
        return output.substring(0, output.length() - 1);
    }

}
