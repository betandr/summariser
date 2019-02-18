package uk.co.bbc.mediaservices.summariser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.bbc.mediaservices.summariser.domain.TestViewing;
import uk.co.bbc.mediaservices.summariser.domain.TestSummary;
import uk.co.bbc.mediaservices.summariser.domain.TestMutableInt;
import uk.co.bbc.mediaservices.summariser.domain.TestDuration;

@RunWith(Suite.class)
@SuiteClasses({
    TestSummariserImpl.class,
    TestCommandLineHandler.class,
    TestViewing.class,
    TestSummary.class,
    TestMutableInt.class,
    TestDuration.class
})
public class AllTests {}
