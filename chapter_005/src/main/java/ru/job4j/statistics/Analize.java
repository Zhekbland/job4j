package ru.job4j.statistics;

import java.util.*;

/**
 * Class Analize is checking differences between previous and current.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 13.01.2019.
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info(0, 0, 0);
        ListIterator<User> itrPre = previous.listIterator();
        ListIterator<User> itrCur = current.listIterator();
        while (itrCur.hasNext()) {
            User tempCur = itrCur.next();
            while (itrPre.hasNext()) {
                User tempPre = itrPre.next();
                if (tempCur.equals(tempPre)) {
                    itrCur.remove();
                    itrPre.remove();
                    while (itrPre.hasPrevious()) {
                        itrPre.previous();
                    }
                    break;
                } else if (tempCur.id == tempPre.id) {
                    result.setChange(result.getChange() + 1);
                    itrCur.remove();
                    itrPre.remove();
                    while (itrPre.hasPrevious()) {
                        itrPre.previous();
                    }
                    break;
                }
            }
        }
        result.setAdded(current.size());
        result.setDeleted(previous.size());
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, name);
        }
    }
}
