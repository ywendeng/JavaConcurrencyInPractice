package com.ywdeng.objectshare;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 * 在没有使用同步的情况下，编译器和处理器以及在运行时都可能对操作的执行顺序进行一些意向不到的
 * 调整----重排序。在缺乏足够同步的多线性程序中，要想对内存操作的执行进行判断，几乎无法得到正确
 * 的结论
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        /**
         * 在没有使用同步的情况下是无法保证主线程对number和ready变量的修改
         * 对读线程是可见的，同时，编译器和处理器以及运行时会对操作进行重排
         */
        
        number = 42;
        ready = true;
    }
}
