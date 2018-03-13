package com.feng;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    public static void main(String[] args) {
        String str = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encode = encoder.encode(str);
        System.out.println(encode);
        final boolean matches = encoder.matches(str, "$2a$10$iy2lUJldeDXs94Dlf3nAlufrp.5EdO0FPXAZN9kqYyVB5ws.iyJDq");
        System.out.println(matches);
    }
}
