package com.dy.redispractice.util;

import com.dy.redispractice.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author dongyang
 * @date 2022/10/11 19:51
 */
public class SystemUtil {

    public static String getCurrentUserCode(){
        return  LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + "";
    }
}
