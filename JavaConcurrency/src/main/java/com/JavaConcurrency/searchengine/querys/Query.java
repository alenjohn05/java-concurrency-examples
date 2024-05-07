package JavaConcurrency.src.main.java.com.JavaConcurrency.searchengine.querys;

public class Query {
    private String searchTerm;

    public Query(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}