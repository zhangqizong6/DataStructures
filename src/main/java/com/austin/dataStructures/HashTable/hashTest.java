package com.austin.dataStructures.HashTable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: hashTest
 * @author: zqz
 * @date: 2023/8/8 16:31
 */

public class hashTest {
    private final Logger logger = LoggerFactory.getLogger(hashTest.class);

    /**
     * 此处模拟碰撞 可以看到第二次碰撞后是喵喵的 这就引进后续通过扩容数组长度解决把碰撞的元素通过链表存放
     */
    @Test
    public void testhash1(){
        Map<String, String> map = new hash01<>();
        map.put("01", "花花");
        map.put("02", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    }
    @Test
    public void test_hashMap02() {
        Map<String, String> map = new hash02<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        logger.info("碰撞后 key：{} value：{}", "09", map.get("09"));
    }


}
