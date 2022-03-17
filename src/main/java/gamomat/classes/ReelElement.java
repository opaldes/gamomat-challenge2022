package gamomat.classes;

import gamomat.interfaces.IReelElement;

/**
 * an element of a reel our implementation uses strings
 */
public class ReelElement implements IReelElement<String> {
    private final String value;
    private IReelElement<String> next = null;

    /**
     * creates a ReelElement
     * @param reelValue string representing the actual reel value
     */
    public ReelElement(String reelValue) {
        this.value = reelValue;
    }

    @Override
    public void add(IReelElement<String> element) {
        if (this.next == null) {
            this.next = element;
            return;
        }
        this.next().add(element);
    }

    @Override
    public IReelElement<String> next() {
        return this.next;
    }

    @Override
    public String value() {
        return this.value;
    }
}
