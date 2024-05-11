package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JSONUpdater {
    private static final String JSON_FILE_PATH = "data.json";
    private static final String TEXT_FILE_PREFIX = "response";
    private static final String TEXT_FILE_EXTENSION = ".txt";
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    private static final Lock fileLock = new ReentrantLock();
    private static final Logger logger = Logger.getLogger(JSONUpdater.class.getName());

    public static void main(String[] args) {
        updateJsonFile();
        createTextFiles();
    }

    public static void updateJsonFile() {
        fileLock.lock();
        try {
            File jsonFile = new File(JSON_FILE_PATH);
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                // Call the API and store the response in the JSON file
                callAPIAndWriteToFile(jsonFile);
            } else {
                // Update the JSON file with the API response
                updateFileWithAPIData(jsonFile);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating JSON file", e);
        } finally {
            fileLock.unlock();
        }
    }

    private static void callAPIAndWriteToFile(File file) throws IOException {
        // Make API call
        String responseData = callAPIAndGetResponse(API_URL);
        // Write response to file
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(responseData);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing JSON data to file", e);
            throw e;
        }
        logger.log(Level.INFO, "JSON data written to file: " + file.getAbsolutePath());
    }

    private static void updateFileWithAPIData(File file) throws IOException {
        // Make API call
        String responseData = callAPIAndGetResponse(API_URL);
        // Read existing data from file
        StringBuilder existingData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                existingData.append(line);
            }
        }
        // Append new data to existing data
        existingData.append(responseData);
        // Update file with combined data
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(existingData.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating JSON file with API data", e);
            throw e;
        }
        logger.log(Level.INFO, "JSON file updated with API data: " + file.getAbsolutePath());
    }

    private static String callAPIAndGetResponse(String apiUrl) throws IOException {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // Make API call
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
        }
        return response.toString();
    }

    private static void createTextFiles() {
        fileLock.lock();
        try {
            File jsonFile = new File(JSON_FILE_PATH);
            if (jsonFile.exists()) {
                String jsonData = readJsonFile(jsonFile);
                for (int i = 0; i < 10; i++) {
                    File textFile = new File(TEXT_FILE_PREFIX + i + TEXT_FILE_EXTENSION);
                    writeJsonDataToTextFile(textFile, jsonData);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating text files", e);
        } finally {
            fileLock.unlock();
        }
    }

    private static String readJsonFile(File file) throws IOException {
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        }
        return jsonData.toString();
    }

    private static void writeJsonDataToTextFile(File file, String jsonData) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonData);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing JSON data to text file", e);
            throw e;
        }
        logger.log(Level.INFO, "JSON data written to text file: " + file.getAbsolutePath());
    }
}
