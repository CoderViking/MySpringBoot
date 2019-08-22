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
    @Test
    public void testNum(){
        //比如46912 取45000 ；  1071 取1000 ；617 取 500； 56取50 ； 8 取5
        System.out.println("46912->"+getRound(46912));
        System.out.println("215876->"+getRound(215876));
        System.out.println("3490->"+getRound(3490));
        System.out.println("3501->"+getRound(3501));
        System.out.println("1071->"+getRound(1071));
        System.out.println("617->"+getRound(617));
        System.out.println("56->"+getRound(56));
        System.out.println("8->"+getRound(8));
    }

    private int getRound(int num) {
        if (num >= 100000) {
            return (num / 50000) % 2 != 0 ? (num / 100000) * 100000 + 50000 : (num / 100000) * 100000;
        } else if (num >= 10000) {
            return (num / 5000) % 2 != 0 ? (num / 10000) * 10000 + 5000 : (num / 10000) * 10000;
        } else if (num >= 1000) {
            return (num / 500) % 2 != 0 ? (num / 1000) * 1000 + 500 : (num / 1000) * 1000;
        } else if (num >= 100) {
            return (num / 50) % 2 != 0 ? (num / 100) * 100 + 50 : (num / 100) * 100;
        } else if (num >= 10) {
            return (num / 10) * 10;
        } else if (num >= 5) {
            return 5;
        }
        return 0;
    }
}
