package homework_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugTask10 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        for (int i = names.size() - 1; i >= 0; i--) {
            String name = names.get(i);
            if (name.startsWith("A")) {
                names.remove(i);
            }
        }
        System.out.println(names);
    }
}
