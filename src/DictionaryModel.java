import java.util.*;

/**
 * @author Laurie Dugdale
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

    private int counter;
    private TreeDictionary dictionary; // The dictionary data structure
    private String signature; // signature value
    private List<String> wordsList;
    private List<String> message;

    /*
     *
     */
    /**
     *
     * @param dictionaryFile
     * @throws java.io.IOException
     */
    public DictionaryModel(String dictionaryFile) throws java.io.IOException{

        this.message = new ArrayList<>();
        wordsList = new ArrayList<>();
        message.add("");
        this.signature = "";
        this.dictionary = new TreeDictionary(dictionaryFile);
    }

    /**
     *
     * @throws java.io.IOException
     */
    public DictionaryModel() throws java.io.IOException{
        wordsList = new ArrayList<>();
        this.message = new ArrayList<>();
        message.add("");
        this.signature = "";
        this.dictionary = new TreeDictionary("words");
    }

    public int getCounter() {
        return counter;
    }

    public TreeDictionary getDictionary() {
        return dictionary;
    }

    public String getSignature() {
        return signature;
    }

    public List<String> getWordsList() {
        return wordsList;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setDictionary(TreeDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    /**
     *
     */
    public List<String> getMessage() {

        return message;
    }

    @Override
    /**
     *
     */
    public void addCharacter(char key) {
        counter = 0;
        this.signature = signature + key;
        wordsList.clear();
        wordsList.addAll(dictionary.signatureToWords(this.signature));

        if(!wordsList.isEmpty()) {

            message.set(message.size() - 1,wordsList.get(0));
            setChanged();
            notifyObservers(message);
        }
    }

    @Override
    /**
     *
     */
    public void removeLastCharacter() {

        counter = 0;
        if (this.signature.length() > 0) {

            this.signature = signature.substring(0, signature.length() - 1);
            wordsList.clear();
            wordsList.addAll(dictionary.signatureToWords(this.signature));

            if (!wordsList.isEmpty()) {
                String tempWord = wordsList.get(0);
                message.set(message.size() - 1, tempWord.substring(0, tempWord.length() - 1));
                setChanged();
                notifyObservers(message);
            }

        } else {

        }
    }

    @Override
    /**
     *
     */
    public void nextMatch() {

        counter = (counter + 1) % wordsList.size();
        message.set(message.size() -1, wordsList.get(counter));

        setChanged();
        notifyObservers(message);
    }

    @Override
    /**
     *
     */
    public void acceptWord() {

        message.add("");
        wordsList.clear();
        signature = "";

        setChanged();
        notifyObservers(message);
    }
}
