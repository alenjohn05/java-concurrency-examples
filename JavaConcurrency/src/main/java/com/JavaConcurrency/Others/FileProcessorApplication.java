package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileProcessorApplication {
    // Directory containing the files to be processed
    private static final String DIRECTORY_PATH = "files";

    // Number of threads for concurrent processing
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        // Create an ExecutorService with a fixed-size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // List to hold results from each file
        List<Integer> results = new ArrayList<>();

        // Get list of files in the directory
        File directory = new File(DIRECTORY_PATH);
        File[] files = directory.listFiles();

        // Submit tasks for processing each file
        if (files != null) {
            for (File file : files) {
                executor.submit(new FileProcessorTask(file, results));
            }
        }

        // Shutdown the ExecutorService when all tasks are completed
        executor.shutdown();

        try {
            // Wait for all tasks to complete or until timeout occurs
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Aggregate results
        int totalLines = 0;
        for (int lines : results) {
            totalLines += lines;
        }

        System.out.println("Total lines processed: " + totalLines);
    }

    static class FileProcessorTask implements Runnable {
        private final File file;
        private final List<Integer> results;

        public FileProcessorTask(File file, List<Integer> results) {
            this.file = file;
            this.results = results;
        }

        @Override
        public void run() {
            int linesProcessed = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Process each line (e.g., count lines)
                    linesProcessed++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add the number of lines processed to the results list
            synchronized (results) {
                results.add(linesProcessed);
            }
            System.out.println("File " + file.getName() + " processed. Lines: " + linesProcessed);
        }
    }
}

// Sure! Let's create an example of an application that processes a batch of
// files concurrently
// using ExecutorService. In this example, we'll create a simple file processing
// application that reads files from a directory,
// processes each file (e.g.,counting lines), and then aggregates the results.