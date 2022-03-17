package gamomat.classes;

import gamomat.exceptions.InvalidIndexException;
import gamomat.exceptions.NoReelElementsException;
import gamomat.interfaces.IReel;
import gamomat.interfaces.IReelElement;
import org.jetbrains.annotations.NotNull;

/**
 * reel is a bare-bones circular single linked list implementation
 */
public class Reel implements IReel {
    private IReelElement<String> head = null;
    private IReelElement<String> current = null;
    private final int length;

    /**
     * set current reel element
     * @param reelElement an reel element contains value and next element
     */
    private void setCurrent(@NotNull IReelElement<String> reelElement) {
        this.current = reelElement;
    }

    /**
     * set head reel element
     * @param reelElement an reel element contains value and next element
     */
    private void setHead(@NotNull IReelElement<String> reelElement) {
        this.head = reelElement;
        this.setCurrent(reelElement);
    }

    /**
     * gets the length of the reel aka the amount of nodes
     * important for determining the index by chance
     * @return int
     */
    public int getLength() {
        return this.length;
    }

    public IReelElement<String> getHead() {
        return this.head;
    }

    /**
     * we create a reel from a string by adding each char as
     * a node during construction
     * @param reelString an reel element contains value and next element
     */
    public Reel(String reelString) throws NoReelElementsException {
        //reels need at least a single element
        if (reelString.length() == 0) {
            throw new NoReelElementsException();
        }
        //length is important for later indexing
        this.length = reelString.length();

        String[] reelValues = reelString.split("");

        //create a single linked list from our values
        for (String reelValue : reelValues) {
            this.add(new ReelElement(reelValue));
        }

        //make the single linked list circular
        this.add(this.head);
    }

    @Override
    public void next() {
        this.setCurrent(this.current.next());
    }

    /**
     * adds an element at the end of the list
     * @param element an reel element contains value and next element
     */
    private void add(IReelElement<String> element) {
        //if no head exist the first element becomes head
        if(this.head == null) {
            this.setHead(element);
            return;
        }
        this.head.add(element);
    }

    @Override
    public IReelElement<String> getCurrent() {
        return this.current;
    }

    @Override
    public IReelElement<String> jumpTo(int index) throws InvalidIndexException {
        if (index > this.length || index < 0) {
            throw new InvalidIndexException();
        }
        //we start at the head of the reel
        this.setCurrent(this.head);
        // we can jump to the index by calling next index times
        for (int i = 0; i < index; i++) {
            this.next();
        }
        return this.current;
    }
}
