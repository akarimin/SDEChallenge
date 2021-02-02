package ca.paytm.sde;

/**
 * <p>An Immutable Collection of Numeric objects providing the Moving Average (MA) of last N elements added
 * by {@link #getMovingAverageOf(int N)}, adds an element to the end of this collection by {@link #add(T)},
 * and gets access to the element index i {@link #get(int i)}.
 * </p>
 * <p>
 * The Efficient implementation of this data structure is {@link MACollection}
 * </p>
 *
 * @param <T> the type of elements in this collection which must be a {@link Number}
 * @author Ali K. Nouri     email: me@akarimin.com
 * @see MACollection
 * @since 1.0.0
 */
public interface PaytmCollection<T extends Number> {

    /**
     * Returns the number of elements in this Collection.
     * If this collection is empty, returns zero.
     * <p>
     * Maximum number that it can return is {@link Integer#MAX_VALUE}
     * </p>
     */
    int size();

    /**
     * Represents whether or not this collection is empty.
     * <p>
     * If this collection is empty, returns true.
     * </p>
     */
    boolean isEmpty();

    /**
     * Returns the Moving Average (MA) of last N elements of this collection.
     * i.e. first it looks up last N elements of this collection,
     * then sums all up, then divides by N, in order to calculate the average of
     * last m elements.
     * <p>
     * This method is called Moving Average due to the fact that if a new element
     * is added into this collection or removed from the tail of this collection,
     * the output of this method might be different based on the added/removed elements,
     * since it re-calculates the average of NEW last N elements.
     * </p>
     * <p>
     * e.g. N = 4
     * last m elements = 5,4,5,6
     * this.getMovingAverageOf(4) = (5 + 4 + 5 + 6) / 4 = 20 / 4 = 5.0
     * this.add(8);
     * NEW last N elements = 4,5,6,8
     * this.getMovingAverageOf(4) = (4 + 5 + 6 + 8) / 4 = 23 / 4 = 5.75
     * </p>
     **/
    double getMovingAverageOf(int n);

    /**
     * Appends the element t to the end of this collection.
     */
    void add(T t);

    /**
     * Appends multiple elements into the head of this collection.
     */
    void addAll(T[] elements);

    /**
     * Gives access to the specified index element of this collection.
     * <p>
     * Index is an integer greater or equal than 0, and less than or
     * equal to {@link Integer#MAX_VALUE}.
     * </p>
     * @throws NullPointerException In case of a null index is popped.
     */
    T get(int index);

    /**
     * Removes all the elements exist in this collection.
     */
    void clear();

}
