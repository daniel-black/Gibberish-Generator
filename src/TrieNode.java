// Daniel Black

/**
 * This class constitutes the individual node in a
 * Trie data structure
 * @param <T>
 */
public class TrieNode<T>{
    private T data;
    private TrieNode<T>[] children;

    /**
     * The empty constructor sets the data of the
     * node to null and sets aside 26 null spaces for
     * the children TrieNode array.
     */
    public TrieNode(){
        data = null;
        children = new TrieNode[26];
    }

    /**
     * getter for the data stored on the node
     * @return
     */
    public T getData(){ return data; }

    /**
     * setter for the data stored on the node
     * @param newData
     */
    public void setData(T newData){ data = newData; }

    /**
     * Returns the child node of a node. The character
     * is transformed into the correct corresponding
     * index by subtracting away the character 'a'.
     * If the reference to the desired child node is
     * null, it is initialized as a new TrieNode and
     * returned.
     * @param letter
     * @return
     */
    public TrieNode<T> getChild(char letter){
        if(letter < 'a' || letter > 'z'){ return null; }

        if(children[letter - 'a'] == null){
            children[letter - 'a'] = new TrieNode<>();
        }
        return children[letter - 'a'];
    }

    /**
     * Given a node, this function returns the number of
     * nodes in the tree (operating as if the given node
     * is the root). Requires a recursive call to itself
     * if the child of a node has children of its own.
     * @return
     */
    public int getTreeSize(){
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(children[i] != null){
                count += children[i].getTreeSize();
            }
        }
        return count + 1;
    }
}
