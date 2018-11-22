package ru.job4j.sort;

import java.util.*;

/**
 * Class Sort is sorting and recovering departments.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 22.11.2018.
 */
public class Sort {
    public String[] directSort(String[] departments) {
        Set<String> sortSet = new TreeSet<>(Comparator.naturalOrder());
        sortSet.addAll(Arrays.asList(recoveryDepartments(departments)));
        return sortSet.toArray(departments);
    }

    public String[] reverseSort(String[] departments) {
        Set<String> sortSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                int minLength = Math.min(o1.length(), o2.length());
                for (int index = 0; index < minLength; index++) {
                    Character left = o1.charAt(index);
                    Character right = o2.charAt(index);
                    if (left.compareTo(right) != 0) {
                        result = right.compareTo(left);
                        break;
                    }
                }
                return result != 0 ? result : Integer.compare(o1.length(), o2.length());
            }
        });
        sortSet.addAll(Arrays.asList(recoveryDepartments(departments)));
        return sortSet.toArray(departments);
    }

    public String[] recoveryDepartments(String[] departments) {
        Set<String> departmentSet = new TreeSet<>();
        departmentSet.addAll(Arrays.asList(departments));
        for (String department : departments) {
            List<Integer> separatorPos = new ArrayList<>();
            for (int index = 0; index < department.length(); index++) {
                if (department.charAt(index) == '\\') {
                    separatorPos.add(index);
                }
            }
            for (Integer pos : separatorPos) {
                char[] departmentChar = department.toCharArray();
                String recover = new String(departmentChar, 0, pos);
                departmentSet.add(recover);
            }
        }
        return departmentSet.toArray(departments);
    }

}
