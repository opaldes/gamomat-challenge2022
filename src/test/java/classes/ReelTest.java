package classes;

import gamomat.classes.Reel;
import gamomat.exceptions.InvalidIndexException;
import gamomat.exceptions.NoReelElementsException;
import org.junit.jupiter.api.*;


/**
 * tests our Reel class, and implicit our ReelElements as they
 * rely on their function
 */
class ReelTest {
    static private Reel reel;
    static private final String reelString = "ACACACABCCCABCBCACBA";

    @BeforeAll
    static void beforeAll(){
        //set up reel
        try {
            reel = new Reel(reelString);
        } catch (NoReelElementsException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void beforeEach(){
        try {
            reel.jumpTo(0);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Reel length should equal the reelString length")
    void reelLength() {
        Assertions.assertEquals(reel.getLength(), reelString.length());
    }

    @Test
    @DisplayName("Reel elements should when concatenated build the reel string")
    void reelElementsConcat() {
        StringBuilder reelElementsConcat = new StringBuilder();
        // we concat every element of our reel, it should be equal to the reelString
        while (reel.getCurrent().next() != reel.getHead()) {
            reelElementsConcat.append(reel.getCurrent().value());
            reel.next();
        }
        //last element needs concat too
        reelElementsConcat.append(reel.getCurrent().value());

        Assertions.assertEquals(reelString,reelElementsConcat.toString());
    }

    @Test
    @DisplayName("Reel elements should when concatenated build the reel string")
    void reelCircular() {
        //testing by going to the last index and looking for head as next
        try {
            Assertions.assertEquals(reel.getHead(),reel.jumpTo(reel.getLength() - 1).next());
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    @RepeatedTest(20)
    @DisplayName("Reel should be able to jump to element at index")
    void reelJumpTo() {
        //we create a random index for better coverage
        int index = (int)(Math.random() *  reel.getLength());

        try {
            Assertions.assertEquals(reelString.split("")[index],reel.jumpTo(index).value());
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Reel should throw Exception when reel has no elements to build")
    void reelNoElementsException() {
        Assertions.assertThrows(NoReelElementsException.class,()-> new Reel(""));
    }

    @Test
    @DisplayName("Reel should throw Exception when wrong index is given")
    void reelJumpToException() {
        //index to low
        Assertions.assertThrows(InvalidIndexException.class,()-> reel.jumpTo(-1));
        //index to high
        Assertions.assertThrows(InvalidIndexException.class,()-> reel.jumpTo(reel.getLength() + 1));
    }
}