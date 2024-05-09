package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDownloaderApllication {
    public static void main(String[] args) {
        // List of URLs to download files from
        List<String> urls = new ArrayList<>();
        urls.add("https://example.com/file1.txt");
        urls.add("https://example.com/file2.txt");
        urls.add("https://example.com/file3.txt");

        // Create an ExecutorService with a fixed-size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks for downloading files
        for (String url : urls) {
            executor.submit(new DownloadTask(url));
        }

        // Shutdown the ExecutorService after all tasks are completed
        executor.shutdown();
    }

    static class DownloadTask implements Runnable {
        private final String url;

        public DownloadTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                downloadFile(url);
                System.out.println("Downloaded: " + url);
            } catch (IOException e) {
                System.err.println("Failed to download: " + url);
                e.printStackTrace();
            }
        }

        private void downloadFile(String url) throws IOException {
            try (InputStream in = new URL(url).openStream();
                    OutputStream out = new FileOutputStream(getFileName(url))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }

        private String getFileName(String url) {
            String[] parts = url.split("/");
            return parts[parts.length - 1];
        }
    }
}
