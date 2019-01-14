package ru.job4j.statistics;

import java.util.*;

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
        Map<Integer, String> preMap = convert(previous);
        for (User user : current) {
            String name = preMap.remove(user.id);
            if (name == null) {
                result.setAdded(result.getAdded() + 1);
            } else if (!name.equals(user.name)) {
                result.setChange(result.getChange() + 1);
            }
        }
        result.setDeleted(preMap.size());
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
