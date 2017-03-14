package testex;

import java.util.Date;

/**
 * Created by hansen on 3/13/17.
 */
public interface IDateFormatter {
    String getFormattedDate(String timeZone, Date date) throws JokeException;
}
