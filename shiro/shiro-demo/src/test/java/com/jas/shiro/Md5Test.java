package com.jas.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author Jas
 * @create 2018-06-26 15:36
 **/
public class Md5Test {
    
    @Test
    public void getMd5Value() {
        Md5Hash md5Hash = new Md5Hash("123456", "jas");
        System.out.println(md5Hash.toString());
    }
}
