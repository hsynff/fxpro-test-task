package com.demo;

import org.junit.Test;

import static org.junit.Assert.*;


public class ThreadSafeBufferTest {

    @Test(expected = IllegalStateException.class)
    public void put_whenFullBuffer_shouldThrowException() throws Exception {
        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);
        threadSafeBuffer.put(new String());
        threadSafeBuffer.put(new String());
        threadSafeBuffer.put(new String());
        threadSafeBuffer.put(new String());
    }

    @Test
    public void get_whenPutOneElement_shouldReturnSameElement() {
        String element = new String("1");

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(1);
        threadSafeBuffer.put(element);

        assertEquals(element, threadSafeBuffer.get());
    }

    @Test
    public void get_whenPutTwoElements_shouldReturnTwoElements(){
        String element1 = new String("1");
        String element2 = new String("2");

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(2);
        threadSafeBuffer.put(element1);
        threadSafeBuffer.put(element2);

        assertEquals(element1, threadSafeBuffer.get());
        assertEquals(element2, threadSafeBuffer.get());
    }

    @Test(expected = IllegalStateException.class)
    public void get_whenEmptyBuffer_shouldThrowException() throws Exception {
        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);
        threadSafeBuffer.get();
    }

    @Test
    public void get_whenGetsAreMoreThanPuts_shoulThrowException(){
        String element1 = new String("1");
        String element2 = new String("2");
        String element3 = new String("3");
        String element4 = new String("4");

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);

        threadSafeBuffer.put(element1);
        threadSafeBuffer.put(element2);
        threadSafeBuffer.put(element3);

        assertEquals(element1, threadSafeBuffer.get());

        threadSafeBuffer.put(element4);
        assertEquals(element2, threadSafeBuffer.get());
        assertEquals(element3, threadSafeBuffer.get());
        assertEquals(element4, threadSafeBuffer.get());

    }

}