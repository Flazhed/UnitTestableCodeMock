package testex.jokesfetching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hansen on 3/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class FetcherFactoryTests {


    private IFetcherFactory _fetcherFactory;
    @Mock Moma moma;
    @Mock ChuckNorris chuck;
    @Mock EduJoke edu;
    @Mock Tambal tambal;

    @Before
    public void setup(){
        _fetcherFactory = new FetcherFactory(edu, chuck, moma, tambal);
    }

    @Test
    public void factoryInjectionTest(){
        List<IJokeFetcher> fetchers = _fetcherFactory.getJokeFetchers("eduprog,chucknorris,moma,tambal");
        assertThat(fetchers, is(notNullValue()));
        assertThat(fetchers.size(), is(4));
        assertThat(fetchers.get(0), is(instanceOf(EduJoke.class)));
        assertThat(fetchers.get(1), is(instanceOf(ChuckNorris.class)));
        assertThat(fetchers.get(2), is(instanceOf(Moma.class)));
        assertThat(fetchers.get(3), is(instanceOf(Tambal.class)));
    }

}
