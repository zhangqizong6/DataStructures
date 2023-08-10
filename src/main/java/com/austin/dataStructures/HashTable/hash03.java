package com.austin.dataStructures.HashTable;

/**
 * @ClassName: hash03
 * @author: zqz
 * @date: 2023/8/10 15:17
 */

import java.util.HashMap;

/**
 * 开放寻址
 * 开放寻址的设计会对碰撞的元素，寻找哈希桶上新的位置，这个位置从当前碰撞位置开始向后寻找，直到找到空的位置存放。
 * 在 ThreadLocal 的实现中会使用斐波那契散列、索引计算累加、启发式清理、探测式清理等操作，以保证尽可能少的碰撞。
 */
public class hash03<K, V> implements Map<K, V> {

    private final Node<K, V>[] tab = new Node[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new Node<>(key, value);
        } else {
            for (int i = idx; i < tab.length; i++) {
                if (tab[i] == null) {
                    tab[i] = new Node<>(key, value);
                    break;
                }
            }
        }

    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        for (int i = idx; i < tab.length; i++) {
            if (tab[idx] != null && tab[idx].key == key) {
                return tab[idx].value;
            }
        }
        return null;
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
