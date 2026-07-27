package homework_mock.algos.repeat;

/*
Список Integer удалить дубликаты и вернуть список без дубликатов.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mock {

    public static <T> List<T> distinct(List<T> list) {

        return list.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {

        System.out.println(distinct(new ArrayList<>(List.of(1, 2, 3, 3, 4, 4))));
        System.out.println(distinct(new ArrayList<>(List.of("Привет", "Привет", "ок", "собес"))));
    }

}
