package com.jaewon.myproject2.common;

public class RoleConverter {

    public static int convert(String role) {
        if (role.equals("admin")) {
            return 0;
        } else {
            return 1;
        }
    }

}
