package com.austin.dataStructures.HashTable;

/**
 * @ClassName: hash01
 * @author: zqz
 * @date: 2023/8/8 16:20
 */


/**
 * 通过模拟简单 HashMap 实现，去掉拉链寻址等设计，验证元素哈新索引位置碰撞。
 */
public class hash01<k, v> implements Map<k, v> {

    private final Object[] tab = new Object[8];

    @Override
    public void put(k key, v value) {
        int idx = key.hashCode() & (tab.length - 1);
        tab[idx] = value;
    }

    @Override
    public v get(k key) {
        int idx = key.hashCode() & (tab.length - 1);
        return (v) tab[idx];
    }
}
