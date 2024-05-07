package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine;

import java.util.List;

import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.concurrency.SearchExecutor;
import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys.Query;
import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys.QueryManager;

public class Main {
    public static void main(String[] args) {
        List<Query> queries = QueryManager.generateQueries();
        SearchExecutor searchExecutor = new SearchExecutor();
        searchExecutor.executeQueries(queries);
    }
}
