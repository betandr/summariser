package uk.co.bbc.mediaservices.summariser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * TestMutableInt tests the functionality of the MutableInt class.
 */
public class TestMutableInt {

    @Test
    public void testNewMutableIntValueIsZero() {
        MutableInt mi = new MutableInt();
        assertEquals(0, mi.get());
    }

    @Test
    public void testAddingYieldsCorrectValue() {
        MutableInt mi = new MutableInt();
        assertEquals(0, mi.get());
        mi.add(2);
        mi.add(2);
        assertEquals(4, mi.get());
    }
}
