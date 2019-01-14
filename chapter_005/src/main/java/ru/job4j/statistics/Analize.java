package ru.job4j.statistics;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Class Analize is checking differences between previous and current.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 13.01.2019.
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info(0, 0, 0);
        Map<Integer, String> prev = convert(previous);
        Map<Integer, String> cur = convert(current);
        Map<Integer, String> allMap = new HashMap<>(prev);
        allMap.putAll(cur);
        for (Map.Entry<Integer, String> map : allMap.entrySet()) {
            if (!cur.containsKey(map.getKey())) {
                result.setDeleted(result.getDeleted() + 1);
            } else if (prev.containsKey(map.getKey())
                    && !prev.get(map.getKey()).equals(map.getValue())) {
                result.setChange(result.getChange() + 1);
            } else if (!prev.containsKey(map.getKey())) {
                result.setAdded(result.getAdded() + 1);
            }
        }
        return result;
    }

    public Map<Integer, String> convert(List<User> previous) {
        Map<Integer, String> result = new HashMap<>();
        for (User user : previous) {
            result.put(user.id, user.name);
        }
        return result;
    }

    public static class Info {
        private int added;
        private int change;
        private int deleted;

        Info(int added, int change, int deleted) {
            this.added = added;
            this.change = change;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChange() {
            return change;
        }

        public void setChange(int change) {
            this.change = change;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && change == info.change
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, change, deleted);
        }
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
