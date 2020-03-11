package com.demo;

import java.util.Arrays;

/*
/ 1. FIFO
// 2. Ring buffer, use all space in buffer



        How ring buffer works:
        e.g.
        new buffer, size = 3,
        - empty buffer _,_,_
        - get -> no data, buffer is empty (throw exception or block (step 3))
        - put 6 ->     6,_,_
        - put 7 ->     6,7,_
        - put 8 ->     6,7,8
        - put 9 -> buffer is full (throw exception or block (step 3))
        - get -> 6     _,7,8
        - put 9 ->    9,7,8
        - get -> 7     9,_,8



// 3. blocking when no free place (on put) or data (on get)
// 4. Thread safe
public class ThreadSafeBuffer{
    private final int MAX_BUF_SIZE;



    private final Object[] data ;



    public void put(Object o){



    }



    public Object get(){



    }
}*/
public class ThreadSafeBuffer {
    private final int MAX_BUF_SIZE;
    private final Object[] data;
    private int size;
    private int readIndex;

    public ThreadSafeBuffer(int MAX_BUF_SIZE) {
        this.MAX_BUF_SIZE = MAX_BUF_SIZE;
        this.data = new Object[MAX_BUF_SIZE];
    }


    public void put(Object o) {
        if (size == MAX_BUF_SIZE) {
            throw new IllegalStateException("Buffer is full");
        }
        data[size] = o;
        size++;
    }


    // _,2,3,4
    public Object get() {
        if (size == 0) {
            throw new IllegalStateException("Empty buffer");
        }

        Object dataToReturn = data[readIndex];
        size--;
        readIndex++;

        return dataToReturn;
    }
}