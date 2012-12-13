package com.hg.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.hg.dto.OpenId;

public class GaeUtil {

    private GaeUtil() {}

    public static User getCurrentUser() {
        final UserService userService = UserServiceFactory.getUserService();
        final User user = userService.getCurrentUser();
        return user;
    }

    public static String getLogoutURL(final String origin) {
        final UserService userService = UserServiceFactory.getUserService();
        return userService.createLogoutURL(origin);
    }

    public static String getLoginURL(final String origin) {
        final UserService userService = UserServiceFactory.getUserService();
        return userService.createLoginURL(origin);
    }

    public static Map<String, String> getLoginURL(final HttpServletRequest req) {
        final UserService userService = UserServiceFactory.getUserService();
        final Map<String, String> map = new LinkedHashMap<String, String>();
        for (final OpenId o : OpenId.OPENID_PROVIDERS) {
            final String loginUrl = userService.createLoginURL(req.getRequestURI(), o.getAuthDomain(),
                                                               o.getFederatedIdentity(), o.getAttributesRequest());
            map.put(o.getLogo(), loginUrl);
        }
        return map;
    }
}
