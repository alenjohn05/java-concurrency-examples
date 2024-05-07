package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.concurrency;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys.Query;

public class SearchExecutor {
    private static final int THREAD_POOL_SIZE = 5;

    public void executeQueries(List<Query> queries) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (Query query : queries) {
            executor.submit(new SearchWorker(query));
        }
        executor.shutdown();
    }
}
