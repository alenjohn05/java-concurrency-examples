package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> sourceList = Arrays.asList(0, 1, 2, 3, 4, 5);
        Integer[] targetArray = sourceList.toArray(new Integer[0]);
    }
}
