package com.viking.MySpringBoot.test;

import org.junit.Test;

/**
 * Created by yanshuai on 2019/5/6
 */
public class MyTest {
    public static void main(String[] args) {
        System.out.println(1+'1'+1);
    }

    @Test
    public void test(){
        System.out.println(89>>1);
        System.out.println(89>>-100);
        System.out.println(-89/2);
    }


    @Test
    public void initTest(){
        Thread t = new Thread(()-> System.out.println("init:"+tt.init));
        t.start();
    }

    private static class tt{

        private static boolean init = false;
        // 线程阻塞代码
        static {
            Thread t = new Thread(()->init = true);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw  new AssertionError(e);
            }
        }
    }
}
