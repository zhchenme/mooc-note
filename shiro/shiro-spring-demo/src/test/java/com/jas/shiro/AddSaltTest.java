package com.jas.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author Jas
 * @create 2018-06-27 10:52
 **/
public class AddSaltTest {
    
    @Test
    public void getAddSaltpassword() {
        // 密码使用 jas 盐处理
        Md5Hash md5Hash = new Md5Hash("123456", "zc");
        System.out.println(md5Hash.toString());
    }
}
