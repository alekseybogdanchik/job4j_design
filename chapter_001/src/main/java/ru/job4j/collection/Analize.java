package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> prvMap = new HashMap<>(previous.size());

        for (User prvUser : previous) {
            prvMap.put(prvUser.id, prvUser.name);
        }

        for (User curUser : current) {
            int curId = curUser.id;
            String curName = curUser.name;
            if (prvMap.containsKey(curId)) {
                String tmpName = prvMap.get(curId);
                if (!tmpName.equals(curName)) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }

        info.deleted = previous.size() + info.added - current.size();
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
            return Objects.equals(id, user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;
    }
}
