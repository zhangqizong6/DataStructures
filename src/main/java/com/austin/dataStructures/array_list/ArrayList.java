package com.austin.dataStructures.array_list;

import java.util.Arrays;

/**
 * @ClassName: ArrayList
 * @author: zqz
 * @date: 2023/8/13 22:22
 */

public class ArrayList<E> implements List<E> {
    /**
     * 默认初始化空间
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空元素
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * ArrayList 元素数组缓存区
     * <p>
     * java 的transient关键字为我们提供了便利，你只需要实现Serilizable接口，
     * 将不需要序列化的属性前添加关键字transient，序列化对象的时候，
     * 这个属性就不会序列化到指定的目的地中。
     */
    transient Object[] elementData;

    /**
     * List集合元素数量
     */
    private int size;


    private ArrayList() {
        //默认给个空元素数组，当开始添加元素的时候初始化长度
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 简化版添加元素
     */

    @Override
    public boolean add(E e) {
        //确保内部容量
        int minCapacity = size + 1;
        //判断是否为空元素
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        //接下来是判断 minCapacity 和元素的数量，是否达到了扩容
        //判断扩容的操作 若内部容量比元素长度大
        if (minCapacity - elementData.length > 0) {

            int oldCapacity = elementData.length;
            //新的容量长度等于旧的加上旧的一半
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 首次创建 ArrayList 是一定会扩容的，也就是初始化 DEFAULT_CAPACITY = 10 的容量。
            if (newCapacity - oldCapacity < 0) {
                newCapacity = minCapacity;
            }
            //Arrays.copyOf 实际上是创建一个新的空间数组，之后调用的 System.arraycopy 迁移到新创建的数组上。
            // 这样后续所有的扩容操作，也就都保持统一了。
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        //添加元素
        elementData[size++] = e;
        return true;
    }


    /**
     * 移除元素
     * ArrayList 的重点离不开对 System.arraycopy 的使用
     * 从原数组特定的位置迁移到新数组指定位置和数量
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            //这里的这段意思就是index位置的后面的换上来 然后将要移除的元素放在最后面 最后删除即可
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        //clear to let GC do its work
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }
}
