package com.austin.dataStructures.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ArrayDeque
 * @author: zqz
 * @date: 2023/8/27 16:38
 */

public class ArrayDeque<E> implements Deque<E> {

    private final Logger logger = LoggerFactory.getLogger(ArrayDeque.class);

    /**
     * 存储双端队列元素的数组。双端队列的容量就是这个数组的长度，它总是 2 的幂。
     */
    transient Object[] elements;

    /**
     * 双端队列头部元素的索引（将被 remove() 或 pop() 删除的元素）；如果双端队列为空，则为等于 tail 的任意数字。
     */
    transient int head;

    /**
     * 将下一个元素添加到双端队列尾部的索引（通过 addLast(E)、add(E) 或 push(E)）
     */
    transient int tail;

    @Override
    public void push(E e) {

        if (e == null) throw new NullPointerException();

        //push的过程就是为了找到初始化数组长度的队尾。同时扩容后是从新的队尾开始添加元素的，所以输出的时候还是从新扩容的那个位置开始输出元素的
        //计算索引找到数组的队尾
        elements[head = (head - 1) & (elements.length - 1)] = e;

        logger.info("push.idx head：{}", head);

        //扩容
        if (head == tail)
            doubleCapacity();
    }

    @Override
    public E pop() {
        int h = head;

        E result = (E) elements[h];
        if (result == null){
            return null;
        }
        elements[h] = null;
        head = (head -1) & (elements.length -1);
        logger.info("pop.idx {} = {} & {}", head, Integer.toBinaryString(h + 1), Integer.toBinaryString(elements.length - 1));
        return result;
    }

    /**
     * 双端队列的容量翻倍。仅在满时调用，即当头部和尾部缠绕成相等时。
     */
    private void doubleCapacity() {

        //assert断言 如果不存在的话那么程序就会中止
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;

        //原数组长度乘于2
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Sorry, deque too big");
        }
        Object[] a = new Object[newCapacity];

        //第一次拷贝数据 [2、1、4、3] 将数组中的扩容后一半元素拷贝到新数组0开始往后的位置。拷贝4、3
        //arraycopy的参解析，第一个原数组，第二个原数组起始位置，第三个新数组，第四个复制数组的长度
        System.arraycopy(elements, p, a, 0, r);

        //第二次拷贝数据
        System.arraycopy(elements, 0, a, r, p);

        elements = a;
        head = 0;
        tail = n;

        /**
         * 每次扩容都是2次分段迁移 以此保证2的幂等性
         */
    }


}
