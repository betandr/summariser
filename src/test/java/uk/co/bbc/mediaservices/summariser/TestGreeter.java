package uk.co.bbc.mediaservices.summariser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Tests different numbers of arguments to main().
 *
 * <p>With an empty args array, {@link Greeter} should print "Hello world". If there are one or more
 * args, {@link Greeter} should print "Hello &lt;arg[0]&gt;".</p>
 */
public class TestGreeter {

    @Test
    public void testGreet() throws Exception {
        String response = Greeter.greet();
        assertEquals("Hello, World!", response);
    }

}
