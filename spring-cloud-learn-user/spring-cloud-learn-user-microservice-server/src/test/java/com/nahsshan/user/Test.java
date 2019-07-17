package com.nahsshan.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by J.zhu on 2019/7/17.
 */
public class Test {

    @org.junit.Test
    public void test(){
        // $2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu
        // $2a$10$6qjP6d1R1L9F4d6Ef4456.TFPBlkepDjDackpHKDSaSeADpsSoJPy
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "hisandy";
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        System.out.println("rawPassword\t|encodedPassword");
        System.out.println(String.format("%s\t|%s",rawPassword,encodedPassword));
    }
}
