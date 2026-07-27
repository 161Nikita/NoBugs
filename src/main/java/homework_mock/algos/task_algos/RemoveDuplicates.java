package homework_mock.algos.task_algos;

import java.util.*;

/**
 * Удалить дубликаты из списка List<Integer>
 *
 *     List<Integer> list = new ArrayList(List.of(1, 2, 3) -> [1,2,3]
 *     List<Integer> list = new ArrayList(List.of(-1, 2, 2, 1, 0, -1) -> [-1, 2, 1, 0]
 *     List<Integer> list = new ArrayList(List.of(2, 2, 2) -> [2]
 *     List<Integer> list = new ArrayList(List.of() -> []
 *     List<Integer> list = new ArrayList(List.of(null) -> IllegalArgumentException
 *     List<Integer> list = new ArrayList(List.of(1, 2, 2, null) -> NullPointerException
 */

public class RemoveDuplicates {

    public static List<Integer> removeDuplicates(List<Integer> list) {

        if (list == null){
            throw new IllegalArgumentException("В качестве аргумента null не должен передаваться");
        }

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

       for (Integer x : list) {
           if (x == null) {
               throw new NullPointerException("Элемент null внутри списка недопустим");
           }
       }

        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3)); // [1,2,3]
        List<Integer> list2 = new ArrayList<>(List.of(-1, 2, 2, 1, 0, -1)); // [1,0]
        List<Integer> list3 = new ArrayList<>(List.of()); // []
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 3, 3, null)); //


        System.out.println(removeDuplicates(list1));
        System.out.println(removeDuplicates(list2));
        System.out.println(removeDuplicates(list3));

        try {
            removeDuplicates(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

        try {
            removeDuplicates(list4);
        } catch (NullPointerException e) {
            System.out.println("Перехватили исключение! " + e.getMessage());
        }

    }
}
