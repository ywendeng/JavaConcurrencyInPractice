package com.ywdeng.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * TaskExecutionWebServer
 * <p/>
 * Web server using a thread pool
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 * 为web应用使用Executor框架中的Executors创建一个固定大小的线程池----避免了
 * 不断创建线程和销毁线程的开销，同时，使用Executor将任务的提交和任务的执行解耦开来
 *
 *
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 100;
    
    private static final Executor exec
            = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
            };
            exec.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
