package com.hg.util;

import com.google.appengine.api.users.User;

public class RoleUtil {

    private static String[] master = { "atealxt@gmail.com", "iamlusuo@gmail.com" };

    private RoleUtil() {}

    public static boolean isMaster() {
        User user = GaeUtil.getCurrentUser();
        if (user == null) {
            return false;
        }
        for (String s : master) {
            if (s.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLogin() {
        User user = GaeUtil.getCurrentUser();
        if (user != null) {
            return true;
        }
        return false;
    }
}
