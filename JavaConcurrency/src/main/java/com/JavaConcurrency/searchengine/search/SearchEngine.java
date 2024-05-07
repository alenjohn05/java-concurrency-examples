package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.search;

import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys.Query;

public class SearchEngine {
    public static SearchResult search(Query query) {
        // Simulate search process
        try {
            Thread.sleep(1000); // Simulate search time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResult(query.getSearchTerm(), "Results for query: " + query.getSearchTerm());
    }
}