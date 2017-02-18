import java.util.*;

/**
 * @author Laurie Dugdale
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

    private int counter; // counter used for keeping track of the current position in the words list. must never be larger than words length
    private TreeDictionary dictionary; // The dictionary data structure
    private String signature; // signature value, used for fetching the right words and prefixes from the dictionary
    private List<String> words; // the current list of words that match the signature
    private List<String> message; // the current message displayed in messagePane

    /*
     * Constructors
     */
    /**
     * DictionaryModel constructor takes a custom path to a dictionary file
     *
     * @param dictionaryFile
     * @throws java.io.IOException
     */
    public DictionaryModel(String dictionaryFile) throws java.io.IOException{

        // Initialize the field variables.
        this.message = new ArrayList<>();
        words = new ArrayList<>();
        message.add("");
        this.signature = "";

        // Create the dictionary
        this.dictionary = new TreeDictionary(dictionaryFile);
    }

    /**
     * DictionaryModel constructor the path to the dictionary file is hard coded.
     *
     * @throws java.io.IOException
     */
    public DictionaryModel() throws java.io.IOException{

        // Initialize the field variables.
        words = new ArrayList<>();
        this.message = new ArrayList<>();
        message.add("");
        this.signature = "";

        // Create the dictionary
        this.dictionary = new TreeDictionary("words");
    }


    /*
     * Getters and Setters - in this scenario mostly used to help with testing. This many setters feels more of a sin.
     * However I feel they are justified for the purposes of testing.
     */
    /**
     * This setter reinitialize the words ArrayList and add all the words that match the current signature value
     * to the words List
     *
     * @param key represents the key to add to the signature used by the addCharacter method must only be one char long
     */
    public void setWords(String key){

        words = new ArrayList<>();
        words.addAll(dictionary.signatureToWords(this.signature + key));
    }

    /**
     * This message is a wrapper for the List set method.
     * sets the variable in the requested position in the
     *
     * @param i the position to change
     * @param word The String to set in the position i
     */
    public void setMessage(int i, String word){

        if (!message.isEmpty()) {
            message.set(i, word);
        }
    }

    /**
     * Gets the String from the specific position in the words array
     *
     * @param i the position to get
     * @return the String at the given position
     */
    public String getWord(int i){


        return words.get(i);
    }

    /**
     * Getter for the words List
     *
     * @return The words List
     */
    public List<String> getWords(){

        return this.words;
    }

    /**
     * Setter for the signature field variable
     *
     * @param signature The signature that represents the digits of a keypad
     */
    public void setSignature(String signature){

        this.signature = signature;
    }

    /**
     * Getter for the signature field variable
     *
     * @return The signature field variable
     */
    public String getSignature(){

        return this.signature;
    }

    /**
     * Setter for the counter field variable
     *
     * @param counter when being set the counter should always be incremented and not exceed the limit of the words List
     */
    public void setCounter(int counter){

        this.counter = counter;
    }

    /**
     * A simple getter for the counter field variable
     *
     * @return the counter field variable
     */
    public int getCounter(){

        return this.counter;
    }


    /*
     * The main instance methods specified in the interface.
     */
    @Override
    /**
     * This method is used by the Message pane to display the message to the JPanel. It is looped and concatenated to
     * a string with an empty space between each entry.
     */
    public List<String> getMessage() {

        return message;
    }

    @Override
    /**
     * addCharacter adds a single character to the signature which updates the word at the end of the message list
     *
     * @param key key represents a number to be added to the signature
     */
    public void addCharacter(char key) {

        // Set the words list to the signature plus the character passed into this method
        setWords("" + key);
        // If the words list is not empty
        if(!words.isEmpty()) {

            // Add the key char to the signature field variable
            setSignature(getSignature() + key);
            // Set the last entry in the message list to the first entry in the words list
            setMessage(getMessage().size() - 1, getWord(0));
            // Reset the counter to 0
            setCounter(0);

            setChanged(); // Set the internal flag that indicates this observable has changed state
            notifyObservers(); // Checks the internal flag and notifies all observers
        }
    }

    @Override
    /**
     * removeLastCharacter removes the last character to the signature which updates the word at the end of the
     * message list
     */
    public void removeLastCharacter() {

        // Set the words list to the signature
        setWords("");

        // If the words list is not empty
        if (!words.isEmpty()) {

            // create temp word from the first entry in the words list
            String tempWord = getWord(0);
            // Set the last entry in the message list to the temp word string with the last character removed
            setMessage(getMessage().size() - 1, tempWord.substring(0, tempWord.length() - 1));
            // Set the signature variable to the current value with the last character removed
            setSignature(getSignature().substring(0, getSignature().length() - 1));
            // Reset the counter to 0
            setCounter(0);

            setChanged(); // Set the internal flag that indicates this observable has changed state
            notifyObservers(); // Checks the internal flag and notifies all observers
        }
    }

    @Override
    /**
     * Increments the counter variable and adds the next word in the List to the Message List
     */
    public void nextMatch() {

        // check that get words is not empty as we don't want to mod 0
        if (!getWords().isEmpty()) {

            // Increment the counter a mod it by the size of the words array so its never bigger and loops
            setCounter((getCounter() + 1) % getWords().size());
            // set the last entry in the message to the next position in the words list
            setMessage(getMessage().size() - 1, getWord(counter));

            setChanged(); // Set the internal flag that indicates this observable has changed state
            notifyObservers(); // Checks the internal flag and notifies all observers
        }
    }

    @Override
    /**
     * Accepts the current word and starts the next potential word
     */
    public void acceptWord() {

        // add a new entry to the end of the message List
        message.add("");
        // reset signature
        setSignature("");

        setChanged(); // Set the internal flag that indicates this observable has changed state
        notifyObservers(); // Checks the internal flag and notifies all observers
    }
}
