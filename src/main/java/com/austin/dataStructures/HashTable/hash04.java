package com.austin.dataStructures.HashTable;

/**
 * @ClassName: hash04
 * @author: zqz
 * @date: 2023/8/12 16:59
 */

/**
 * 其实这种叫做合并散列是开放寻址和单独链接的混合，
 * 他实际上理解起来就是 当前的节点idx如果被碰撞了的话 就直接在哈希表里面去找一个空闲的槽值
 * @param <K>
 * @param <V>
 */
public class hash04<K, V> implements Map<K, V> {
    private final Node<K, V>[] tab = new Node[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new Node<>(key, value);
            return;
        }

        int cursor = tab.length - 1;
        while (tab[cursor] != null && tab[cursor].key != key) {
            --cursor;
        }

        tab[cursor] = new Node<>(key, value);

        //将碰撞的点指向列表中
        while (tab[idx].idxOfNext != 0) {
            idx = tab[idx].idxOfNext;
        }

        tab[idx].idxOfNext = cursor;


    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        while (tab[idx].key != key){
            idx = tab[idx].idxOfNext;
        }
        return tab[idx].value;
    }

    static class Node<K, V> {
        final K key;
        V value;
        int idxOfNext;

        public int getIdxOfNext() {
            return idxOfNext;
        }

        public void setIdxOfNext(int idxOfNext) {
            this.idxOfNext = idxOfNext;
        }

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

        public void setValue(V value) {
            this.value = value;
        }
    }

}
