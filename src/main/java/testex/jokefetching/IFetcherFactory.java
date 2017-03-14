package testex.jokefetching;

import java.util.List;

/**
 * Created by hansen on 3/13/17.
 */
public interface IFetcherFactory {
    List<String> getAvailableTypes();
    List<IJokeFetcher> getJokeFetchers(String jokesToFetch);
}
