import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing for the DictionaryModel class
 *
 * @author Laurie Dgudale
 */
public class DictionaryModelTest {

    private DictionaryModel dm;
    private DictionaryModel dmc;

    @Before
    public void setUp() {

        try {

            dm = new DictionaryModel();
            dmc = new DictionaryModel("words");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * addCharacter Test
     */
    @Test
    public void addCharacterTest1(){

        dm.addCharacter('2');

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("a");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTest2(){

        dm.addCharacter('2');

        List<String> actual = dm.getWords();
        List<String> expected = Arrays.asList("a", "b", "c");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTest3(){

        dm.addCharacter('1');

        List<String> actual = dm.getMessage();
        List<String> expected = new ArrayList<>();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void addCharacterTest4(){

        dm.addCharacter('1');

        List<String> actual = dm.getWords();
        List<String> expected = Arrays.asList();

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTest5(){

        dm.addCharacter('2');
        dm.addCharacter('3');
        dm.addCharacter('4');

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("aeg");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void addCharacterTest6(){

        dm.addCharacter('2');
        dm.addCharacter('3');
        dm.addCharacter('4');

        List<String> actual = dm.getWords();
        List<String> expected = Arrays.asList("aeg", "afg", "beh", "adh", "cfh", "ceg", "aei", "beg", "afi", "bfh", "adg", "cei", "adi", "bei", "cdg", "cfi", "cdi");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTest7(){

        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');
        dm.addCharacter('9');

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("zyzzy");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    /**
     * removeLastCharacter Test
     */
    @Test
    public void removeLastCharacterTest1(){

        dm.removeLastCharacter();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void removeLastCharacterTest2(){

        dm.addCharacter('2');
        dm.addCharacter('3');
        dm.removeLastCharacter();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("c");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void removeLastCharacterTest3(){

        dm.addCharacter('2');
        dm.addCharacter('3');
        dm.removeLastCharacter();
        dm.removeLastCharacter();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    /**
     * nextMatch Test
     */
    @Test
    public void nextMatchTest1(){

        dm.addCharacter('2');
        dm.addCharacter('3');
        dm.addCharacter('4');

        dm.nextMatch();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("afg");

        assertTrue(actual.toString().equals(expected.toString()));
        assertEquals(dm.getCounter(), 1);
    }

    @Test
    public void nextMatchTest2(){

        dm.nextMatch();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
        assertEquals(dm.getCounter(), 0);
    }

    @Test
    public void nextMatchTest3(){

        dm.addCharacter('2');

        dm.nextMatch();
        dm.nextMatch();
        dm.nextMatch();
        dm.nextMatch();
        dm.nextMatch();
        dm.nextMatch();
        dm.nextMatch();

        assertEquals(dm.getCounter(), 1);
    }

    /**
     * acceptWord Test
     */
    @Test
    public void acceptWord1(){

        dm.acceptWord();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("", "");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void acceptWord2(){

        dm.addCharacter('2');
        dm.acceptWord();

        List<String> actual = dm.getMessage();
        List<String> expected = Arrays.asList("a", "");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    //-----------------------------------------------------------------------------------------------------//

    /**
     * addCharacter Test
     */
    @Test
    public void addCharacterTestdmc1(){

        dmc.addCharacter('2');

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("a");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTestdmc2(){

        dmc.addCharacter('2');

        List<String> actual = dmc.getWords();
        List<String> expected = Arrays.asList("a", "b", "c");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTestdmc3(){

        dmc.addCharacter('1');

        List<String> actual = dmc.getMessage();
        List<String> expected = new ArrayList<>();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void addCharacterTestdmc4(){

        dmc.addCharacter('1');

        List<String> actual = dmc.getWords();
        List<String> expected = Arrays.asList();

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTestdmc5(){

        dmc.addCharacter('2');
        dmc.addCharacter('3');
        dmc.addCharacter('4');

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("aeg");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void addCharacterTestdmc6(){

        dmc.addCharacter('2');
        dmc.addCharacter('3');
        dmc.addCharacter('4');

        List<String> actual = dmc.getWords();
        List<String> expected = Arrays.asList("aeg", "afg", "beh", "adh", "cfh", "ceg", "aei", "beg", "afi", "bfh", "adg", "cei", "adi", "bei", "cdg", "cfi", "cdi");

        assertTrue(actual.equals(expected));
    }

    @Test
    public void addCharacterTestdmc7(){

        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');
        dmc.addCharacter('9');

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("zyzzy");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    /**
     * removeLastCharacter Test
     */
    @Test
    public void removeLastCharacterTestdmc1(){

        dmc.removeLastCharacter();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void removeLastCharacterTestdmc2(){

        dmc.addCharacter('2');
        dmc.addCharacter('3');
        dmc.removeLastCharacter();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("c");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void removeLastCharacterTestdmc3(){

        dmc.addCharacter('2');
        dmc.addCharacter('3');
        dmc.removeLastCharacter();
        dmc.removeLastCharacter();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
    }

    /**
     * nextMatch Test
     */
    @Test
    public void nextMatchTestdmc1(){

        dmc.addCharacter('2');
        dmc.addCharacter('3');
        dmc.addCharacter('4');

        dmc.nextMatch();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("afg");

        assertTrue(actual.toString().equals(expected.toString()));
        assertEquals(dmc.getCounter(), 1);
    }

    @Test
    public void nextMatchTestdmc2(){

        dmc.nextMatch();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList();

        assertTrue(actual.toString().equals(expected.toString()));
        assertEquals(dmc.getCounter(), 0);
    }

    @Test
    public void nextMatchTestdmc3(){

        dmc.addCharacter('2');

        dmc.nextMatch();
        dmc.nextMatch();
        dmc.nextMatch();
        dmc.nextMatch();
        dmc.nextMatch();
        dmc.nextMatch();
        dmc.nextMatch();

        assertEquals(dmc.getCounter(), 1);
    }

    /**
     * acceptWord Test
     */
    @Test
    public void acceptWorddmc1(){

        dmc.acceptWord();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("", "");

        assertTrue(actual.toString().equals(expected.toString()));
    }

    @Test
    public void acceptWorddmc2(){

        dmc.addCharacter('2');
        dmc.acceptWord();

        List<String> actual = dmc.getMessage();
        List<String> expected = Arrays.asList("a", "");

        assertTrue(actual.toString().equals(expected.toString()));
    }

}
