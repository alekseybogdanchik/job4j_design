package ru.job4j.collection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        for (User prvUser : previous) {
            int prvId = prvUser.id;
            Stream<User> curUserStream = current.stream();
            if (!curUserStream.anyMatch(user -> user.equals(prvUser))) {
                curUserStream = current.stream();
                if (curUserStream.anyMatch(user -> user.id == prvId)) {
                    info.changed++;
                } else {
                    info.deleted++;
                }
            }
        }

        for (User curUser : current) {
            int curId = curUser.id;
            Stream<User> prvUserStream = previous.stream();
            if (!prvUserStream.anyMatch(user -> user.id == curId)) {
                info.added++;
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;

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
            return Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;
    }
}
