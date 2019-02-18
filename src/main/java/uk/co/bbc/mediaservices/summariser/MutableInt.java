package uk.co.bbc.mediaservices.summariser;


/**
 * Represents a mutable int type which can be used to efficiently sum using a map.
 */
public class MutableInt {
    private int value;

    public MutableInt() {
        this.value = 0;
    }

    public MutableInt(int value) {
        this.value = value;
    }

    /**
     * Adds a value to the MutableInt. If get() yields 2, calling add(2) will
     * then yield 4.
     * @param value The value to add
     */
    public void add(int value) {
        this.value += value;
    }

    /**
     * Returns the value of a MutableInt
     * @return int The current value of the MutableInt.
     */
    public int get() {
        return value;
    }

    public String toString() {
        return "" + value;
    }
}
