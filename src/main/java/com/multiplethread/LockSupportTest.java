package com.multiplethread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void test() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {            @Override
        public void run() {

            System.out.println("child thread begin park!");                // 调用park方法，挂起自己
            LockSupport.park();

            System.out.println("child thread unpark!");

        }
        });        //启动子线程
        thread.start();        //主线程休眠1S
        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");        //调用unpark让thread线程持有许可证，然后park方法会返回
        LockSupport.unpark(thread);
    }
}
