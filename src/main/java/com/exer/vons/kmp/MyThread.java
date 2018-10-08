package com.exer.vons.kmp;

import java.util.concurrent.*;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 14:25
 */
public class MyThread {
    /**
     * 互斥信号量
     */
    private static final Semaphore SM=new Semaphore(1);
    public static void main(String[] args) {
        ExecutorService pool=Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            final int index=i;
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        SM.acquire();
//                        System.out.println(String.format("[Thread-%s]任务id---%s",Thread.currentThread().getId(),index));
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }finally {
//                        SM.release();
//                    }
//                }
//            };
            pool.execute(()->{
                try {
                    SM.acquire();
                    System.out.println(String.format("[Thread-%s]任务id---%s",Thread.currentThread().getId(),index));
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    SM.release();
                }
            });
        }
        pool.shutdown();
    }
}
