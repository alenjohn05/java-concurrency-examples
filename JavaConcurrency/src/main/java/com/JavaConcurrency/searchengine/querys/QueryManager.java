package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys;

import java.util.ArrayList;
import java.util.List;

public class QueryManager {
    public static List<Query> generateQueries() {
        List<Query> queries = new ArrayList<>();
        queries.add(new Query("Java concurrency"));
        queries.add(new Query("Multithreading in Java"));
        queries.add(new Query("Parallel processing"));
        queries.add(new Query("Thread synchronization"));
        queries.add(new Query("Concurrent programming"));
        return queries;
    }

}
