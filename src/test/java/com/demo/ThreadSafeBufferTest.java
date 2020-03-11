package com.demo;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;


public class ThreadSafeBufferTest {


// Should hang up infinitively instead throwing exception

//    @Test(expected = IllegalStateException.class)
//    public void put_whenFullBuffer_shouldThrowException() throws Exception {
//        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);
//        threadSafeBuffer.put("");
//        threadSafeBuffer.put("");
//        threadSafeBuffer.put("");
//        threadSafeBuffer.put("");
//    }

    @Test
    public void get_whenPutOneElement_shouldReturnSameElement() {
        String element = "1";

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(1);
        threadSafeBuffer.put(element);

        assertEquals(element, threadSafeBuffer.get());
    }

    @Test
    public void get_whenPutTwoElements_shouldReturnTwoElements() {
        String element1 = "1";
        String element2 = "2";

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(2);
        threadSafeBuffer.put(element1);
        threadSafeBuffer.put(element2);

        assertEquals(element1, threadSafeBuffer.get());
        assertEquals(element2, threadSafeBuffer.get());
    }

    // Should hang up infinitively instead throwing exception

//    @Test(expected = IllegalStateException.class)
//    public void get_whenEmptyBuffer_shouldThrowException() {
//        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);
//        threadSafeBuffer.put("1");
//        threadSafeBuffer.put("2");
//        threadSafeBuffer.get();
//        threadSafeBuffer.get();
//        threadSafeBuffer.get();
//    }

    @Test
    public void get() {

        String element1 = "1";
        String element2 = "2";
        String element3 = "3";
        String element4 = "4";

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

    @Test
    public void testConcurrent() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        ThreadSafeBuffer threadSafeBuffer = new ThreadSafeBuffer(3);
        service.submit(() -> threadSafeBuffer.put("1"));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));
        service.submit(() -> threadSafeBuffer.put("2"));
        service.submit(() -> threadSafeBuffer.put("3"));
        service.submit(() -> System.out.println(threadSafeBuffer.get()));

        Thread.sleep(2000);

        threadSafeBuffer.put("4");
        threadSafeBuffer.put("5");
        threadSafeBuffer.put("6");
        //
        threadSafeBuffer.put("7");
        threadSafeBuffer.put("8");
        threadSafeBuffer.put("9");
        service.submit(() ->  threadSafeBuffer.put("HANG"));

        Thread.sleep(2000);

        System.out.println(threadSafeBuffer.get());

        service.shutdown();
    }

}