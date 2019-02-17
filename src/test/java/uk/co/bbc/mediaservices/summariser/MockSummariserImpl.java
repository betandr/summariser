package uk.co.bbc.mediaservices.summariser;


/**
 * Mock SummariserImpl exposing a probe to return the count of the number of
 * times process has been run.
 * Ideally this would be run using a org.mockito.Mockito.spy/verify such as:
 * SummariserImpl s = spy(new SummariserImpl());
 * verify(s, times(2)).process(any());
 * ...however some runtime issues prevented this:
 * `Could not initialize plugin: interface org.mockito.plugins.MockMaker` during
 * test runs. So instead of using Mockito a simple inheritance method was used.
 */
public class MockSummariserImpl extends SummariserImpl {

    private int count;

    protected void process(String line) {
        this.count++;
    }

    protected int getCount() {
        return this.count;
    }

}
