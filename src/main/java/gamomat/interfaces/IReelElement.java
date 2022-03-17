package gamomat.interfaces;

/**
 * reel item interface contains index and value
 * @param <T>
 */
public interface IReelElement<T> {
    /**
     * next element in the list
     * @return IReelElement
     */
    IReelElement<T> next();

    /**
     * returns value
     * @return T
     */
    T value();

    /**
     * add the next element, when next element is given it traverses the list
     */
    void add(IReelElement<T> element);
}
