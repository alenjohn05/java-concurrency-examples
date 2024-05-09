package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class WebCrawler {
    private static final Logger LOGGER = Logger.getLogger(WebCrawler.class.getName());
    private final Set<String> vistedUrls = ConcurrentHashMap.newKeySet();

    private void crawl(List<String> urls) {

    }

    public static void main(String[] args) {
        List<String> urls = Arrays.asList("https://example.com/page1", "https://example.com/page2",
                "https://example.com/page3");
        WebCrawler crawler = new WebCrawler();

    }

}
