package com.austin.dataStructures.HashTable;

import java.util.LinkedList;

/**
 * @ClassName: hash02
 * @author: zqz
 * @date: 2023/8/8 16:52
 */

public class hash02<K, V> implements Map<K, V> {

    /**
     * 用链表做数据接口解决冲突
     */
    private final LinkedList<Node<K, V>>[] tab = new LinkedList[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new LinkedList<>();
            tab[idx].add(new Node<>(key, value));
        } else {
            tab[idx].add(new Node<>(key, value));
        }

    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        for (Node<K, V> kvNode : tab[idx]) {
            if (kvNode.key.equals(key)){
                return kvNode.value;
            }
        }
        return null;
    }

    /**
     * 定义节点
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }
}
