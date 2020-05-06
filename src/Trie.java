// Daniel Black

/**
 * This class constitutes the Trie data structure
 * which is composed of individual TrieNodes.
 * @param <T>
 */
public class Trie<T> {
    // the type parameter represents the type of data that
    // we are looking up by string
    private TrieNode<T> root;

    /**
     * The constructor simply initializes the root as
     * a new TrieNode
     */
    public Trie(){
        root = new TrieNode<>();
    }

    /**
     * Given a string, this function returns the node
     * associated with the string. It has to burrow
     * down from the root to the appropriate child
     * nodes so that each node represents a letter
     * in the string.
     * @param input
     * @return
     */
    private TrieNode<T> getNode(String input){
        TrieNode<T> ref = root;

        for(int i = 0; i < input.length(); i++){
            ref = ref.getChild(input.charAt(i));
        }
        return ref;
    }

    /**
     * Returns the data associated with string
     * represented in the Trie. Essentially this
     * gets the data from the last node in the
     * string representation in the Trie.
     * @param input
     * @return
     */
    public T get(String input){
        return getNode(input).getData();
        // in our application, the get function returns the
        // CharBag from the last node in the string
        // TLDR: returns the CharBag for a string in the tree
    }

    /**
     * Sets the data on the last node of
     * the representation of a string in
     * the Trie
     * @param input
     * @param val
     * @return
     */
    public T put(String input, T val){
        getNode(input).setData(val);
        return val;
    }

    /**
     * Returns the root of the Trie
     * @return
     */
    public TrieNode<T> getRoot(){
        return root;
    }


}
