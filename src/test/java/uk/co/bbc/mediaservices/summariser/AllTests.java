package uk.co.bbc.mediaservices.summariser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestSummariserImpl.class,
    TestCommandLineHandler.class,
    TestViewing.class,
    TestSummary.class,
    TestMutableInt.class
})
public class AllTests {}
