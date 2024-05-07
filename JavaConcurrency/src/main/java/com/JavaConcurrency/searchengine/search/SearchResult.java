package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.search;

public class SearchResult {
    private String query;
    private String result;

    public SearchResult(String query, String result) {
        this.query = query;
        this.result = result;
    }

    public String getQuery() {
        return query;
    }

    public String getResult() {
        return result;
    }
}