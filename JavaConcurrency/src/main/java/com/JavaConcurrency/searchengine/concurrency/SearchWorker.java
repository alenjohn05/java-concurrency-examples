package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.concurrency;

import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys.Query;
import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.search.SearchEngine;
import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.search.SearchResult;

public class SearchWorker implements Runnable {
    private Query query;

    public SearchWorker(Query query) {
        this.query = query;
    }

    @Override
    public void run() {
        SearchResult result = SearchEngine.search(query);
        System.out.println(result.getResult());
    }
}
