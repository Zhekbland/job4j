package ru.job4j.compare;

import java.util.*;

/**
 * Class SortUser to sort.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 24.10.2018.
 */
public class SortUser {
    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>();
        users.addAll(list);
        return users;
    }

    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int result = Integer.compare(o1.getName().length(), o2.getName().length());
                return result != 0 ? result : 0;
            }
        });
        return list;
    }

    public List<User> sortNameLengthAdvanced(List<User> list) {
        list.sort((o1, o2) -> Integer.compare(o1.getName().length(), o2.getName().length()));
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }

    public List<User> sortByAllFieldsAdvanced(List<User> list) {
        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()) != 0 ? o1.getName().compareTo(o2.getName())
                : Integer.compare(o1.getAge(), o2.getAge()));
        return list;
    }
}
