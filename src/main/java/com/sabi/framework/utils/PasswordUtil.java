package com.sabi.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class PasswordUtil {



    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

    private static final String USERNAME_PATTERN = "^[A-Za-z0-9_-]{8,15}$";

    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean passwordValidator(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public static boolean userNameValidator(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);

        matcher = pattern.matcher(username);
        return matcher.matches();
    }


}
