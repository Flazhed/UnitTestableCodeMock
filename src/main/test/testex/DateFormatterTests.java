package testex;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testex.DateFormatter;
import testex.IDateFormatter;
import testex.JokeException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by hansen on 3/12/17.
 */
@RunWith(Parameterized.class)
public class DateFormatterTests {

    private Date date;
    private String timeZone;
    private String expectedString;
    private IDateFormatter dateFormatter;

    public DateFormatterTests(Date date, String timeZone, String expectedString){
        this.date = date;
        this.timeZone = timeZone;
        this.expectedString = expectedString;
        this.dateFormatter = new DateFormatter();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {new Date(1489352280000L), "CET", "12 Mar 2017 09:58 PM"}

        });
    }

    //This class should also hold a test for testing the exception, but this is java :)
    @Test
    public void dateTimeParamsTest() throws JokeException {
        String result = dateFormatter.getFormattedDate(this.timeZone, this.date);
        assertThat(result, is(expectedString));
    }
}
