import java.util.*;

/**
 * @author Laurie Dugdale
 */
public class DictionaryModel extends Observable implements DictionaryModelInterface {

    private int counter;
    private TreeDictionary dictionary; // The dictionary data structure
    private String signature; // signature value
    private List<String> words;
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
        words = new ArrayList<>();
        message.add("");
        this.signature = "";
        this.dictionary = new TreeDictionary(dictionaryFile);
    }

    /**
     *
     * @throws java.io.IOException
     */
    public DictionaryModel() throws java.io.IOException{

        words = new ArrayList<>();
        this.message = new ArrayList<>();
        message.add("");
        this.signature = "";
        this.dictionary = new TreeDictionary("words");
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
        words.clear();
        words.addAll(dictionary.signatureToWords(this.signature));

        if(!words.isEmpty()) {

            message.set(message.size() - 1,words.get(0));
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

            words = new LinkedList<>();
            words.addAll(dictionary.signatureToWords(this.signature));

            if (!words.isEmpty()) {
                String tempWord = words.get(0);
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

        counter = (counter + 1) % words.size();
        message.set(message.size() -1, words.get(counter));

        setChanged();
        notifyObservers(message);
    }

    @Override
    /**
     *
     */
    public void acceptWord() {

        message.add("");
        words.clear();
        signature = "";

        setChanged();
        notifyObservers(message);
    }
}
