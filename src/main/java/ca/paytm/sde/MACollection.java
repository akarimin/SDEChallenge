package ca.paytm.sde;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MACollection<T extends Number> implements PaytmCollection<T> {

    private T[] keys;
    private int size;

    @SuppressWarnings("unchecked")
    public MACollection() {
        this.keys = (T[]) new Number[1];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MACollection(int initialCapacity) {
        this.keys = (T[]) new Number[initialCapacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public double getMovingAverageOf(int n) {
        this.validateNumberOfElements(n);
        int index = size - 1;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += this.get(index - i).doubleValue();
        }
        return sum / n;
    }

    @Override
    public void add(T t) {
        this.validatePushingItemNullity(t);
        if (size == keys.length)
            this.resize(2 * keys.length);
        keys[size++] = t;
    }

    @Override
    public void addAll(T[] elements) {
        this.validatePushingItemsNullity(elements);
        if (size + elements.length > keys.length)
            this.resize(2 * (keys.length + elements.length));
        for (T t: elements)
            keys[size++] = t;
    }

    @Override
    public T get(int index) {
        this.validateAccessingItemNullity();
        this.validateIndexRange(index);
        T key = keys[index];
        if (Objects.nonNull(key))
            return key;
        throw new NullPointerException("Element at index: " + index + " is null!");
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            keys[i] = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Number[] resized = new Number[newCapacity];
        System.arraycopy(keys, 0, resized, 0, keys.length);
        keys = (T[]) resized;
    }

    private void validatePushingItemNullity(final T key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Pushing item cannot be null.");
    }

    private void validatePushingItemsNullity(final T[] keys) {
        for (T key: keys) {
            this.validatePushingItemNullity(key);
        }
    }

    private void validateAccessingItemNullity() {
        if (isEmpty())
            throw new NoSuchElementException("Accessing item cannot be null.");
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Accessing index is invalid. index: " + index);
    }

    private void validateNumberOfElements(int n) {
        if (n >= size)
            throw new IllegalStateException(String.format("Collection does not have enough elements. " +
                "Requested number of elements: %d, size of " + "collection: %d", n, size));
    }
}
