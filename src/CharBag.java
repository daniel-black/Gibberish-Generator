// Daniel Black

import java.util.Random;

/**
 * Counts occurrences of lowercase english characters and the
 * STOP character, '.'. Also capable of generating random
 * letters based on the frequency with which they appear
 * in the CharBag
 */
public class CharBag {

    private int numThingsAdded;
    private int numThingsRemoved;
    static final int SIZE = 27;         // indices 0 to 26
    int[] bagCount = new int[SIZE];

    /**
     * Default constructor initializes two helpful tracking
     * variables to zero
     */
    public CharBag(){
        numThingsAdded = 0;
        numThingsRemoved = 0;
    }

    /**
     * Adds a character to the CharBag. Unlike a set, a bag
     * can have duplicates of a single item. I chose to use
     * an array implementation where the indices of the array
     * correspond like: 0 -> 'a', 1 -> 'b' ... 25 -> 'z',
     * 26 -> '.'
     * @param myChar
     */
    public void add(char myChar){
        numThingsAdded++;
        // If myChar is uppercase, make it lowercase
        // If myChar is not alphabetic, make it the stop character
        if(Character.isUpperCase(myChar)){
            myChar = Character.toLowerCase(myChar);
        } else if(!Character.isAlphabetic(myChar)){
            myChar = 46;
        }

        if(myChar == 46){
            bagCount[SIZE - 1]++;
        } else {
            bagCount[myChar - 'a']++;
        }
    }

    /**
     * This function removes a character from the CharBag. It
     * performs the same uppercase -> lowercase or
     * non-alphabetic -> stop character transformation like
     * we did in the add function. From their, we check it myChar
     * is the stop character. If it is and we have 1 or more already
     * in the bag, we remove one and update our tracking variable.
     * If its a valid alphabetic character with 1 or more instances
     * already present, one is removed and the tracking variable
     * is updated.
     * @param myChar
     */
    public void remove(char myChar){
        if(Character.isUpperCase(myChar)){
            myChar = Character.toLowerCase(myChar);
        } else if(!Character.isAlphabetic(myChar)){
            myChar = 46;
        }

        if(myChar == 46){
            if(bagCount[SIZE - 1] > 0){
                bagCount[SIZE - 1]--;
                numThingsRemoved++;
            } else {
                return;
            }
        } else if(bagCount[myChar - 'a'] > 0){
            bagCount[myChar - 'a']--;
            numThingsRemoved++;
        }
    }

    /**
     * This function counts how many of a specified
     * character are present in the CharBag.
     * @param someChar
     * @return
     */
    public int getCount(char someChar){
        if(Character.isUpperCase(someChar)){
            someChar = Character.toLowerCase(someChar);
        } else if(!Character.isAlphabetic(someChar)){
            someChar = 46;
        }

        if(someChar == 46){
            return bagCount[SIZE -1];
        } else {
            return bagCount[someChar - 'a'];
        }
    }

    /**
     * This function computes how many items are in the CharBag
     * by subtracting the number of things removed from the
     * number of things added. The tracking variables allow this
     * to be done in O(1) time instead of O(n) if we had to loop
     * through every item.
     * @return
     */
    public int getSize() {
        return numThingsAdded - numThingsRemoved;
    }

    /**
     * This function returns a random character based on the
     * frequency with which characters appear in the CharBag.
     * If the bag is empty, the STOP character is returned.
     * Otherwise, a random number between 0 and the total
     * number of items in the bag (exclusive) is created and
     * the bag is looped through and the number of occurences
     * of each item starting with 'a' is subtracted away from
     * the random number until it is less than zero. The
     * character that made the random number go below zero is
     * then returned.
     * @return
     */
    public char getRandomChar(){
        if(getSize() == 0){ return '.'; }

        Random rand = new Random();
        int count = rand.nextInt(this.getSize());

        for(int k = 0; k < SIZE - 1; k++) {
            count -= bagCount[k];
            if (count < 0) { return (char) (k + 'a'); }
        }
        return '.';
    }

    /**
     * Loops through all 27 items and prints how many of each
     * are in the bag. Formatted like:
     * "CharBag{a:1, b:3, c:0 ...}"
     * @return
     */
    @Override
    public String toString() {
        String overview = "CharBag{";
        for(int i = 0; i < SIZE; i++){
            if(i + 1 != SIZE){
                overview += (char) (i + 'a') + ":" + bagCount[i] + ", ";
            } else {
                overview += ".:" + bagCount[i] + "}";
            }
        }
        return overview;
    }
}
