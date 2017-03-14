package testex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.*;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hansen on 3/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTests {
    private JokeFetcher jokeFetcher;
    @Mock IDateFormatter _dateFormatter;
    @Mock private IFetcherFactory _fetcherFactory;
    @Mock Moma moma;
    @Mock ChuckNorris chuck;
    @Mock EduJoke edu;
    @Mock Tambal tambal;

    @Before
    public void setup(){
        //Given:
        String typesToFetchString = "eduprog,chucknorris,moma,tambal";
        List<IJokeFetcher> fetcherMocks = Arrays.asList(edu, chuck, moma, tambal);
        List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

        //WHEN
        when(_fetcherFactory.getAvailableTypes()).thenReturn(availableTypes);
        when(_fetcherFactory.getJokeFetchers(eq(typesToFetchString))).thenReturn(fetcherMocks);

        //Kinda setup THEN
        jokeFetcher = new JokeFetcher(_dateFormatter, _fetcherFactory);
    }

    @Test
    public void getAvailableTypesTest(){

        List<String> result = jokeFetcher.getAvailableTypes();
        assertThat(result.size(), is(4));
        assertThat(result, hasItems("eduprog","chucknorris","moma","tambal"));

    }

    @Test
    public void isStringValidTest(){
        //Testing for both invalid and valid strings in the same test. Bad practice, but this is java
        // and params test are so much work...
        JokeFetcher jokeFetcher = new JokeFetcher(_dateFormatter, null);
        boolean result = jokeFetcher.isStringValid("eduprog");
        assertThat(result, is(true));
        result = jokeFetcher.isStringValid("clearlyWrongToken");
        assertThat(result, is(false));
    }

    @Test
    public void getJokesTest() throws JokeException {

        String mockDateString = "13 Mar 2017 09:58 PM";
        String timeZoneString = "Europe/Copenhagen";
        given(_dateFormatter.getFormattedDate(eq(timeZoneString), anyObject())).willReturn(mockDateString);
        Jokes jokes = jokeFetcher.getJokes("eduprog", timeZoneString);
        assertThat(jokes.getTimeZoneString(), is(mockDateString));
        verify(_dateFormatter, times(1)).getFormattedDate(eq(timeZoneString), anyObject());
    }

    @Test
    public void chuckNorrisTest() throws JokeException {
        //GIVEN
        String jokeString = "This is clearly a joke";
        String referenceString = "Some Reference";
        given(chuck.getJoke()).willReturn(new Joke(jokeString, referenceString));

        //WHEN
        //This hardcoded string is UGLY, but the when() in @Before uses this matcher and im lazy.
        Jokes jokesResult = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen");

        //THEN
        assertThat(jokesResult.getJokes().get(1), is(notNullValue()));
        assertThat(jokesResult.getJokes().get(1).getJoke(), is(jokeString));
        assertThat(jokesResult.getJokes().get(1).getReference(), is(referenceString));
    }

    @Test
    public void eduTest() throws JokeException {
        //GIVEN
        String jokeString = "This is clearly a joke BUT NOT THE SAME";
        String referenceString = "Some Reference NEW REFERENCE";
        given(edu.getJoke()).willReturn(new Joke(jokeString, referenceString));

        //WHEN
        //This hardcoded string is UGLY, but the when() in @Before uses this matcher and im lazy.
        Jokes jokesResult = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen");

        //THEN
        assertThat(jokesResult.getJokes().get(0), is(notNullValue()));
        assertThat(jokesResult.getJokes().get(0).getJoke(), is(jokeString));
        assertThat(jokesResult.getJokes().get(0).getReference(), is(referenceString));
    }

    @Test
    public void momaTest() throws JokeException {
        //GIVEN
        String jokeString = "This is clearly a joke BUT NOT THIS TIME YO MOMA";
        String referenceString = "Some Reference MOMA";
        given(moma.getJoke()).willReturn(new Joke(jokeString, referenceString));

        //WHEN
        //This hardcoded string is UGLY, but the when() in @Before uses this matcher and im lazy.
        Jokes jokesResult = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen");

        //THEN
        assertThat(jokesResult.getJokes().get(2), is(notNullValue()));
        assertThat(jokesResult.getJokes().get(2).getJoke(), is(jokeString));
        assertThat(jokesResult.getJokes().get(2).getReference(), is(referenceString));
    }

    @Test
    public void tambalTest() throws JokeException {
        //GIVEN
        String jokeString = "This is clearly a What is tambal even";
        String referenceString = "Some Reference NEW REFERENCE tambal";
        given(tambal.getJoke()).willReturn(new Joke(jokeString, referenceString));

        //WHEN
        //This hardcoded string is UGLY, but the when() in @Before uses this matcher and im lazy.
        Jokes jokesResult = jokeFetcher.getJokes("eduprog,chucknorris,moma,tambal", "Europe/Copenhagen");

        //THEN
        assertThat(jokesResult.getJokes().get(3), is(notNullValue()));
        assertThat(jokesResult.getJokes().get(3).getJoke(), is(jokeString));
        assertThat(jokesResult.getJokes().get(3).getReference(), is(referenceString));
    }

}
