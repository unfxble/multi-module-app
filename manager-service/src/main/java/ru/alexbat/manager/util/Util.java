package ru.alexbat.manager.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class Util {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("manager_pass"));
    }
}
