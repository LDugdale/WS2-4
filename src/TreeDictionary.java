import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * 4 Prefix-matching (25%)
 *
 * @author Laurie Dugdale
 */
public class TreeDictionary {

    private boolean root; // True only the current node is the main root node.
    private TreeDictionary[] children; // Array of the child nodes must always be of length 8.
    private Set<String> words; // Set containing the words for each node except the root node.

    /*
     * Constructors
     */
    /**
     * Root node constructor.
     * This constructor accepts a String representing the path to the dictionary file. When the class is instantiated
     * with this constructor (the root node constructor), the tree is created and populated with the valid words
     * ( words only containing letters of the alphabet ) in the file.
     *
     * @param path A string representing the path to the required dictionary file.
     */
    public TreeDictionary(String path){

        this.children = new TreeDictionary[8];
        this.root = true;
        String word;

        // read in the file -- scanner is inside "try with resources" as it explicitly closes
        try( Scanner in = new Scanner( new File(path) ) ){

            // while there is a next line
            while ( in.hasNextLine() ) {

                if(isValidWord(word = in.nextLine())) {
                    word = word.toLowerCase();
                    addToTree(word, word);
                }
            }

        } catch(IOException e){

            System.err.println("Got an IOException: " + e.getMessage());
        }
    }

    /**
     * Constructor for the child nodes.
     * After the Tree is created with the root node constructor this constructor is used to create the child nodes.
     */
    public TreeDictionary() {

        this.children = new TreeDictionary[8];
        this.root = false;
        this.words = new HashSet<>();
    }

    /*
     *  Getters & Setters
     */
    /**
     * Getter for words field variable
     *
     * @return words field variable.
     */
    public Set<String> getWords(){

        return this.words;
    }

    /**
     * Setter for word field variable
     * adds a word to the words Set belonging to the current node. Does not add the word String if the current node
     * is the main root node.
     *
     * @return words field variable.
     */
    public void addWord(String word){

        if (!root) {

            this.words.add(word);
        }
    }

    /**
     * Getter for the nodes in the tree
     * Looking up the array is done using char.
     *
     * e.g.
     * 'a', 'b', 'c' = 1
     * 'd', 'e', 'f' = 2
     *
     * PRECONDITION:
     * the char must be a lower case letter of the english alphabet.
     *
     * @param c a lowercase letter of the alphabet, used to find the position in the array.
     * @return The corresponding TreeDictionary node out of the array.
     */
    public TreeDictionary getNode(char c){

        return children[convertToArrayVal(c)];
    }

    /**
     * Setter for the nodes in the tree - adds an empty tree to the specified position in the children array
     * Looking up the array is done using char.
     *
     * e.g.
     * 'a', 'b', 'c' = 1
     * 'd', 'e', 'f' = 2
     *
     * PRECONDITION:
     * the char must be a lower case letter of the english alphabet.
     *
     * @param c a lowercase letter of the alphabet, used to find the position in the array.
     */
    public void setNode(char c){

        // check if trying to create a node that already exists.
        if (children[convertToArrayVal(c)] != null) {

            throw new IllegalStateException("Trying to set a node that already exists");
        }

        this.children[convertToArrayVal(c)] = new TreeDictionary();
    }

    /*
     *  Main instance methods
     */
    /**
     * addToTree is used for populating the Tree with words.
     * Method continues until word length is 0.
     *
     * PRECONDITION:
     * As this method is used directly by the root constructor there are no checks within this method to see if the
     * word is valid.
     * e.g. only chars including and between a - z and A - Z.
     * Method assumes word is valid word.
     *
     * @param word Represents the word to be added. Used for iterating through the recursive method. Should be the same String as nodeWord.
     * @param nodeWord Represents the word to be added. Used for adding the word to the set of each node passed through. Should be the same String as word.
     */
    public void addToTree(String word, String nodeWord){

        // If the word length is 0 return.
        if(word.length() == 0 ){
            return;
        }
        // Assigning to prevent calling the same method multiple times.
        char c = word.charAt(0);

        // If there is no node at the needed position create one and add the word.
        if (getNode(c) == null) {

            setNode(c);
            getNode(c).addWord(nodeWord);

            // else the node exists and we can add the word as normal
        } else {

            getNode(c).addWord(nodeWord);
        }

        // recursive method - continues until the word length is 0
        getNode(c).addToTree(word.substring(1), nodeWord);
    }


    /**
     * signatureToWords accepts a String containing numbers as a parameter representing a signature to be matched to
     * a word in the dictionary LinkedList ( e.g. if a number in the string is 2 it will be matched to "abc" ).
     * A set of all the Strings that are matched to the signature are returned.
     *
     * @param signature String of numbers representing the numbers on a keypad used for a t9 texting system
     * @return Set of Strings containing the matched words from the "words" file in the directory.
     */
    public Set<String> signatureToWords(String signature){

        // create local variables
        int length = signature.length();
        Set<String> results = new HashSet<String>();

        // check if signature is not empty
        if(length == 0){
            return new HashSet<>();
        }

        // loop through all the words returned by getFullWords method
        for (String s: getFullWords(signature)) {

            // cut down each word and add it to the set
            String word = s.substring(0, length);
            results.add(word);
        }

        return results;
    }


    /*
     * Helper methods - also instance
     */
    /**
     * Recursive method to fetch the Set of Strings from a particular node, the words in the set are uncropped.
     * getFullWords accepts a String of numbers
     * Method continues until signature length is 0.
     *
     * PRECONDITION:
     * Method assumes the signature is a valid signature.
     * e.g. characters only numeric characters 2 <= c => 9.
     *
     * @param signature String of numbers representing the numbers on a keypad used for a t9 texting system
     * @return
     */
    public Set<String> getFullWords(String signature){

        if(signature.length() == 0){

            return getWords();
        } else {

            int pos = (signature.charAt(0)-'0') - 2;

            // check signature is including and between 0 and 8
            if (pos >= 0 && 8 >= pos && children[pos] != null){

                return children[pos].getFullWords(signature.substring(1));
            } else {
                // end recursion and return empty set if invalid number
                return new HashSet<String>();
            }

        }
    }

    /**
     * convertToArrayVal is used for converting a character ( including and between a - z and A - Z) to its
     * corresponding keypad value. Used for fetching and setting nodes in the array.
     *
     * PRECONDITION:
     * char c must be a lower case alphabet character including and between a - z
     *
     * @param c the character that needs to be assigned to an array position.
     * @return an int that relates to the position of the array that character belongs in.
     */
    private int convertToArrayVal(char c){

        // if c - 'a' is equal to 18, 21, 24, 25
        if ( c- 'a' == 18 || c - 'a' == 21  || c - 'a' == 24 || c - 'a' == 25) {

            //subtract one from the result due to p and q containing 4 digits
            return ((c - 'a') - ((c - 'a') % 3)) / 3 - 1;
        } else {

            // Sum to map the letters to the 8 index array;
            return ((c - 'a') - ((c - 'a') % 3)) / 3;
        }
    }

    /**
     * isValidWord checks if a given String contains only chars including and between a - z and A - Z.
     * if another char is found method returns false else it returns true.
     *
     * @param word String of any length or type
     * @return boolean true if all chars are including and between a - z and A - Z
     */
    public static boolean isValidWord(String word){

        // check if string is empty
        if (word.isEmpty()){
            return false;
        }

        // loop through the chars in the String word
        for (char c : word.toCharArray()) {
            // if char is not between a-z and A-Z return false
            if(!Character.isLetter(c) ) {
                return false;
            }
        }

        return true;
    }
}

