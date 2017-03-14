import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testex.DateFormatterTests;
import testex.JokeFetcherTests;
import testex.jokesfetching.FetcherFactoryTests;

/**
 * Created by hansen on 3/14/17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JokeFetcherTests.class,
        FetcherFactoryTests.class,
        DateFormatterTests.class
})
public class SuiteClass {
}
