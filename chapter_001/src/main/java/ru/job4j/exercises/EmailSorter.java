package ru.job4j.exercises;

import java.util.*;


public class EmailSorter {
    private List<User> rsl = new ArrayList<>();

    public List<User> sorter(List<User> users) {
        Map<String, User> sortMap = new HashMap<>();

        for (User user : users) {
            for (String email : user.getEmailList()) {
                if (sortMap.containsKey(email)) {
                    String tmpName = sortMap.get(email).getName();
                    Set<String> tmpMailList = new HashSet<>(sortMap.get(email).getEmailList());
                    user.setName(tmpName);
                    tmpMailList.addAll(user.getEmailList());
                    User tmp = new User(tmpName, tmpMailList);
                    sortMap.put(email, tmp);
                } else {
                    sortMap.put(email, user);
                }
            }
        }
        Set<User> set = new HashSet<>(sortMap.values());
        rsl.addAll(set);
        return rsl;
    }
}
