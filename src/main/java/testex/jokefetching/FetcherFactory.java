package testex.jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hansen on 3/13/17.
 */
public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");
    private IJokeFetcher _eduJoke;
    private IJokeFetcher _chuckJoke;
    private IJokeFetcher _momaJoke;
    private IJokeFetcher _tambalJoke;


    public FetcherFactory(IJokeFetcher eduJoke, IJokeFetcher chuckJoke, IJokeFetcher momaJoke, IJokeFetcher tambalJoke){
        _eduJoke = eduJoke;
        _chuckJoke = chuckJoke;
        _momaJoke = momaJoke;
        _tambalJoke = tambalJoke;
    }

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> fetchers = new ArrayList<>();
        List<String> tokens = Arrays.asList(jokesToFetch.split(","));

        //Honestly this is ugly. But switch-cases are even uglier.
        if(tokens.contains("eduprog")){
            fetchers.add(_eduJoke);
        }
        if(tokens.contains("chucknorris")){
            fetchers.add(_chuckJoke);
        }
        if(tokens.contains("moma")){
            fetchers.add(_momaJoke);
        }
        if(tokens.contains("tambal")){
            fetchers.add(_tambalJoke);
        }
        return fetchers;
    }
}
