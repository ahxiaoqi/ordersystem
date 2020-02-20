package com.orderSystem.util;

import org.springframework.util.DigestUtils;

/**
 * @author ahxiaoqi
 * @date 2019/5/15 15:34
 */
public class PasswordUtils {

    private static String salt = "ah18831*#_$";

    /**
     * 对密码进行加盐 md5
     * @param password  原始密码
     * @return  加密的结果
     */
    public static String saltMd5(String password){

        // 把不可变字符串,转化位可变字符串
        StringBuffer stringBuffer = new StringBuffer(password);

        // 向索引为 3 的位置插入数据
        stringBuffer.insert(3,salt);

        // 向索引为 12 的位置插入数据
        stringBuffer.insert(12,salt);

        // 把可变字符串变为不可变字符串
        String saltPassword = stringBuffer.toString();

        // 使用spring 内置的 md5 算法对 saltPassword 进行加密
        String md5Password = DigestUtils.md5DigestAsHex(saltPassword.getBytes());

        // 返回加密结果
        return md5Password;

    }
}
